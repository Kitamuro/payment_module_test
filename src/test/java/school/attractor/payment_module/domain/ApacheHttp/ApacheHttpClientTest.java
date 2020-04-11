package school.attractor.payment_module.domain.ApacheHttp;



import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;


public class ApacheHttpClientTest {


    @Spy
    ApacheHttpClientPost apacheHttpClientPostSpy = Mockito.spy ( ApacheHttpClientPost.class );

    @Mock
    ApacheHttpClientPost apacheHttpClientPostMock = Mockito.mock ( ApacheHttpClientPost.class );


    @Test
    public void test_BankCommunicator(){
        String htmlString = "<HTML>\n" +
                "<HEAD><TITLE>Transaction approved</TITLE>\n" +
                "<SCRIPT>\n" +
                "function OnLoadEvent() {\n" +
                "\tvar host = \"https://crystalspring.top/ecom/api\";\n" +
                "\twindow.location.href = host + \"?CB=SR&RES=0&ORDER=995292&RC=00&RDESC=Approved&AMOUNT=10000.00&CUR=USD&MERCH=&MERCH_URL=http://test.atfbank.kz&DESK=Merchant_test&REF=010290001932&INT_REF=EEDCD934745037A3&AC=HOSTOK\";\n" +
                "}\n" +
                "</SCRIPT>\n" +
                "</HEAD>\n" +
                "<BODY ONLOAD=\"javascript:OnLoadEvent();\">\n" +
                "<BR><BR><B>Processing...</B>\n" +
                "</BODY>\n" +
                "</HTML>";
        Mockito.when ( apacheHttpClientPostMock.BankCommunicator ( Mockito.anyString ( ), Mockito.anyString ( ), Mockito.anyString ( ), Mockito.anyString ( ), Mockito.anyString ( ), Mockito.anyString ( ) ) ).thenReturn ( htmlString );
    }

    @Test
    public void parse_StringDataReturned(){
          String htmlString = "html = <html>\n"+
            " <head>\n"+
            "  <title>Transaction declined</title> \n"+
            "  <script>\n"+
            "function OnLoadEvent() {\n"+
            "\tvar host = \"https://crystalspring.top/ecom/api\";\n"+
            "\twindow.location.href = host + \"?CB=SR&RES=3&ORDER=492582&RC=-9&RDESC=Error in card expiration date field&AMOUNT=&CUR=&MERCH=&MERCH_URL=http://test.atfbank.kz&DESK=Merchant_test&ECODE=Error in card expiration date field&EDESC=\"\n"+
            "}\n"+
            "</script> \n"+
            " </head> \n"+
            " <body onload=\"javascript:OnLoadEvent();\"> \n"+
            "  <br>\n"+
            "  <br>\n"+
            "  <b>Processing...</b>   \n"+
            " </body>\n"+
            "</html>";

        ResponseDTO responseDTO = new ResponseDTO ( );
        responseDTO.setRcCode ( "9" );
        Assert.assertEquals ( responseDTO, apacheHttpClientPostSpy.parseResponse ( htmlString ));

    }

    @Test
    public void parse_StringDataSuccessful(){
        String html = "<HTML>\n" +
                "<HEAD><TITLE>Transaction approved</TITLE>\n" +
                "<SCRIPT>\n" +
                "function OnLoadEvent() {\n" +
                "\tvar host = \"https://crystalspring.top/ecom/api\";\n" +
                "\twindow.location.href = host + \"?CB=SR&RES=0&ORDER=995292&RC=00&RDESC=Approved&AMOUNT=10000.00&CUR=USD&MERCH=&MERCH_URL=http://test.atfbank.kz&DESK=Merchant_test&REF=010290001932&INT_REF=EEDCD934745037A3&AC=HOSTOK\";\n" +
                "}\n" +
                "</SCRIPT>\n" +
                "</HEAD>\n" +
                "<BODY ONLOAD=\"javascript:OnLoadEvent();\">\n" +
                "<BR><BR><B>Processing...</B>\n" +
                "</BODY>\n" +
                "</HTML>";
        ResponseDTO responseDTO = new ResponseDTO ( );
        responseDTO.setTransactionAmount ( 10000.0 );
        responseDTO.setTransactionCcy ( "USD" );
        responseDTO.setTransactionReference ( "010290001932" );
        responseDTO.setInternalTransReference ( "EEDCD934745037A3" );
        responseDTO.setApprovalCode ( "0" );
        responseDTO.setRcCode ( "00" );
        Assert.assertEquals (responseDTO , apacheHttpClientPostSpy.parseResponse ( html ) );
    }


}
