package sec.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;


@Service
public class SignupService {
    
     @Autowired
    private SignupRepository signupRepository;
   
 
    public Iterable<Signup> list() {
        return signupRepository.findAll();
    }
 
    @Transactional
    public void add(String name) {
        Signup signup = new Signup();
        signup.setName(name);
 
        signupRepository.save(signup);
         
    }
 
 
    
 
    public Signup findById(Long actorId) {
        return signupRepository.findOne(actorId);
    }
    
}
