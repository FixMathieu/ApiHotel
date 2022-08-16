package fr.fms.apihotel.web;

import fr.fms.apihotel.business.IBusinessImpl;
import fr.fms.apihotel.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private IBusinessImpl iBusiness;

    // test login provisoire pour creer user dans localstorage
    @GetMapping("/login/{mail}")
    public Users userLogin(@PathVariable("mail") String mail) throws Exception {
        return iBusiness.getUserByMail(mail).get();
    }
}
