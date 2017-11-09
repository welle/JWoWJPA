package aka.jwowjpa.test.controllers;

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
import aka.jwowjpa.test.TestHelper;

/**
 * Integration tests for ItemController.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@Transactional
public class ItemController_Test {

    @Resource
    private ItemController itemController;

    private final byte[] icon = TestHelper.readBytesFromFile("imgtest.jpg");

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
            item.setIcon(this.icon);
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
            Assert.assertEquals(this.icon, item.getIcon());
            Assert.assertNotNull(item.getIcon());
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
        item.setIcon(this.icon);
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
        item.setIcon(this.icon);
        item = this.itemController.insert(item);

        final Long id = item.getId();
        Assert.assertNotNull(id);
        final Item reloadedItem = this.itemController.getItemById(id);
        Assert.assertNotNull(reloadedItem);
        Assert.assertEquals(item.getId(), reloadedItem.getId());
        Assert.assertEquals(item.getName(), reloadedItem.getName());
        Assert.assertEquals(item.getNameEN(), reloadedItem.getNameEN());
        Assert.assertEquals(item.getQuality(), reloadedItem.getQuality());
        Assert.assertEquals(this.icon, item.getIcon());
        Assert.assertNotNull(item.getIcon());
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
        item.setIcon(this.icon);
        item = this.itemController.insert(item);

        final List<@NonNull Item> totalItemList = this.itemController.getItems();
        final List<@NonNull Item> itemList = this.itemController.getItemByNameLike("Item");
        Assert.assertNotNull(itemList);
        Assert.assertEquals(10, itemList.size());
        Assert.assertNotEquals(totalItemList.size(), itemList.size());
        for (final Item currentItem : itemList) {
            Assert.assertTrue(currentItem.getName().startsWith("Item"));
            Assert.assertEquals(this.icon, item.getIcon());
            Assert.assertNotNull(currentItem.getIcon());
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
        item.setIcon(this.icon);
        item = this.itemController.insert(item);

        item.setName("Item inserted updated");
        item.setNameEN("Item EN name updated");
        item.setIdWoW(Long.valueOf(20));
        item.setQuality("1");
        item = this.itemController.update(item);

        final Long id = item.getId();
        Assert.assertNotNull(id);
        final Item reloadedItem = this.itemController.getItemById(id);
        Assert.assertNotNull(reloadedItem);
        Assert.assertEquals(item.getId(), reloadedItem.getId());
        Assert.assertEquals(item.getName(), reloadedItem.getName());
        Assert.assertEquals(item.getNameEN(), reloadedItem.getNameEN());
        Assert.assertEquals(item.getQuality(), reloadedItem.getQuality());
        Assert.assertEquals(item.getIcon(), reloadedItem.getIcon());
        Assert.assertNotNull(reloadedItem.getIcon());
    }
}
