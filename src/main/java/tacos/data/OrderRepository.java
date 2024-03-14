package tacos.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tacos.entity.TacoOrder;
import tacos.entity.User;
import java.util.List;

public interface OrderRepository extends JpaRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

    List<TacoOrder> findAllByOrderByPlacedAtDesc(Pageable pageable);
}
