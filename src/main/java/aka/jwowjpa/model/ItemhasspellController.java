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
 * Itemhasspell controller.
 *
 * @author charlottew
 */
public class ItemhasspellController {
    private EntityManagerFactory emf;

    private EntityManager getEntityManager() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("JDumpWoW");
            this.emf.getCache().evictAll();
        }
        return this.emf.createEntityManager();
    }

    /**
     * Get all Itemhasspell.
     *
     * @return List of Itemhasspell
     */
    @NonNull
    public List<ItemhasspellPK> getItemhasspells() {
        List<ItemhasspellPK> result = new ArrayList<>();
        final EntityManager em = getEntityManager();
        try {
            final javax.persistence.Query q = em.createQuery("select c from Itemhasspell c");
            result = q.getResultList();
        } finally {
            em.close();
        }

        return result;
    }

    /**
     * Insert new itemhasspellPK.
     *
     * @param itemhasspellPK
     * @return inserted itemhasspellPK
     */
    @NonNull
    public ItemhasspellPK insert(@NonNull final ItemhasspellPK itemhasspellPK) {
        try {
            final EntityManager em = getEntityManager();
            final EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(itemhasspellPK);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "ItemhasspellController", "insert", e.getMessage(), e);
        }

        return itemhasspellPK;
    }

    /**
     * Update itemhasspellPK.
     *
     * @param itemhasspellPK
     * @return update itemhasspellPK
     */
    @NonNull
    public ItemhasspellPK update(@NonNull final ItemhasspellPK itemhasspellPK) {
        try {
            final EntityManager em = getEntityManager();
            final EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(itemhasspellPK);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "ItemhasspellController", "update", e.getMessage(), e);
        }

        return itemhasspellPK;
    }
}
