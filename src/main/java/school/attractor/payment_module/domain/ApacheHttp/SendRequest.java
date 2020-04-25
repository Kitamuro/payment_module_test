package school.attractor.payment_module.domain.ApacheHttp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import school.attractor.payment_module.domain.transaction.Transaction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendRequest {

    private Response response;
    private List<NameValuePair> params = new ArrayList<> ( );
    private String bankUrl = "https://test-ecom.atfbank.kz:5443/cgi-bin/cgi_link";

    public SendRequest(Transaction transaction, String transactionAmount, String trType) {
        String htmlString = BankCommunicator ( transaction, transactionAmount, trType);
        response = parseResponse ( htmlString );
    }

    String BankCommunicator(Transaction transaction, String transactionAmount, String trType) {
        final CloseableHttpClient httpclient = HttpClients.createDefault ( );
        final HttpPost httpPost = new HttpPost ( bankUrl );
        if(trType.equals ( "24" ) || trType.equals ( "21" )){
            params = addParam ( transaction, transactionAmount, trType );
        } else {
            params = addParamForAuth ( transaction, trType );
        }
        try {
            httpPost.setEntity ( new UrlEncodedFormEntity ( params ) );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ( );
        }
        try (CloseableHttpResponse response = httpclient.execute ( httpPost )) {
            final HttpEntity entity = response.getEntity ( );
            return EntityUtils.toString ( entity );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return "";
    }


    Response parseResponse(String htmlString){
             Document html = Jsoup.parse ( htmlString );
             System.out.println ( "html = " + html );
             String[] substring = htmlString.split ( "(\\bwindow.location.href\\b)" )[1].split ( "\\b&\\b" );
             String rcCode = substring[3].split ( "\\bRC\\b" )[1].replaceAll ( "[^\\w \\xC0-\\xFF]","" );
        if(rcCode.equals ( "00" ) && html.title ().equals ( "Transaction approved" )) {
            String trAm = getStringData( substring, 5, "AMOUNT" );
            String trCcy = getStringData( substring, 6, "CUR" );
            String transRef = getStringData( substring, 9, "REF" );
            String intTrRef = getStringData( substring, 10, "INT_REF" );
            String aprCode = getStringData( substring, 1, "RES" );
            consolePrint(htmlString, trAm, trCcy, transRef, intTrRef, aprCode,rcCode);
            return buildResponseSuccessful (trAm, trCcy, transRef, intTrRef, aprCode, rcCode);
        }else{
            return buildResponseFail ( rcCode );
        }
    }

    private Response buildResponseFail(String rcCode) {
        return response = Response.builder()
                .rcCode ( rcCode )
                .build();
    }

    private Response buildResponseSuccessful(String trAm, String trCcy, String transRef, String intTrRef, String aprCode, String rcCode) {
            return response = Response.builder ( )
                    .transactionAmount ( Double.parseDouble ( trAm )/100 )
                    .transactionCcy ( trCcy )
                    .RetrievalReferenceNumber ( transRef )
                    .internalReferenceNumber ( intTrRef )
                    .approvalCode ( aprCode )
                    .rcCode ( rcCode )
                    .build ( );
    }

    private List<NameValuePair> addParam(Transaction transaction, String transactionAmount, String trType) {
        Response transactionResponse = transaction.getResponses ( ).get ( 0 );
        params.add(new BasicNameValuePair ( "RRN", transactionResponse.getRetrievalReferenceNumber () ));
        params.add(new BasicNameValuePair (  "INT_REF", transactionResponse.getInternalReferenceNumber ()));
        params.add ( new BasicNameValuePair ( "ORDER",  transaction.getOrderId () ));
        params.add ( new BasicNameValuePair ( "AMOUNT", transactionAmount ) );
        params.add ( new BasicNameValuePair ( "CURRENCY", "840" ) );
        params.add ( new BasicNameValuePair ( "TERMINAL", "ECOMM001" ) );
        params.add ( new BasicNameValuePair ( "TRTYPE", trType ) );
        return params;
    }

    private List<NameValuePair> addParamForAuth(Transaction transaction, String trType) {
        params.add ( new BasicNameValuePair ( "CARD", transaction.getCARD () ) );
        params.add ( new BasicNameValuePair ( "EXP", transaction.getEXP () ) );
        params.add ( new BasicNameValuePair ( "EXP_YEAR", transaction.getEXP_YEAR () ) );
        params.add ( new BasicNameValuePair ( "CVC2", transaction.getCVC2 () ) );
        params.add ( new BasicNameValuePair ( "CVC2_RC", "1" ) );
        params.add ( new BasicNameValuePair ( "AMOUNT", transaction.getAmount () ) );
        params.add ( new BasicNameValuePair ( "CURRENCY", "840" ) );
        params.add ( new BasicNameValuePair ( "DESC", "Merchant_test" ) );
        params.add ( new BasicNameValuePair ( "MERCHANT", "ECOMM001" ) );
        params.add ( new BasicNameValuePair ( "TERMINAL", "ECOMM001" ) );
        params.add ( new BasicNameValuePair ( "ORDER", transaction.getOrderId () ) );
        params.add ( new BasicNameValuePair ( "TRTYPE", trType ) );
        params.add ( new BasicNameValuePair ( "EMAIL", "test@atfbank.kz" ) );
        params.add ( new BasicNameValuePair ( "MERCH_GMT", "+6" ) );
        params.add ( new BasicNameValuePair ( "MERCH_URL", "http://test.atfbank.kz" ) );
        params.add ( new BasicNameValuePair ( "COUNTRY", "KZ" ) );
        return params;
    }

    private String getStringData(String[] substring, int index1, String splitWord) {
        String data = substring[index1].split ( "\\b"+splitWord+"\\b" )[1].replaceAll ( "[^\\w \\xC0-\\xFF]", "" );
        System.out.println ( "data = " + data );
        return data;
    }
    private void consolePrint(String htmlString, String trAm, String trCcy, String transRef, String intTrRef, String apprCode, String rcCode) {
        System.out.println ( "whole message from the Bank = " + htmlString );
        System.out.println ( "Transaction amount = " + trAm );
        System.out.println ( "Transaction currency = " + trCcy );
        System.out.println ( "Transaction reference number = " + transRef );
        System.out.println ( "Internal Transaction number = " + intTrRef );
        System.out.println ( "Bank's approval code = " + apprCode );
        System.out.println ( "RC CODE = " + rcCode );
    }

}
