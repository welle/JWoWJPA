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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
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
    @Nullable
    public Mount getLatestMount() {
        Mount result = null;
        try {
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<Mount> criteriaQuery = criteriaBuilder.createQuery(Mount.class);
            final Root<Mount> root = criteriaQuery.from(Mount.class);
            criteriaQuery.select(root);
            final Order orderBy = criteriaBuilder.desc(root.get("idCreature"));
            criteriaQuery.orderBy(orderBy);
            final TypedQuery<Mount> q = this.entityManager.createQuery(criteriaQuery);
            q.setMaxResults(1);
            result = q.getSingleResult();
        } finally {
            this.entityManager.close();
        }

        return result;
    }

    /**
     * Get mount by name "like".
     *
     * @param terms terms to be used in like query
     * @return List of mount
     */
    @Transactional
    @NonNull
    public List<@NonNull Mount> getMountByNameLike(@NonNull final String terms) {
        List<@NonNull Mount> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<Mount> criteriaQuery = criteriaBuilder.createQuery(Mount.class);
            final Root<Mount> root = criteriaQuery.from(Mount.class);
            criteriaQuery.select(root);
            final Predicate nameLike = criteriaBuilder.like(root.get("name"), terms + "%");
            final Predicate nameENlike = criteriaBuilder.like(root.get("nameEN"), terms + "%");
            criteriaQuery.where(criteriaBuilder.or(nameLike, nameENlike));
            final TypedQuery<Mount> q = this.entityManager.createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            this.entityManager.close();
        }

        return result;
    }

    /**
     * Get mount by the creature id.
     *
     * @param id
     * @return List of mount
     */
    @Transactional
    @NonNull
    public List<@NonNull Mount> getMountByIdCreature(@NonNull final Long id) {
        List<@NonNull Mount> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<Mount> criteriaQuery = criteriaBuilder.createQuery(Mount.class);
            final Root<Mount> root = criteriaQuery.from(Mount.class);
            criteriaQuery.select(root);
            final Predicate idWoWEqual = criteriaBuilder.equal(root.get("idCreature"), id);
            criteriaQuery.where(idWoWEqual);
            final TypedQuery<Mount> q = this.entityManager.createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            this.entityManager.close();
        }

        return result;
    }

    /**
     * Get mount by the id.
     *
     * @param id
     * @return mount
     */
    @Transactional
    @Nullable
    public Mount getMountById(@NonNull final Long id) {
        Mount result = null;
        try {
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<Mount> criteriaQuery = criteriaBuilder.createQuery(Mount.class);
            final Root<Mount> root = criteriaQuery.from(Mount.class);
            criteriaQuery.select(root);
            final Predicate idEqual = criteriaBuilder.equal(root.get("id"), id);
            criteriaQuery.where(idEqual);
            final TypedQuery<Mount> q = this.entityManager.createQuery(criteriaQuery);
            result = q.getSingleResult();
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
