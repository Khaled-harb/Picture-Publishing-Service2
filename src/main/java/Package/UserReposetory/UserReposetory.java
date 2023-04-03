package Package.UserReposetory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Package.Entity.Users;
@Repository
public interface UserReposetory extends CrudRepository<Users, Integer> {
	Users findByEmail(String email);

}
