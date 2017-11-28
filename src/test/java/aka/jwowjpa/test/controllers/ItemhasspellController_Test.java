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

import aka.jwowjpa.persistence.controllers.ItemController;
import aka.jwowjpa.persistence.controllers.ItemhasspellController;
import aka.jwowjpa.persistence.controllers.SpellController;
import aka.jwowjpa.persistence.models.Item;
import aka.jwowjpa.persistence.models.ItemhasspellPK;
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
public class ItemhasspellController_Test {

    @Resource
    private ItemhasspellController itemhasspellController;
    @Resource
    private ItemController itemController;
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

        // Insert item for tests
        for (int i = 0; i < 10; i++) {
            final Item item = new Item();
            item.setName("Item " + i);
            item.setNameEN("ItemEN " + i);
            item.setIdWoW(Long.valueOf(i));
            item.setQuality("" + (i + 2));
            item.setIcon(this.icon);
            this.itemController.insert(item);
        }

        final List<@NonNull Item> itemList = this.itemController.getItemByIdWoW(Long.valueOf(1));
        final List<@NonNull Spell> spellList = this.spellController.getSpellByIdWoW(Long.valueOf(1));

        final ItemhasspellPK itemhasspell = new ItemhasspellPK();
        itemhasspell.setIdItem(itemList.get(0).getId());
        itemhasspell.setIdSpell(spellList.get(0).getId());
        this.itemhasspellController.insert(itemhasspell);
    }

    /**
     * Clean the test.
     */
    @After
    public void cleanUp() {
        this.itemhasspellController.deleteAll();
        this.spellController.deleteAll();
        this.itemController.deleteAll();
    }

    /**
     * Test getItemhasspellBySpellId method.
     */
    @Test
    public void getItemhasspellBySpellId() {
        final List<@NonNull Spell> spellList = this.spellController.getSpellByIdWoW(Long.valueOf(1));
        final List<ItemhasspellPK> result = this.itemhasspellController.getItemhasspellBySpellId(spellList.get(0).getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
    }

    /**
     * Test getItemhasspellByItemId method.
     */
    @Test
    public void getItemhasspellByItemId() {
        final List<@NonNull Item> itemList = this.itemController.getItemByIdWoW(Long.valueOf(1));
        final List<ItemhasspellPK> result = this.itemhasspellController.getItemhasspellByItemId(itemList.get(0).getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
    }

    /**
     * Test insert method.
     */
    @Test
    public void Test_insert() {
        final List<@NonNull Item> itemList = this.itemController.getItemByIdWoW(Long.valueOf(2));
        final List<@NonNull Spell> spellList = this.spellController.getSpellByIdWoW(Long.valueOf(1));

        ItemhasspellPK itemhasspell = new ItemhasspellPK();
        itemhasspell.setIdItem(itemList.get(0).getId());
        itemhasspell.setIdSpell(spellList.get(0).getId());
        itemhasspell = this.itemhasspellController.insert(itemhasspell);

        Assert.assertNotNull(itemhasspell);
    }

    /**
     * Test delete method.
     */
    @Test
    public void Test_delete() {
        final List<@NonNull Item> itemList = this.itemController.getItemByIdWoW(Long.valueOf(2));
        final List<@NonNull Spell> spellList = this.spellController.getSpellByIdWoW(Long.valueOf(1));

        ItemhasspellPK itemhasspell = new ItemhasspellPK();
        itemhasspell.setIdItem(itemList.get(0).getId());
        itemhasspell.setIdSpell(spellList.get(0).getId());
        itemhasspell = this.itemhasspellController.insert(itemhasspell);

        List<ItemhasspellPK> reloadedList = this.itemhasspellController.getItemhasspellBySpellId(spellList.get(0).getId());
        Assert.assertNotNull(reloadedList);
        Assert.assertEquals(2, reloadedList.size());

        this.itemhasspellController.delete(itemhasspell);
        reloadedList = this.itemhasspellController.getItemhasspellBySpellId(spellList.get(0).getId());
        Assert.assertNotNull(reloadedList);
        Assert.assertEquals(1, reloadedList.size());
    }

    /**
     * Test delete method.
     */
    @Test
    public void Test_deleteAll() {
        final Integer countDelete = this.itemhasspellController.deleteAll();
        Assert.assertEquals(Integer.valueOf(1), countDelete);
    }
}
