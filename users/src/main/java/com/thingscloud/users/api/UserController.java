package com.thingscloud.users.api;



import com.thingscloud.users.Service.UserService;
import com.thingscloud.users.repo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping (value = "/{firstName}/{lastName}")
    public ResponseEntity<List<User>> getUserByNameOrLastName(@PathVariable String firstName, @PathVariable String lastName){
        try {
            return ResponseEntity.ok(userService.getUserByFirstNameAndLastName(firstName,lastName ));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<User> getUserEmail(@PathVariable String email){
        try {
            return ResponseEntity.ok(userService.getUserByEmail(email));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/device/{user_id}")
    public ResponseEntity getUserDevices(@PathVariable String user_id){
            return ResponseEntity.ok(userService.getUserDevices(UUID.fromString(user_id)));
    }


    @GetMapping (value = "/id=/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        try {
            return ResponseEntity.ok(userService.getUserById(UUID.fromString(id)));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping()
    @ResponseBody
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        try {
            return  ResponseEntity.ok(userService.save(user));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable String id){
        try {
            userService.deleteUserById(UUID.fromString(id));
            return "User Deleted";
        } catch (Exception e){
            return "User not found!";
        }

    }
}
