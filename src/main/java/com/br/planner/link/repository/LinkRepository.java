package com.br.planner.link.repository;

import com.br.planner.link.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LinkRepository extends JpaRepository<Link, UUID> {
}
