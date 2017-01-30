package sec.project.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Signup;
import sec.project.repository.AccountRepository;
import sec.project.repository.SignupRepository;
import sec.project.service.SignupService;



@Controller
public class SignupController {
    
   

    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    private SignupService signupService;
  
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("form", signupService.list());
        return "form";
    }
    
    
    @RequestMapping("*")
    public String defaultMapping() {
        return "form";
    }
    
@Autowired
    private HttpSession session;
   // @RequestMapping(value = "/form", method = RequestMethod.GET)
   // public String loadForm() {
    //    return "form";
    //}

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        signupRepository.save(new Signup(name, address));
       // return "done";
        session.setAttribute("name", name);
        session.setAttribute("address", address);
       return "redirect:/form";
      //return "done";
    }


    
}
