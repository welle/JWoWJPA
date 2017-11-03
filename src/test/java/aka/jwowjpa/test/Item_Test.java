package aka.jwowjpa.test;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jdt.annotation.NonNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import aka.jwowjpa.persistence.controllers.ItemController;
import aka.jwowjpa.persistence.models.Item;

/**
 * Integration tests for ItemController.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@Transactional
public class Item_Test {

    @Resource
    private ItemController itemController;

    /**
     * Setup the test.
     */
    @Before
    public void setUp() {
        // Insert item for tests
        for (int i = 0; i < 10; i++) {
            final Item item = new Item();
            item.setName("Item " + i);
            item.setNameEN("ItemEN " + i);
            item.setIdWoW(Long.valueOf(i));
            item.setQuality("" + (i + 2));
            this.itemController.insert(item);
        }
    }

    /**
     * Test getLatestItem method.
     */
    @Test
    public void Test_getLatestItem() {
        final Item item = this.itemController.getLatestItem();
        Assert.assertNotNull(item);
        Assert.assertEquals(Long.valueOf(9), item.getIdWoW());
    }

    /**
     * Test getItemByIdWoW method.
     */
    @Test
    public void Test_getItemByIdWoW() {
        final Long idWoW = Long.valueOf(5);
        final List<@NonNull Item> itemList = this.itemController.getItemByIdWoW(idWoW);
        Assert.assertNotNull(itemList);
        Assert.assertEquals(1, itemList.size());
        for (final Item item : itemList) {
            Assert.assertEquals(idWoW, item.getIdWoW());
            Assert.assertEquals("Item " + idWoW, item.getName());
            Assert.assertEquals("ItemEN " + idWoW, item.getNameEN());
            Assert.assertEquals("" + (idWoW.intValue() + 2), item.getQuality());
        }
    }

    /**
     * Test insert method.
     */
    @Test
    public void Test_insert() {
        Item item = new Item();
        item.setName("Item inserted");
        item.setNameEN("Item EN name");
        item.setIdWoW(Long.valueOf(15));
        item.setQuality("4");
        item = this.itemController.insert(item);

        Assert.assertNotNull(item);
    }

    /**
     * Test getItemById method.
     */
    @Test
    public void Test_getItemById() {
        Item item = new Item();
        item.setName("Item inserted");
        item.setNameEN("Item EN name");
        item.setIdWoW(Long.valueOf(15));
        item.setQuality("4");
        item = this.itemController.insert(item);

        final Item reloadedItem = this.itemController.getItemById(item.getId());
        Assert.assertNotNull(reloadedItem);
        Assert.assertEquals(item.getId(), reloadedItem.getId());
        Assert.assertEquals(item.getName(), reloadedItem.getName());
        Assert.assertEquals(item.getNameEN(), reloadedItem.getNameEN());
        Assert.assertEquals(item.getQuality(), reloadedItem.getQuality());
    }

    /**
     * Test getItems method.
     */
    @Test
    public void Test_getItems() {
        final List<@NonNull Item> totalItemList = this.itemController.getItems();
        Assert.assertNotNull(totalItemList);
        Assert.assertEquals(10, totalItemList.size());
    }

    /**
     * Test getItemByNameLike method.
     */
    @Test
    public void Test_getItemByNameLike() {
        Item item = new Item();
        item.setName("inserted Item");
        item.setNameEN("inserted ItemEN");
        item.setIdWoW(Long.valueOf(15));
        item.setQuality("4");
        item = this.itemController.insert(item);

        final List<@NonNull Item> totalItemList = this.itemController.getItems();
        final List<@NonNull Item> itemList = this.itemController.getItemByNameLike("Item");
        Assert.assertNotNull(itemList);
        Assert.assertEquals(10, itemList.size());
        Assert.assertNotEquals(totalItemList.size(), itemList.size());
        for (final Item currentItem : itemList) {
            Assert.assertTrue(currentItem.getName().startsWith("Item"));
        }
    }

    /**
     * Test update method.
     */
    @Test
    public void Test_update() {
        Item item = new Item();
        item.setName("Item inserted");
        item.setNameEN("Item EN name");
        item.setIdWoW(Long.valueOf(15));
        item.setQuality("4");
        item = this.itemController.insert(item);

        item.setName("Item inserted updated");
        item.setNameEN("Item EN name updated");
        item.setIdWoW(Long.valueOf(20));
        item.setQuality("1");
        item = this.itemController.update(item);

        final Item reloadedItem = this.itemController.getItemById(item.getId());
        Assert.assertNotNull(reloadedItem);
        Assert.assertEquals(item.getId(), reloadedItem.getId());
        Assert.assertEquals(item.getName(), reloadedItem.getName());
        Assert.assertEquals(item.getNameEN(), reloadedItem.getNameEN());
        Assert.assertEquals(item.getQuality(), reloadedItem.getQuality());
    }
}
