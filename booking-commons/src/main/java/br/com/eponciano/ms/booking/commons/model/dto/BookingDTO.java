package br.com.eponciano.ms.booking.commons.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
    private BigDecimal totalPrice;
    private String hotelName;
    private String customerName;
    private String customerEmail;
    
}
