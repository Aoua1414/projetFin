package com.aoua.medoc.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.aoua.medoc.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aoua.medoc.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);


//  Role RecupererRoleUser(User user);

  //List<User> findByRole(Role role);
}
