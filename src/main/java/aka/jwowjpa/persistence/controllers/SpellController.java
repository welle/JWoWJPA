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
import aka.jwowjpa.persistence.models.Spell;

/**
 * Spell controller.
 *
 * @author charlottew
 */
@Repository
public class SpellController {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get all spell.
     *
     * @return List of spell
     */
    @NonNull
    public List<aka.jwowjpa.persistence.models.Spell> getSpells() {
        List<aka.jwowjpa.persistence.models.Spell> result = new ArrayList<>();
        try {
            final javax.persistence.Query q = this.entityManager.createQuery("select c from Spell c");
            result = q.getResultList();
        } finally {
            this.entityManager.close();
        }

        return result;
    }

    /**
     * Get latest spell.
     *
     * @return List of spell
     */
    @NonNull
    public List<aka.jwowjpa.persistence.models.Spell> getLatestSpell() {
        List<aka.jwowjpa.persistence.models.Spell> result = new ArrayList<>();
        try {
            final javax.persistence.Query q = this.entityManager.createQuery("select c from Spell c order by c.idWoW desc");
            q.setMaxResults(1);
            result = q.getResultList();
        } finally {
            this.entityManager.close();
        }

        return result;
    }

    /**
     * Get spell by id.
     *
     * @return List of spell
     */
    @NonNull
    public List<aka.jwowjpa.persistence.models.Spell> getSpellById(final int id) {
        List<aka.jwowjpa.persistence.models.Spell> result = new ArrayList<>();
        try {
            final javax.persistence.Query q = this.entityManager.createQuery("select c from Spell c Where idWoW = " + id);
            q.setMaxResults(1);
            result = q.getResultList();
        } finally {
            this.entityManager.close();
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
            final EntityTransaction tx = this.entityManager.getTransaction();
            tx.begin();
            this.entityManager.persist(spell);
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
            final EntityTransaction tx = this.entityManager.getTransaction();
            tx.begin();
            this.entityManager.merge(spell);
            tx.commit();
        } catch (final Exception e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, "SpellController", "update", e.getMessage(), e);
        }

        return spell;
    }
}
