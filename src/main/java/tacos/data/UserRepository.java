package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import tacos.User;

public interface UserRepository extends CrudRepository<User, Long> {
    UserDetails findByUsername(String userName);
}
