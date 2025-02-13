package c3.msmb.exceptions.publication;

import jakarta.persistence.EntityNotFoundException;

public class SaveByPublicationIdException extends EntityNotFoundException{
    public SaveByPublicationIdException(String message) {
        super(message);
    }
}