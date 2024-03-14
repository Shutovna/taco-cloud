package tacos.web.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.data.TacoRepository;
import tacos.entity.Taco;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/tacos", produces = "application/json")
@CrossOrigin
public class TacoApiController {
    @Autowired
    TacoRepository tacoRepository;

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(
                0, 20,
                Sort.by("createdAt").descending()
        );
        return tacoRepository.findAll(pageRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getTaco(@PathVariable(value = "id") Long tacoId) {
        Optional<Taco> taco = tacoRepository.findById(tacoId);
        if (taco.isPresent()) {
            return new ResponseEntity<>(taco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody @Valid Taco taco) {
        return tacoRepository.save(taco);
    }
}
