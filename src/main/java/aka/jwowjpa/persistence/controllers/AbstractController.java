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
import org.eclipse.persistence.exceptions.EntityManagerSetupException;
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
     * @throws EntityManagerSetupException if EntityManager is null
     */
    @NonNull
    public EntityManager getEntityManager() throws EntityManagerSetupException {
        final EntityManager result = this.entityManager;
        if (result == null) {
            throw new EntityManagerSetupException();
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
     * Insert new user.
     *
     * @param user
     * @return inserted user
     */
    @Transactional
    @NonNull
    public T insert(@NonNull final T user) {
        try {
            this.entityManager.persist(user);
            this.entityManager.refresh(user);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "UserController", "insert", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }

        return user;
    }

    /**
     * Update user.
     *
     * @param user
     */
    @Transactional
    public void update(@NonNull final T user) {
        try {
            this.entityManager.merge(user);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "UserController", "update", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }
    }

    /**
     * Update user.
     *
     * @param user
     * @return updated user
     */
    @Transactional
    @NonNull
    public T updateAndRefresh(@NonNull final T user) {
        try {
            this.entityManager.merge(user);
            this.entityManager.refresh(user);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "UserController", "update", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }

        return user;
    }

    /**
     * Delete user.
     *
     * @param user
     */
    @Transactional
    public void delete(@NonNull final T user) {
        try {
            this.entityManager.remove(user);
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "UserController", "delete", e.getMessage(), e);
        } finally {
            this.entityManager.close();
        }
    }
}
