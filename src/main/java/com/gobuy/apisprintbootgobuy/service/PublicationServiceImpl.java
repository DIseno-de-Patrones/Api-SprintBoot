package com.gobuy.apisprintbootgobuy.service;

import com.gobuy.apisprintbootgobuy.domain.model.Publication;
import com.gobuy.apisprintbootgobuy.domain.model.Purchase;
import com.gobuy.apisprintbootgobuy.domain.repository.PublicationRepository;
import com.gobuy.apisprintbootgobuy.domain.service.PublicationService;
import com.gobuy.apisprintbootgobuy.domain.service.PurchaseService;
import com.gobuy.apisprintbootgobuy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public Publication getPublicationById(Long publicationId) {
        return publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));
    }

    @Override
    public Publication updatePublication(Long publicationId, Publication publication) {
        Publication publication1 = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));
        publication1.setPublishAt(publication.getPublishAt());
        publication1.setDescription(publication.getDescription());
        publication1.setPhoto(publication.getPhoto());
        return publicationRepository.save(publication1);    }

    @Override
    public ResponseEntity<?> deletePublication(Long publicationId) {
        Publication role = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "Id", publicationId));
       publicationRepository.delete(role);
        return ResponseEntity.ok().build();    }

    @Override
    public Publication createPublication(Publication publication) {
        return publicationRepository.save(publication);
    }
}
