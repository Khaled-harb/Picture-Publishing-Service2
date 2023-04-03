package Package.RoleServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Package.Entity.Role;
import Package.UserReposetory.RoleRepo;

@Service
public class RoleServices {
	@Autowired
       private  RoleRepo roleRepo;

	public List<Role> findAll() {

		return (List<Role>) roleRepo.findAll();
	}

	public Role findById(int id) {

		return roleRepo.findById(id).get();
	}
	
	public Role findByName(String name) {

		return roleRepo.findByName(name);
	}
	
	public Role save(Role entity) {

		return roleRepo.save(entity);
	}
}
