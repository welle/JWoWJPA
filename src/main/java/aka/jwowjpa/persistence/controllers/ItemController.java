package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aka.jwowjpa.context.ApplicationContext;
import aka.jwowjpa.exceptions.EntityManagerException;
import aka.jwowjpa.persistence.models.Item;
import aka.jwowjpa.persistence.models.Item_;

/**
 * Item controller.
 *
 * @author charlottew
 */
@Repository
public class ItemController extends AbstractController<Item> {

    @NonNull
    private static final String CLASS_NAME = ItemController.class.getSimpleName();

    /**
     * Constructor.
     */
    public ItemController() {
        super(Item.class);
    }

    /**
     * Get latest item.
     *
     * @return List of item
     */
    @Transactional
    @Nullable
    public Item getLatestItem() {
        Item result = null;
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
            final Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.select(root);
            final Order orderBy = criteriaBuilder.desc(root.get(Item_.idWoW));
            criteriaQuery.orderBy(orderBy);
            final TypedQuery<Item> q = getEntityManager().createQuery(criteriaQuery);
            q.setMaxResults(1);
            result = q.getSingleResult();
        } catch (final NoResultException e) {
            // No result found, nothing to do
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getLatestItem", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getLatestItem", e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * Get item by name "like".
     *
     * @param terms terms to be used in like query
     * @return List of item
     */
    @Transactional
    @NonNull
    public List<@NonNull Item> getItemByNameLike(@NonNull final String terms) {
        List<@NonNull Item> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
            final Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.select(root);
            final Predicate nameLike = criteriaBuilder.like(root.get(Item_.name), terms + "%");
            final Predicate nameENlike = criteriaBuilder.like(root.get(Item_.nameEN), terms + "%");
            criteriaQuery.where(criteriaBuilder.or(nameLike, nameENlike));
            final TypedQuery<Item> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getItemByNameLike", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getItemByNameLike", e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * Get item by the WoW id.
     *
     * @param idWoW
     * @return List of item
     */
    @Transactional
    @NonNull
    public List<@NonNull Item> getItemByIdWoW(@NonNull final Long idWoW) {
        List<@NonNull Item> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
            final Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.select(root);
            final Predicate idWoWEqual = criteriaBuilder.equal(root.get(Item_.idWoW), idWoW);
            criteriaQuery.where(idWoWEqual);
            final TypedQuery<Item> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getItemByIdWoW", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getItemByIdWoW", e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * Get item by the id.
     *
     * @param id
     * @return item
     */
    @Transactional
    @Nullable
    public Item getItemById(@NonNull final Long id) {
        Item result = null;
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
            final Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.select(root);
            final Predicate idEqual = criteriaBuilder.equal(root.get(Item_.id), id);
            criteriaQuery.where(idEqual);
            final TypedQuery<Item> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getSingleResult();
        } catch (final NoResultException e) {
            // No result found, nothing to do
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getItemById", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getItemById", e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * Get item(s) by the id list.
     *
     * @param idList
     * @return list of item(s)
     */
    @Transactional
    @NonNull
    public List<@NonNull Item> getItemByIdList(@NonNull final List<@NonNull Long> idList) {
        List<@NonNull Item> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
            final Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.select(root);
            final Expression<Long> exp = root.get(Item_.id);
            final Predicate idEqual = exp.in(idList);
            criteriaQuery.where(idEqual);
            final TypedQuery<Item> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getItemByIdList", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getItemByIdList", e.getMessage(), e);
            }
        }

        return result;
    }
}
