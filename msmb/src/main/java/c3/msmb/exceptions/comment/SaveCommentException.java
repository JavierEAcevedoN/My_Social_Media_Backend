package c3.msmb.exceptions.comment;

import jakarta.persistence.EntityNotFoundException;

public class SaveCommentException extends EntityNotFoundException{
    public SaveCommentException(String message) {
        super(message);
    }
}