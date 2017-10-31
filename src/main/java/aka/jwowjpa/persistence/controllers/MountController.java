package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.eclipse.jdt.annotation.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aka.jwowjpa.context.ApplicationContext;
import aka.jwowjpa.persistence.models.Mount;

/**
 * Mount controller.
 *
 * @author charlottew
 */
@Repository
public class MountController {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get all mounts.
     *
     * @return List of mounts
     */
    @Transactional
    @NonNull
    public List<@NonNull Mount> getMounts() {
        List<@NonNull Mount> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<Mount> criteriaQuery = criteriaBuilder.createQuery(Mount.class);
            final Root<Mount> root = criteriaQuery.from(Mount.class);
            criteriaQuery.select(root);
            final TypedQuery<Mount> q = this.entityManager.createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            this.entityManager.close();
        }

        return result;
    }

    /**
     * Get latest mount.
     *
     * @return List of mount
     */
    @Transactional
    @NonNull
    public List<@NonNull Mount> getLatestMount() {
        List<@NonNull Mount> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<Mount> criteriaQuery = criteriaBuilder.createQuery(Mount.class);
            final Root<Mount> root = criteriaQuery.from(Mount.class);
            criteriaQuery.select(root);
            final Order orderBy = criteriaBuilder.desc(root.get("idCreature"));
            criteriaQuery.orderBy(orderBy);
            final TypedQuery<Mount> q = this.entityManager.createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            this.entityManager.close();
        }

        return result;
    }

    /**
     * Insert new mount.
     *
     * @param mount
     * @return inserted mount
     */
    @Transactional
    @NonNull
    public Mount insert(@NonNull final Mount mount) {
        try {
            this.entityManager.persist(mount);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "MountController", "insert", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }

        return mount;
    }

    /**
     * Update mount.
     *
     * @param mount
     * @return update mount
     */
    @Transactional
    @NonNull
    public Mount update(@NonNull final Mount mount) {
        try {
            this.entityManager.merge(mount);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "MountController", "update", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }

        return mount;
    }
}
