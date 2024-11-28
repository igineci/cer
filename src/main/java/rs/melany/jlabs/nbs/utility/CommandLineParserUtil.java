package rs.melany.jlabs.nbs.utility;

import org.apache.commons.cli.*;
import rs.melany.jlabs.nbs.model.ParsedArgs;


/**
 * Utility Class ruled for setting options and parsing Command Line Arguments.
 *
 * @author andjela.djekic
 */
public class CommandLineParserUtil {

    /**
     * Parses command-line arguments and returns a ParsedArgs object.
     */
    public static ParsedArgs parseCommandLineArguments(String[] args) {

        Options options = new Options();

        options.addOption(Option.builder("h").longOpt("host").hasArg().required(true).desc("Host name or IP, this parameter is required.").build());
        options.addOption(Option.builder("p").longOpt("port").hasArg().required(true).desc("Port name, this parameter is required.").build());
        options.addOption(Option.builder("d").longOpt("database").hasArg().required(true).desc("Database name, this parameter is required.").build());
        options.addOption(Option.builder("U").longOpt("user").hasArg().required(true).desc("Database username, this parameter is required.").build());
        options.addOption(Option.builder("P").longOpt("password").hasArg().required(true).desc("Database password, this parameter is required.").build());

        CommandLineParser parser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();

        ParsedArgs parsedArgs = new ParsedArgs();

        try {

            CommandLine cmd = parser.parse(options, args);

            parsedArgs.setHost(cmd.getOptionValue("h"));
            parsedArgs.setPort(Integer.parseInt(cmd.getOptionValue("p")));
            parsedArgs.setDatabase(cmd.getOptionValue("d"));
            parsedArgs.setUsername(cmd.getOptionValue("U"));
            parsedArgs.setPassword(cmd.getOptionValue("P"));

        } catch (ParseException | IllegalArgumentException e) {
            System.out.println("Parsing failed: " + e.getMessage());
            helpFormatter.printHelp("java -jar dodati !!!", options);
        } catch (NullPointerException e) {

            System.err.println("Nullpointer: " + e.getMessage());
            System.err.println("Check the type of passed values. Port must be integer.");
            helpFormatter.printHelp("java -jar dodati !!!", options);
        }

        return parsedArgs;
    }
}
