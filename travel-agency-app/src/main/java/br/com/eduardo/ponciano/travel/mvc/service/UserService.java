//package br.com.eduardo.ponciano.travel.mvc.service;
//
//import java.util.List;
//
//import br.com.eduardo.ponciano.travel.mvc.model.User;
//import br.com.eduardo.ponciano.travel.mvc.model.dto.ResetPasswordDTO;
//import br.com.eduardo.ponciano.travel.mvc.model.dto.UserDTO;
//import br.com.eduardo.ponciano.travel.mvc.model.dto.UserRegistrationDTO;
//
//public interface UserService {
//
//    User saveUser(UserRegistrationDTO registrationDTO);
//
//    // For registration
//    User findUserByUsername(String username);
//
//    UserDTO findUserDTOByUsername(String username);
//
//    UserDTO findUserById(Long id);
//
//    List<UserDTO> findAllUsers();
//
//    void updateUser(UserDTO userDTO);
//
//    void updateLoggedInUser(UserDTO userDTO);
//
//    void deleteUserById(Long id);
//
//    User resetPassword(ResetPasswordDTO resetPasswordDTO);
//
//}
