package com.cracow;
public class ParserHtml {}

/*
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserPreferences;
import com.teamdev.jxbrowser.chromium.BrowserType;
import com.teamdev.jxbrowser.chromium.Callback;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import com.teamdev.jxbrowser.chromium.swing.internal.LightWeightWidget;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParserHtml {

    @GetMapping("/parse")
    public void doIt()throws Exception{
        parse("https://wiadomosci.onet.pl/tylko-w-onecie/bitwa-o-lotnisko-w-radomiu-na-pierwszej-linii-czolowi-politycy-pis/cr9vw8z",
                "wiadomosciOnet");
    }
   public void parse(String URL, String nazwa)  throws Exception {
        // #1 Create Browser instance
        Browser browser = new Browser(BrowserType.LIGHTWEIGHT);
        // Gets the current Browser's preferences
        BrowserPreferences preferences = browser.getPreferences();
        preferences.setJavaScriptEnabled(false);

        // Updates Browser's preferences
        browser.setPreferences(preferences);
        BrowserView view = new BrowserView(browser);

        // #2 Set the required view size
        browser.setSize(1280, 1024);

        // Wait until Chromium resizes view
        Thread.sleep(500);

        // #3 Load web page and wait until web page is loaded completely
        Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
            @Override
            public void invoke(Browser browser) {
                browser.loadURL(URL);
            }
        });
        // Wait until Chromium renders web page content
        Thread.sleep(500);
        // #4 Get java.awt.Image of the loaded web page.
        LightWeightWidget lightWeightWidget = (LightWeightWidget) view.getComponent(0);
        Image image = lightWeightWidget.getImage();
        ImageIO.write((RenderedImage) image, "PNG", new File(nazwa+".png"));

        // Dispose Browser instance
        browser.dispose();
    }



}
*/