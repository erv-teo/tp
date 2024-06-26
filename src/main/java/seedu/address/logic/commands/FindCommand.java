package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the person whose name or ic contain any of "
            + "the specified keywords (case-insensitive).\n"
            + "Parameters: [" + PREFIX_NAME + "NAME] [" + PREFIX_IC + "IC_NUMBER]\n"
            + "Examples: " + COMMAND_WORD + " " + PREFIX_NAME + "alice herman, "
            + COMMAND_WORD + " " + PREFIX_IC + "A1234567W";

    private final Predicate<Person> predicate;

    /**
     * Creates a FindCommand to find the specified {@code Predicate<Person>}
     */
    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        int listSize = model.getFilteredPersonList().size();
        if (listSize < 2) {
            return new CommandResult(String.format(Messages.MESSAGE_PERSON_LISTED_OVERVIEW, listSize));
        }
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, listSize));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
