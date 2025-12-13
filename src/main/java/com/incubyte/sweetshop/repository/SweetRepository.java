package com.incubyte.sweetshop.repository;

import com.incubyte.sweetshop.domain.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SweetRepository extends JpaRepository<Sweet, Long> {

    List<Sweet> findByNameContainingIgnoreCase(String name);
}
