package br.com.eduardo.ponciano.travel.mvc.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.debug("Redirecionando o usario autenticado");

        String redirectUrl = RedirectUtil.getRedirectUrl(authentication);

        log.debug("Redirecionando para a url: " + redirectUrl);

        if (redirectUrl == null) {
            throw new IllegalStateException("Não foi possivel encontrar o perfil para redirecionar");
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
