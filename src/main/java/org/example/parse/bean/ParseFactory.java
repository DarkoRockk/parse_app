package org.example.parse.bean;

import org.example.parse.ParseService;
import org.example.web_bazaraki.HtmlUnitParseService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ParseFactory {

    private static Map<String, ParseService> parseServices = Map.of(
            "*//bazaraki.com", HtmlUnitParseService.getInstance()
    );

    public static Optional<String> fetchPattern(String address) {
        return parseServices.keySet().stream().sorted((a, b) -> Integer.compare(b.replace("*", "").length(), a.replace("*", "").length()))
                .filter(pattern -> {
                    if (!address.startsWith(pattern)) {
                        var preparedPattern = "^" + pattern.replace("*", "([^\\/]+)");
                        var addressAfterPatternUse = address.replaceFirst(preparedPattern, "*");
                        return !address.equals(addressAfterPatternUse);
                    }
                    return true;
                })
                .findFirst();
    }

    public static WebPage parse(String path) throws MalformedURLException {
        var url = new URL(path);
        AtomicReference<WebPage> out = new AtomicReference<>(new WebPage());
        fetchPattern(path).ifPresent(row -> out.set(parseServices.get(row).parse(url)));
        return out.get();
    }
}
