package br.com.eponciano.ms.booking.commons.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomType {

    SOLTEIRO(1),
    CASAL(2);

    private final int capacity;

}
