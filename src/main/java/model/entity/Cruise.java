package model.entity;

import java.time.LocalDate;
import java.util.List;

import model.dto.CruiseDTO;
import model.dto.PassengerDTO;
import model.dto.CruiseDTO.Builder;

public class Cruise {
	private Long id;
	private String name;
	private String description;
	private Ship ship;
	private List<Port> ports;
	private List<PassengerDTO> passengers;
	private LocalDate start;
	private LocalDate finish;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public List<Port> getPorts() {
		return ports;
	}

	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}

	public List<PassengerDTO> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerDTO> passengers) {
		this.passengers = passengers;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getFinish() {
		return finish;
	}

	public void setFinish(LocalDate finish) {
		this.finish = finish;
	}

	public void addPassenger(PassengerDTO passenger) {
		passengers.add(passenger);
	}

	public void removePassenger(PassengerDTO passenger) {
		passengers.remove(passenger);
	}

	public static Builder builder() {
		return new Cruise().new Builder();
	}

	public class Builder {
		public Builder id(Long id) {
			Cruise.this.setId(id);
			return this;
		}

		public Builder name(String name) {
			Cruise.this.setName(name);
			return this;
		}
		
		public Builder description(String description) {
			Cruise.this.setDescription(description);
			return this;
		}

		public Builder ship(Ship ship) {
			Cruise.this.setShip(ship);
			return this;
		}

		public Builder ports(List<Port> ports) {
			Cruise.this.setPorts(ports);
			return this;
		}

		public Builder passengers(List<PassengerDTO> passengers) {
			Cruise.this.setPassengers(passengers);
			return this;
		}

		public Builder start(LocalDate start) {
			Cruise.this.setStart(start);
			return this;
		}

		public Builder finish(LocalDate finish) {
			Cruise.this.setFinish(finish);
			return this;
		}

		public Cruise build() {
			return Cruise.this;
		}
	}

	@Override
	public String toString() {
		return "Cruise {" + "    id: " + id + " ,\n" + "    name: " + name + " ,\n" + "    ship: " + ship + " ,\n"
				+ "    ports: " + ports + " ,\n" + "    passengers: " + passengers + " ,\n" + "    start: " + start
				+ " ,\n" + "    finish: " + finish + "}";
	}
}
