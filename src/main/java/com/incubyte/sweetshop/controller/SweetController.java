package com.incubyte.sweetshop.controller;

import com.incubyte.sweetshop.domain.Sweet;
import com.incubyte.sweetshop.repository.SweetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
