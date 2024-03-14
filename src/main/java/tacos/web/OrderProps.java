package tacos.web;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Validated
public class OrderProps {
    @Setter
    @Getter
    @Min(value = 5)
    @Max(value = 25)
    private int pageSize;

}
