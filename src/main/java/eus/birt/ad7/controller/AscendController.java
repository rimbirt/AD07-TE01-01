package eus.birt.ad7.controller;

import eus.birt.ad7.domain.Ascend;
import eus.birt.ad7.domain.dto.AscendCommand;
import eus.birt.ad7.exception.DuplicateKeyException;
import eus.birt.ad7.exception.ResourceNotFoundException;
import eus.birt.ad7.repository.AscendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v0/ascends")
@RequiredArgsConstructor
@Slf4j
public class AscendController {

    private final AscendRepository ascendRepository;

    @GetMapping
    public List<Ascend> getAll() {
        return ascendRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Ascend createAscend(@RequestBody @Valid AscendCommand ascendCommand) throws DuplicateKeyException {
        if (ascendRepository.existsById(ascendCommand.getId())) {
            throw new DuplicateKeyException(String.format("%s does already have an ascent logged for route %s in the db",
                ascendCommand.getClimber().getName(), ascendCommand.getRoute().getName()));
        }
        return ascendRepository.save(ascendCommand.toAscend());
    }

    @PutMapping("/edit")
    public Ascend editAscend(@RequestBody @Valid AscendCommand ascendCommand) throws ResourceNotFoundException {
        Ascend ascend = ascendRepository.findById(ascendCommand.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Ascend not found in db"));
        ascend.setGrade(ascendCommand.getGrade());
        return ascendRepository.save(ascend);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody @Valid AscendCommand ascend) {
        ascendRepository.delete(ascend.toAscend());
    }

}
