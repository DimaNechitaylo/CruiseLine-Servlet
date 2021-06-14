package ua.training.model.dto;

public class ShipDTO {
	private String name;
	private int passengerСapacity;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPassengerСapacity() {
		return passengerСapacity;
	}
	public void setPassengerСapacity(int passengerСapacity) {
		this.passengerСapacity = passengerСapacity;
	}
	public static Builder builder() {
        return new ShipDTO().new Builder();
    }
	public class Builder{
    	public Builder name(String name) {
    		ShipDTO.this.setName(name);;
            return this;
        }
    	
    	public Builder passengerСapacity(int passengerСapacity) {
    		ShipDTO.this.setPassengerСapacity(passengerСapacity);
            return this;
        }
    	
    	public ShipDTO build() {
            return ShipDTO.this;
        }

    }
}
