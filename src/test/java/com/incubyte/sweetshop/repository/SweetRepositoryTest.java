package com.incubyte.sweetshop.repository;

import com.incubyte.sweetshop.domain.Sweet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SweetRepositoryTest {

    @Autowired
    private SweetRepository sweetRepository;

    @Test
    void shouldSaveAndRetrieveSweet() {
        Sweet sweet = new Sweet("Ladoo", "Indian", 10.0, 20);

        Sweet savedSweet = sweetRepository.save(sweet);

        assertThat(savedSweet.getId()).isNotNull();
    }
}
