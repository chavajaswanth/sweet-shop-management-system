package com.incubyte.sweetshop.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SweetTest {

    @Test
    void shouldCreateSweetWithInitialQuantity() {
        Sweet sweet = new Sweet("Ladoo", "Indian", 10.0, 5);

        assertEquals("Ladoo", sweet.getName());
        assertEquals("Indian", sweet.getCategory());
        assertEquals(10.0, sweet.getPrice());
        assertEquals(5, sweet.getQuantity());
    }

    @Test
    void shouldReduceQuantityWhenSweetIsPurchased() {
        Sweet sweet = new Sweet("Ladoo", "Indian", 10.0, 10);

        sweet.purchase(3);

        assertEquals(7, sweet.getQuantity());
    }

}
