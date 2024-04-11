package br.com.eponciano.ms.boooking.user.model.dto;

import br.com.eponciano.ms.boooking.user.model.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationDTO {

    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "Invalid email address")
    private String username;

    @NotBlank(message = "Senha é obrigatorio")
    @Size(min = 6, max = 20, message = "Senha precisa ter de 6 a 20 caracteres")
    private String password;

    @NotBlank(message = "Nome é obrigatorio")
    @Pattern(regexp = "^(?!\\s*$)[A-Za-z ]+$", message = "Nome precisa conter somente letras")
    private String name;

    @NotBlank(message = "Sobrenome é obrigatorio")
    @Pattern(regexp = "^(?!\\s*$)[A-Za-z ]+$", message = "Sobrenome precisa conter somente letras")
    private String lastName;

    private RoleType roleType;

}
