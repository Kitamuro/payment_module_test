package school.attractor.payment_module.domain.ApacheHttp;



import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import school.attractor.payment_module.domain.transaction.Transaction;


public class ApacheHttpClientTest {


    @Spy
    SendRequest sendRequestSpy = Mockito.spy ( SendRequest.class );

    @Mock
    SendRequest sendRequestMock = Mockito.mock ( SendRequest.class );


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
        Mockito.when ( sendRequestMock.BankCommunicator (  Mockito.spy ( Transaction.class ),Mockito.anyString (), Mockito.anyString ( ) ) ).thenReturn ( htmlString );
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

        Response response = new Response ( );
        response.setRcCode ( "9" );
        Assert.assertEquals ( response, sendRequestMock.parseResponse ( htmlString ));

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
        Response response = new Response ( );
        response.setTransactionAmount ( 10000.0 );
        response.setTransactionCcy ( "USD" );
        response.setRetrievalReferenceNumber ( "010290001932" );
        response.setInternalReferenceNumber ( "EEDCD934745037A3" );
        response.setApprovalCode ( "0" );
        response.setRcCode ( "00" );
        Assert.assertEquals (response , sendRequestMock.parseResponse ( html ) );
    }


}
