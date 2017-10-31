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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aka.jwowjpa.context.ApplicationContext;
import aka.jwowjpa.persistence.models.Item;

/**
 * Item controller.
 *
 * @author charlottew
 */
@Repository
public class ItemController {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get all items.
     *
     * @return List of items
     */
    @Transactional
    @NonNull
    public List<@NonNull Item> getItems() {
        List<@NonNull Item> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
            final Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.select(root);
            final TypedQuery<Item> q = this.entityManager.createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            this.entityManager.close();
        }

        return result;
    }

    /**
     * Get latest item.
     *
     * @return List of item
     */
    @Transactional
    @NonNull
    public List<@NonNull Item> getLatestItem() {
        List<@NonNull Item> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
            final Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.select(root);
            final Order orderBy = criteriaBuilder.desc(root.get("idWoW"));
            criteriaQuery.orderBy(orderBy);
            final TypedQuery<Item> q = this.entityManager.createQuery(criteriaQuery);
            q.setMaxResults(1);
            result = q.getResultList();
        } finally {
            this.entityManager.close();
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
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
            final Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.select(root);
            final Predicate nameLike = criteriaBuilder.like(root.get("name"), terms + "%");
            final Predicate nameENlike = criteriaBuilder.like(root.get("nameEN"), terms + "%");
            criteriaQuery.where(criteriaBuilder.or(nameLike, nameENlike));
            final TypedQuery<Item> q = this.entityManager.createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            this.entityManager.close();
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
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
            final Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.select(root);
            final Predicate idWoWEqual = criteriaBuilder.equal(root.get("idWoW"), idWoW);
            criteriaQuery.where(idWoWEqual);
            final TypedQuery<Item> q = this.entityManager.createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            this.entityManager.close();
        }

        return result;
    }

    /**
     * Insert new item.
     *
     * @param item
     * @return inserted item
     */
    @Transactional
    @NonNull
    public Item insert(@NonNull final Item item) {
        try {
            this.entityManager.persist(item);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "ItemController", "insert", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }

        return item;
    }

    /**
     * Update item.
     *
     * @param item
     * @return update item
     */
    @Transactional
    @NonNull
    public Item update(@NonNull final Item item) {
        try {
            this.entityManager.merge(item);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "ItemController", "update", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }

        return item;
    }
}
