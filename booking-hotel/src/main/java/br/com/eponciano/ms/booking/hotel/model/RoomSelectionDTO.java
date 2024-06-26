package br.com.eponciano.ms.booking.hotel.model;

import br.com.eponciano.ms.booking.commons.model.dto.RoomType;
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
