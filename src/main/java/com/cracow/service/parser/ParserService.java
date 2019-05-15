package com.cracow.service.parser;

import com.cracow.error.common.InternalServerErrorProblem;
import com.cracow.repository.BookmarkRepository;
import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import com.teamdev.jxbrowser.chromium.swing.internal.LightWeightWidget;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ParserService {

    private byte[] imageInByte = null;

    private final BookmarkRepository bookmarkRepository;

    public ParserService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    private static BufferedImage convertImageToBufferedImage(Image im) {
        BufferedImage bi = new BufferedImage
                (im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
    }

//    private static void writeBufferedImageToPNG(BufferedImage bufferedImage) throws IOException {
//        ImageIO.write(bufferedImage, "png", new File("nazwa.png"));
//    }
//
//    private static void writeBufferedImageToJPG(BufferedImage bufferedImage) throws IOException {
//        ImageIO.write(bufferedImage, "jpg", new File("nazwa.jpg"));
//    }

    private static byte[] convertBufferedImageToByte(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        return baos.toByteArray();
    }

//    private void parse(String id) throws Exception {
//        if (bookmarkRepository.findById(id).get() != null) {
//            BookmarkEntity bookmarkEntity = bookmarkRepository.findById(id).get();
//            bookmarkEntity.setBlob(parseURLToByte(bookmarkEntity.getSource()));
//            bookmarkRepository.deleteById(id);
//            bookmarkRepository.save(bookmarkEntity);
//        } else {
//            throw new Exception();
//        }
//    }
//
//    private void convertByteToImage(byte[] imageInByte) throws IOException {
//        // convert byte array back to BufferedImage
//        InputStream in = new ByteArrayInputStream(imageInByte);
//        BufferedImage bImageFromConvert = ImageIO.read(in);
//        ImageIO.write(bImageFromConvert, "jpg", new File("nazwa.jpg"));
//    }
//
//    private BufferedImage convertBlobToBufferImage(Blob blob) throws IOException, SQLException {
//        InputStream in = blob.getBinaryStream();
//        return ImageIO.read(in);
//    }
//
//    private void convertBlobToImage(Blob blob) throws IOException, SQLException {
//        BufferedImage bufferedImage = convertBlobToBufferImage(blob);
//        byte[] imageByte = convertBufferedImageToByte(bufferedImage);
//        convertByteToImage(imageByte);
//    }

    //TODO Angelika Wątroba
    //TODO tutaj obsługujemy bład i zwracamy byte[]
    public byte[] parseToByte(String URL) {
        try {
//            return parseURLToByte(URL);
            return new byte[10];
        } catch (Exception e) {
            throw new InternalServerErrorProblem(e.getMessage());
        }
    }

    //TODO (Alternatywa) Zwrocenie HTML jako String, serwis ma tylko parsować a nie zapisywać jak sama nazwa mówi :)
    //TODO Angelika Wątroba
    private byte[] parseURLToByte(String URL) throws Exception {
        final int scrollBarSize = 25;
        int viewWidth = 0;
        int viewHeight = 0;

        Browser browser = new Browser(BrowserType.LIGHTWEIGHT);
        BrowserPreferences preferences = browser.getPreferences();
        // preferences.setJavaScriptEnabled(false);
        browser.setPreferences(preferences);
        Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
            @Override
            public void invoke(Browser browser) {
                browser.loadURL(URL);
            }
        });

        JSValue documentHeight = browser.executeJavaScriptAndReturnValue(
                "Math.max(document.body.scrollHeight, " +
                        "document.documentElement.scrollHeight, document.body.offsetHeight, " +
                        "document.documentElement.offsetHeight, document.body.clientHeight, " +
                        "document.documentElement.clientHeight);");
        JSValue documentWidth = browser.executeJavaScriptAndReturnValue(
                "Math.max(document.body.scrollWidth, " +
                        "document.documentElement.scrollWidth, document.body.offsetWidth, " +
                        "document.documentElement.offsetWidth, document.body.clientWidth, " +
                        "document.documentElement.clientWidth);");

        viewWidth = documentWidth.asNumber().getInteger() + scrollBarSize;

        viewHeight = documentHeight.asNumber().getInteger() + scrollBarSize;
        BrowserView view = new BrowserView(browser);
        browser.setSize(viewWidth, viewHeight);
        //Thread.sleep(500);
        // Wait until Chromium renders web page content
        Thread.sleep(500);
        //  Get java.awt.Image of the loaded web page.
        LightWeightWidget lightWeightWidget = (LightWeightWidget) view.getComponent(0);
        Image image = lightWeightWidget.getImage();
        // ImageIO.write((RenderedImage) image, "PNG", new File(nazwa+".png"));
        browser.dispose();
        BufferedImage bufferedImage = convertImageToBufferedImage(image);
        //writeImageToPNG(bufferedImage);
        imageInByte = convertBufferedImageToByte(bufferedImage);

        return imageInByte;
    }
}
