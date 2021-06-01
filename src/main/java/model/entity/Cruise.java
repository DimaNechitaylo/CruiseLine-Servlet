package model.entity;

import java.time.LocalDate;
import java.util.List;

public class Cruise {
	private Long id;
	private String name;
	private Ship ship;
	private List<Port> ports;
	private List<User> passengers;
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
	public List<User> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<User> passengers) {
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
	
	public void addPassenger(User user) {
		passengers.add(user);
	}
	public void removePassenger(User user) {
		passengers.remove(user);
	}
	public static Builder builder() {
        return new Cruise().new Builder();
    }
	public class Builder{
		public Builder id(Long id) {
			Cruise.this.setId(id);
			return this;
		}
		public Builder name(String name) {
			Cruise.this.setName(name);
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
		public Builder passengers(List<User> passengers) {
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
		
		public Cruise build(){
            return Cruise.this;
        }
	}
}
