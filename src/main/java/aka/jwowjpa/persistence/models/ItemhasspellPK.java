package aka.jwowjpa.persistence.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The primary key class for the itemhasspell database table.
 *
 */
@Entity
@Table(name = "itemhasspell")
@SuppressWarnings("javadoc")
public class ItemhasspellPK implements Serializable {

    private static final long serialVersionUID = -1533261880985708045L;

    @Id
    @Column(updatable = false)
    private Long idItem;

    @Column(updatable = false)
    private Long idSpell;

    public ItemhasspellPK() {
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

}
