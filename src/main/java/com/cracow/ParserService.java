package com.cracow;
//import com.cracow.repositories.BookmarkRepository;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserPreferences;
import com.teamdev.jxbrowser.chromium.BrowserType;
import com.teamdev.jxbrowser.chromium.Callback;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import com.teamdev.jxbrowser.chromium.swing.internal.LightWeightWidget;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class ParserService {
    private byte[] imageInByte=null;
   /* private BookmarkRepository bookmarkRepository;

    public  ParserService (BookmarkRepository bookmarkRepository){
        this.bookmarkRepository= bookmarkRepository;
    }

    private void parse(String id) {
        String URL = bookmarkRepository.findById(id).get().getSource();
        if(URL != null){
            try {
                parseImg(URL);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

  @GetMapping("/parse")
  private void doIt()throws Exception{
      parseImg("https://wiadomosci.onet.pl/tylko-w-onecie/bitwa-o-lotnisko-w-radomiu-na-pierwszej-linii-czolowi-politycy-pis/cr9vw8z");
  }*/
    private void convertByteToImage(byte[] imageInByte) throws IOException {
      // convert byte array back to BufferedImage
      InputStream in = new ByteArrayInputStream(imageInByte);
      BufferedImage bImageFromConvert = ImageIO.read(in);
      ImageIO.write(bImageFromConvert, "jpg", new File("nazwa.jpg"));
  }
    private  BufferedImage convertBlobToBufferImage(Blob blob) throws IOException, SQLException {
      InputStream in = blob.getBinaryStream();
     return ImageIO.read(in);
  }
    private static BufferedImage convertImageToBufferedImage(Image im) {
        BufferedImage bi = new BufferedImage
                (im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
    }
    private static void writeBufferedImageToPNG (BufferedImage bufferedImage) throws IOException {
        ImageIO.write(bufferedImage,"png", new File("nazwa.png"));
    }
    private static void writeBufferedImageToJPG(BufferedImage bufferedImage) throws IOException {
        ImageIO.write(bufferedImage,"jpg", new File("nazwa.jpg"));
    }
    private  static byte[] convertBufferedImageToByte(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos );
       return baos.toByteArray();
    }
    private  void convertBlobToImage(Blob blob) throws IOException, SQLException {
        BufferedImage bufferedImage= convertBlobToBufferImage(blob);
        byte[] imageByte=convertBufferedImageToByte(bufferedImage);
        convertByteToImage(imageByte);
    }
    private Blob parseImg(String URL)  throws Exception {
        //  Create Browser instance
        Browser browser = new Browser(BrowserType.LIGHTWEIGHT);
        // Gets the current Browser's preferences
        BrowserPreferences preferences = browser.getPreferences();
        preferences.setJavaScriptEnabled(false);
        // Updates Browser's preferences
        browser.setPreferences(preferences);
        BrowserView view = new BrowserView(browser);
        //  Set the required view size
        browser.setSize(1280, 1024);
        // Wait until Chromium resizes view
        Thread.sleep(500);

        // Load web page and wait until web page is loaded completely
        Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
            @Override
            public void invoke(Browser browser) {
                browser.loadURL(URL);
            }
        });

        // Wait until Chromium renders web page content
        Thread.sleep(500);
        //  Get java.awt.Image of the loaded web page.
        LightWeightWidget lightWeightWidget = (LightWeightWidget) view.getComponent(0);
        Image image = lightWeightWidget.getImage();
       // ImageIO.write((RenderedImage) image, "PNG", new File(nazwa+".png"));
        // Dispose Browser instance
        browser.dispose();

        //convert imge to bufferImage
        BufferedImage bufferedImage= convertImageToBufferedImage(image);
        //writeImageToPNG(bufferedImage);

        imageInByte=convertBufferedImageToByte(bufferedImage);
        //convertByteToImage(imageInByte);
        Blob blob = new javax.sql.rowset.serial.SerialBlob(imageInByte);
        return blob;
    }
}
