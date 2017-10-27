package aka.jwowjpa.test;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aka.jwowjpa.model.Item;
import aka.jwowjpa.model.ItemController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/testApplicationContext.xml" })
public class Item_Test {

    @Autowired
    private ItemController carDao;
    private final Logger logger = Logger.getLogger("myLog");
    private Long id;

    @Before
    public void init() {
//          carNumber = carDao.getCars().size();
        this.id = 1L;
    }

    @Test
    public void listCarsTest() {
        final List<Item> cars = this.carDao.getItems();
//          logger.info("Cars: " + cars.size());
        Assert.assertNotNull(cars);
        Assert.assertEquals(15, cars.size());
    }

    @Test
    public void getCarTest() {
        final List<Item> car = this.carDao.getItemById(this.id);
    }
}
