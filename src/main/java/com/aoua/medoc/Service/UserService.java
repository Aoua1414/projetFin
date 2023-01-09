package com.aoua.medoc.Service;

import com.aoua.medoc.models.User;

import java.util.List;

public interface UserService {

    //Methode pour creer user
    User ajouter(User user);


    User modifier(Long id, User user);

    String supprimer(Long id);

    List<User> afficher();
}
