package com.gobuy.apisprintbootgobuy.domain.service;

import com.gobuy.apisprintbootgobuy.domain.model.Publication;
import org.springframework.http.ResponseEntity;

public interface PublicationService {
    Publication getPublicationById(Long publicationId);
    Publication updatePublication(Long publicationId,Publication publication);
    ResponseEntity<?> deletePublication(Long publicationId);
    Publication createPublication(Publication publication);

}
