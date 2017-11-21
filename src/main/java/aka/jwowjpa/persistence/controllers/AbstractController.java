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
import org.springframework.transaction.annotation.Transactional;

import aka.jwowjpa.context.ApplicationContext;

/**
 * Abstract controller to be extended by all controllers.
 *
 * @author charlottew
 * @param <T> Entity
 */
public class AbstractController<T> {

    @NonNull
    private final Class<T> typeParameterClass;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Constructor.
     *
     * @param typeParameterClass
     */
    public AbstractController(@NonNull final Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    /**
     * Get the entity manager.
     *
     * @return the entityManager
     * @throws IllegalStateException if EntityManager is null
     */
    @NonNull
    public EntityManager getEntityManager() throws IllegalStateException {
        final EntityManager result = this.entityManager;
        if (result == null) {
            throw new IllegalStateException();
        }

        return result;
    }

    /**
     * Get all item in table.
     *
     * @return List of T
     */
    @Transactional
    @NonNull
    public List<@NonNull T> getAll() {
        List<@NonNull T> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.typeParameterClass);
            final Root<T> root = criteriaQuery.from(this.typeParameterClass);
            criteriaQuery.select(root);
            final TypedQuery<T> q = this.entityManager.createQuery(criteriaQuery);
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
    public T insert(@NonNull final T item) {
        try {
            this.entityManager.persist(item);
            this.entityManager.refresh(item);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "AbstractController", "insert", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }

        return item;
    }

    /**
     * Update item.
     *
     * @param item
     */
    @Transactional
    public void update(@NonNull final T item) {
        try {
            this.entityManager.merge(item);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "AbstractController", "update", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }
    }

    /**
     * Delete item.
     *
     * @param item
     */
    @Transactional
    public void delete(@NonNull final T item) {
        try {
            this.entityManager.remove(item);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "AbstractController", "delete", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }
    }
}
