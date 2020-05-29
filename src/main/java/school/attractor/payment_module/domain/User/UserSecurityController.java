package school.attractor.payment_module.domain.User;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserSecurityController {

    private final UserSecurityService userSecurityService;

    @GetMapping("/register")
    public  String registerPage(Model model){
        if(!model.containsAttribute ( "user" )){
            model.addAttribute ( "user", new UserRegisterForm () );
        }
        return "auth/register";
    }

    @PostMapping("/register")
    public String registration(@Valid UserRegisterForm userRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute ( "user", userRegisterForm );
        if(bindingResult.hasFieldErrors (  )){
            redirectAttributes.addFlashAttribute ( "errors", bindingResult.getFieldErrors ()  );
            return "redirect:/user/register";
        }
        try {
            userSecurityService.register(userRegisterForm);
        } catch (UserAlreadyRegisteredException uarEx) {
            redirectAttributes.addFlashAttribute ("message", "User already exists.");
            return "redirect:/user/register";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model){
        model.addAttribute ( "error", error );
        return "auth/login";
    }
}

