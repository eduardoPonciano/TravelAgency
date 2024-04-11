package br.com.eduardo.ponciano.travel.hotel.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class TypeBed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    
    private Integer capacity;
    
    @OneToMany(mappedBy = "typeBed", fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "typeBed", fetch = FetchType.LAZY)
    private List<BookedRoom> bookdRooms = new ArrayList<>();

	@Override
	public String toString() {
		return "TypeBed [" + 
				"id =" + id +
				", description =" + description +
				", capacity =" + capacity +
				"]";
	}

}
