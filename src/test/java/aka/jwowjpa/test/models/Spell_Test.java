package aka.jwowjpa.test.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aka.jwowjpa.persistence.models.Item;
import aka.jwowjpa.persistence.models.Mount;
import aka.jwowjpa.persistence.models.Spell;
import aka.jwowjpa.test.TestHelper;

/**
 * Unit tests for Spell.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@SuppressWarnings("javadoc")
public class Spell_Test {

    @Test
    public void Test_Id() {
        final Spell spell = new Spell();

        final Long id = Long.valueOf(1);
        spell.setId(id);

        Assert.assertEquals(id, spell.getId());
    }

    @Test
    public void Test_Icon() {
        final Spell spell = new Spell();

        final byte[] icon = TestHelper.readBytesFromFile("imgtest.jpg");
        spell.setIcon(icon);

        Assert.assertEquals(icon, spell.getIcon());
    }

    @Test
    public void Test_IdWoW() {
        final Spell spell = new Spell();

        final Long idWoW = Long.valueOf(1);
        spell.setIdWoW(idWoW);

        Assert.assertEquals(idWoW, spell.getIdWoW());
    }

    @Test
    public void Test_Name() {
        final Spell spell = new Spell();

        final String name = "Name";
        spell.setName(name);

        Assert.assertEquals(name, spell.getName());
    }

    @Test
    public void Test_NameEN() {
        final Spell spell = new Spell();

        final String nameEN = "NameEN";
        spell.setNameEN(nameEN);

        Assert.assertEquals(nameEN, spell.getNameEN());
    }

    @Test
    public void Test_Spells() {
        final Item item = new Item();
        final List<Item> list = new ArrayList<>();
        list.add(item);

        final Spell spell = new Spell();
        spell.setItems(list);

        Assert.assertEquals(1, spell.getItems().size());
    }

    @Test
    public void Test_Mounts() {
        final Mount mount = new Mount();
        final List<Mount> list = new ArrayList<>();
        list.add(mount);

        final Spell spell = new Spell();
        spell.setMounts(list);

        Assert.assertEquals(1, spell.getMounts().size());

        final Mount mount2 = new Mount();
        spell.addMount(mount2);
        Assert.assertEquals(2, spell.getMounts().size());

        spell.removeMount(mount);
        Assert.assertEquals(1, spell.getMounts().size());
    }
}