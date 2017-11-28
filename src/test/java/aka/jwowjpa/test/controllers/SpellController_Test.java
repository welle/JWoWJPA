package aka.jwowjpa.test.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jdt.annotation.NonNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import aka.jwowjpa.persistence.controllers.SpellController;
import aka.jwowjpa.persistence.models.Spell;
import aka.jwowjpa.test.TestHelper;

/**
 * Integration tests for SpellController.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@Transactional
public class SpellController_Test {

    @Resource
    private SpellController spellController;

    private final byte[] icon = TestHelper.readBytesFromFile("imgtest.jpg");

    /**
     * Setup the test.
     */
    @Before
    public void setUp() {
        // Insert item for tests
        for (int i = 0; i < 10; i++) {
            final Spell spell = new Spell();
            spell.setName("Spell " + i);
            spell.setNameEN("SpellEN " + i);
            spell.setIdWoW(Long.valueOf(i));
            spell.setIcon(this.icon);
            this.spellController.insert(spell);
        }
    }

    /**
     * Clean the test.
     */
    @After
    public void cleanUp() {
        this.spellController.deleteAll();
    }

    /**
     * Test getLatestItem method.
     */
    @Test
    public void Test_getLatestItem() {
        final Spell spell = this.spellController.getLatestSpell();
        Assert.assertNotNull(spell);
        Assert.assertEquals(Long.valueOf(9), spell.getIdWoW());
    }

    /**
     * Test getItemByIdWoW method.
     */
    @Test
    public void Test_getItemByIdWoW() {
        final Long idWoW = Long.valueOf(5);
        final List<@NonNull Spell> spellList = this.spellController.getSpellByIdWoW(idWoW);
        Assert.assertNotNull(spellList);
        Assert.assertEquals(1, spellList.size());
        for (final Spell spell : spellList) {
            Assert.assertEquals(idWoW, spell.getIdWoW());
            Assert.assertNotNull(spell.getIcon());
            Assert.assertEquals("Spell " + idWoW, spell.getName());
            Assert.assertEquals("SpellEN " + idWoW, spell.getNameEN());
        }
    }

    /**
     * Test insert method.
     */
    @Test
    public void Test_insert() {
        Spell spell = new Spell();
        spell.setName("Spell inserted ");
        spell.setNameEN("Spell EN name");
        spell.setIdWoW(Long.valueOf(15));
        spell.setIcon(this.icon);
        spell = this.spellController.insert(spell);

        Assert.assertNotNull(spell);
    }

    /**
     * Test getItemById method.
     */
    @Test
    public void Test_getItemById() {
        Spell spell = new Spell();
        spell.setName("Spell inserted ");
        spell.setNameEN("Spell EN name");
        spell.setIdWoW(Long.valueOf(15));
        spell.setIcon(this.icon);
        spell = this.spellController.insert(spell);

        final Long id = spell.getId();
        Assert.assertNotNull(id);
        final Spell reloadedSpell = this.spellController.getSpellById(id);
        Assert.assertNotNull(reloadedSpell);
        Assert.assertEquals(spell.getId(), reloadedSpell.getId());
        Assert.assertEquals(spell.getName(), reloadedSpell.getName());
        Assert.assertEquals(spell.getNameEN(), reloadedSpell.getNameEN());
        Assert.assertNotNull(spell.getIcon());
    }

    /**
     * Test getItems method.
     */
    @Test
    public void Test_getItems() {
        final List<@NonNull Spell> totalSpellList = this.spellController.getAll();
        Assert.assertNotNull(totalSpellList);
        Assert.assertEquals(10, totalSpellList.size());
    }

    /**
     * Test getItemByNameLike method.
     */
    @Test
    public void Test_getItemByNameLike() {
        Spell spell = new Spell();
        spell.setName("inserted Spell");
        spell.setNameEN("inserted SpellEn");
        spell.setIdWoW(Long.valueOf(15));
        spell.setIcon(this.icon);
        spell = this.spellController.insert(spell);

        final List<@NonNull Spell> totalSpellList = this.spellController.getAll();
        final List<@NonNull Spell> spellList = this.spellController.getSpellByNameLike("Spell");
        Assert.assertNotNull(spellList);
        Assert.assertEquals(10, spellList.size());
        Assert.assertNotEquals(totalSpellList.size(), spellList.size());
        for (final Spell currentSpell : spellList) {
            Assert.assertTrue(currentSpell.getName().startsWith("Spell"));
            Assert.assertNotNull(currentSpell.getIcon());
        }
    }

    /**
     * Test update method.
     */
    @Test
    public void Test_update() {
        Spell spell = new Spell();
        spell.setName("Spell inserted ");
        spell.setNameEN("Spell EN name");
        spell.setIdWoW(Long.valueOf(15));
        spell.setIcon(this.icon);
        spell = this.spellController.insert(spell);

        spell.setName("Spell inserted updated");
        spell.setNameEN("Spell EN name updated");
        spell.setIdWoW(Long.valueOf(20));
        this.spellController.update(spell);

        final Long id = spell.getId();
        Assert.assertNotNull(id);
        final Spell reloadedSpell = this.spellController.getSpellById(id);
        Assert.assertNotNull(reloadedSpell);
        Assert.assertEquals(spell.getId(), reloadedSpell.getId());
        Assert.assertEquals(spell.getName(), reloadedSpell.getName());
        Assert.assertEquals(spell.getNameEN(), reloadedSpell.getNameEN());
        Assert.assertEquals(spell.getIcon(), reloadedSpell.getIcon());
        Assert.assertNotNull(reloadedSpell.getIcon());
    }
}
