package br.com.eduardo.ponciano.travel.mvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.eduardo.ponciano.travel.mvc.model.dto.HotelSearchDTO;
import br.com.eduardo.ponciano.travel.mvc.security.RedirectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PublicController {

    @GetMapping("/")
    public String homePage(Authentication authentication) {
        String redirect = getAuthenticatedUserRedirectUrl(authentication);
        if (redirect != null) return redirect;
        log.debug("Accessando home page");
        return "index";
    }

    @GetMapping("/country")
    public String showCountryForm(Authentication authentication) {
        return "paises";
    }

    @GetMapping("/turismo")
    public String showSearchTurismoForm(@ModelAttribute("hotelSearchDTO") HotelSearchDTO hotelSearchDTO) {
        return "turismo";
    }

    @GetMapping("/assento")
    public String showSearchF2orm(@ModelAttribute("hotelSearchDTO") HotelSearchDTO hotelSearchDTO) {
        return "hotelsearch/assento";
    }

    private String getAuthenticatedUserRedirectUrl(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() && !authentication.getAuthorities().toString().contains("ROLE_CUSTOMER") ) {
            String redirectUrl = RedirectUtil.getRedirectUrl(authentication);
            if (redirectUrl != null) {
                return "redirect:" + redirectUrl;
            }
        }
        return null;
    }
    

}