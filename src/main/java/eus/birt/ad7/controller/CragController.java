package eus.birt.ad7.controller;

import eus.birt.ad7.domain.Crag;
import eus.birt.ad7.exception.ResourceNotFoundException;
import eus.birt.ad7.repository.CragRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v0/crags")
@RequiredArgsConstructor
@Slf4j
public class CragController {

    private final CragRepository cragRepository;

    @GetMapping
    public List<Crag> getAll() {
        return cragRepository.findAll();
    }

    @GetMapping("/{id}")
    public Crag findById(@PathVariable Long id) throws ResourceNotFoundException {
        return cragRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Crag not found in db"));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Crag createCrag(@RequestBody @Valid Crag crag) {
        return cragRepository.save(crag);
    }

    @PutMapping("/{id}/edit")
    public Crag editCrag(@PathVariable Long id, @RequestBody @Valid Crag crag) throws ResourceNotFoundException {
        if (!cragRepository.existsById(id)) {
            throw new ResourceNotFoundException("Crag not found in db");
        }
        crag.setId(id);
        return cragRepository.save(crag);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cragRepository.deleteById(id);
    }
}
