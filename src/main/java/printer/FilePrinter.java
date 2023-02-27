package printer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FilePrinter implements Printer {
    private String fileName;

    public FilePrinter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * The informain that we want to print
     * @param dataToPrint
     * @throws Exception
     */
    public void print(List<String> dataToPrint) throws Exception {
        try {
            FileWriter fileWriter = new FileWriter(this.fileName);

            for (String data : dataToPrint) {
                fileWriter.write(data + "\n");
            }

            fileWriter.close();
        } catch (IOException exception) {
            boolean isDeleted = (new File(this.fileName)).delete();

            throw new Exception("error");
        }
    }
}
