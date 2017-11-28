package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;

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

import aka.jwowjpa.persistence.models.Item;

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
            final Order orderBy = criteriaBuilder.desc(root.get("idWoW"));
            criteriaQuery.orderBy(orderBy);
            final TypedQuery<Item> q = getEntityManager().createQuery(criteriaQuery);
            q.setMaxResults(1);
            result = q.getSingleResult();
        } finally {
            getEntityManager().close();
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
            final Predicate nameLike = criteriaBuilder.like(root.get("name"), terms + "%");
            final Predicate nameENlike = criteriaBuilder.like(root.get("nameEN"), terms + "%");
            criteriaQuery.where(criteriaBuilder.or(nameLike, nameENlike));
            final TypedQuery<Item> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            getEntityManager().close();
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
            final Predicate idWoWEqual = criteriaBuilder.equal(root.get("idWoW"), idWoW);
            criteriaQuery.where(idWoWEqual);
            final TypedQuery<Item> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            getEntityManager().close();
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
            final Predicate idEqual = criteriaBuilder.equal(root.get("id"), id);
            criteriaQuery.where(idEqual);
            final TypedQuery<Item> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getSingleResult();
        } finally {
            getEntityManager().close();
        }

        return result;
    }
}
