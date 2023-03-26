package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.FileNotFoundException;
import java.io.IOException;

import seedu.address.logic.commands.ImportCsvCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ImportCsvCommand} object
 */
public class ImportCsvCommandParser implements Parser<ImportCsvCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ImportCsvCommand}
     * and returns a {@code ImportCsvCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ImportCsvCommand parse(String args) throws ParseException {
        String path = args.strip();
        if (path.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCsvCommand.MESSAGE_USAGE));
        }

        ParseFromCsvToPersons csvToPersons;

        try {
            csvToPersons = new ParseFromCsvToPersons(path);
        } catch (FileNotFoundException e) {
            throw new ParseException("File was not found!");
        } catch (IOException e) {
            throw new ParseException("Error occurred while reading the given file");
        }

        return new ImportCsvCommand(csvToPersons.parse());
    }
}
