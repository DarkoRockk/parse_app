package org.example.parse;

import java.net.URL;

public interface ParseService {

    ParseService init();

    WebPage parse(URL url);

}
