package com.br.planner.trip.controller;

import com.br.planner.link.dto.LinkData;
import com.br.planner.link.service.LinkServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TripGetLinksController {

    private final LinkServiceInterface linkService;

    public TripGetLinksController(LinkServiceInterface linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/trips/{tripId}/links")
    public ResponseEntity<List<LinkData>> getAllLinks(@PathVariable UUID tripId) {
        List<LinkData> links = this.linkService.getAllLinks(tripId);

        if (links.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(links);
    }
}
