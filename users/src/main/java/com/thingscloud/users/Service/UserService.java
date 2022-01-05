package com.thingscloud.users.Service;

import com.thingscloud.users.repo.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> getUsers();
    User getUserById(UUID id)  throws IllegalArgumentException;
    User getUserByEmail(String email)  throws IllegalArgumentException;
    List getUserDevices(UUID id)  throws IllegalArgumentException;
    User save( User user);
    void deleteUserById(UUID id);
}
