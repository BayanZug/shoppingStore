package com.retailer.shop;
import exceptions.BadUrlException;
import exceptions.InvalidCommandException;
import htmltags.HtmlTag;
import htmltags.HtmlTagsFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import parser.Command;
import parser.Parser;
import printer.FilePrinter;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Main of the program as described above
     * @param args
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String commandToParse = scanner.nextLine();

            Command parsedCommand = Parser.parse(commandToParse);

            List<HtmlTag> htmlTags = HtmlTagsFactory.getTags(parsedCommand.getOptions());

            Document doc = Jsoup.connect(parsedCommand.getUrl()).get();

            if ("".equals(doc.html())) {
                throw new BadUrlException();
            }

            List<String> dataToPrint = new ArrayList<>();
            for (HtmlTag htmlTag : htmlTags) {
                dataToPrint.addAll(htmlTag.getTagInformation(doc));
                dataToPrint.add("");
            }
            dataToPrint.remove(dataToPrint.size() - 1);

            FilePrinter filePrinter = new FilePrinter(parsedCommand.getFileName());
            filePrinter.print(dataToPrint);
        } catch (InvalidCommandException exception) {
            System.out.println("invalid command");
        } catch (MalformedURLException | BadUrlException | IllegalArgumentException exception) {
            System.out.println("bad url");
        } catch (Exception exception) {
            System.out.println("error");
        }
    }
}
