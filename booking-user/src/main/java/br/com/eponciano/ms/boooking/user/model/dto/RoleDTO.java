package br.com.eponciano.ms.boooking.user.model.dto;

import br.com.eponciano.ms.boooking.user.model.enums.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {

    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

}

