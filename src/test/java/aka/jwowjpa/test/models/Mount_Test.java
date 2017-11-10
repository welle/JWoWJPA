package aka.jwowjpa.test.models;

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
 * Integration tests for Mount.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@SuppressWarnings("javadoc")
public class Mount_Test {

    @Test
    public void Test_Id() {
        final Mount mount = new Mount();

        final Long id = Long.valueOf(1);
        mount.setId(id);

        Assert.assertEquals(id, mount.getId());
    }

    @Test
    public void Test_Icon() {
        final Mount mount = new Mount();

        final byte[] icon = TestHelper.readBytesFromFile("imgtest.jpg");
        mount.setIcon(icon);

        Assert.assertEquals(icon, mount.getIcon());
    }

    @Test
    public void Test_IdCreature() {
        final Mount mount = new Mount();

        final Long idCreature = Long.valueOf(1);
        mount.setIdCreature(idCreature);

        Assert.assertEquals(idCreature, mount.getIdCreature());
    }

    @Test
    public void Test_Quality() {
        final Mount mount = new Mount();

        final String quality = "Quality";
        mount.setQuality(quality);

        Assert.assertEquals(quality, mount.getQuality());
    }

    @Test
    public void Test_IsAquatic() {
        final Mount mount = new Mount();

        final Boolean isAquatic = Boolean.TRUE;
        mount.setIsAquatic(isAquatic);

        Assert.assertEquals(isAquatic, mount.getIsAquatic());
    }

    @Test
    public void Test_IsFlying() {
        final Mount mount = new Mount();

        final Boolean isFlying = Boolean.TRUE;
        mount.setIsFlying(isFlying);

        Assert.assertEquals(isFlying, mount.getIsFlying());
    }

    @Test
    public void Test_IsGround() {
        final Mount mount = new Mount();

        final Boolean isGround = Boolean.TRUE;
        mount.setIsGround(isGround);

        Assert.assertEquals(isGround, mount.getIsGround());
    }

    @Test
    public void Test_Item() {
        final Mount mount = new Mount();

        final Item item = new Item();
        mount.setItem(item);

        Assert.assertEquals(item, mount.getItem());
    }

    @Test
    public void Test_IsJumping() {
        final Mount mount = new Mount();

        final Boolean isJumping = Boolean.TRUE;
        mount.setIsJumping(isJumping);

        Assert.assertEquals(isJumping, mount.getIsJumping());
    }

    @Test
    public void Test_Name() {
        final Mount mount = new Mount();

        final String name = "Name";
        mount.setName(name);

        Assert.assertEquals(name, mount.getName());
    }

    @Test
    public void Test_NameEN() {
        final Mount mount = new Mount();

        final String nameEN = "NameEN";
        mount.setNameEN(nameEN);

        Assert.assertEquals(nameEN, mount.getNameEN());
    }

    @Test
    public void Test_Spells() {
        final Spell spell = new Spell();
        final Mount mount = new Mount();
        mount.setSpell(spell);

        Assert.assertEquals(spell, mount.getSpell());
    }
}