package model.dto;

public class UserDTO {
	private String username;
    private String password;
    
    public UserDTO(){    }

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
    
	public static Builder builder() {
        return new UserDTO().new Builder();
    }
	
    public class Builder{
    	public Builder username(String username) {
            UserDTO.this.setUsername(username);;
            return this;
        }
    	
    	public Builder password(String password) {
            UserDTO.this.setPassword(password);
            return this;
        }
    	
    	public UserDTO build() {
            return UserDTO.this;
        }

    }
    
	@Override
	public String toString() {
		return "UserDTO{"+
				"username = "+this.getUsername()+
				"password = "+this.getPassword() +
                "}";
	}
}
