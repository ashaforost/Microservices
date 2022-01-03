package com.thingscloud.users.api;
import com.thingscloud.users.Service.impl.UserServiceImpl;
import com.thingscloud.users.repo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;


    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userServiceImpl.getUsers());
    }


    @GetMapping(value = "/{email}")
    public ResponseEntity<User> getUserEmail(@PathVariable String email){
        try {
            return ResponseEntity.ok(userServiceImpl.getUserByEmail(email));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/device/{user_id}")
    public ResponseEntity getUserDevices(@PathVariable String user_id){
            try{
                return ResponseEntity.ok(userServiceImpl.getUserDevices(UUID.fromString(user_id)));
            }catch (IllegalArgumentException e){
                return ResponseEntity.notFound().build();
            }
    }


    @GetMapping (value = "/id=/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        try {
            return ResponseEntity.ok(userServiceImpl.getUserById(UUID.fromString(id)));
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping()
    @ResponseBody
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        try{
            return  ResponseEntity.ok(userServiceImpl.save(user));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }



    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
            userServiceImpl.deleteUserById(UUID.fromString(id));
        return ResponseEntity.noContent().build();

    }
}
