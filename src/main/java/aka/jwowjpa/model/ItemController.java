package aka.jwowjpa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.eclipse.jdt.annotation.NonNull;
import org.springframework.stereotype.Repository;

import aka.jwowjpa.context.ApplicationContext;

/**
 * Item controller.
 *
 * @author charlottew
 */
@Repository
public class ItemController {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Get all items.
     *
     * @return List of items
     */
    @Transactional
    @NonNull
    public List<aka.jwowjpa.model.Item> getItems() {
        List<aka.jwowjpa.model.Item> result = new ArrayList<>();
        final EntityManager em = getEntityManager();
        try {
            final javax.persistence.Query q = em.createQuery("select c from Item c");
            result = q.getResultList();
        } finally {
            em.close();
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
    public List<aka.jwowjpa.model.Item> getLatestItem() {
        List<aka.jwowjpa.model.Item> result = new ArrayList<>();
        final EntityManager em = getEntityManager();
        try {
            final javax.persistence.Query q = em.createQuery("select c from Item c order by c.idWoW desc");
            q.setMaxResults(1);
            result = q.getResultList();
        } finally {
            em.close();
        }

        return result;
    }

    /**
     * Get item by name "like".
     *
     * @return List of item
     */
    @Transactional
    @NonNull
    public List<@NonNull Item> getItemByNameLike(@NonNull final String terms) {
        List<@NonNull Item> result = new ArrayList<>();
        final EntityManager em = getEntityManager();
        try {
            final javax.persistence.Query q = em.createQuery("select c from Item c Where nameEN like '" + terms + "%' or name like '" + terms + "%'");
            result = q.getResultList();
        } finally {
            em.close();
        }

        return result;
    }

    /**
     * Get item by id.
     *
     * @return List of item
     */
    @Transactional
    @NonNull
    public List<aka.jwowjpa.model.Item> getItemById(@NonNull final Long id) {
        List<aka.jwowjpa.model.Item> result = new ArrayList<>();
        final EntityManager em = getEntityManager();
        try {
            final javax.persistence.Query q = em.createQuery("select c from Item c Where idWoW = " + id);
            q.setMaxResults(1);
            result = q.getResultList();
        } finally {
            em.close();
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
            final EntityManager em = getEntityManager();
            final EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(item);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "ItemController", "insert", e.getMessage(), e);
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
            final EntityManager em = getEntityManager();
            final EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(item);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "ItemController", "update", e.getMessage(), e);
        }

        return item;
    }
}
