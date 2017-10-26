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
 * Spell controller.
 *
 * @author charlottew
 */
public class SpellController {
    private EntityManagerFactory emf;

    private EntityManager getEntityManager() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("JDumpWoW");
            this.emf.getCache().evictAll();
        }
        return this.emf.createEntityManager();
    }

    /**
     * Get all spell.
     *
     * @return List of spell
     */
    @NonNull
    public List<aka.jwowjpa.model.Spell> getSpells() {
        List<aka.jwowjpa.model.Spell> result = new ArrayList<>();
        final EntityManager em = getEntityManager();
        try {
            final javax.persistence.Query q = em.createQuery("select c from Spell c");
            result = q.getResultList();
        } finally {
            em.close();
        }

        return result;
    }

    /**
     * Get latest spell.
     *
     * @return List of spell
     */
    @NonNull
    public List<aka.jwowjpa.model.Spell> getLatestSpell() {
        List<aka.jwowjpa.model.Spell> result = new ArrayList<>();
        final EntityManager em = getEntityManager();
        try {
            final javax.persistence.Query q = em.createQuery("select c from Spell c order by c.idWoW desc");
            q.setMaxResults(1);
            result = q.getResultList();
        } finally {
            em.close();
        }

        return result;
    }

    /**
     * Get spell by id.
     *
     * @return List of spell
     */
    @NonNull
    public List<aka.jwowjpa.model.Spell> getSpellById(final int id) {
        List<aka.jwowjpa.model.Spell> result = new ArrayList<>();
        final EntityManager em = getEntityManager();
        try {
            final javax.persistence.Query q = em.createQuery("select c from Spell c Where idWoW = " + id);
            q.setMaxResults(1);
            result = q.getResultList();
        } finally {
            em.close();
        }

        return result;
    }

    /**
     * Insert new spell.
     *
     * @param spell
     * @return inserted spell
     */
    @NonNull
    public Spell insert(@NonNull final Spell spell) {
        try {
            final EntityManager em = getEntityManager();
            final EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(spell);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "SpellController", "insert", e.getMessage(), e);
        }

        return spell;
    }

    /**
     * Update spell.
     *
     * @param spell
     * @return update spell
     */
    @NonNull
    public Spell update(@NonNull final Spell spell) {
        try {
            final EntityManager em = getEntityManager();
            final EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(spell);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "SpellController", "update", e.getMessage(), e);
        }

        return spell;
    }
}
