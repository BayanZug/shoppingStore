package parser;

import exceptions.InvalidCommandException;

public class Parser {

    /**
     * Parse a HTML input stream. The result is Command String options, String url, String fileName.
     * @param command - the command to parse
     * @throws  InvalidCommandException  only if the command mismatch a command
     */

    public static Command parse(String command) throws InvalidCommandException {
        String[] words = command.split(" ");

        if (words.length == 5 && "java".equals(words[0]) && "Main".equals(words[1])) {
            String options = words[2];
        	System.out.println("comming "+options);

            if (options.startsWith("-") && options.length() > 1) {
            	System.out.println("comming option");

                String url = words[3];
                String fileName = words[4];
                return new Command(options.substring(1), url, fileName);
            }
        }

        throw new InvalidCommandException();
    }
}
