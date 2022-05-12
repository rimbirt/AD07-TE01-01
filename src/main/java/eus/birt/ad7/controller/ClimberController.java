package eus.birt.ad7.controller;

import eus.birt.ad7.domain.Climber;
import eus.birt.ad7.exception.ResourceNotFoundException;
import eus.birt.ad7.repository.ClimberRepository;
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
@RequestMapping("api/v0/climbers")
@RequiredArgsConstructor
@Slf4j
public class ClimberController {

    private final ClimberRepository climberRepository;

    @GetMapping
    public List<Climber> getAll() {
        return climberRepository.findAll();
    }

    @GetMapping("/{id}")
    public Climber findById(@PathVariable Long id) throws ResourceNotFoundException {
        return climberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Climber not found in db"));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Climber createClimber(@RequestBody @Valid Climber climber) {
        return climberRepository.save(climber);
    }

    @PutMapping("/{id}/edit")
    public Climber editClimber(@PathVariable Long id, @RequestBody @Valid Climber climber) throws ResourceNotFoundException {
        if (!climberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Climber not found in db");
        }
        climber.setId(id);
        return climberRepository.save(climber);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        climberRepository.deleteById(id);
    }
}
