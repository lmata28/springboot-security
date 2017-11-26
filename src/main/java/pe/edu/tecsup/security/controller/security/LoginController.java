package pe.edu.tecsup.security.controller.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value = {"login", "/"})
    public String index(@RequestParam(required = false) String error, Model model) {

        SecurityContext cntx = SecurityContextHolder.getContext();
        Authentication auth = cntx.getAuthentication();
//si el usuario esta autenticado
        if (auth instanceof UsernamePasswordAuthenticationToken) {
            return "redirect:/index";
        }
        
        if (error != null) {
            model.addAttribute("loginError", true);
        }

        return "security/login";
    }

}
