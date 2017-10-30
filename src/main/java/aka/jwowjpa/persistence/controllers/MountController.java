package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.eclipse.jdt.annotation.NonNull;
import org.springframework.stereotype.Repository;

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
    @NonNull
    public List<aka.jwowjpa.persistence.models.Mount> getMounts() {
        List<aka.jwowjpa.persistence.models.Mount> result = new ArrayList<>();
        try {
            final javax.persistence.Query q = this.entityManager.createQuery("select c from Mount c");
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
    @NonNull
    public List<aka.jwowjpa.persistence.models.Mount> getLatestMount() {
        List<aka.jwowjpa.persistence.models.Mount> result = new ArrayList<>();
        try {
            final javax.persistence.Query q = this.entityManager.createQuery("select c from Mount c order by c.idCreature desc");
            q.setMaxResults(1);
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
    @NonNull
    public Mount insert(@NonNull final Mount mount) {
        try {
            final EntityTransaction tx = this.entityManager.getTransaction();
            tx.begin();
            this.entityManager.persist(mount);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "MountController", "insert", e.getMessage(), e);
        }

        return mount;
    }

    /**
     * Update mount.
     *
     * @param mount
     * @return update mount
     */
    @NonNull
    public Mount update(@NonNull final Mount mount) {
        try {
            final EntityTransaction tx = this.entityManager.getTransaction();
            tx.begin();
            this.entityManager.merge(mount);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "MountController", "update", e.getMessage(), e);
        }

        return mount;
    }
}
