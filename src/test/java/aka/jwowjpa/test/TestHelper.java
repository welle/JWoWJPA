package aka.jwowjpa.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Helping methods in Tests.
 *
 * @author charlottew
 */
public class TestHelper {

    /**
     * Get byte[] from a physical file present in src/test/resources.
     *
     * @param filePath file path in classloader
     * @return byte array
     */
    public static byte[] readBytesFromFile(final String filePath) {
        byte[] bytesArray = null;

        try {

            final ClassLoader classLoader = TestHelper.class.getClassLoader();
            final File file = new File(classLoader.getResource("imgtest.jpg").getFile());

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BufferedImage originalImage = ImageIO.read(file);
            if (originalImage != null) {
                ImageIO.write(originalImage, "jpg", baos);
                baos.flush();
                bytesArray = baos.toByteArray();
                baos.close();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return bytesArray;
    }
}
