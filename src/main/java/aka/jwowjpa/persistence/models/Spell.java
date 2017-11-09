package aka.jwowjpa.persistence.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * The persistent class for the spell database table.
 *
 */
@Component
@Entity
@Table(name = "spell")
public class Spell implements Serializable {

    private static final long serialVersionUID = 8601953332244028881L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] icon;

    private Long idWoW;

    private String name;

    private String nameEN;

    // bi-directional many-to-many association to Item
    @ManyToMany
    @JoinTable(name = "itemhasspell", joinColumns = {
            @JoinColumn(name = "idSpell")
    }, inverseJoinColumns = {
            @JoinColumn(name = "idItem")
    })
    private List<Item> items;

    // bi-directional many-to-one association to Mount
    @OneToMany(mappedBy = "spell")
    private List<Mount> mounts;

    public Spell() {
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

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(final List<Item> items) {
        this.items = items;
    }

    public List<Mount> getMounts() {
        return this.mounts;
    }

    public void setMounts(final List<Mount> mounts) {
        this.mounts = mounts;
    }

    public Mount addMount(final Mount mount) {
        getMounts().add(mount);
        mount.setSpell(this);

        return mount;
    }

    public Mount removeMount(final Mount mount) {
        getMounts().remove(mount);
        mount.setSpell(null);

        return mount;
    }

}