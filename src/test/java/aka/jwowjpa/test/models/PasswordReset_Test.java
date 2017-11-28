package aka.jwowjpa.test.models;

import java.io.Serializable;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aka.jwowjpa.persistence.models.PasswordReset;

/**
 * Unit tests for PasswordReset.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@SuppressWarnings("javadoc")
public class PasswordReset_Test implements Serializable {

    private static final long serialVersionUID = -3538070214318629961L;

    @Test
    public void Test_Id() {
        final PasswordReset passwordReset = new PasswordReset();

        final Long id = Long.valueOf(1);
        passwordReset.setId(id);

        Assert.assertEquals(id, passwordReset.getId());
    }

    @Test
    public void Test_CreatedAt() {
        final PasswordReset passwordReset = new PasswordReset();

        final Date date = new Date();
        passwordReset.setCreatedAt(date);

        Assert.assertEquals(date, passwordReset.getCreatedAt());
    }

    @Test
    public void Test_Email() {
        final PasswordReset passwordReset = new PasswordReset();

        final String email = "email";
        passwordReset.setEmail(email);

        Assert.assertEquals(email, passwordReset.getEmail());
    }

    @Test
    public void Test_Token() {
        final PasswordReset passwordReset = new PasswordReset();

        final String token = "token";
        passwordReset.setToken(token);

        Assert.assertEquals(token, passwordReset.getToken());
    }

}
