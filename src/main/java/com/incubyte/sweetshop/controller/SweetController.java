package com.incubyte.sweetshop.controller;

import com.incubyte.sweetshop.domain.Sweet;
import com.incubyte.sweetshop.repository.SweetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sweets")
public class SweetController {

    private final SweetRepository sweetRepository;

    public SweetController(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sweet createSweet(@RequestBody Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    @GetMapping
    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    @GetMapping("/search")
    public List<Sweet> searchSweets(@RequestParam String name) {
        return sweetRepository.findByNameContainingIgnoreCase(name);
    }


    @PutMapping("/{id}")
    public Sweet updateSweet(@PathVariable Long id, @RequestBody Sweet updatedSweet) {
        Sweet existingSweet = sweetRepository.findById(id).get();

        existingSweet.setName(updatedSweet.getName());
        existingSweet.setCategory(updatedSweet.getCategory());
        existingSweet.setPrice(updatedSweet.getPrice());
        existingSweet.setQuantity(updatedSweet.getQuantity());

        return sweetRepository.save(existingSweet);
    }

}
