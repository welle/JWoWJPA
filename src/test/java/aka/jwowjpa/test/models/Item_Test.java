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
 * Unit tests for Item.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@SuppressWarnings("javadoc")
public class Item_Test {

    @Test
    public void Test_Id() {
        final Item item = new Item();

        final Long id = Long.valueOf(1);
        item.setId(id);

        Assert.assertEquals(id, item.getId());
    }

    @Test
    public void Test_Icon() {
        final Item item = new Item();

        final byte[] icon = TestHelper.readBytesFromFile("imgtest.jpg");
        item.setIcon(icon);

        Assert.assertEquals(icon, item.getIcon());
    }

    @Test
    public void Test_IdWoW() {
        final Item item = new Item();

        final Long idWoW = Long.valueOf(1);
        item.setIdWoW(idWoW);

        Assert.assertEquals(idWoW, item.getIdWoW());
    }

    @Test
    public void Test_Name() {
        final Item item = new Item();

        final String name = "Name";
        item.setName(name);

        Assert.assertEquals(name, item.getName());
    }

    @Test
    public void Test_NameEN() {
        final Item item = new Item();

        final String nameEN = "NameEN";
        item.setNameEN(nameEN);

        Assert.assertEquals(nameEN, item.getNameEN());
    }

    @Test
    public void Test_Quality() {
        final Item item = new Item();

        final String quality = "Quality";
        item.setQuality(quality);

        Assert.assertEquals(quality, item.getQuality());
    }

    @Test
    public void Test_SourceId() {
        final Item item = new Item();

        final String sourceId = "SourceId";
        item.setSourceId(sourceId);

        Assert.assertEquals(sourceId, item.getSourceId());
    }

    @Test
    public void Test_SourceType() {
        final Item item = new Item();

        final String sourceType = "SourceType";
        item.setSourceType(sourceType);

        Assert.assertEquals(sourceType, item.getSourceType());
    }

    @Test
    public void Test_Spells() {
        final Spell spell = new Spell();
        final List<Spell> list = new ArrayList<>();
        list.add(spell);

        final Item item = new Item();
        item.setSpells(list);

        Assert.assertEquals(1, item.getSpells().size());
    }

    @Test
    public void Test_Mounts() {
        final Mount mount = new Mount();
        final List<Mount> list = new ArrayList<>();
        list.add(mount);

        final Item item = new Item();
        item.setMounts(list);

        Assert.assertEquals(1, item.getMounts().size());

        final Mount mount2 = new Mount();
        item.addMount(mount2);
        Assert.assertEquals(2, item.getMounts().size());

        item.removeMount(mount);
        Assert.assertEquals(1, item.getMounts().size());
    }
}