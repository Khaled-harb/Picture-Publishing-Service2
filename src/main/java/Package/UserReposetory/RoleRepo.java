package Package.UserReposetory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Package.Entity.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {
	Role findByName (String name);

}
