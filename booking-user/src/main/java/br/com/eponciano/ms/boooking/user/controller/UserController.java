package br.com.eponciano.ms.boooking.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eponciano.ms.boooking.user.model.User;
import br.com.eponciano.ms.boooking.user.model.dto.ResetPasswordDTO;
import br.com.eponciano.ms.boooking.user.model.dto.UserDTO;
import br.com.eponciano.ms.boooking.user.model.dto.UserRegistrationDTO;
import br.com.eponciano.ms.boooking.user.service.UserService;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<UserDTO> findAllUsers() {
		return userService.findAllUsers();
    }
    
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/findUfindUserByIdserByUsername/{id}")
    public UserDTO findUserById(@PathVariable Long id) {
    	return userService.findUserById(id);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
    
    @GetMapping(value = "/user/findUserByUsername/{username}", produces="application/json")
    public ResponseEntity<?>  findUserByUsername(@PathVariable String username) {
    	return ResponseEntity.ok(userService.findUserByUsername(username));
    }
    
    @GetMapping("/user/findUserDTOByUsername/{username}")
    public UserDTO findUserDTOByUsername(@PathVariable String username) {
    	return userService.findUserDTOByUsername(username);
    }
    
    
    

    @PostMapping("/user/")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/user/regitration")
    public User saveUser(UserRegistrationDTO registrationDTO) {
    	return userService.saveUser(registrationDTO);
    }
    
    @PutMapping("/user/updateUser")
    public void updateUser(UserDTO userDTO) {
    	userService.updateUser(userDTO);
    }
    
    @PutMapping("/user/updateUserLogged")
    public void updateLoggedInUser(UserDTO userDTO) {
    	userService.updateLoggedInUser(userDTO);
    }
    
    @PutMapping("/user/resetPassword")
    public User resetPassword(ResetPasswordDTO resetPasswordDTO) {
    	return userService.resetPassword(resetPasswordDTO);
    }
}
