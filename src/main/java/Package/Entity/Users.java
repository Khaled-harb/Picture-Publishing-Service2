package Package.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table

public class Users  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;

	private String password;

	
	 @JsonIgnore 
	   @JoinTable(name = "user_roles", joinColumns = {
	        @JoinColumn(name = "role_id", referencedColumnName = "id")},
	         inverseJoinColumns = {
	        @JoinColumn(name = "user_id", referencedColumnName = "id")})
	   @ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();
	




	
	public Users(Integer id, String email, String password, List<Role> roleList) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roleList;
	}



	public List<Role> getRoleList() {
		return roles;
	}



	public void setRoleList(List<Role> roleList) {
		this.roles = roleList;
	}



	public Users() {

	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
