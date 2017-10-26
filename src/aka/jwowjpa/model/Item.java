package aka.jwowjpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(insertable=false, updatable=false)
	private Long id;

	private byte[] icon;

	private Long idWoW;

	private String name;

	private String nameEN;

	private String quality;

	private String sourceId;

	private String sourceType;

	//bi-directional many-to-many association to Spell
	@ManyToMany(mappedBy="items")
	private List<Spell> spells;

	//bi-directional many-to-one association to Mount
	@OneToMany(mappedBy="item")
	private List<Mount> mounts;

	public Item() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getIcon() {
		return this.icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public Long getIdWoW() {
		return this.idWoW;
	}

	public void setIdWoW(Long idWoW) {
		this.idWoW = idWoW;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEN() {
		return this.nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public List<Spell> getSpells() {
		return this.spells;
	}

	public void setSpells(List<Spell> spells) {
		this.spells = spells;
	}

	public List<Mount> getMounts() {
		return this.mounts;
	}

	public void setMounts(List<Mount> mounts) {
		this.mounts = mounts;
	}

	public Mount addMount(Mount mount) {
		getMounts().add(mount);
		mount.setItem(this);

		return mount;
	}

	public Mount removeMount(Mount mount) {
		getMounts().remove(mount);
		mount.setItem(null);

		return mount;
	}

}