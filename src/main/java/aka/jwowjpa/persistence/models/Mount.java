package aka.jwowjpa.persistence.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the mount database table.
 *
 */
@Entity
@Table(name = "mount")
public class Mount implements Serializable {

    private static final long serialVersionUID = -3926814206361328989L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] icon;

    private Long idCreature;

    private Boolean isAquatic;

    private Boolean isFlying;

    private Boolean isGround;

    private Boolean isJumping;

    private String name;

    private String nameEN;

    private String quality;

    // bi-directional many-to-one association to Item
    @ManyToOne
    @JoinColumn(name = "idItem")
    private Item item;

    // bi-directional many-to-one association to Spell
    @ManyToOne
    @JoinColumn(name = "idSpell")
    private Spell spell;

    public Mount() {
        // Nothing to do
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public byte[] getIcon() {
        return this.icon;
    }

    public void setIcon(final byte[] icon) {
        this.icon = icon;
    }

    public Long getIdCreature() {
        return this.idCreature;
    }

    public void setIdCreature(final Long idCreature) {
        this.idCreature = idCreature;
    }

    public Boolean getIsAquatic() {
        return this.isAquatic;
    }

    public void setIsAquatic(final Boolean isAquatic) {
        this.isAquatic = isAquatic;
    }

    public Boolean getIsFlying() {
        return this.isFlying;
    }

    public void setIsFlying(final Boolean isFlying) {
        this.isFlying = isFlying;
    }

    public Boolean getIsGround() {
        return this.isGround;
    }

    public void setIsGround(final Boolean isGround) {
        this.isGround = isGround;
    }

    public Boolean getIsJumping() {
        return this.isJumping;
    }

    public void setIsJumping(final Boolean isJumping) {
        this.isJumping = isJumping;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNameEN() {
        return this.nameEN;
    }

    public void setNameEN(final String nameEN) {
        this.nameEN = nameEN;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(final String quality) {
        this.quality = quality;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(final Item item) {
        this.item = item;
    }

    public Spell getSpell() {
        return this.spell;
    }

    public void setSpell(final Spell spell) {
        this.spell = spell;
    }

}