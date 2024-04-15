package br.com.eponciano.ms.booking.commons.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingInitiationDTO implements Serializable{

    private static final long serialVersionUID = 8013989661377978563L;
	private long hotelId;
    private long customerId;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private long durationDays;
    private List<RoomSelectionDTO> roomSelections = new ArrayList<>();
    private BigDecimal totalPrice;

}
