package br.com.eduardo.ponciano.travel.carrental.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	
	private Brand brand;
	
	@ManyToOne
	private Model model;
	
	@ManyToOne
	private FuelType fuelType;

	private String color;
	
	private int year;
	
	private String licensePlate;
	
	private BigDecimal dailyPrice;

	@Override
	public String toString() {
		return "Car [" + 
				"id =" + id +
				", brand =" + brand +
				", model =" + model + 
				", fuelType =" + fuelType + 
				", color =" + color + 
				", year =" + year + 
				", licensePlate =" + licensePlate + 
				", dailyPrice =" + dailyPrice + 
				"]";
	}
}
