package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.UserService;
import com.aoua.medoc.models.User;
import org.aspectj.bridge.Message;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Ajout d'un user
   @PostMapping(value = "/user")
   @PostAuthorize("hasAuthority('USER')")
    public Object ajouter(User user){
        try{
            userService.ajouter(user);
            return "Ajouté avec succès";

        }catch (Exception e ){
            return "Cet user existe deja";
        }

    }
// Afficher la liste des users
    @GetMapping(value = "/listeUsers")
    @PostAuthorize("hasAuthority('ADMIN')")
    public List<User> afficher(){
        return userService.afficher();
    }
}
