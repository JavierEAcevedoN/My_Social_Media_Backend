package c3.msmb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.msmb.exceptions.publication.DeleteByPublicationIdException;
import c3.msmb.exceptions.publication.GetPublicationsException;
import c3.msmb.exceptions.publication.PublicationByIdException;
import c3.msmb.exceptions.publication.PublicationsByUsernameException;
import c3.msmb.exceptions.publication.SaveByPublicationIdException;
import c3.msmb.exceptions.publication.UpdatePublicationException;
import c3.msmb.model.Publication;
import c3.msmb.repository.PublicationRepository;
import jakarta.transaction.Transactional;

@Service
public class PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    public List<Publication> getPublications() {
        List<Publication> publications = publicationRepository.findAll();
        if (publications.isEmpty()) {
            throw new GetPublicationsException("Publications not found");
        }
        return publications;
    }

    public Publication getPublicationById(Long idPublication) {
        return publicationRepository.findById(idPublication).orElseThrow(() -> new PublicationByIdException("Publication " + idPublication + " not found"));
    }

    public List<Publication> getPublicationsByUsername(String username) {
        List<Publication> publications = publicationRepository.findByUsernameUsername(username);
        if (publications.isEmpty()) {
            throw new PublicationsByUsernameException("Publications not found for user " + username);
        }
        return publications;
    }

    public Publication savePublication(Publication publication) {
        try {
            return publicationRepository.save(publication);
        } catch (Exception e) {
            throw new SaveByPublicationIdException("Username " + publication.getUsername().getUsername() + " not Exist");
        }
    }

    public void deletePublication(Long id) {
        try {
            publicationRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteByPublicationIdException("Publication not found: " + id);
        }
    }

    @Transactional
    public void updateInformationPublication(Publication publication, Long id) {
        int updatedRows = publicationRepository.updatePartialPublication(publication.getContent(), publication.getImgSrc(), publication.getTags() ,id);
        if (updatedRows == 0) {
            throw new UpdatePublicationException("Publication not found: " + id);
        }
    }
}