package printer;

import java.util.List;

public interface Printer {
    /**
     * The informain that we want to print
     * @param dataToPrint
     * @throws Exception
     */
    public void print(List<String> dataToPrint) throws Exception;
}
