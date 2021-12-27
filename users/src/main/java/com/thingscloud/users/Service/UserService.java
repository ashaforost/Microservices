package com.thingscloud.users.Service;



import com.thingscloud.users.repo.model.User;
import com.thingscloud.users.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

     private final UserRepository userRepository;

    private final RestTemplate restTemplate;


    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(UUID id){
        return userRepository.getUserById(id);
    }

    public List<User> getUserByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findAllByFirstNameAndLastName(firstName,lastName);

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
        if (user.getRole().isEmpty()) {
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
               user_role);
     return userRepository.save(createdUser);


    }


    public void deleteUserById(UUID id){
        userRepository.deleteById(id);
    }


}
