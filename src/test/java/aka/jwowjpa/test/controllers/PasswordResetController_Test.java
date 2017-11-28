package aka.jwowjpa.test.controllers;

import java.util.ArrayList;
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

import aka.jwowjpa.persistence.controllers.PasswordResetController;
import aka.jwowjpa.persistence.models.PasswordReset;
import aka.jwowjpa.utils.CryptoUtils;

/**
 * Integration tests for PasswordResetController.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@Transactional
public class PasswordResetController_Test {

    @Resource
    private PasswordResetController passwordResetController;

    /**
     * Setup the test.
     */
    @Before
    public void setUp() {
        // Insert passwordReset for tests
        for (int i = 0; i < 10; i++) {
            final PasswordReset passwordReset = new PasswordReset();
            passwordReset.setEmail("Email" + i + "@test.com");
            final String token = CryptoUtils.getSHA512SecurePassword("Email " + i + "@test.com", "salt" + i);
            passwordReset.setToken(token);
            this.passwordResetController.insert(passwordReset);
        }
    }

    /**
     * Clean the test.
     */
    @After
    public void cleanUp() {
        this.passwordResetController.deleteAll();
    }

    /**
     * Test insert method.
     */
    @Test
    public void Test_insert() {
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setEmail("Emailnew@test.com");
        final String token = CryptoUtils.getSHA512SecurePassword("Emailnew@test.com", "saltnew");
        passwordReset.setToken(token);
        passwordReset = this.passwordResetController.insert(passwordReset);

        Assert.assertNotNull(passwordReset);
    }

    /**
     * Test getPasswordResetById method.
     */
    @Test
    public void Test_getPasswordResetById() {
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setEmail("Emailnew@test.com");
        final String token = CryptoUtils.getSHA512SecurePassword("Emailnew@test.com", "saltnew");
        passwordReset.setToken(token);
        passwordReset = this.passwordResetController.insert(passwordReset);

        final Long id = passwordReset.getId();
        Assert.assertNotNull(id);
        final PasswordReset reloadedPasswordReset = this.passwordResetController.getPasswordResetById(id);
        Assert.assertNotNull(reloadedPasswordReset);
        Assert.assertEquals(passwordReset.getId(), reloadedPasswordReset.getId());
        Assert.assertEquals(passwordReset.getEmail(), reloadedPasswordReset.getEmail());
        Assert.assertEquals(passwordReset.getToken(), reloadedPasswordReset.getToken());
        Assert.assertEquals(token, reloadedPasswordReset.getToken());
        Assert.assertEquals("Emailnew@test.com", reloadedPasswordReset.getEmail());
    }

    /**
     * Test getPasswordResetByIdList method.
     */
    @Test
    public void Test_getPasswordResetByIdList() {
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setEmail("Emailnew@test.com");
        final String token = CryptoUtils.getSHA512SecurePassword("Emailnew@test.com", "saltnew");
        passwordReset.setToken(token);
        passwordReset = this.passwordResetController.insert(passwordReset);

        final Long id = passwordReset.getId();
        Assert.assertNotNull(id);
        final List<@NonNull Long> idList = new ArrayList<>();
        idList.add(id);
        idList.add(Long.valueOf(-1));
        final List<PasswordReset> passwordResetList = this.passwordResetController.getPasswordResetByIdList(idList);
        Assert.assertNotNull(passwordResetList);
        Assert.assertEquals(1, passwordResetList.size());
    }

    /**
     * Test getPasswordResets method.
     */
    @Test
    public void Test_getPasswordResets() {
        final List<@NonNull PasswordReset> totalPasswordResetList = this.passwordResetController.getAll();
        Assert.assertNotNull(totalPasswordResetList);
        Assert.assertEquals(10, totalPasswordResetList.size());
    }

    /**
     * Test getPasswordResetByEmail method.
     */
    @Test
    public void Test_getPasswordResetByEmail() {
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setEmail("Emailnew@test.com");
        final String token = CryptoUtils.getSHA512SecurePassword("Emailnew@test.com", "saltnew");
        passwordReset.setToken(token);
        passwordReset = this.passwordResetController.insert(passwordReset);

        final List<@NonNull PasswordReset> totalPasswordResetList = this.passwordResetController.getAll();
        final List<@NonNull PasswordReset> passwordResetList = this.passwordResetController.getPasswordResetByEmail("Emailnew@test.com");
        Assert.assertNotNull(passwordResetList);
        Assert.assertEquals(1, passwordResetList.size());
        Assert.assertNotEquals(totalPasswordResetList.size(), passwordResetList.size());
        for (final PasswordReset currentPasswordReset : passwordResetList) {
            Assert.assertEquals("Emailnew@test.com", currentPasswordReset.getEmail());
        }
    }

    /**
     * Test getPasswordResetByEmailPassword method.
     */
    @Test
    public void Test_getPasswordResetByEmailPassword() {
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setEmail("Emailnew@test.com");
        String token = CryptoUtils.getSHA512SecurePassword("Emailnew@test.com", "saltnew");
        if (token == null) {
            token = "myfakegeneratedtoken";
        }
        passwordReset.setToken(token);
        passwordReset = this.passwordResetController.insert(passwordReset);

        final PasswordReset reloadedPasswordReset = this.passwordResetController.getPasswordResetByEmailPassword("Emailnew@test.com", token);
        Assert.assertNotNull(reloadedPasswordReset);
        Assert.assertEquals(passwordReset.getId(), reloadedPasswordReset.getId());
        Assert.assertEquals(passwordReset.getEmail(), reloadedPasswordReset.getEmail());
        Assert.assertEquals(passwordReset.getToken(), reloadedPasswordReset.getToken());
        Assert.assertEquals(token, reloadedPasswordReset.getToken());
        Assert.assertEquals("Emailnew@test.com", reloadedPasswordReset.getEmail());
    }

    /**
     * Test update method.
     */
    @Test
    public void Test_update() {
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setEmail("Emailnew@test.com");
        final String token = CryptoUtils.getSHA512SecurePassword("Emailnew@test.com", "saltnew");
        passwordReset.setToken(token);
        passwordReset = this.passwordResetController.insert(passwordReset);

        passwordReset.setEmail("emailchanged@email.com");
        this.passwordResetController.update(passwordReset);

        final Long id = passwordReset.getId();
        Assert.assertNotNull(id);
        final PasswordReset reloadedPasswordReset = this.passwordResetController.getPasswordResetById(id);
        Assert.assertNotNull(reloadedPasswordReset);
        Assert.assertEquals(passwordReset.getId(), reloadedPasswordReset.getId());
        Assert.assertEquals(passwordReset.getEmail(), reloadedPasswordReset.getEmail());
        Assert.assertEquals(passwordReset.getToken(), reloadedPasswordReset.getToken());
        Assert.assertEquals(token, reloadedPasswordReset.getToken());
        Assert.assertEquals("emailchanged@email.com", reloadedPasswordReset.getEmail());
    }

    /**
     * Test delete method.
     */
    @Test
    public void Test_delete() {
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setEmail("Emailnew@test.com");
        final String token = CryptoUtils.getSHA512SecurePassword("Emailnew@test.com", "saltnew");
        passwordReset.setToken(token);
        passwordReset = this.passwordResetController.insert(passwordReset);

        final Long id = passwordReset.getId();
        Assert.assertNotNull(id);
        PasswordReset reloadedPasswordReset = this.passwordResetController.getPasswordResetById(id);
        Assert.assertNotNull(reloadedPasswordReset);
        Assert.assertEquals(passwordReset.getId(), reloadedPasswordReset.getId());
        Assert.assertEquals(passwordReset.getEmail(), reloadedPasswordReset.getEmail());
        Assert.assertEquals(passwordReset.getToken(), reloadedPasswordReset.getToken());
        Assert.assertEquals(token, reloadedPasswordReset.getToken());
        Assert.assertEquals("Emailnew@test.com", reloadedPasswordReset.getEmail());

        this.passwordResetController.delete(reloadedPasswordReset);
        reloadedPasswordReset = this.passwordResetController.getPasswordResetById(id);
        Assert.assertNull(reloadedPasswordReset);
    }

    /**
     * Test delete method.
     */
    @Test
    public void Test_deleteAll() {
        final Integer countDelete = this.passwordResetController.deleteAll();
        Assert.assertEquals(Integer.valueOf(10), countDelete);
    }
}
