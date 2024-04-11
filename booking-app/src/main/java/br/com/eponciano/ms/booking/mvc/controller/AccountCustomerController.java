package br.com.eponciano.ms.booking.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.eponciano.ms.booking.mvc.client.UserFeignClient;
import br.com.eponciano.ms.booking.mvc.consumer.UserServiceConsumer;
import br.com.eponciano.ms.booking.mvc.exception.UsernameAlreadyExistsException;
import br.com.eponciano.ms.booking.mvc.model.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/account")
@Slf4j
public class AccountCustomerController {

    @Autowired
    private UserFeignClient userService;

    @GetMapping
    public String showCustomerAccount(Model model){
        log.debug("Apresentando dados do cliente");
        addLoggedInUserDataToModel(model);
        return "customer/account";
    }

    @GetMapping("/edit")
    public String showCustomerEditForm(Model model){
        log.debug("Apresentando dados de cliente a ser editado");
        addLoggedInUserDataToModel(model);
        return "customer/account-edit";
    }

    @PostMapping("/edit")
    public String editCustomerAccount(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result) {
        log.debug("Edição de usuario pelo ID: {}", userDTO.getId());
        if (result.hasErrors()) {
            log.warn("Erro de validação");
            return "customer/account-edit";
        }
        try {
            userService.updateLoggedInUser(userDTO);
            log.debug("Usuario Atualizado com sucesso");
        } catch (UsernameAlreadyExistsException e) {
            log.error("Erro: Username ja existe", e);
            result.rejectValue("username", "user.exists", "Username ja esta cadastrado!");
            return "customer/account-edit";
        }
        return "redirect:/customer/account?success";
    }

    private void addLoggedInUserDataToModel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserDTO userDTO = userService.findUserDTOByUsername(username);
        log.debug("Adcionando dados do usuario logado ao Model com ID: {}", userDTO.getId());
        model.addAttribute("user", userDTO);
    }

}
