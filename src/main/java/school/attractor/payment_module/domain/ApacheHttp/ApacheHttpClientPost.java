package school.attractor.payment_module.domain.ApacheHttp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
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

    public List<String> secureData = new ArrayList<> (  );
    public String responseCode="";
    public String errorMessage ="";
    public ResponseDTO responseDTO;


    public void sendRequest(String card, String exp, String expYear, String cvc2) throws IOException {

        try {
            final CloseableHttpClient httpclient = HttpClients.createDefault ( );
            final HttpPost httpPost = new HttpPost ( "https://test-ecom.atfbank.kz:5443/cgi-bin/cgi_link" );
            final List<NameValuePair> params = new ArrayList<> ( );
            params.add ( new BasicNameValuePair ( "CARD", card ) );
            params.add ( new BasicNameValuePair ( "EXP", exp ) );
            params.add ( new BasicNameValuePair ( "EXP_YEAR", expYear ) );
            params.add ( new BasicNameValuePair ( "CVC2", cvc2 ) );
            params.add ( new BasicNameValuePair ( "CVC2_RC", "1" ) );
            params.add ( new BasicNameValuePair ( "AMOUNT", "3" ) );
            params.add ( new BasicNameValuePair ( "CURRENCY", "840" ) );
            params.add ( new BasicNameValuePair ( "DESC", "Merchant_test" ) );
            params.add ( new BasicNameValuePair ( "MERCHANT", "ECOMM001" ) );
            params.add ( new BasicNameValuePair ( "TERMINAL", "ECOMM001" ) );
            params.add ( new BasicNameValuePair ( "ORDER", "123456" ) );
            params.add ( new BasicNameValuePair ( "TRTYPE", "1" ) );
            params.add ( new BasicNameValuePair ( "EMAIL", "test@atfbank.kz" ) );
            params.add ( new BasicNameValuePair ( "MERCH_GMT", "+6" ) );
            params.add ( new BasicNameValuePair ( "MERCH_URL", "http://test.atfbank.kz" ) );
            params.add ( new BasicNameValuePair ( "COUNTRY", "KZ" ) );
            try {
                httpPost.setEntity ( new UrlEncodedFormEntity ( params ) );
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace ( );
            }

            try (CloseableHttpResponse response = httpclient.execute ( httpPost )) {
                final HttpEntity entity = response.getEntity ( );
                String htmlString = EntityUtils.toString ( entity );
                Document html = Jsoup.parse ( htmlString );
                System.out.println ( "html = " + html );
                String[] substring = htmlString.split ( "(\\bwindow.location.href\\b)" )[1].split ( "\\b&\\b" );
                String rcCode = substring[3].split ( "\\bRC\\b" )[1].replaceAll ( "[^\\w \\xC0-\\xFF]","" );
                if(rcCode.equals ( "00" )) {
                    String trAm = substring[5].split ( "\\bAMOUNT\\b" )[1].replaceAll ( "[^\\w \\xC0-\\xFF]", "" );
                    String trCcy = substring[6].split ( "\\bCUR\\b" )[1].replaceAll ( "[^\\w \\xC0-\\xFF]", "" );
                    String transRef = substring[9].split ( "\\bREF\\b" )[1].replaceAll ( "[^\\w \\xC0-\\xFF]", "" );
                    String intTrRef = substring[10].split ( "\\bINT_REF\\b" )[1].replaceAll ( "[^\\w \\xC0-\\xFF]", "" );
                    String apprCode = substring[1].split ( "\\bRES\\b" )[1].replaceAll ( "[^\\w \\xC0-\\xFF]", "" );
                    System.out.println ( "whole message from the Bank = " + htmlString );
                    System.out.println ( "Transaction amount = " + trAm );
                    System.out.println ( "Transaction currency = " + trCcy );
                    System.out.println ( "Transaction reference number = " + transRef );
                    System.out.println ( "Internal Transaction number = " + intTrRef );
                    System.out.println ( "Bank's approval code = " + apprCode );
                    System.out.println ( "RC CODE = " + rcCode );
                    responseDTO = ResponseDTO.builder()
                            .transactionAmount ( Double.parseDouble (trAm)/100 )
                            .transactionCcy ( trCcy )
                            .transactionReference ( transRef )
                            .internalTransReference ( intTrRef )
                            .approvalCode ( apprCode )
                            .rcCode ( rcCode )
                            .build();
                    System.out.println ("responseDTO: " + responseDTO );
                }else{
                    System.out.println ("response code " + rcCode );
                }
                responseCode = rcCode;

            }
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

}
