package aka.jwowjpa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mount database table.
 * 
 */
@Entity
@NamedQuery(name="Mount.findAll", query="SELECT m FROM Mount m")
public class Mount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(insertable=false, updatable=false)
	private Long id;

	private byte[] icon;

	private long idCreature;

	private Boolean isAquatic;

	private Boolean isFlying;

	private Boolean isGround;

	private Boolean isJumping;

	private String name;

	private String nameEN;

	private String quality;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="idItem")
	private Item item;

	//bi-directional many-to-one association to Spell
	@ManyToOne
	@JoinColumn(name="idSpell")
	private Spell spell;

	public Mount() {
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

	public long getIdCreature() {
		return this.idCreature;
	}

	public void setIdCreature(long idCreature) {
		this.idCreature = idCreature;
	}

	public Boolean getIsAquatic() {
		return this.isAquatic;
	}

	public void setIsAquatic(Boolean isAquatic) {
		this.isAquatic = isAquatic;
	}

	public Boolean getIsFlying() {
		return this.isFlying;
	}

	public void setIsFlying(Boolean isFlying) {
		this.isFlying = isFlying;
	}

	public Boolean getIsGround() {
		return this.isGround;
	}

	public void setIsGround(Boolean isGround) {
		this.isGround = isGround;
	}

	public Boolean getIsJumping() {
		return this.isJumping;
	}

	public void setIsJumping(Boolean isJumping) {
		this.isJumping = isJumping;
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

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Spell getSpell() {
		return this.spell;
	}

	public void setSpell(Spell spell) {
		this.spell = spell;
	}

}