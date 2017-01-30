
package sec.project.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class ParticipantsController {
    @Autowired
    private HttpSession session;

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping(value = "/participants", method = RequestMethod.GET)
    public String loadEventSession(Model model, @RequestParam(required = false) boolean admin) {
        List<Signup> members = signupRepository.findAll();
        model.addAttribute("signups", members);
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("address", session.getAttribute("address"));
        if (admin == true) {
            model.addAttribute("admin", true);
        }
        return "participants";
    }

    //@RequestMapping(value = "/participants/login", method = RequestMethod.GET)
    @RequestMapping(value = "/participants/login")
    public String adminLogin(Model model, @RequestParam String username, @RequestParam String password) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (!(auth instanceof AnonymousAuthenticationToken)) {
      
            return "redirect:/participants?admin=true";
        }
        return "redirect:/participants?admin=false";
    }
  
        @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String loadEvent(Model model, @RequestParam(required = false) boolean admin) {
        List<Signup> members = signupRepository.findAll();
        model.addAttribute("signups", members);
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("address", session.getAttribute("address"));
        if (admin == true) {
            model.addAttribute("admin", true);
        }
        return "admin";
    }
        
       
  
}
