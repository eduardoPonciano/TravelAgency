//package br.com.eduardo.ponciano.travel.hotel.model;
//
//import java.time.LocalDate;
//import java.util.Objects;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Availability {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Hotel hotel;
//
//    @Column(nullable = false)
//    private LocalDate date;
//
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Room room;
//
//    @Column(nullable = false)
//    private int availableRooms;
//
//    @Override
//    public String toString() {
//        return "Availability{" +
//                "id=" + id +
//                ", hotel=" + hotel +
//                ", date=" + date +
//                ", room=" + room +
//                ", availableRooms=" + availableRooms +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Availability that = (Availability) o;
//        return Objects.equals(id, that.id) && Objects.equals(hotel, that.hotel) && Objects.equals(room, that.room);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, hotel, room);
//    }
//}
