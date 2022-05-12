package eus.birt.ad7.controller;

import eus.birt.ad7.domain.Route;
import eus.birt.ad7.exception.ResourceNotFoundException;
import eus.birt.ad7.repository.RouteRepository;
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
@RequestMapping("api/v0/routes")
@RequiredArgsConstructor
@Slf4j
public class RouteController {

    private final RouteRepository routeRepository;

    @GetMapping
    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Route findById(@PathVariable Long id) throws ResourceNotFoundException {
        return routeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Route not found in db"));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Route createRoute(@RequestBody @Valid Route route) {
        return routeRepository.save(route);
    }

    @PutMapping("/{id}/edit")
    public Route editRoute(@PathVariable Long id, @RequestBody @Valid Route route) throws ResourceNotFoundException {
        if (!routeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Route not found in db");
        }
        route.setId(id);
        return routeRepository.save(route);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        routeRepository.deleteById(id);
    }
}
