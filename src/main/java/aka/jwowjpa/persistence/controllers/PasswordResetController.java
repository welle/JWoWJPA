package aka.jwowjpa.persistence.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aka.jwowjpa.persistence.models.PasswordReset;
import aka.jwowjpa.persistence.models.PasswordReset_;

/**
 * Item controller.
 *
 * @author charlottew
 */
@Repository
public class PasswordResetController extends AbstractController<PasswordReset> {

    @NonNull
    private static final String CLASS_NAME = PasswordResetController.class.getSimpleName();

    /**
     * Constructor.
     */
    public PasswordResetController() {
        super(PasswordReset.class);
    }

    /**
     * Get PasswordReset by the id.
     *
     * @param id
     * @return PasswordReset
     */
    @Transactional
    @Nullable
    public PasswordReset getPasswordResetById(@NonNull final Long id) {
        PasswordReset result = null;
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<PasswordReset> criteriaQuery = criteriaBuilder.createQuery(PasswordReset.class);
            final Root<PasswordReset> root = criteriaQuery.from(PasswordReset.class);
            criteriaQuery.select(root);
            final Predicate idEqual = criteriaBuilder.equal(root.get(PasswordReset_.id), id);
            criteriaQuery.where(idEqual);
            final TypedQuery<PasswordReset> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getSingleResult();
        } catch (final NoResultException e) {
            // No result found, nothing to do
        } finally {
            getEntityManager().close();
        }

        return result;
    }

    /**
     * Get PasswordReset by the email and token.
     *
     * @param email
     * @param token
     * @return PasswordReset
     */
    @Transactional
    @Nullable
    public PasswordReset getPasswordResetByEmailPassword(@NonNull final String email, @NonNull final String token) {
        PasswordReset result = null;
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<PasswordReset> criteriaQuery = criteriaBuilder.createQuery(PasswordReset.class);
            final Root<PasswordReset> root = criteriaQuery.from(PasswordReset.class);
            criteriaQuery.select(root);
            final Predicate emailEqual = criteriaBuilder.equal(root.get(PasswordReset_.email), email);
            final Predicate tokenEqual = criteriaBuilder.equal(root.get(PasswordReset_.token), token);
            final Predicate andPredicate = criteriaBuilder.and(emailEqual, tokenEqual);
            criteriaQuery.where(andPredicate);
            final TypedQuery<PasswordReset> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getSingleResult();
        } catch (final NoResultException e) {
            // No result found, nothing to do
        } finally {
            getEntityManager().close();
        }

        return result;
    }

    /**
     * Get PasswordReset(s) by the email.
     *
     * @param email
     * @return PasswordReset
     */
    @Transactional
    @NonNull
    public List<@NonNull PasswordReset> getPasswordResetByEmail(@NonNull final String email) {
        List<@NonNull PasswordReset> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<PasswordReset> criteriaQuery = criteriaBuilder.createQuery(PasswordReset.class);
            final Root<PasswordReset> root = criteriaQuery.from(PasswordReset.class);
            criteriaQuery.select(root);
            final Predicate emailEqual = criteriaBuilder.equal(root.get(PasswordReset_.email), email);
            criteriaQuery.where(emailEqual);
            final TypedQuery<PasswordReset> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } catch (final NoResultException e) {
            // No result found, nothing to do
        } finally {
            getEntityManager().close();
        }

        return result;
    }

    /**
     * Get PasswordReset(s) by the id list.
     *
     * @param idList
     * @return list of PasswordReset(s)
     */
    @Transactional
    @NonNull
    public List<@NonNull PasswordReset> getPasswordResetByIdList(@NonNull final List<@NonNull Long> idList) {
        List<@NonNull PasswordReset> result = new ArrayList<>();
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<PasswordReset> criteriaQuery = criteriaBuilder.createQuery(PasswordReset.class);
            final Root<PasswordReset> root = criteriaQuery.from(PasswordReset.class);
            criteriaQuery.select(root);
            final Expression<Long> exp = root.get(PasswordReset_.id);
            final Predicate idEqual = exp.in(idList);
            criteriaQuery.where(idEqual);
            final TypedQuery<PasswordReset> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getResultList();
        } finally {
            getEntityManager().close();
        }

        return result;
    }
}
