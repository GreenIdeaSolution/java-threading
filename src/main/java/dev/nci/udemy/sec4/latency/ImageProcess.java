package dev.nci.udemy.sec4.latency;

import java.awt.image.BufferedImage;

/**
 * A pixel is represented by an ARGB value.
 */
public class ImageProcess {
    public static int getRed(int rgb) {
        return (rgb & 0xFF0000) >> 16; // shift to the right 2bytes.
    }

    public static int getGreen(int rgb) {
        return (rgb & 0x00FF00) >> 8; // shift to the right 1byte.
    }

    public static int getBlue(int rgb) {
        return rgb & 0x0000FF;
    }

    public static int createRGBFromColors(int red, int green, int blue) {
        int rgb = 0;

        // BLUE
        rgb |= blue;

        // GREEN
        rgb |= (green << 8); // shift to the left 1byte.

        // RED
        rgb |= (red << 16); // shift to the left 2bytes.

        // ALPHA
        rgb |= 0xFF000000;

        return rgb;
    }

    public static boolean isShadeOfGray(int red, int green, int blue) {
        return Math.abs(red - green) < 30 && Math.abs(red - blue) < 30 && Math.abs(green - blue) < 30;
    }

    public static void recolorPixel(BufferedImage srcImage, BufferedImage dstImage, int x, int y) {
        int rgb = srcImage.getRGB(x, y);

        // get each value of RGB:
        int red = getRed(rgb);
        int green = getGreen(rgb);
        int blue = getBlue(rgb);

        // calculate the new Color for each value of RGB:
        int newRed, newGreen, newBlue;
        if (isShadeOfGray(red, green, blue)) {
            newRed = Math.min(255, red + 10);
            newGreen = Math.max(0, green - 80);
            newBlue = Math.max(0, blue - 20);
        } else {
            newRed = red;
            newGreen = green;
            newBlue = blue;
        }

        // create new RGB color and set to Dest image:
        int newRGB = createRGBFromColors(newRed, newGreen, newBlue);
        setRGB(dstImage, x, y, newRGB);
    }

    public static void recolorImage(BufferedImage srcImage, BufferedImage dstImage,
                                    int leftCorner, int topCorner, int width, int height) {
        for (int x = leftCorner; x < leftCorner + width && x < srcImage.getWidth(); x++) {
            for (int y = topCorner; y < topCorner + height && y < srcImage.getHeight(); y++) {
                recolorPixel(srcImage, dstImage, x, y);
            }
        }
    }

    public static void setRGB(BufferedImage image, int x, int y, int rgb) {
        image.getRaster().setDataElements(x, y, image.getColorModel().getDataElements(rgb, null));
    }
}
