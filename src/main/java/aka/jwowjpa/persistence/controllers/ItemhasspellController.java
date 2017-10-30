package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.eclipse.jdt.annotation.NonNull;
import org.springframework.stereotype.Repository;

import aka.jwowjpa.context.ApplicationContext;
import aka.jwowjpa.persistence.models.ItemhasspellPK;

/**
 * Itemhasspell controller.
 *
 * @author charlottew
 */
@Repository
public class ItemhasspellController {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get all Itemhasspell.
     *
     * @return List of Itemhasspell
     */
    @NonNull
    public List<ItemhasspellPK> getItemhasspells() {
        List<ItemhasspellPK> result = new ArrayList<>();
        try {
            final javax.persistence.Query q = this.entityManager.createQuery("select c from Itemhasspell c");
            result = q.getResultList();
        } finally {
            this.entityManager.close();
        }

        return result;
    }

    /**
     * Insert new itemhasspellPK.
     *
     * @param itemhasspellPK
     * @return inserted itemhasspellPK
     */
    @NonNull
    public ItemhasspellPK insert(@NonNull final ItemhasspellPK itemhasspellPK) {
        try {
            final EntityTransaction tx = this.entityManager.getTransaction();
            tx.begin();
            this.entityManager.persist(itemhasspellPK);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "ItemhasspellController", "insert", e.getMessage(), e);
        }

        return itemhasspellPK;
    }

    /**
     * Update itemhasspellPK.
     *
     * @param itemhasspellPK
     * @return update itemhasspellPK
     */
    @NonNull
    public ItemhasspellPK update(@NonNull final ItemhasspellPK itemhasspellPK) {
        try {
            final EntityTransaction tx = this.entityManager.getTransaction();
            tx.begin();
            this.entityManager.merge(itemhasspellPK);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "ItemhasspellController", "update", e.getMessage(), e);
        }

        return itemhasspellPK;
    }
}
