package school.attractor.payment_module.domain.ApacheHttp;


import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;


public class ApacheHttpClientTest {

    private ApacheHttpClientPost apacheHttpClientPostSpy = Mockito.spy(ApacheHttpClientPost.class);


    @Test
    public void test_SendRequest_Fail() throws IOException {
        try {
            apacheHttpClientPostSpy.sendRequest ( "1111111111111111", "12", "27", "212", "1", "123987" );
        }catch (Exception ex){
            Assert.fail ();
        }
        Assert.assertNotEquals ( "00",apacheHttpClientPostSpy.getResponseCode () );
    }

    @Test
    public void test_SendRequest_Success() throws IOException {
//        нужно указать валидный номер карты
        try {
            apacheHttpClientPostSpy.sendRequest ( "5449...", "12", "27", "212", "1", "456987" );
        }catch (Exception ex){
            Assert.fail ();
        }
        Assert.assertEquals ( "00",apacheHttpClientPostSpy.getResponseDTO ().getRcCode () );
    }

    @Test
    public void parse_StringDataReturned(){
//        var apacheHttpClientPost = new ApacheHttpClientPost ( );
        String[] substring= {"RES=1", "ORDER=456789", "AMOUNT=200", "CUR=KZT"};
        Assert.assertEquals ( "1", apacheHttpClientPostSpy.getStringData ( substring,0,  "RES"));
        Assert.assertEquals ( "456789", apacheHttpClientPostSpy.getStringData ( substring,1,  "ORDER"));
        Assert.assertEquals ( "200", apacheHttpClientPostSpy.getStringData ( substring,2,  "AMOUNT"));
        Assert.assertEquals ( "KZT", apacheHttpClientPostSpy.getStringData ( substring,3,  "CUR"));
    }

}
