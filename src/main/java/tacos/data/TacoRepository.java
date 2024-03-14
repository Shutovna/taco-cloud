package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tacos.entity.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {

}
