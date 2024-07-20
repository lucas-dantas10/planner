package com.br.planner.link.service;

import com.br.planner.link.dto.LinkData;
import com.br.planner.link.dto.LinkRequestPayload;
import com.br.planner.link.dto.LinkResponse;
import com.br.planner.link.entity.Link;
import com.br.planner.link.repository.LinkRepository;
import com.br.planner.trip.entity.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService implements LinkServiceInterface {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public LinkResponse registerLink(LinkRequestPayload payload,
                                     Trip trip) {
        Link newLink = new Link(payload.title(), payload.url(), trip);

        this.linkRepository.save(newLink);

        return new LinkResponse(newLink.getId());
    }

    public List<LinkData> getAllLinks(UUID tripId) {
        return this.linkRepository.findByTripId(tripId)
                .stream()
                .map(link -> new LinkData(link.getId(), link.getTitle(), link.getUrl()))
                .toList();
    }
}
