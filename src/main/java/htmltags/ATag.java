package htmltags;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 this class ,Each html tag implements from the html tag interface which has a function called getTagInformation
 whose job is to retrieve the tag information using the document sent to this function
 */
public class ATag implements HtmlTag {
    /**
     * to retrieve the A tag information using the document sent to this function
     * @param doc  the html document
     * @return List of the document a tags eith information about them
     */
    public List<String> getTagInformation(Document doc) {
        Elements aElements = doc.select("a");

        return aElements.stream().map(el -> el.absUrl("href")).collect(Collectors.toList());
    }
}
