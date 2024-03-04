package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import tacos.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
