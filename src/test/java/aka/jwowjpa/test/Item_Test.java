package aka.jwowjpa.test;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import aka.jwowjpa.persistence.controllers.ItemController;
import aka.jwowjpa.persistence.models.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class Item_Test {

    private final Logger logger = Logger.getLogger("myLog");

    ItemController itemController;

    @Before
    public void setUp() {
        final ApplicationContext ctx = new ClassPathXmlApplicationContext("application-test-context.xml");

        this.itemController = ctx.getBean(ItemController.class);
    }

    @Test
    @DatabaseSetup("/dataset/itemRepository.xml")
    public void happyPathScenario() {
        final List<Item> cars = this.itemController.getItems();
    }

//    @Test
//    public void listCarsTest() {
//
//
//        final UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");
//
//        List<User> list = userManager.findAllUsers();
//        System.out.println("User count: " + list.size());
//
//        final User user = new User();
//        user.setUsername("johndoe");
//        user.setName("John Doe");
//        userManager.insertUser(user);
//        System.out.println("User inserted!");
//
//        list = userManager.findAllUsers();
//        System.out.println("User count: " + list.size());
//        final List<Item> cars = this.itemController.getItems();
////          logger.info("Cars: " + cars.size());
//        Assert.assertNotNull(cars);
//
//        final List<Item> test = this.itemController.getItemByNameLike("Bo");
//        for (final Item item : test) {
//            System.err.println("[Item_Test] listCarsTest - " + item.getName());
//        }
//        final List<Item> test2 = this.itemController.getItemById(Long.valueOf(-1));
//        for (final Item item : test2) {
//            System.err.println("[Item_Test] listCarsTest - " + item.getName());
//        }
//
//    }
//
//    @Test
//    public void getCarTest() {
////        final List<Item> car = this.carDao.getItemById(this.id);
//    }
}
