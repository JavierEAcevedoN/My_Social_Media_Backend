package c3.msmb.exceptions.publication;

import jakarta.persistence.EntityNotFoundException;

public class DeleteByPublicationIdException extends EntityNotFoundException {
    public DeleteByPublicationIdException(String message) {
        super(message);
    }
}