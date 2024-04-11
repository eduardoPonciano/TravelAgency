package br.com.eponciano.ms.booking.mvc.model.dto;

import br.com.eponciano.ms.booking.mvc.validation.annotation.CardExpiry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCardDTO {

    @NotBlank(message = "Titular do cartão é obrigatorio")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Titular do cartão deve conter somente letras e espaços")
    @Size(min = 3, max = 50, message = "Titular do cartão deve ter de 3 a 50 caracteres")
    private String cardholderName;

    private String cardNumber;

    @CardExpiry
    private String expirationDate;

    @Pattern(regexp = "^\\d{3}$", message = "CVC deve conter 3 digitos")
    private String cvc;
}
