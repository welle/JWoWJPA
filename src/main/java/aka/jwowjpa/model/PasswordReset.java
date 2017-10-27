package aka.jwowjpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the password_resets database table.
 *
 */
@Entity
@Table(name = "password_resets")
@NamedQuery(name = "PasswordReset.findAll", query = "SELECT p FROM PasswordReset p")
public class PasswordReset implements Serializable {
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    private String email;

    @Id
    private String token;

    public PasswordReset() {
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

}