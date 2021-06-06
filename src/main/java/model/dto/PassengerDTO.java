package model.dto;

public class PassengerDTO {
	private Long id;
	private String username;

	public PassengerDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassengername() {
		return username;
	}

	public void setPassengername(String username) {
		this.username = username;
	}

	public static Builder builder() {
		return new PassengerDTO().new Builder();
	}

	public class Builder {
		public Builder id(Long id) {
			PassengerDTO.this.setId(id);
			;
			return this;
		}

		public Builder username(String username) {
			PassengerDTO.this.setPassengername(username);
			;
			return this;
		}

		public PassengerDTO build() {
			return PassengerDTO.this;
		}

	}

	@Override
	public String toString() {
		return "PassengerDTO{" + "   username = " + this.getPassengername() + ", "+ "   username = " + this.getPassengername() + " }";
	}

}
