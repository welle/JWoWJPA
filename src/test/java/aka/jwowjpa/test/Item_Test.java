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
     * Test getItemByIdWoW method.
     */
    @Test
    public void Test_getItemById() {
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
}
