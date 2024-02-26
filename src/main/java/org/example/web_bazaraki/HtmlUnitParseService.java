package org.example.web_bazaraki;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;
import org.example.parse.ParseService;
import org.example.parse.bean.WebPage;
import org.example.web_bazaraki.bean.DataToParseItem;
import org.example.web_bazaraki.bean.WebBazarakiPage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Slf4j
public class HtmlUnitParseService implements ParseService {

    private static final HtmlUnitParseService instance = new HtmlUnitParseService();


    private WebClient webClient;

    public HtmlUnitParseService() {
        this.init();
    }

    public static ParseService getInstance() {
        return instance;
    }

    @Override
    public ParseService init() {
        webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
        return this;
    }

    @Override
    public WebPage parse(URL url) throws IOException {
        WebBazarakiPage out = new WebBazarakiPage();
        HtmlPage page = webClient.getPage(url);
        List<HtmlElement> titlesList = page.getByXPath("//title");
        titlesList.forEach(htmlElement -> {
            out.setTitle(htmlElement.getTextContent());
        });

        parseInstagramLink(page, out);
        parseDataToParse(page, out);

        return out;
    }

    private void parseInstagramLink(HtmlPage page, WebBazarakiPage out) {
        List<HtmlElement> linksList = page.getByXPath("//a[contains(@href, 'instagram.com')]");
        linksList.forEach(htmlElement -> {
            out.getInstagramLink().add(htmlElement.getAttribute("href"));
        });
    }

    private void parseDataToParse(HtmlPage page, WebBazarakiPage out) {
        List<HtmlElement> dataToParseBlocks = page.getByXPath("""
                //img[@alt="Motors"]
                /../../..
                """);
        dataToParseBlocks.forEach(htmlElement -> {
            DataToParseItem item = new DataToParseItem();
            htmlElement.getByXPath("""
                    ./div/p
                    """).stream().findFirst().ifPresent(el -> {
                item.setTitle(((HtmlElement) el).getTextContent());
            });
            htmlElement.getByXPath("""
                    ./div/div[@class="sub-category-container"]
                    /div/ul
                    """).stream().findFirst().ifPresent(el -> {
                item.setDetails(((HtmlElement) el).getTextContent());
            });
            out.addDataToParseItem(item);
        });
    }
}

