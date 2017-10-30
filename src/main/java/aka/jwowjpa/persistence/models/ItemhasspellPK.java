package aka.jwowjpa.persistence.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

/**
 * The primary key class for the itemhasspell database table.
 *
 */
@Component
@Embeddable
public class ItemhasspellPK implements Serializable {

    private static final long serialVersionUID = -3355358069618912079L;

    @Column(insertable = false, updatable = false)
    private int idItem;

    @Column(insertable = false, updatable = false)
    private int idSpell;

    public ItemhasspellPK() {
    }

    public int getIdItem() {
        return this.idItem;
    }

    public void setIdItem(final int idItem) {
        this.idItem = idItem;
    }

    public int getIdSpell() {
        return this.idSpell;
    }

    public void setIdSpell(final int idSpell) {
        this.idSpell = idSpell;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ItemhasspellPK)) {
            return false;
        }
        final ItemhasspellPK castOther = (ItemhasspellPK) other;
        return (this.idItem == castOther.idItem)
                && (this.idSpell == castOther.idSpell);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.idItem;
        hash = hash * prime + this.idSpell;

        return hash;
    }
}