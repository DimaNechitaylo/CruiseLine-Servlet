package model.dto;

import model.entity.OrderStatus;

public class OrderDTO {
	private Long userId;
	private CruiseDTO cruiseDto;
	private OrderStatus status;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
    	public Builder userId(Long userId) {
    		OrderDTO.this.setUserId(userId);
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
