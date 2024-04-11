package br.com.eduardo.ponciano.travel.carrental.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class BookingCar {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String confirmationNumber;
    
    @CreationTimestamp
    private LocalDateTime bookingDate;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Car car;
    
    private Long idUser;
    
    private LocalDate checkinDate;
    
    private LocalDate checkoutDate;
    
    private BigDecimal totalPrice;
    
    @PrePersist
    protected void onCreate() {
        this.confirmationNumber = UUID.randomUUID().toString().substring(0, 8);
    }

	@Override
	public String toString() {
		return "BookingCar [" + 
				"confirmationNumber=" + confirmationNumber +
				", bookingDate=" + bookingDate +
				", car=" + car + 
				", idUser=" + idUser + 
				", checkinDate=" + checkinDate + 
				", checkoutDate=" + checkoutDate + 
				", totalPrice=" + totalPrice + 
				"]";
	}

    @Override
    public int hashCode() {
        return Objects.hash(id, confirmationNumber);
    }
    
}
