package br.com.eponciano.ms.booking.mvc.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eponciano.ms.booking.mvc.model.User;
import br.com.eponciano.ms.booking.mvc.model.dto.ResetPasswordDTO;
import br.com.eponciano.ms.booking.mvc.model.dto.UserDTO;
import br.com.eponciano.ms.booking.mvc.model.dto.UserRegistrationDTO;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserFeignClient {

    @GetMapping("/users")
    List<User> getAllUsers();

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id);

    @PostMapping("/user")
    User createUser(@RequestBody User user);

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id);

    // Outros métodos aqui conforme necessário

    @PostMapping("/user/regitration")
    public User saveUser(UserRegistrationDTO registrationDTO) ;

    @GetMapping("/user/findUserByUsername/{username}")
    public User findUserByUsername(@PathVariable String username);

    @GetMapping("/user/findUserDTOByUsername/{username}")
    public UserDTO findUserDTOByUsername(@PathVariable String username);

    @GetMapping("/user/findUfindUserByIdserByUsername/{id}")
    public UserDTO findUserById(Long id);

    @GetMapping("/users")
    public List<UserDTO> findAllUsers() ;

    @PutMapping("/user/updateUser")
    public void updateUser(UserDTO userDTO);

    @PutMapping("/user/updateUserLogged")
    public void updateLoggedInUser(UserDTO userDTO);


    @PutMapping("/user/resetPassword")
    public User resetPassword(ResetPasswordDTO resetPasswordDTO);
}

