package aka.jwowjpa.context;

import java.util.logging.Logger;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Application Context.
 *
 * @author charlottew
 */
public class ApplicationContext {

    @NonNull
    private static final ApplicationContext INSTANCE = new ApplicationContext();
    @NonNull
    private static final Logger LOGGER = Logger.getLogger(ApplicationContext.class.getName());

    /**
     * Get current context.
     *
     * @return instance of context
     */
    @NonNull
    public static ApplicationContext getInstance() {
        return INSTANCE;
    }

    /**
     * Get logger.
     *
     * @return logger
     */
    @NonNull
    public Logger getLogger() {
        return LOGGER;
    }
}
