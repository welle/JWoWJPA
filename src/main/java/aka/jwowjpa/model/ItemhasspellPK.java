package aka.jwowjpa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the itemhasspell database table.
 * 
 */
@Embeddable
public class ItemhasspellPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idItem;

	@Column(insertable=false, updatable=false)
	private int idSpell;

	public ItemhasspellPK() {
	}
	public int getIdItem() {
		return this.idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public int getIdSpell() {
		return this.idSpell;
	}
	public void setIdSpell(int idSpell) {
		this.idSpell = idSpell;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemhasspellPK)) {
			return false;
		}
		ItemhasspellPK castOther = (ItemhasspellPK)other;
		return 
			(this.idItem == castOther.idItem)
			&& (this.idSpell == castOther.idSpell);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idItem;
		hash = hash * prime + this.idSpell;
		
		return hash;
	}
}