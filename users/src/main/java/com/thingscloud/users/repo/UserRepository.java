package com.thingscloud.users.repo;

import com.thingscloud.users.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserById(UUID id);

    User getUserById(UUID id);

    List<User> findAllByFirstNameAndLastName(String firstName, String lastName);

    User findByEmail(String email);


}
