package br.com.eduardo.ponciano.travel.mvc.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class RedirectUtil {

    public static String getRedirectUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                return "/admin/dashboard";
            } else if (grantedAuthority.getAuthority().equals("ROLE_CUSTOMER")) {
                return "/booking/initiate";
            } else if (grantedAuthority.getAuthority().equals("ROLE_HOTEL_MANAGER")) {
                return "/manager/dashboard";
            }
        }
        return null;
    }
    

}
