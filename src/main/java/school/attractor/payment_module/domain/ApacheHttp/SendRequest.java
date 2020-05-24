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
import school.attractor.payment_module.domain.transaction.TransactionStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
class SendRequest {

    private ResponseDTO responseDTO;
    private Request request;
    private List<NameValuePair> params = new ArrayList<> ( );
    private String bankUrl = "https://test-ecom.atfbank.kz:5443/cgi-bin/cgi_link";


    SendRequest(Transaction transaction, String trType) {
        String htmlString = BankCommunicator ( transaction, trType);
        parseResponse ( htmlString);
    }


    private String BankCommunicator(Transaction transaction, String trType) {
        final CloseableHttpClient httpclient = HttpClients.createDefault ( );
        final HttpPost httpPost = new HttpPost ( bankUrl );
        if(trType.equals ( "14" ) || trType.equals ( "21" )){
            params = addParam ( transaction, trType );
        } else {
            params = addParamForAuth ( transaction, trType );
        }
        try {
            httpPost.setEntity ( new UrlEncodedFormEntity ( params ) );
            UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity ( params );
            String htmlRequest = EntityUtils.toString ( requestEntity );
            request = Request.from ( htmlRequest,transaction );
        } catch (IOException e) {
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

    private void parseResponse(String htmlString){
             String responseHtml= htmlString.split ( "(\\bhost\\b)" )[2].split ( "</" )[0].substring ( 3 );
             Document html = Jsoup.parse ( htmlString );
        if(html.title ().equals ( "Transaction approved" )) {
            String transRef = htmlString.split ( "(\\bREF\\b)" )[1].split ( "\\bINT_REF\\b" )[0].replaceAll ( "[^\\w \\xC0-\\xFF]", "" );
            String intTrRef = htmlString.split ( "\\bINT_REF\\b" )[1].split ( "\\bAC\\b" )[0].replaceAll ( "[^\\w \\xC0-\\xFF]", "" );;
            consolePrint(htmlString, transRef, intTrRef);
            buildResponseDTO(responseHtml, transRef, intTrRef, TransactionStatus.APPROVED );
        }else{
            buildResponseDTO ( responseHtml, "", "", TransactionStatus.REFUSED );
        }
    }

    private void buildResponseDTO(String htmlString, String transRef, String intTrRef, TransactionStatus TransactionStatus) {
        responseDTO = ResponseDTO.builder ( ).
                RetrievalReferenceNumber(transRef).
                InternalReferenceNumber ( intTrRef ).
                responseHtml (htmlString  ).
                status ( TransactionStatus ).
                build ();
    }

    private List<NameValuePair> addParam(Transaction transaction, String trType) {
        params.add(new BasicNameValuePair ( "RRN", transaction.getOrder ().getRetrievalReferenceNumber () ));
        params.add(new BasicNameValuePair (  "INT_REF", transaction.getOrder ().getInternalReferenceNumber ()));
        params.add ( new BasicNameValuePair ( "ORDER",  String.valueOf(transaction.getOrder ().getOrderId () )));
        params.add ( new BasicNameValuePair ( "AMOUNT", String.valueOf (transaction.getAmount ())));
        params.add ( new BasicNameValuePair ( "CURRENCY", "840" ) );
        params.add ( new BasicNameValuePair ( "TERMINAL", "ECOMM001" ) );
        params.add ( new BasicNameValuePair ( "TRTYPE", trType ) );
        return params;
    }

    private List<NameValuePair> addParamForAuth(Transaction transaction, String trType) {
        params.add ( new BasicNameValuePair ( "CARD", String.valueOf(transaction.getOrder ().getCard() ) ));
        params.add ( new BasicNameValuePair ( "EXP", String.valueOf(transaction.getOrder ().getExp() ) ));
        params.add ( new BasicNameValuePair ( "EXP_YEAR", String.valueOf(transaction.getOrder ().getExp_year () ) ));
        params.add ( new BasicNameValuePair ( "CVC2", String.valueOf(transaction.getOrder ().getCvc2() ) ));
        params.add ( new BasicNameValuePair ( "CVC2_RC", "1" ) );
        params.add ( new BasicNameValuePair ( "AMOUNT", String.valueOf(transaction.getAmount () ) ));
        params.add ( new BasicNameValuePair ( "CURRENCY", "840" ) );
        params.add ( new BasicNameValuePair ( "DESC", "Merchant_test" ) );
        params.add ( new BasicNameValuePair ( "MERCHANT", "ECOMM001" ) );
        params.add ( new BasicNameValuePair ( "TERMINAL", "ECOMM001" ) );
        params.add ( new BasicNameValuePair ( "ORDER", String.valueOf(transaction.getOrder().getOrderId () ) ));
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
    private void consolePrint(String htmlString, String transRef, String intTrRef) {
        System.out.println ( "whole message from the Bank = " + htmlString );
        System.out.println ( "Transaction reference number = " + transRef );
        System.out.println ( "Internal Transaction number = " + intTrRef );
    }

}
