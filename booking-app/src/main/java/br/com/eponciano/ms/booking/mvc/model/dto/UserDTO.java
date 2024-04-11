package br.com.eponciano.ms.booking.mvc.model.dto;

import br.com.eponciano.ms.booking.mvc.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private Long id;

    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "email invalido")
    private String username;

    @NotBlank(message = "Nome é obrigatorio")
    @Pattern(regexp = "^(?!\\s*$)[A-Za-z ]+$", message = "Nome precisa conter somente letras")
    private String name;

    @NotBlank(message = "Sobrenome é obrigatorio")
    @Pattern(regexp = "^(?!\\s*$)[A-Za-z ]+$", message = "Sobrenome precisa conter somente letras")
    private String lastName;

    private Role role;

}
