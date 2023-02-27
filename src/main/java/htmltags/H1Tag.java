package htmltags;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 this class ,Each html tag implements from the html tag interface which has a function called getTagInformation
 whose job is to retrieve the tag information using the document sent to this function
 */

public class H1Tag implements HtmlTag {
    /**
     * to retrieve the H1 tag information using the document sent to this function
     * @param doc the html document
     * @return List of the document H1 tags eith information about them
     */
    public List<String> getTagInformation(Document doc) {
        Elements h1Elements = doc.select("h1");

        return h1Elements.stream().map(Element::text).collect(Collectors.toList());
    }
}
