package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.jdt.annotation.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aka.jwowjpa.persistence.models.ItemhasspellPK;
import aka.jwowjpa.persistence.models.ItemhasspellPK_;

/**
 * Itemhasspell controller.
 *
 * @author charlottew
 */
@Repository
public class ItemhasspellController extends AbstractController<ItemhasspellPK> {

    @NonNull
    private static final String CLASS_NAME = ItemhasspellController.class.getSimpleName();

    /**
     * Constructor.
     */
    public ItemhasspellController() {
        super(ItemhasspellPK.class);
    }

    /**
     * Get Itemhasspell by the Spell id.
     *
     * @param id
     * @return List of Itemhasspell
     */
    @Transactional
    @NonNull
    public List<@NonNull ItemhasspellPK> getItemhasspellBySpellId(@NonNull final Long id) {
        List<@NonNull ItemhasspellPK> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<ItemhasspellPK> criteriaQuery = criteriaBuilder.createQuery(ItemhasspellPK.class);
            final Root<ItemhasspellPK> root = criteriaQuery.from(ItemhasspellPK.class);
            criteriaQuery.select(root);
            final Predicate idSpell = criteriaBuilder.equal(root.get(ItemhasspellPK_.idSpell), id);
            criteriaQuery.where(idSpell);
            final TypedQuery<ItemhasspellPK> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            getEntityManager().close();
        }

        return result;
    }

    /**
     * Get Itemhasspell by the Item id.
     *
     * @param id
     * @return List of Itemhasspell
     */
    @Transactional
    @NonNull
    public List<@NonNull ItemhasspellPK> getItemhasspellByItemId(@NonNull final Long id) {
        List<@NonNull ItemhasspellPK> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<ItemhasspellPK> criteriaQuery = criteriaBuilder.createQuery(ItemhasspellPK.class);
            final Root<ItemhasspellPK> root = criteriaQuery.from(ItemhasspellPK.class);
            criteriaQuery.select(root);
            final Predicate idItem = criteriaBuilder.equal(root.get(ItemhasspellPK_.idItem), id);
            criteriaQuery.where(idItem);
            final TypedQuery<ItemhasspellPK> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            getEntityManager().close();
        }

        return result;
    }

}
