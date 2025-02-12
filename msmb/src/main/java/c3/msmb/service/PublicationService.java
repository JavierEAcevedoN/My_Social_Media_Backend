package c3.msmb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.msmb.model.Publication;
import c3.msmb.repository.PublicationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    public List<Publication> getPublications() {
        return publicationRepository.findAll();
    }

    public Publication getPublicationById(Long idPublication) {
        return publicationRepository.findById(idPublication).orElse(null);
    }

    public List<Publication> getPublicationsByUsername(String username) {
        return publicationRepository.findByUsernameUsername(username);
    }

    public Publication savePublication(Publication publication) {
        try {
            return publicationRepository.save(publication);
        } catch (Exception e) {
            System.out.println("Ese usuario no existe");
            return null;
        }
    }

    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }

    @Transactional
    public void updateInformationPublication(Publication publication, Long id) {
        int updatedRows = publicationRepository.updatePartialPublication(publication.getContent(), publication.getImgSrc(), publication.getTags() ,id);
        if (updatedRows == 0) {
            throw new EntityNotFoundException("Publication not found: " + id);
        }
    }
}