package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.parse.ParseService;
import org.example.parse.bean.ParseFactory;
import org.example.parse.bean.WebPage;
import org.example.web_bazaraki.HtmlUnitParseService;

import java.net.MalformedURLException;

/**
 * Hello world!
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        log.info("Hello world!");
        log.error("Hello world!");
        log.warn("Hello world!");

        var app = new App();
        app.runApp();
    }

    public void runApp() {
        ParseService parseService = new HtmlUnitParseService();
        parseService.init();

        try {
            WebPage webPage = ParseFactory.parse("http://bazaraki.com");
            log.info("Parsed: {}", webPage);
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
        }
    }
}
