package br.com.eponciano.ms.booking.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.eponciano.ms.booking.mvc.consumer.UserServiceConsumer;
import br.com.eponciano.ms.booking.mvc.exception.UsernameAlreadyExistsException;
import br.com.eponciano.ms.booking.mvc.model.dto.UserRegistrationDTO;
import br.com.eponciano.ms.booking.mvc.model.enums.RoleType;
import br.com.eponciano.ms.booking.mvc.security.RedirectUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
@Slf4j
public class ResgisterUserController {

	@Autowired
	private final UserServiceConsumer userService;
    

    @GetMapping("/customer")
    public String showCustomerRegistrationForm(@ModelAttribute("user") UserRegistrationDTO registrationDTO, Authentication authentication) {
        String redirect = getAuthenticatedUserRedirectUrl(authentication);
        if (redirect != null) return redirect;
        log.info("Apresentando formulario de registro de cliente");
        return "register-customer";
    }

    @PostMapping("/customer")
    public String registerCustomerAccount(@Valid @ModelAttribute("user") UserRegistrationDTO registrationDTO, BindingResult result) {
        log.info("Attempting to register customer account: {}", registrationDTO.getUsername());
        registrationDTO.setRoleType(RoleType.CUSTOMER);
        return registerUser(registrationDTO, result, "register-customer", "register/customer");
    }

    @GetMapping("/manager")
    public String showManagerRegistrationForm(@ModelAttribute("user") UserRegistrationDTO registrationDTO, Authentication authentication) {
        String redirect = getAuthenticatedUserRedirectUrl(authentication);
        if (redirect != null) return redirect;
        log.info("Apresentando formulario de registro de adminstrador");
        return "register-manager";
    }

    @PostMapping("/manager")
    public String registerManagerAccount(@Valid @ModelAttribute("user") UserRegistrationDTO registrationDTO, BindingResult result) {
        log.info("Attempting to register manager account: {}", registrationDTO.getUsername());
        registrationDTO.setRoleType(RoleType.HOTEL_MANAGER);
        return registerUser(registrationDTO, result, "register-manager", "register/manager");
    }

    private String registerUser(UserRegistrationDTO registrationDTO, BindingResult result, String view, String redirectUrl) {
        if (result.hasErrors()) {
            log.warn("Falha ao realizar o Cadastro -erro de validação: {}", result.getAllErrors());
            return view;
        }
        try {
            userService.saveUser(registrationDTO);
            log.info("Cadastro de Usuario feito com sucesso: {}", registrationDTO.getUsername());
        } catch (UsernameAlreadyExistsException e) {
            log.error("Falha ao realizar o Cadastro - usuario ja existe: {}", e.getMessage());
            result.rejectValue("username", "user.exists", e.getMessage());
            return view;
        }
        return "redirect:/" + redirectUrl + "?success";
    }

    private String getAuthenticatedUserRedirectUrl(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String redirectUrl = RedirectUtil.getRedirectUrl(authentication);
            if (redirectUrl != null) {
                return "redirect:" + redirectUrl;
            }
        }
        return null;
    }

}

