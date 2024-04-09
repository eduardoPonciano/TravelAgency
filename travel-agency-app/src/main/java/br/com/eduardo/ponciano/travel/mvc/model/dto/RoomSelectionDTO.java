package br.com.eduardo.ponciano.travel.mvc.model.dto;

import br.com.eduardo.ponciano.travel.commons.model.dto.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomSelectionDTO {

    private RoomType roomType;
    private int count;
}
