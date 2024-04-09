package br.com.eduardo.ponciano.travel.commons.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomType {

    SOLTEIRO(1),
    CASAL(2);

    private final int capacity;

}
