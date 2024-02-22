package org.example.parse.bean;

import org.example.parse.ParseService;
import org.example.web_bazaraki.HtmlUnitParseService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ParseFactory {

    private static Map<String, ParseService> parseServices = Map.of(
            "http://bazaraki.com", new HtmlUnitParseService().init()
    );

    public static WebPage parse(String path) throws MalformedURLException {
        var url = new URL(path);
        return parseServices.get(path).parse(url);
    }
}
