package pl.myprivatepocket.services;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class UrlExtractService {

    public String extractHtmlFromUrl(String url, boolean includeImages) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements body = doc.select("body");
        for (Element element : doc.select("nav, footer")) {
            element.remove();
        }

        for (Element element : doc.select("img")) {
            String attr = element.attr("src");
            if (!attr.startsWith("http") && !attr.startsWith("https")) {
                element.attr("src", "http:" + attr);
            }
        }

        Whitelist whitelist;
        if (includeImages) {
            whitelist = Whitelist.basicWithImages().addTags("table", "td", "th", "tr", "tbody", "thead", "tfoot");
        } else {
            whitelist = Whitelist.basic().addTags("table", "td", "th", "tr", "tbody", "thead", "tfoot");
        }

        return Jsoup.clean(body.html(), whitelist);
    }

}