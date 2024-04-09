package br.com.eduardo.ponciano.travel.mvc.controller;

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

import br.com.eduardo.ponciano.travel.mvc.consumer.UserServiceConsumer;
import br.com.eduardo.ponciano.travel.mvc.exception.UsernameAlreadyExistsException;
import br.com.eduardo.ponciano.travel.mvc.model.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/account")
@Slf4j
public class AccountManagentController {

	@Autowired
	private final UserServiceConsumer userService;

    @GetMapping
    public String showHotelManagerAccount(Model model){
        log.debug("Apresentando dados de contato do hotel");
        addLoggedInUserDataToModel(model);
        return "hotelmanager/account";
    }

    @GetMapping("/edit")
    public String showHotelManagerEditForm(Model model){
        log.debug("Apresentando dado de contado do hotel a ser editado");
        addLoggedInUserDataToModel(model);
        return "hotelmanager/account-edit";
    }

    @PostMapping("/edit")
    public String editHotelManagerAccount(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result) {
        log.debug("Carregando dados o usuario com ID: {}", userDTO.getId());
        if (result.hasErrors()) {
            log.warn("Erro de validação");
            return "hotelmanager/account-edit";
        }
        try {
            userService.updateLoggedInUser(userDTO);
            log.debug("Cadastro atualizado com sucess");
        } catch (UsernameAlreadyExistsException e) {
            log.error("Erro: Username ja existe", e);
            result.rejectValue("username", "user.exists", "Username ta cadastrado!");
            return "hotelmanager/account-edit";
        }
        return "redirect:/manager/account?success";
    }

    private void addLoggedInUserDataToModel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserDTO userDTO = userService.findUserDTOByUsername(username);
        log.debug("Adcionando dados do usuario logado ao Model com ID: {}", userDTO.getId());
        model.addAttribute("user", userDTO);
    }

}
