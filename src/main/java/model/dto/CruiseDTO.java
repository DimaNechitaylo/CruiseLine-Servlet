package model.dto;

import java.time.LocalDate;
import java.util.List;

import model.dto.OrderDTO.Builder;
import model.entity.Port;
import model.entity.Ship;
import model.entity.User;

public class CruiseDTO {
	private String name;
	private Ship ship;
	private int passengersCount;
	private int availableCount;
	private LocalDate start;
	private LocalDate finish;
	private List<String> portNames;
	
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
	public int getPassengersCount() {
		return passengersCount;
	}
	public void setPassengersCount(int passengersCount) {
		this.passengersCount = passengersCount;
	}
	public int getAvailableCount() {
		return availableCount;
	}
	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
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
	public List<String> getPortNames() {
		return portNames;
	}
	public void setPortNames(List<String> portNames) {
		this.portNames = portNames;
	}
	public static Builder builder() {
        return new CruiseDTO().new Builder();
    }
	public class Builder{
		
		public Builder name(String name) {
			CruiseDTO.this.setName(name);
			return this;
		}
		public Builder ship(Ship ship) {
			CruiseDTO.this.setShip(ship);
			return this;
		}
		public Builder portNames(List<String> portNames) {
			CruiseDTO.this.setPortNames(portNames);
			return this;
		}
		public Builder passengersCount(int passengersCount) {
			CruiseDTO.this.setPassengersCount(passengersCount);
			return this;
		}
		public Builder availableCount(int availableCount) {
			CruiseDTO.this.setAvailableCount(availableCount);
			return this;
		}
		public Builder start(LocalDate start) {
			CruiseDTO.this.setStart(start);
			return this;
		}
		public Builder finish(LocalDate finish) {
			CruiseDTO.this.setFinish(finish);
			return this;
		}
		
		public CruiseDTO build(){
            return CruiseDTO.this;
        }
	}
}
