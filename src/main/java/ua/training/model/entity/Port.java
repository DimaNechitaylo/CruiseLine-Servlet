package ua.training.model.entity;

public class Port {
	private Long id;
	private String name;

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

	public static Builder builder() {
		return new Port().new Builder();
	}

	public class Builder {
		public Builder id(Long id) {
			Port.this.setId(id);
			;
			return this;
		}

		public Builder name(String name) {
			Port.this.setName(name);
			;
			return this;
		}

		public Port build() {
			return Port.this;
		}

	}

	@Override
	public String toString() {
		return "Port" + "    id: " + id + " ,\n" + "    name: " + name + " ,\n" + "}";
	}
}
