package aka.jwowjpa;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import aka.jwowjpa.persistence.controllers.ItemController;
import aka.jwowjpa.persistence.models.Item;

/**
 * Hello world!
 *
 */
public class App {

    private ItemController itemController;

    public void test() {
        final AbstractApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        this.itemController = context.getBean(ItemController.class);
        final List<Item> cars = this.itemController.getItems();
//      logger.info("Cars: " + cars.size());
        System.err.println("[App] main - " + cars.size());
    }

    public static void main(final String[] args) {
        System.out.println("Hello World!");

        final App app = new App();
        app.test();
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

    }
}
