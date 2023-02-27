package htmltags;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * this the html tag interface which has a function called getTagInformation
 *  whose job is to retrieve the tag information using the document sent to this function.
 */
public interface HtmlTag {
    List<String> getTagInformation(Document doc);
}
