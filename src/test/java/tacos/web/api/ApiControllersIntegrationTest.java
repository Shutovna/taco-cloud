package tacos.web.api;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.entity.Ingredient;
import tacos.entity.Taco;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApiControllersIntegrationTest {
    private static final Logger LOG = LoggerFactory.getLogger(ApiControllersIntegrationTest.class);

    @Autowired
    private TestRestTemplate template;
    @Autowired
    private TacoRepository tacoRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    /*@Test
    public void testIngredientsGet() throws Exception {
        ResponseEntity<List<Ingredient>> response = template.exchange(
                "http://localhost:8080/api/ingredients", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Ingredient>>() {
                });

        List<Ingredient> ingredients = response.getBody();
        System.out.println("allIngredients = " + ingredients);
        assertEquals(10, ingredients.size());

        Ingredient ingredient = ingredients.get(0);
        assertEquals("FLTO", ingredient.getId());
        assertEquals("Flour Tortilla", ingredient.getName());
        assertEquals(Ingredient.Type.WRAP, ingredient.getType());
    }

    @Test
    public void testIngredientPut() {
        Ingredient ingredient = new Ingredient("FLTO", "newName", Ingredient.Type.VEGGIES);
        RequestEntity<Ingredient> request = RequestEntity
                .put("http://localhost:8080/ingredients/{id}", ingredient.getId())
                .accept(MediaType.APPLICATION_JSON)
                .body(ingredient);

        ResponseEntity<Ingredient> response = template.exchange(request, Ingredient.class);
        System.out.println("response = " + response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody().getId());
    }*/

    @Test
    void recentTacos() {
        LOG.trace("This is a TRACE log");
        LOG.debug("This is a DEBUG log");
        LOG.info("This is an INFO log");
        LOG.error("This is an ERROR log");

        List<Ingredient> ingredients = List.of(ingredientRepository.findById("FLTO").orElseThrow());
        Taco taco = new Taco(null, "tacoName", ingredients, new Date());
        taco = tacoRepository.save(taco);

        ResponseEntity<Taco> response = template.getForEntity(
                "http://localhost:8080/api/tacos/{id}", Taco.class, taco.getId());
        System.out.println("response = " + response);
/*
        ResponseEntity<List<Taco>> response2 = template.exchange(
                "http://localhost:8080/api/tacos?recent", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Taco>>() {
                });
        System.out.println("response2 = " + response2);*/

       /* ResponseEntity<List<Taco>> response = template.exchange(
                "http://localhost:8080/api/tacos?recent", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Taco>>() {
                });
        System.out.println("response = " + response);
        List<Taco> body = response.getBody();
        Taco taco2 = body.get(0);
        assertEquals(taco.getName(), taco2.getName());
        assertEquals(taco.getCreatedAt(), taco2.getCreatedAt());
        assertEquals(taco.getIngredients(), taco2.getIngredients());
        assertNotNull(taco2.getId());*/

    }

    @Test
    void getTaco() {
    }

    @Test
    void postTaco() {
    }

}
