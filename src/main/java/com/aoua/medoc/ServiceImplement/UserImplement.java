package com.aoua.medoc.ServiceImplement;


import com.aoua.medoc.Service.UserService;
import com.aoua.medoc.models.User;
import com.aoua.medoc.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class UserImplement implements UserService {

    private final UserRepository userRepository;

    @Override
    public User ajouter(User user) {
        return userRepository.save(user);
    }



    @Override
    public User modifier(Long id, User user) {
        return userRepository.findById(id)
                .map(user1 -> {
                    user1.setNumero(user.getNumero());
                    user1.setPassword(user.getPassword());
                    return userRepository.save(user1);
                }).orElseThrow(()->new RuntimeException("User introuvable") );
    }

    @Override
    public String supprimer(Long id) {
         userRepository.deleteById(id);
         return "supprime avec succes";
    }


    @Override
    public List<User> afficher() {
        return userRepository.findAll();
    }


}
