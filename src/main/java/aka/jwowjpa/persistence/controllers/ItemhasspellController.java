package aka.jwowjpa.persistence.controllers;

import org.springframework.stereotype.Repository;

import aka.jwowjpa.persistence.models.Itemhasspell;

/**
 * Itemhasspell controller.
 *
 * @author charlottew
 */
@Repository
public class ItemhasspellController extends AbstractController<Itemhasspell> {

    /**
     * Constructor.
     */
    public ItemhasspellController() {
        super(Itemhasspell.class);
    }

}
