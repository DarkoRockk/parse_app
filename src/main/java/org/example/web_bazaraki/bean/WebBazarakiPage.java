package org.example.web_bazaraki.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.example.parse.bean.WebPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class WebBazarakiPage extends WebPage {

    private List<DataToParseItem> dataToParse = Collections.synchronizedList(new ArrayList<>());

    private List<String> instagramLink = Collections.synchronizedList(new ArrayList<>());


    public WebBazarakiPage addDataToParseItem(DataToParseItem item) {
        this.dataToParse.add(item);
        return this;
    }
}
