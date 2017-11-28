package aka.jwowjpa.test.models;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aka.jwowjpa.persistence.models.ItemhasspellPK;

/**
 * Unit tests for ItemhasspellPK.
 *
 * @author charlottew
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
@SuppressWarnings("javadoc")
public class ItemhasspellPK_Test implements Serializable {

    private static final long serialVersionUID = -9134788463525170563L;

    @Test
    public void Test_IdItem() {
        final ItemhasspellPK itemhasspellPK = new ItemhasspellPK();

        final Long id = Long.valueOf(1);
        itemhasspellPK.setIdItem(id);

        Assert.assertEquals(id, itemhasspellPK.getIdItem());
    }

    @Test
    public void Test_IdSpell() {
        final ItemhasspellPK itemhasspellPK = new ItemhasspellPK();

        final Long id = Long.valueOf(1);
        itemhasspellPK.setIdSpell(id);

        Assert.assertEquals(id, itemhasspellPK.getIdSpell());
    }
}
