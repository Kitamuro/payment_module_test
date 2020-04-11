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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApacheHttpClientPost {

    public ResponseDTO responseDTO;
    private List<NameValuePair> params = new ArrayList<> ( );
    private String bankUrl = "https://test-ecom.atfbank.kz:5443/cgi-bin/cgi_link";

    public ApacheHttpClientPost(String card, String exp, String expYear, String cvc2, String amount, String order) {
        String htmlString = BankCommunicator ( card, exp, expYear, cvc2, amount, order );
        responseDTO = parseResponse ( htmlString );
    }

    String BankCommunicator(String card, String exp, String expYear, String cvc2, String amount, String order) {
        final CloseableHttpClient httpclient = HttpClients.createDefault ( );
        final HttpPost httpPost = new HttpPost ( bankUrl );
        params = addParam ( card, exp, expYear, cvc2, amount, order );
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

    ResponseDTO parseResponse(String htmlString){
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
            return buildResponseDTOSuccessful (trAm, trCcy, transRef, intTrRef, aprCode, rcCode);
        }else{
            return buildResponseDTOFail ( rcCode );
        }
    }

    private ResponseDTO buildResponseDTOFail(String rcCode) {
        return responseDTO = ResponseDTO.builder()
                .rcCode ( rcCode )
                .build();
    }

    private ResponseDTO buildResponseDTOSuccessful(String trAm, String trCcy, String transRef, String intTrRef, String aprCode, String rcCode) {
            return responseDTO = ResponseDTO.builder ( )
                    .transactionAmount ( Double.parseDouble ( trAm )/100 )
                    .transactionCcy ( trCcy )
                    .transactionReference ( transRef )
                    .internalTransReference ( intTrRef )
                    .approvalCode ( aprCode )
                    .rcCode ( rcCode )
                    .build ( );
    }

    private List<NameValuePair> addParam(String card, String exp, String expYear, String cvc2, String amount, String order) {
        params.add ( new BasicNameValuePair ( "CARD", card ) );
        params.add ( new BasicNameValuePair ( "EXP", exp ) );
        params.add ( new BasicNameValuePair ( "EXP_YEAR", expYear ) );
        params.add ( new BasicNameValuePair ( "CVC2", cvc2 ) );
        params.add ( new BasicNameValuePair ( "CVC2_RC", "1" ) );
        params.add ( new BasicNameValuePair ( "AMOUNT", amount ) );
        params.add ( new BasicNameValuePair ( "CURRENCY", "840" ) );
        params.add ( new BasicNameValuePair ( "DESC", "Merchant_test" ) );
        params.add ( new BasicNameValuePair ( "MERCHANT", "ECOMM001" ) );
        params.add ( new BasicNameValuePair ( "TERMINAL", "ECOMM001" ) );
        params.add ( new BasicNameValuePair ( "ORDER", order ) );
        params.add ( new BasicNameValuePair ( "TRTYPE", "1" ) );
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
