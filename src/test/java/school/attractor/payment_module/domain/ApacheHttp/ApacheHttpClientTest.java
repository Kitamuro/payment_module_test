package school.attractor.payment_module.domain.ApacheHttp;



import org.junit.Assert;
import org.junit.Test;
import school.attractor.payment_module.domain.transaction.TransactionStatus;


public class ApacheHttpClientTest {


    @Test
    public void parseStringDataFailed(){
          String htmlString = "<html>\n"+
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
        SendRequest sendRequest = new SendRequest ();
        sendRequest.parseResponse(htmlString);
        ResponseDTO responseDTO = new ResponseDTO ("", "", TransactionStatus.REFUSED,htmlString);
        Assert.assertEquals (sendRequest.getResponseDTO (),responseDTO );

    }

    @Test
    public void parseStringDataSuccessful(){
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
        SendRequest sendRequest = new SendRequest ();
        sendRequest.parseResponse(htmlString);
        ResponseDTO responseDTO = new ResponseDTO ("010290001932", "EEDCD934745037A3", TransactionStatus.APPROVED,htmlString);
        Assert.assertEquals (sendRequest.getResponseDTO (),responseDTO );

    }


}
