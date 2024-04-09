package br.com.eduardo.ponciano.travel.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.user.model.User;
import br.com.eduardo.ponciano.travel.user.model.dto.ResetPasswordDTO;
import br.com.eduardo.ponciano.travel.user.model.dto.UserDTO;
import br.com.eduardo.ponciano.travel.user.model.dto.UserRegistrationDTO;

@Service
public interface UserService {
//
    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User saveUser(User user);



    //service api


    User saveUser(UserRegistrationDTO registrationDTO);

    // For registration
    User findUserByUsername(String username);

    UserDTO findUserDTOByUsername(String username);

    UserDTO findUserById(Long id);

    List<UserDTO> findAllUsers();

    void updateUser(UserDTO userDTO);

    void updateLoggedInUser(UserDTO userDTO);

    void deleteUserById(Long id);

    User resetPassword(ResetPasswordDTO resetPasswordDTO);


}

