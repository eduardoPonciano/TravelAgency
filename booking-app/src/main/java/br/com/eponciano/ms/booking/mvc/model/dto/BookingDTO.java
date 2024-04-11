package br.com.eponciano.ms.booking.mvc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.eponciano.ms.booking.mvc.model.enums.PaymentMethod;
import br.com.eponciano.ms.booking.mvc.model.enums.PaymentStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO  implements Serializable{

    private static final long serialVersionUID = -6002378044194308768L;
	private Long id;
    private String confirmationNumber;
    private LocalDateTime bookingDate;
    private Long customerId;
    
    private Long hotelId;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private int numeroQuarto;
    
    private List<RoomSelectionDTO> roomSelections = new ArrayList<>();
    private BigDecimal totalPrice;
    private String hotelName;
    private AddressDTO hotelAddress;
    private String customerName;
    private String customerEmail;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    
}
