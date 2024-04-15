package br.com.eponciano.ms.booking.mvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.eponciano.ms.booking.commons.model.dto.BookingInitiationDTO;
import br.com.eponciano.ms.booking.mvc.security.RedirectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @GetMapping("/login")
    public String loginPage(Authentication authentication,@ModelAttribute BookingInitiationDTO bookingInitiationDTO) {
        String redirect = getAuthenticatedUserRedirectUrl(authentication);
        if (redirect != null) return redirect;
        log.debug("Accessando login page");
        return "login";
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

