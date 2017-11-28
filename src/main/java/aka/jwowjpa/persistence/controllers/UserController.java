package aka.jwowjpa.persistence.controllers;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aka.jwowjpa.persistence.models.User;

/**
 * User controller.
 *
 * @author charlottew
 */
@Repository
public class UserController extends AbstractController<User> {

    @NonNull
    private static final String CLASS_NAME = UserController.class.getSimpleName();

    /**
     * Constructor.
     */
    public UserController() {
        super(User.class);
    }

    /**
     * Get user by the id.
     *
     * @param id
     * @return user
     */
    @Transactional
    @Nullable
    public User getUserById(@NonNull final Long id) {
        User result = null;
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            final Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            final Predicate idEqual = criteriaBuilder.equal(root.get("id"), id);
            criteriaQuery.where(idEqual);
            final TypedQuery<User> q = getEntityManager().createQuery(criteriaQuery);
            result = q.getSingleResult();
        } finally {
            getEntityManager().close();
        }

        return result;
    }

    /**
     * Get user by the email.
     *
     * @param email
     * @return user
     */
    @Transactional
    @Nullable
    public User getUserByEmail(@NonNull final String email) {
        User result = null;
        try {
            final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            final Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            final Predicate idEqual = criteriaBuilder.equal(root.get("email"), email);
            criteriaQuery.where(idEqual);
            final TypedQuery<User> q = getEntityManager().createQuery(criteriaQuery);
            q.setMaxResults(1);
            result = q.getSingleResult();
        } finally {
            getEntityManager().close();
        }

        return result;
    }

}
