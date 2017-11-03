package aka.jwowjpa.test.context;

import org.junit.Assert;

import aka.jwowjpa.context.ApplicationContext;

@SuppressWarnings("javadoc")
public class ApplicationContext_Test {

    @org.junit.Test
    public void getInstance_Test() {
        final ApplicationContext applicationContext = ApplicationContext.getInstance();
        Assert.assertNotNull(applicationContext);
    }

    @org.junit.Test
    public void getLogger_Test() {
        final ApplicationContext applicationContext = ApplicationContext.getInstance();
        Assert.assertNotNull(applicationContext);
        Assert.assertNotNull(applicationContext.getLogger());
    }
}
