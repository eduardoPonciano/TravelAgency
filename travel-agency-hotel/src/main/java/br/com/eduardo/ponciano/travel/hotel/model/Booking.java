package br.com.eduardo.ponciano.travel.hotel.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    
    @Column(unique = true, nullable = false)
    private String confirmationNumber;

    @CreationTimestamp
    private LocalDateTime bookingDate;


    @ManyToOne
    @JoinColumn(nullable = true)
    private Hotel hotel;

    @Column(nullable = false)
    private LocalDate checkinDate;

    @Column(nullable = false)
    private LocalDate checkoutDate;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<BookedRoom> bookedRooms = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.confirmationNumber = UUID.randomUUID().toString().substring(0, 8);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", confirmationNumber='" + confirmationNumber + '\'' +
                ", bookingDate=" + bookingDate +
                ", hotel=" + hotel +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", bookedRooms=" + bookedRooms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(confirmationNumber, booking.confirmationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, confirmationNumber);
    }
}
