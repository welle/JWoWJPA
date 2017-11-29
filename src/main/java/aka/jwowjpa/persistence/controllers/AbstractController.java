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
import aka.jwowjpa.exceptions.EntityManagerException;

/**
 * Abstract controller to be extended by all controllers.
 *
 * @author charlottew
 * @param <T> Entity
 */
public class AbstractController<T> {

    @NonNull
    private static final String CLASS_NAME = AbstractController.class.getSimpleName();

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
     * @throws EntityManagerException if EntityManager is null
     */
    @NonNull
    public EntityManager getEntityManager() throws EntityManagerException {
        final EntityManager result = this.entityManager;
        if (result == null) {
            throw new EntityManagerException("Entity manager was not found.");
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
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.typeParameterClass);
            final Root<T> root = criteriaQuery.from(this.typeParameterClass);
            criteriaQuery.select(root);
            final TypedQuery<T> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getAll", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getAll", e.getMessage(), e);
            }
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
            getEntityManager().persist(item);
            getEntityManager().refresh(item);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "insert", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "insert", e.getMessage(), e);
            }
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
            getEntityManager().merge(item);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "update", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "update", e.getMessage(), e);
            }
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
            getEntityManager().remove(item);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "delete", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "delete", e.getMessage(), e);
            }
        }
    }

    /**
     * Delete all.
     *
     * @return number of deleted rows.
     */
    @Transactional
    @NonNull
    public Integer deleteAll() {
        Integer result = Integer.valueOf(0);
        try {
            String tableName = this.typeParameterClass.getSimpleName();
            tableName = Character.toUpperCase(tableName.charAt(0)) + tableName.substring(1);

            final int deletedCount = getEntityManager().createQuery("DELETE FROM " + tableName).executeUpdate();
            result = Integer.valueOf(deletedCount);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "deleteAll", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "deleteAll", e.getMessage(), e);
            }
        }

        return result;
    }
}
