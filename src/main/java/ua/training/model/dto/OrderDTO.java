package ua.training.model.dto;

import ua.training.model.entity.OrderStatus;

public class OrderDTO {
	private Long id;
	private UserDTO userDto;
	private CruiseDTO cruiseDto;
	private OrderStatus status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public UserDTO getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
	public CruiseDTO getCruiseDto() {
		return cruiseDto;
	}
	public void setCruiseDto(CruiseDTO cruiseDto) {
		this.cruiseDto = cruiseDto;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public static Builder builder() {
        return new OrderDTO().new Builder();
    }
	public class Builder{
		
		public Builder id(Long id) {
    		OrderDTO.this.setId(id);
            return this;
        }
		
    	public Builder userId(UserDTO userDto) {
    		OrderDTO.this.setUserDto(userDto);
            return this;
        }
    	
    	public Builder cruiseDto(CruiseDTO cruiseDto) {
    		OrderDTO.this.setCruiseDto(cruiseDto);
            return this;
        }
    	
    	public Builder status(OrderStatus status) {
    		OrderDTO.this.setStatus(status);
            return this;
        }
    	
    	public OrderDTO build() {
            return OrderDTO.this;
        }

    }
}
