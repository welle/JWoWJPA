package aka.jwowjpa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.eclipse.jdt.annotation.NonNull;

import aka.jwowjpa.context.ApplicationContext;

/**
 * Mount controller.
 *
 * @author charlottew
 */
public class MountController {
    private EntityManagerFactory emf;

    private EntityManager getEntityManager() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("JDumpWoW");
            this.emf.getCache().evictAll();
        }
        return this.emf.createEntityManager();
    }

    /**
     * Get all mounts.
     *
     * @return List of mounts
     */
    @NonNull
    public List<aka.jwowjpa.model.Mount> getMounts() {
        List<aka.jwowjpa.model.Mount> result = new ArrayList<>();
        final EntityManager em = getEntityManager();
        try {
            final javax.persistence.Query q = em.createQuery("select c from Mount c");
            result = q.getResultList();
        } finally {
            em.close();
        }

        return result;
    }

    /**
     * Get latest mount.
     *
     * @return List of mount
     */
    @NonNull
    public List<aka.jwowjpa.model.Mount> getLatestMount() {
        List<aka.jwowjpa.model.Mount> result = new ArrayList<>();
        final EntityManager em = getEntityManager();
        try {
            final javax.persistence.Query q = em.createQuery("select c from Mount c order by c.idCreature desc");
            q.setMaxResults(1);
            result = q.getResultList();
        } finally {
            em.close();
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
            final EntityManager em = getEntityManager();
            final EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(mount);
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
            final EntityManager em = getEntityManager();
            final EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(mount);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "MountController", "update", e.getMessage(), e);
        }

        return mount;
    }
}
