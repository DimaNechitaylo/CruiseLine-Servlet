package model.dto;

import model.entity.Role;

public class UserDTO {
	private Long id;
	private String username;
    private String password;
    private Role role;

    public UserDTO(){    }
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
        return new UserDTO().new Builder();
    }
	
    public class Builder{
    	public Builder id(Long id) {
            UserDTO.this.setId(id);;
            return this;
        }
    	public Builder username(String username) {
            UserDTO.this.setUsername(username);;
            return this;
        }
    	
    	public Builder password(String password) {
            UserDTO.this.setPassword(password);
            return this;
        }
    	
    	public Builder role(Role role) {
            UserDTO.this.setRole(role);
            return this;
        }
    	
    	
    	
    	public UserDTO build() {
            return UserDTO.this;
        }

    }
    
	@Override
	public String toString() {
		return "UserDTO{"+
				" username = "+this.getUsername()+
				" password = "+this.getPassword() +
				" role = "+this.getRole() +
                " }";
	}
}
