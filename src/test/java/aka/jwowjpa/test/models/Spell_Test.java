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
 * Integration tests for SpellController.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@SuppressWarnings("javadoc")
public class Spell_Test {

    @Test
    public void Test_Id() {
        final Spell item = new Spell();

        final Long id = Long.valueOf(1);
        item.setId(id);

        Assert.assertEquals(id, item.getId());
    }

    @Test
    public void Test_Icon() {
        final Spell item = new Spell();

        final byte[] icon = TestHelper.readBytesFromFile("imgtest.jpg");
        item.setIcon(icon);

        Assert.assertEquals(icon, item.getIcon());
    }

    @Test
    public void Test_IdWoW() {
        final Spell item = new Spell();

        final Long idWoW = Long.valueOf(1);
        item.setIdWoW(idWoW);

        Assert.assertEquals(idWoW, item.getIdWoW());
    }

    @Test
    public void Test_Name() {
        final Spell item = new Spell();

        final String name = "Name";
        item.setName(name);

        Assert.assertEquals(name, item.getName());
    }

    @Test
    public void Test_NameEN() {
        final Spell item = new Spell();

        final String nameEN = "NameEN";
        item.setNameEN(nameEN);

        Assert.assertEquals(nameEN, item.getNameEN());
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

        final Spell item = new Spell();
        item.setMounts(list);

        Assert.assertEquals(1, item.getMounts().size());

        final Mount mount2 = new Mount();
        item.addMount(mount2);
        Assert.assertEquals(2, item.getMounts().size());

        item.removeMount(mount);
        Assert.assertEquals(1, item.getMounts().size());
    }
}