package com.thingscloud.users.Service.impl;



import com.thingscloud.users.Service.UserService;
import com.thingscloud.users.repo.model.User;
import com.thingscloud.users.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

     private final UserRepository userRepository;

    private final RestTemplate restTemplate;


    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(UUID id){
        return userRepository.getUserById(id);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List getUserDevices(UUID id){
       List devices =  restTemplate.getForObject(
                "http://devices:8092/device/v1/user/{userId}",
                List.class,
                id
        );

       return devices;
    }

    public User save( User user) {
        String user_role;
        int devLimit;
        if (user.getRole() == null) {
            user_role = "CUSTOMER";
            devLimit =5;
        }
       else  {
           user_role = user.getRole();
           devLimit =10;
        }

       User createdUser = new User(
               user.getFirstName(),
               user.getLastName(),
               user.getEmail(),
               System.currentTimeMillis(),
               user.getPassword(),
               user_role,
               devLimit);
     try {
         return userRepository.save(createdUser);
     } catch (Exception e){
         throw new IllegalArgumentException();
     }

    }


    public void deleteUserById(UUID id){
        userRepository.deleteById(id);
    }


}
