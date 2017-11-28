package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.NoResultException;
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
import aka.jwowjpa.exceptions.EntityManagerException;
import aka.jwowjpa.persistence.models.Mount;
import aka.jwowjpa.persistence.models.Mount_;

/**
 * Mount controller.
 *
 * @author charlottew
 */
@Repository
public class MountController extends AbstractController<Mount> {

    @NonNull
    private static final String CLASS_NAME = MountController.class.getSimpleName();

    /**
     * Constructor.
     */
    public MountController() {
        super(Mount.class);
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
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Mount> criteriaQuery = criteriaBuilder.createQuery(Mount.class);
            final Root<Mount> root = criteriaQuery.from(Mount.class);
            criteriaQuery.select(root);
            final Order orderBy = criteriaBuilder.desc(root.get(Mount_.idCreature));
            criteriaQuery.orderBy(orderBy);
            final TypedQuery<Mount> q = getEntityManager().createQuery(criteriaQuery);
            q.setMaxResults(1);
            result = q.getSingleResult();
        } catch (final NoResultException e) {
            // No result found, nothing to do
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getLatestMount", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getLatestMount", e.getMessage(), e);
            }
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
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Mount> criteriaQuery = criteriaBuilder.createQuery(Mount.class);
            final Root<Mount> root = criteriaQuery.from(Mount.class);
            criteriaQuery.select(root);
            final Predicate nameLike = criteriaBuilder.like(root.get(Mount_.name), terms + "%");
            final Predicate nameENlike = criteriaBuilder.like(root.get(Mount_.nameEN), terms + "%");
            criteriaQuery.where(criteriaBuilder.or(nameLike, nameENlike));
            final TypedQuery<Mount> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getMountByNameLike", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getMountByNameLike", e.getMessage(), e);
            }
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
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Mount> criteriaQuery = criteriaBuilder.createQuery(Mount.class);
            final Root<Mount> root = criteriaQuery.from(Mount.class);
            criteriaQuery.select(root);
            final Predicate idWoWEqual = criteriaBuilder.equal(root.get(Mount_.idCreature), id);
            criteriaQuery.where(idWoWEqual);
            final TypedQuery<Mount> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getMountByIdCreature", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getMountByIdCreature", e.getMessage(), e);
            }
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
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Mount> criteriaQuery = criteriaBuilder.createQuery(Mount.class);
            final Root<Mount> root = criteriaQuery.from(Mount.class);
            criteriaQuery.select(root);
            final Predicate idEqual = criteriaBuilder.equal(root.get(Mount_.id), id);
            criteriaQuery.where(idEqual);
            final TypedQuery<Mount> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getSingleResult();
        } catch (final NoResultException e) {
            // No result found, nothing to do
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getMountById", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getMountById", e.getMessage(), e);
            }
        }

        return result;
    }
}
