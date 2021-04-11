package dev.nci.udemy.sec4;

import dev.nci.udemy.sec4.latency.ImageProcess;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Section4 {
    public static final String SOURCE_FILE = "sec4/many-flowers.jpg";
    public static final String DESTINATION_FILE = "out/sec4/many-flowers.jpg";
    public static final String DESTINATION_FILE_MT = "out/sec4/mt-many-flowers.jpg";

    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(SOURCE_FILE);
        BufferedImage originalImage = ImageIO.read(Objects.requireNonNull(is));
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        // single thread:
        long startTime = System.currentTimeMillis();
        recolorSingleThreaded(originalImage, resultImage);
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("Duration of single-thread: " + duration);

        // multi-thread:
        int numberOfThreads = 8;
        startTime = System.currentTimeMillis();
        recolorMultiThreaded(originalImage, resultImage, numberOfThreads);
        endTime = System.currentTimeMillis();

        duration = endTime - startTime;
        System.out.println("Duration of multi-thread: " + duration);
    }

    public static void recolorSingleThreaded(BufferedImage srcImage, BufferedImage dstImage) throws IOException {
        ImageProcess.recolorImage(srcImage, dstImage, 0, 0,
                srcImage.getWidth(), srcImage.getHeight());

        writeImage(DESTINATION_FILE, dstImage);
    }

    public static void recolorMultiThreaded(BufferedImage srcImage, BufferedImage dstImage,
                                            int numberOfThreads) throws IOException {
        List<Thread> threads = new ArrayList<>();
        int width = srcImage.getWidth();
        int height = srcImage.getHeight() / numberOfThreads;

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadMultiplier = i;

            Thread thread = new Thread(() -> {
                int leftCorner = 0;
                int topCorner = height * threadMultiplier;

                ImageProcess.recolorImage(srcImage, dstImage, leftCorner, topCorner,
                        width, height);
            });
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        writeImage(DESTINATION_FILE_MT, dstImage);
    }

    public static void writeImage(String filePath, BufferedImage image) throws IOException {
        File outputFile = new File(filePath);
        if (!outputFile.getParentFile().exists()) {
            Files.createDirectories(Paths.get(outputFile.getParent()));
        }
        ImageIO.write(image, "jpg", outputFile);
    }
}
