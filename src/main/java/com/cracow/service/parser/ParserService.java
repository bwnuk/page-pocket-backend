package com.cracow.service.parser;

import com.cracow.error.common.InternalServerErrorProblem;
import com.cracow.repository.BookmarkRepository;
import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import com.teamdev.jxbrowser.chromium.swing.internal.LightWeightWidget;
import org.jsoup.Connection;
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

    private static byte[] convertBufferedImageToByte(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        return baos.toByteArray();
    }

    public byte[] parseToByte(String URL) {
        try {
            return parseURLToByte(URL);
        } catch (Exception e) {
            throw new InternalServerErrorProblem(e.getMessage());
        }
    }

    private byte[] parseURLToByte(String URL) throws Exception {

        Browser browser = new Browser(BrowserType.LIGHTWEIGHT);
        BrowserPreferences preferences = browser.getPreferences();
        preferences.setJavaScriptEnabled(false);
        browser.setPreferences(preferences);
        browser.setSize(1280, 3000);
                BrowserView view = new BrowserView(browser);
        Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
            @Override
            public void invoke(Browser browser) {
                browser.loadURL(URL);
            }
        });

        Thread.sleep(500);
        LightWeightWidget lightWeightWidget = (LightWeightWidget) view.getComponent(0);
        Image image = lightWeightWidget.getImage();
        browser.dispose();
        BufferedImage bufferedImage= convertImageToBufferedImage(image);
        imageInByte=convertBufferedImageToByte(bufferedImage);
        return imageInByte;
    }



}
