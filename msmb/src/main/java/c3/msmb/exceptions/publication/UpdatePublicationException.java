package c3.msmb.exceptions.publication;

import jakarta.persistence.EntityNotFoundException;

public class UpdatePublicationException extends EntityNotFoundException{
    public UpdatePublicationException(String message) {
        super(message);
    }
}