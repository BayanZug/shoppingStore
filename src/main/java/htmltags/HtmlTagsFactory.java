package htmltags;

import exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.List;

public class HtmlTagsFactory {
    /**
     This function gets an list of options from the
     command line and returns all the html tags was on the options
     * @param options List of options from cmd
     * @return Html tags
     */
    public static List<HtmlTag> getTags(String options) {
        List<HtmlTag> htmlTags = new ArrayList<>();

        for (char option : options.toCharArray()) {
            switch (option) {
                case 'a':
                    htmlTags.add(new ATag());
                    break;
                case 'i':
                    htmlTags.add(new ImgTag());
                    break;
                case 'h':
                    htmlTags.add(new H1Tag());
                    break;
                default:
                    throw new InvalidCommandException();
            }
        }

        return htmlTags;
    }

}
