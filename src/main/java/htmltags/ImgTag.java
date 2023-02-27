package htmltags;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 this class , Each html tag implements from the html tag interface which has a function called getTagInformation
 whose job is to retrieve the tag information using the document sent to this function
 */
public class ImgTag implements HtmlTag {
    /**
     * to retrieve the img tag information using the document sent to this function
     * @param doc the html document
     * @return List of the document img tags eith information about them
     */
    public List<String> getTagInformation(Document doc) {
        Elements imgElements = doc.select("img");

        return imgElements.stream().map(element -> element.absUrl("src")).collect(Collectors.toList());
    }
}
