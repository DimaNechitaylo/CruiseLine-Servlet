package model.entity;

public class Ship {
	private Long id;
	private String name;
	private int passengerСapacity;
	
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
	public int getPassengerСapacity() {
		return passengerСapacity;
	}
	public void setPassengerСapacity(int passengerСapacity) {
		this.passengerСapacity = passengerСapacity;
	}
	public static Builder builder() {
        return new Ship().new Builder();
    }
	public class Builder{
		
		public Builder id(Long id) {
    		Ship.this.setId(id);;
            return this;
        }
		
    	public Builder name(String name) {
    		Ship.this.setName(name);;
            return this;
        }
    	
    	public Builder passengerСapacity(int passengerСapacity) {
    		Ship.this.setPassengerСapacity(passengerСapacity);
            return this;
        }
    	
    	public Ship build() {
            return Ship.this;
        }

    }
	
	@Override
	public String toString() {
		return "Ship {" + 
				"    id: " + id + " ,\n" +
				"    name: " + name + " ,\n" +
				"    passengerСapacity: " + passengerСapacity +
				"}";	
	}
	
}
