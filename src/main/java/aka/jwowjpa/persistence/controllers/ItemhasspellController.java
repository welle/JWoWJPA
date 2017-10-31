package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.eclipse.jdt.annotation.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @NonNull
    public List<@NonNull Itemhasspell> getItemhasspells() {
        List<@NonNull Itemhasspell> result = new ArrayList<>();
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
    @Transactional
    @NonNull
    public Itemhasspell insert(@NonNull final Itemhasspell itemhasspellPK) {
        try {
            this.entityManager.persist(itemhasspellPK);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "ItemhasspellController", "insert", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }

        return itemhasspellPK;
    }

    /**
     * Update itemhasspellPK.
     *
     * @param itemhasspellPK
     * @return update itemhasspellPK
     */
    @Transactional
    @NonNull
    public Itemhasspell update(@NonNull final Itemhasspell itemhasspellPK) {
        try {
            this.entityManager.merge(itemhasspellPK);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "ItemhasspellController", "update", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }

        return itemhasspellPK;
    }
}
