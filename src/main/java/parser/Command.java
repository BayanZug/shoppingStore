package parser;

/**
 * Class which helps manipulate the options from cmd
 */
public class Command {
    private final String options;
    private final String url;
    private final String fileName;

    public Command(String options, String url, String fileName) {
        this.options = options;
        this.url = url;
        this.fileName = fileName;
    }

    public String getOptions() {
        return options;
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return fileName;
    }
}
