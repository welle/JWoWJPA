package aka.jwowjpa.test.models;

import java.io.Serializable;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aka.jwowjpa.persistence.models.User;

/**
 * Unit tests for User.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@SuppressWarnings("javadoc")
public class User_Test implements Serializable {

    private static final long serialVersionUID = -3538070214318629961L;

    @Test
    public void Test_Id() {
        final User user = new User();

        final Long id = Long.valueOf(1);
        user.setId(id);

        Assert.assertEquals(id, user.getId());
    }

    @Test
    public void Test_CreatedAt() {
        final User user = new User();

        final Date date = new Date();
        user.setCreatedAt(date);

        Assert.assertEquals(date, user.getCreatedAt());
    }

    @Test
    public void Test_UpdatedAt() {
        final User user = new User();

        final Date updatedAt = new Date();
        user.setUpdatedAt(updatedAt);

        Assert.assertEquals(updatedAt, user.getUpdatedAt());
    }

    @Test
    public void Test_Email() {
        final User user = new User();

        final String email = "email";
        user.setEmail(email);

        Assert.assertEquals(email, user.getEmail());
    }

    @Test
    public void Test_Code() {
        final User user = new User();

        final String code = "code";
        user.setCode(code);

        Assert.assertEquals(code, user.getCode());
    }

    @Test
    public void Test_Name() {
        final User user = new User();

        final String name = "name";
        user.setName(name);

        Assert.assertEquals(name, user.getName());
    }

    @Test
    public void Test_HashPassword() {
        final User user = new User();

        final String hashPassword = "hashPassword";
        user.setHashPassword(hashPassword);

        Assert.assertEquals(hashPassword, user.getHashPassword());
    }

    @Test
    public void Test_RememberToken() {
        final User user = new User();

        final String rememberToken = "rememberToken";
        user.setRememberToken(rememberToken);

        Assert.assertEquals(rememberToken, user.getRememberToken());
    }

}
