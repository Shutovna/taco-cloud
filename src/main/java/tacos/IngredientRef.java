package tacos;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
@Table
@Data
public class IngredientRef {
    private final String ingredient;
}
