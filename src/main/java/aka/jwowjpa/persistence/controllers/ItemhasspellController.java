package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.eclipse.jdt.annotation.NonNull;
import org.springframework.stereotype.Repository;

import aka.jwowjpa.context.ApplicationContext;
import aka.jwowjpa.persistence.models.Itemhasspell;

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
    public List<Itemhasspell> getItemhasspells() {
        List<Itemhasspell> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<Itemhasspell> criteriaQuery = criteriaBuilder.createQuery(Itemhasspell.class);
            final Root<Itemhasspell> root = criteriaQuery.from(Itemhasspell.class);
            criteriaQuery.select(root);
            final TypedQuery<Itemhasspell> q = this.entityManager.createQuery(criteriaQuery);
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
    public Itemhasspell insert(@NonNull final Itemhasspell itemhasspellPK) {
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
    public Itemhasspell update(@NonNull final Itemhasspell itemhasspellPK) {
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
