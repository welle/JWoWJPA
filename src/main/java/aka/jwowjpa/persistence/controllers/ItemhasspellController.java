package aka.jwowjpa.persistence.controllers;

import org.eclipse.jdt.annotation.NonNull;
import org.springframework.stereotype.Repository;

import aka.jwowjpa.persistence.models.Itemhasspell;

/**
 * Itemhasspell controller.
 *
 * @author charlottew
 */
@Repository
public class ItemhasspellController extends AbstractController<Itemhasspell> {

    @NonNull
    private static final String CLASS_NAME = ItemhasspellController.class.getSimpleName();

    /**
     * Constructor.
     */
    public ItemhasspellController() {
        super(Itemhasspell.class);
    }

}
