package br.com.eduardo.ponciano.travel.mvc.consumer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.mvc.client.UserFeignClient;
import br.com.eduardo.ponciano.travel.mvc.model.User;
import br.com.eduardo.ponciano.travel.mvc.model.dto.ResetPasswordDTO;
import br.com.eduardo.ponciano.travel.mvc.model.dto.UserDTO;
import br.com.eduardo.ponciano.travel.mvc.model.dto.UserRegistrationDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceConsumer {

    @Autowired
    private UserFeignClient userFeignClient;

    public List<User> getAllUsers() {
        return userFeignClient.getAllUsers();
    }

    public User getUserById(Long id) {
        return userFeignClient.getUserById(id);
    }

    public User createUser(User user) {
        return userFeignClient.createUser(user);
    }

    public void deleteUser(Long id) {
        userFeignClient.deleteUser(id);
    }

    // Outros métodos conforme necessário
    


    public User saveUser(UserRegistrationDTO registrationDTO) {
    	return userFeignClient.saveUser(registrationDTO);
    }

    public User findUserByUsername(String username) {
    	return userFeignClient.findUserByUsername(username);
    }

    public UserDTO findUserDTOByUsername(String username) {
    	return userFeignClient.findUserDTOByUsername(username);
    }

    public UserDTO findUserById(Long id) {
    	return userFeignClient.findUserById(id);
    }

    public List<UserDTO> findAllUsers() {
		return userFeignClient.findAllUsers();
    }

    public void updateUser(UserDTO userDTO) {
    	userFeignClient.updateUser(userDTO);
    }

    public void updateLoggedInUser(UserDTO userDTO) {
    	userFeignClient.updateLoggedInUser(userDTO);
    }

    public User resetPassword(ResetPasswordDTO resetPasswordDTO) {
    	return userFeignClient.resetPassword(resetPasswordDTO);
    }
}
