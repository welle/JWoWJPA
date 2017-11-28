package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aka.jwowjpa.context.ApplicationContext;
import aka.jwowjpa.exceptions.EntityManagerException;
import aka.jwowjpa.persistence.models.Spell;
import aka.jwowjpa.persistence.models.Spell_;

/**
 * Spell controller.
 *
 * @author charlottew
 */
@Repository
public class SpellController extends AbstractController<Spell> {

    @NonNull
    private static final String CLASS_NAME = SpellController.class.getSimpleName();

    /**
     * Constructor.
     */
    public SpellController() {
        super(Spell.class);
    }

    /**
     * Get latest spell.
     *
     * @return List of spell
     */
    @Transactional
    @Nullable
    public Spell getLatestSpell() {
        Spell result = null;
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Spell> criteriaQuery = criteriaBuilder.createQuery(Spell.class);
            final Root<Spell> root = criteriaQuery.from(Spell.class);
            criteriaQuery.select(root);
            final Order orderBy = criteriaBuilder.desc(root.get(Spell_.idWoW));
            criteriaQuery.orderBy(orderBy);
            final TypedQuery<Spell> q = getEntityManager().createQuery(criteriaQuery);
            q.setMaxResults(1);
            result = q.getSingleResult();
        } catch (final NoResultException e) {
            // No result found, nothing to do
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getLatestSpell", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getLatestSpell", e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * Get spell by id.
     *
     * @param id
     * @return spell
     */
    @Transactional
    @Nullable
    public Spell getSpellById(@NonNull final Long id) {
        Spell result = null;
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Spell> criteriaQuery = criteriaBuilder.createQuery(Spell.class);
            final Root<Spell> root = criteriaQuery.from(Spell.class);
            criteriaQuery.select(root);
            final Predicate idEqual = criteriaBuilder.equal(root.get(Spell_.id), id);
            criteriaQuery.where(idEqual);
            final TypedQuery<Spell> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getSingleResult();
        } catch (final NoResultException e) {
            // No result found, nothing to do
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getSpellById", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getSpellById", e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * Get spell(s) by the id list.
     *
     * @param idList
     * @return list of spell(s)
     */
    @Transactional
    @NonNull
    public List<@NonNull Spell> getSpellByIdList(@NonNull final List<@NonNull Long> idList) {
        List<@NonNull Spell> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Spell> criteriaQuery = criteriaBuilder.createQuery(Spell.class);
            final Root<Spell> root = criteriaQuery.from(Spell.class);
            criteriaQuery.select(root);
            final Expression<Long> exp = root.get(Spell_.id);
            final Predicate idEqual = exp.in(idList);
            criteriaQuery.where(idEqual);
            final TypedQuery<Spell> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getSpellByIdList", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getSpellByIdList", e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * Get spell by id WoW.
     *
     * @param idWoW
     * @return List of spell
     */
    @Transactional
    @NonNull
    public List<@NonNull Spell> getSpellByIdWoW(@NonNull final Long idWoW) {
        List<@NonNull Spell> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Spell> criteriaQuery = criteriaBuilder.createQuery(Spell.class);
            final Root<Spell> root = criteriaQuery.from(Spell.class);
            criteriaQuery.select(root);
            final Predicate idWoWEqual = criteriaBuilder.equal(root.get(Spell_.idWoW), idWoW);
            criteriaQuery.where(idWoWEqual);
            final TypedQuery<Spell> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getSpellByIdWoW", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getSpellByIdWoW", e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * Get spell by name "like".
     *
     * @param terms terms to be used in like query
     * @return List of spell
     */
    @Transactional
    @NonNull
    public List<@NonNull Spell> getSpellByNameLike(@NonNull final String terms) {
        List<@NonNull Spell> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<Spell> criteriaQuery = criteriaBuilder.createQuery(Spell.class);
            final Root<Spell> root = criteriaQuery.from(Spell.class);
            criteriaQuery.select(root);
            final Predicate nameLike = criteriaBuilder.like(root.get(Spell_.name), terms + "%");
            final Predicate nameENlike = criteriaBuilder.like(root.get(Spell_.nameEN), terms + "%");
            criteriaQuery.where(criteriaBuilder.or(nameLike, nameENlike));
            final TypedQuery<Spell> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } catch (final EntityManagerException e) {
            ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getSpellByNameLike", e.getMessage(), e);
        } finally {
            try {
                getEntityManager().close();
            } catch (final EntityManagerException e) {
                ApplicationContext.getInstance().getLogger().logp(Level.SEVERE, CLASS_NAME, "getSpellByNameLike", e.getMessage(), e);
            }
        }

        return result;
    }
}
