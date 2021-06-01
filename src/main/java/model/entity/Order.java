package model.entity;
public class Order {
	private Long id;
	private User user;
	private Cruise cruise;
	private OrderStatus status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Cruise getCruise() {
		return cruise;
	}
	public void setCruise(Cruise cruise) {
		this.cruise = cruise;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public void reject() {
		this.setStatus(OrderStatus.REJECTED);
	}
	public void cancel() {
		this.setStatus(OrderStatus.CANCELED);
	}
	public void pay() {
		this.setStatus(OrderStatus.PAID);
	}
	public void start() {
		this.setStatus(OrderStatus.STARTED);
	}
	public void finish() {
		this.setStatus(OrderStatus.FINISHED);
	}
	public void confirm() {
		this.setStatus(OrderStatus.WATING_PAYMENT);
	}
	public static Builder builder() {
        return new Order().new Builder();
    }
	public class Builder{
		public Builder id(Long id) {
    		Order.this.setId(id);
            return this;
        }
		
    	public Builder user(User user) {
    		Order.this.setUser(user);
            return this;
        }
    	
    	public Builder cruise(Cruise cruise) {
    		Order.this.setCruise(cruise);
            return this;
        }
    	
    	public Builder status(OrderStatus status) {
    		Order.this.setStatus(status);
            return this;
        }
    	
    	public Order build() {
            return Order.this;
        }

    }
}