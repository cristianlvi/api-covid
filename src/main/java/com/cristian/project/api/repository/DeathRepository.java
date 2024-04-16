package com.cristian.project.api.repository;

import com.cristian.project.api.model.Death;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeathRepository extends JpaRepository<Death, Long> {
}
