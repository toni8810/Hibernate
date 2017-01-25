package hibernate.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 4013970009744210116L;
	
	@Id
	@Column(name="username")
	private String username;
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="username")
	private List<Image> images;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Image> getImage() {
		return images;
	}

	public void setImage(List<Image> images) {
		this.images = images;
	}
	

	
}
