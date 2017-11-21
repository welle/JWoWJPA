package aka.jwowjpa.test.controllers;

import java.util.Date;
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

import aka.jwowjpa.persistence.controllers.UserController;
import aka.jwowjpa.persistence.models.User;

/**
 * Integration tests for UserController.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@Transactional
public class UserController_Test {

    @Resource
    private UserController userController;

    /**
     * Setup the test.
     */
    @Before
    public void setUp() {
        // Insert item for tests
        for (int i = 0; i < 10; i++) {
            final User user = new User();
            user.setName("User " + i);
            user.setEmail("Email " + i + "@test.com");
            user.setHashPassword("0ae29b3ee870d58005182d34b72f3c9b1afb8c4c6102a76d46ffb53557bbf5cca30873a95fecbcecc7a1aaceb18bdad11c3ecd291bf86484849dd7d26f319d68");
            this.userController.insert(user);
        }
    }

    /**
     * Test getUserByEmail method.
     */
    @Test
    public void Test_getUserByEmail() {
        final String email = "Email " + Long.valueOf(5) + "@test.com";
        final User user = this.userController.getUserByEmail(email);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getEmail());
        Assert.assertNotNull(user.getName());
        Assert.assertTrue(user.getEmail().contains("5") && user.getEmail().startsWith("Email "));
        Assert.assertTrue(user.getName().contains("5") && user.getName().startsWith("User "));
        Assert.assertEquals("0ae29b3ee870d58005182d34b72f3c9b1afb8c4c6102a76d46ffb53557bbf5cca30873a95fecbcecc7a1aaceb18bdad11c3ecd291bf86484849dd7d26f319d68", user.getHashPassword());
    }

    /**
     * Test insert method.
     */
    @Test
    public void Test_insert() {
        User user = new User();
        user.setName("User inserted ");
        user.setEmail("User Email");
        user.setHashPassword("0ae29b3ee870d58005182d34b72f3c9b1afb8c4c6102a76d46ffb53557bbf5cca30873a95fecbcecc7a1aaceb18bdad11c3ecd291bf86484849dd7d26f319d68");
        user = this.userController.insert(user);

        Assert.assertNotNull(user);
    }

    /**
     * Test getUserById method.
     */
    @Test
    public void Test_getUserById() {
        User user = new User();
        user.setName("User inserted ");
        user.setEmail("User Email");
        user.setHashPassword("0ae29b3ee870d58005182d34b72f3c9b1afb8c4c6102a76d46ffb53557bbf5cca30873a95fecbcecc7a1aaceb18bdad11c3ecd291bf86484849dd7d26f319d68");
        user = this.userController.insert(user);

        final Long id = user.getId();
        Assert.assertNotNull(id);
        final User reloadedUser = this.userController.getUserById(id);
        Assert.assertNotNull(reloadedUser);
        Assert.assertEquals(user.getId(), reloadedUser.getId());
        Assert.assertEquals(user.getName(), reloadedUser.getName());
        Assert.assertEquals(user.getEmail(), reloadedUser.getEmail());
    }

    /**
     * Test getUsers method.
     */
    @Test
    public void Test_getUsers() {
        final List<@NonNull User> totalUserList = this.userController.getAll();
        Assert.assertNotNull(totalUserList);
        Assert.assertEquals(10, totalUserList.size());
    }

    /**
     * Test delete method.
     */
    @Transactional
    @Test
    public void Test_delete() {
        List<@NonNull User> totalUserList = this.userController.getAll();
        Assert.assertNotNull(totalUserList);
        Assert.assertEquals(10, totalUserList.size());

        User user = new User();
        user.setName("User inserted ");
        user.setEmail("User Email");
        user.setHashPassword("0ae29b3ee870d58005182d34b72f3c9b1afb8c4c6102a76d46ffb53557bbf5cca30873a95fecbcecc7a1aaceb18bdad11c3ecd291bf86484849dd7d26f319d68");
        user = this.userController.insert(user);
        totalUserList = this.userController.getAll();
        Assert.assertNotNull(totalUserList);
        Assert.assertEquals(11, totalUserList.size());
        this.userController.delete(user);
        totalUserList = this.userController.getAll();
        Assert.assertNotNull(totalUserList);
        Assert.assertEquals(10, totalUserList.size());
    }

    /**
     * Test update method.
     *
     * @throws InterruptedException
     */
    @Test
    public void Test_update() throws InterruptedException {
        User user = new User();
        user.setName("User inserted ");
        user.setEmail("User Email");
        user.setHashPassword("0ae29b3ee870d58005182d34b72f3c9b1afb8c4c6102a76d46ffb53557bbf5cca30873a95fecbcecc7a1aaceb18bdad11c3ecd291bf86484849dd7d26f319d68");
        user = this.userController.insert(user);

        final Date createdDate = user.getCreatedAt();
        Assert.assertNotNull(createdDate);
        final Date updatedDate = user.getUpdatedAt();
        Assert.assertNotNull(updatedDate);

        Thread.sleep(2000);

        user.setName("User inserted updated");
        user.setEmail("User Email updated");
        this.userController.update(user);

        final Long id = user.getId();
        Assert.assertNotNull(id);
        final User reloadedUser = this.userController.getUserById(id);
        Assert.assertNotNull(reloadedUser);
        Assert.assertEquals(user.getId(), reloadedUser.getId());
        Assert.assertEquals(user.getName(), reloadedUser.getName());
        Assert.assertEquals(user.getEmail(), reloadedUser.getEmail());
        Assert.assertEquals(createdDate, reloadedUser.getCreatedAt());
        Assert.assertNotEquals(updatedDate, reloadedUser.getUpdatedAt());
        Assert.assertTrue(updatedDate.before(reloadedUser.getUpdatedAt()));
    }
}
