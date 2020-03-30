package school.attractor.payment_module;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.attractor.payment_module.domain.ApacheHttp.ResponseDTO;
import school.attractor.payment_module.domain.transaction.TransactionDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping
@AllArgsConstructor
public class FrontEndControllerTemp {

    private final FrontEndServiceTemp FrontEndServiceTemp;

    @GetMapping("/status")
    public String status(Model model){
        String responseCode = FrontEndServiceTemp.apacheHttpClientPostTemp.getResponseCode ( );
        String errorMessage = FrontEndServiceTemp.apacheHttpClientPostTemp.getErrorMessage ();
        ResponseDTO responseDTO = FrontEndServiceTemp.apacheHttpClientPostTemp.getResponseDTO ();
        model.addAttribute ( "rcCode", responseCode );
        if(responseCode.equals ( "00" )) {
            model.addAttribute ( "url", "https://test-ecom.atfbank.kz:5443/cgi-bin/cgi_link" );
            model.addAttribute ( "REF", responseDTO.getTransactionReference () );
            model.addAttribute ( "ITR", responseDTO.getInternalTransReference () );
            model.addAttribute ( "apprCode", responseDTO.getApprovalCode () );
            return "response";
        }else{
            model.addAttribute ( "error", errorMessage );
            return "status";
        }
    }

    @PostMapping
    public String status(@RequestParam String termUrl, @RequestParam String paRes, @RequestParam String md, Model model){
        return "redirect:/status";
    }

    @PostMapping("/3DsecureMVCTemp")
    public String redirectedPostToPost(@RequestBody TransactionDTO transactionDTO, RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {
        FrontEndServiceTemp.makeHttpClient ( transactionDTO );
        return  "redirect:/status";
    }
}
