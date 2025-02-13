package c3.msmb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import c3.msmb.model.Publication;
import c3.msmb.service.PublicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/publications")
public class PublicationsController {
    @Autowired
    private PublicationService publicationService;

    @GetMapping
    public List<Publication> getPublications() {
        return publicationService.getPublications();
    }
    
    @GetMapping("/{username}")
    public List<Publication> getPublicationsByUsername(@PathVariable(name = "username") String username) {
        return publicationService.getPublicationsByUsername(username);
    }
    
    @PostMapping("/save")
    public Publication savePublication(@RequestBody Publication publication) {
        return publicationService.savePublication(publication);
    }
    
    @DeleteMapping("/{id}")
    public void deletePublication(@PathVariable(name = "id") Long id) {
        publicationService.deletePublication(id);
    }

    @PatchMapping("/{id}")
    public void patchPublication(@RequestBody Publication publication, @PathVariable(name = "id") Long id) {
        publicationService.updateInformationPublication(publication, id);
    }
}