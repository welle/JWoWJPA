package aka.jwowjpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the spell database table.
 * 
 */
@Entity
@NamedQuery(name="Spell.findAll", query="SELECT s FROM Spell s")
public class Spell implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(insertable=false, updatable=false)
	private Long id;

	private byte[] icon;

	private Long idWoW;

	private String name;

	private String nameEN;

	//bi-directional many-to-many association to Item
	@ManyToMany
	@JoinTable(
		name="itemhasspell"
		, joinColumns={
			@JoinColumn(name="idSpell")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idItem")
			}
		)
	private List<Item> items;

	//bi-directional many-to-one association to Mount
	@OneToMany(mappedBy="spell")
	private List<Mount> mounts;

	public Spell() {
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

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Mount> getMounts() {
		return this.mounts;
	}

	public void setMounts(List<Mount> mounts) {
		this.mounts = mounts;
	}

	public Mount addMount(Mount mount) {
		getMounts().add(mount);
		mount.setSpell(this);

		return mount;
	}

	public Mount removeMount(Mount mount) {
		getMounts().remove(mount);
		mount.setSpell(null);

		return mount;
	}

}