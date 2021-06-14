package ua.training.model.entity;

public class User {
	private Long id;
    private String username;
    private String password;
    private Role role;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public static Builder builder() {
        return new User().new Builder();
    }
	
	public class Builder{
		public Builder id(Long id) {
			User.this.setId(id);
			return this;
		}
		public Builder username(String username) {
			User.this.setUsername(username);
			return this;
		}
		
		public Builder password(String password) {
			User.this.setPassword(password);
			return this;
		}
		public Builder role(Role role) {
			User.this.setRole(role);
			return this;
		}
		public User build(){
            return User.this;
        }
	}
	
	@Override
	public String toString() {
		return "User {" + 
				"    id: " + id + " ,\n" +
				"    name: " + username + " ,\n" +
				"    passengerСapacity: " + password +
				"    passengerСapacity: " + role +

				"}";
	}
}
