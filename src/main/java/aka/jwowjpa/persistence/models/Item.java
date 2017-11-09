package aka.jwowjpa.persistence.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * The persistent class for the item database table.
 *
 */
@Component
@Entity
@Table(name = "item")
@SuppressWarnings("javadoc")
public class Item implements Serializable {

    private static final long serialVersionUID = -3094292180961668413L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] icon;

    private Long idWoW;

    private String name;

    private String nameEN;

    private String quality;

    private String sourceId;

    private String sourceType;

    // bi-directional many-to-many association to Spell
    @ManyToMany(mappedBy = "items")
    private List<Spell> spells;

    // bi-directional many-to-one association to Mount
    @OneToMany(mappedBy = "item")
    private List<Mount> mounts;

    public Item() {
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

    public Long getIdWoW() {
        return this.idWoW;
    }

    public void setIdWoW(final Long idWoW) {
        this.idWoW = idWoW;
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

    public String getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(final String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(final String sourceType) {
        this.sourceType = sourceType;
    }

    public List<Spell> getSpells() {
        return this.spells;
    }

    public void setSpells(final List<Spell> spells) {
        this.spells = spells;
    }

    public List<Mount> getMounts() {
        return this.mounts;
    }

    public void setMounts(final List<Mount> mounts) {
        this.mounts = mounts;
    }

    public Mount addMount(final Mount mount) {
        this.mounts.add(mount);
        mount.setItem(this);

        return mount;
    }

    public Mount removeMount(final Mount mount) {
        this.mounts.remove(mount);
        mount.setItem(null);

        return mount;
    }
}
