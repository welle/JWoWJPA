package aka.jwowjpa.persistence.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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

    private static final long serialVersionUID = 6235948467267475366L;
    @EmbeddedId
    private ItemhasspellPK id;

    public Itemhasspell() {
    }

    public ItemhasspellPK getId() {
        return this.id;
    }

    public void setId(final ItemhasspellPK id) {
        this.id = id;
    }

}