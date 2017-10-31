package aka.jwowjpa.persistence.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * The persistent class for the itemhasspell database table.
 *
 */
@Component
@Entity
@Table(name = "itemhasspell")
public class Itemhasspell implements Serializable {

    private static final long serialVersionUID = -3355358069618912079L;

    @Id
    @Column(insertable = true, updatable = true)
    private Long idItem;

    @Column(insertable = true, updatable = true)
    private Long idSpell;

    public Itemhasspell() {
    }

    public Long getIdItem() {
        return this.idItem;
    }

    public void setIdItem(final Long idItem) {
        this.idItem = idItem;
    }

    public Long getIdSpell() {
        return this.idSpell;
    }

    public void setIdSpell(final Long idSpell) {
        this.idSpell = idSpell;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Itemhasspell)) {
            return false;
        }
        final Itemhasspell castOther = (Itemhasspell) other;
        return (this.idItem == castOther.idItem)
                && (this.idSpell == castOther.idSpell);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + (this.idItem == null ? 0 : this.idItem.intValue());
        hash = hash * prime + (this.idSpell == null ? 0 : this.idSpell.intValue());

        return hash;
    }

}