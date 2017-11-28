package aka.jwowjpa.exceptions;

import org.eclipse.jdt.annotation.NonNull;

/**
 * EntityManagerException.
 *
 * @author charlottew
 */
public final class EntityManagerException extends Exception {

    private static final long serialVersionUID = 4316439134279722041L;

    /**
     * Constructor.
     *
     * @param message
     */
    public EntityManagerException(@NonNull final String message) {
        super(message);
    }
}
