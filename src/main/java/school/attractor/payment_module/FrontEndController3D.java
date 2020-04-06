package school.attractor.payment_module;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.attractor.payment_module.domain.transaction.TransactionDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/3D") //temporarily past
@AllArgsConstructor
public class FrontEndController3D {

    private final FrontEndService3D FrontEndService;

    @GetMapping("/status")
    public String status(Model model){
        String responseCode = FrontEndService.apacheHttpClientPost3D.getResponseCode ( );
        String errorMessage = FrontEndService.apacheHttpClientPost3D.getErrorMessage ();
        if(responseCode.equals ( "00" )) {
            model.addAttribute ( "url", "https://test-ecom.atfbank.kz:5443/cgi-bin/cgi_link" );
            return "index";
        }else{
            model.addAttribute ( "rcCode", responseCode );
            model.addAttribute ( "error", errorMessage );
            return "status";
        }
    }

    @PostMapping
    public String status(@RequestParam String termUrl, @RequestParam String paRes, @RequestParam String md, Model model){
        return "redirect:/status";
    }

    @PostMapping("/3DsecureMVC")
    public String redirectedPostToPost(@RequestBody TransactionDTO transactionDTO, RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {
        FrontEndService.makeHttpClient ( transactionDTO );
        return  "redirect:/status";
    }
}
