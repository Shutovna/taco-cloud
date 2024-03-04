package tacos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taco {

    @Id
    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull(message = "You must choose at least 1 ingredient")
    @Size(min=1, message="You must choose at least 1 ingredient")
    @MappedCollection(idColumn = "TACO")
    List<IngredientRef> ingredients = new ArrayList<>();

    private Date createdAt = new Date();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(new IngredientRef(ingredient.getId()));
    }
}
