package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import tacos.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String userName);
}
