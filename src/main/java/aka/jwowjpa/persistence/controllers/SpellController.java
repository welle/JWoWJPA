package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aka.jwowjpa.persistence.models.Spell;

/**
 * Spell controller.
 *
 * @author charlottew
 */
@Repository
public class SpellController extends AbstractController<Spell> {

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
            final Order orderBy = criteriaBuilder.desc(root.get("idWoW"));
            criteriaQuery.orderBy(orderBy);
            final TypedQuery<Spell> q = getEntityManager().createQuery(criteriaQuery);
            q.setMaxResults(1);
            result = q.getSingleResult();
        } finally {
            getEntityManager().close();
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
            final Predicate idEqual = criteriaBuilder.equal(root.get("id"), id);
            criteriaQuery.where(idEqual);
            final TypedQuery<Spell> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getSingleResult();
        } finally {
            getEntityManager().close();
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
            final Predicate idWoWEqual = criteriaBuilder.equal(root.get("idWoW"), idWoW);
            criteriaQuery.where(idWoWEqual);
            final TypedQuery<Spell> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            getEntityManager().close();
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
            final Predicate nameLike = criteriaBuilder.like(root.get("name"), terms + "%");
            final Predicate nameENlike = criteriaBuilder.like(root.get("nameEN"), terms + "%");
            criteriaQuery.where(criteriaBuilder.or(nameLike, nameENlike));
            final TypedQuery<Spell> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            getEntityManager().close();
        }

        return result;
    }
}
