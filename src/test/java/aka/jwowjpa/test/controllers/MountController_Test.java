package aka.jwowjpa.test.controllers;

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

import aka.jwowjpa.persistence.controllers.MountController;
import aka.jwowjpa.persistence.models.Mount;
import aka.jwowjpa.test.TestHelper;

/**
 * Integration tests for MountController.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@Transactional
public class MountController_Test {

    @Resource
    private MountController mountController;

    private final byte[] icon = TestHelper.readBytesFromFile("imgtest.jpg");

    /**
     * Setup the test.
     */
    @Before
    public void setUp() {
        // Insert item for tests
        for (int i = 0; i < 10; i++) {
            final Mount mount = new Mount();
            mount.setName("Mount " + i);
            mount.setNameEN("MountEN " + i);
            mount.setIdCreature(Long.valueOf(i));
            mount.setIcon(this.icon);
            this.mountController.insert(mount);
        }
    }

    /**
     * Clean the test.
     */
    @After
    public void cleanUp() {
        this.mountController.deleteAll();
    }

    /**
     * Test getLatestItem method.
     */
    @Test
    public void Test_getLatestItem() {
        final Mount mount = this.mountController.getLatestMount();
        Assert.assertNotNull(mount);
        Assert.assertEquals(Long.valueOf(9), mount.getIdCreature());
    }

    /**
     * Test getItemByIdCreature method.
     */
    @Test
    public void Test_getItemByIdCreature() {
        final Long idCreature = Long.valueOf(5);
        final List<@NonNull Mount> mountList = this.mountController.getMountByIdCreature(idCreature);
        Assert.assertNotNull(mountList);
        Assert.assertEquals(1, mountList.size());
        for (final Mount mount : mountList) {
            Assert.assertEquals(idCreature, mount.getIdCreature());
            Assert.assertNotNull(mount.getIcon());
            Assert.assertEquals("Mount " + idCreature, mount.getName());
            Assert.assertEquals("MountEN " + idCreature, mount.getNameEN());
        }
    }

    /**
     * Test insert method.
     */
    @Test
    public void Test_insert() {
        Mount mount = new Mount();
        mount.setName("Mount inserted ");
        mount.setNameEN("Mount EN name");
        mount.setIdCreature(Long.valueOf(15));
        mount.setIcon(this.icon);
        mount = this.mountController.insert(mount);

        Assert.assertNotNull(mount);
    }

    /**
     * Test getItemById method.
     */
    @Test
    public void Test_getItemById() {
        Mount mount = new Mount();
        mount.setName("Mount inserted ");
        mount.setNameEN("Mount EN name");
        mount.setIdCreature(Long.valueOf(15));
        mount.setIcon(this.icon);
        mount = this.mountController.insert(mount);

        final Long id = mount.getId();
        Assert.assertNotNull(id);
        final Mount reloadedMount = this.mountController.getMountById(id);
        Assert.assertNotNull(reloadedMount);
        Assert.assertEquals(mount.getId(), reloadedMount.getId());
        Assert.assertEquals(mount.getName(), reloadedMount.getName());
        Assert.assertEquals(mount.getNameEN(), reloadedMount.getNameEN());
        Assert.assertEquals(mount.getIcon(), reloadedMount.getIcon());
        Assert.assertNotNull(mount.getIcon());
    }

    /**
     * Test getItems method.
     */
    @Test
    public void Test_getItems() {
        final List<@NonNull Mount> totalMountList = this.mountController.getAll();
        Assert.assertNotNull(totalMountList);
        Assert.assertEquals(10, totalMountList.size());
    }

    /**
     * Test getItemByNameLike method.
     */
    @Test
    public void Test_getItemByNameLike() {
        Mount mount = new Mount();
        mount.setName("inserted Mount");
        mount.setNameEN("inserted MountEn");
        mount.setIdCreature(Long.valueOf(15));
        mount.setIcon(this.icon);
        mount = this.mountController.insert(mount);

        final List<@NonNull Mount> totalMountList = this.mountController.getAll();
        final List<@NonNull Mount> mountList = this.mountController.getMountByNameLike("Mount");
        Assert.assertNotNull(mountList);
        Assert.assertEquals(10, mountList.size());
        Assert.assertNotEquals(totalMountList.size(), mountList.size());
        for (final Mount currentMount : mountList) {
            Assert.assertTrue(currentMount.getName().startsWith("Mount"));
            Assert.assertNotNull(currentMount.getIcon());
        }
    }

    /**
     * Test update method.
     */
    @Test
    public void Test_update() {
        Mount mount = new Mount();
        mount.setName("Mount inserted ");
        mount.setNameEN("Mount EN name");
        mount.setIdCreature(Long.valueOf(15));
        mount.setIcon(this.icon);
        mount = this.mountController.insert(mount);

        mount.setName("Mount inserted updated");
        mount.setNameEN("Mount EN name updated");
        mount.setIdCreature(Long.valueOf(20));
        this.mountController.update(mount);

        final Long id = mount.getId();
        Assert.assertNotNull(id);
        final Mount reloadedMount = this.mountController.getMountById(id);
        Assert.assertNotNull(reloadedMount);
        Assert.assertEquals(mount.getId(), reloadedMount.getId());
        Assert.assertEquals(mount.getName(), reloadedMount.getName());
        Assert.assertEquals(mount.getNameEN(), reloadedMount.getNameEN());
        Assert.assertEquals(mount.getIcon(), reloadedMount.getIcon());
        Assert.assertNotNull(reloadedMount.getIcon());
    }
}
