package br.com.eduardo.ponciano.travel.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPasswordDTO {

    @NotBlank(message = "O campo senha atual é obrigatoria")
    @Size(min = 6, max = 20, message = "Senha deve ter de 6 a 20 caracteres")
    private String oldPassword;

    @NotBlank(message = "O campo nova senha é obrigatorio")
    @Size(min = 6, max = 20, message = "Senha deve ter de 6 a 20 caracteres")
    private String newPassword;

    @NotBlank(message = "O campo confirmar senha é obrigatorio")
    @Size(min = 6, max = 20, message = "Senha deve ter de 6 a 20 caracteres")
    private String confirmNewPassword;

}
