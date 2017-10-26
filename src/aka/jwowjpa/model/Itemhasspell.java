package aka.jwowjpa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the itemhasspell database table.
 * 
 */
@Entity
@NamedQuery(name="Itemhasspell.findAll", query="SELECT i FROM Itemhasspell i")
public class Itemhasspell implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemhasspellPK id;

	public Itemhasspell() {
	}

	public ItemhasspellPK getId() {
		return this.id;
	}

	public void setId(ItemhasspellPK id) {
		this.id = id;
	}

}