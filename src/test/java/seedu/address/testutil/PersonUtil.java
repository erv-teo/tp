package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADMISSION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().toString() + " ");
        sb.append(PREFIX_IC + person.getIc().toString() + " ");
        sb.append(PREFIX_DOB + person.getDob().toString() + " ");
        sb.append(PREFIX_WARD + person.getWard().toString() + " ");
        sb.append(PREFIX_ADMISSION_DATE + person.getAdmissionDate().toString() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " "));
        sb.append(PREFIX_REMARK + person.getRemark().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.toString()).append(" "));
        descriptor.getIc().ifPresent(ic -> sb.append(PREFIX_IC).append(ic.toString()).append(" "));
        descriptor.getDob().ifPresent(dob -> sb.append(PREFIX_DOB).append(dob.toString()).append(" "));
        descriptor.getWard().ifPresent(ward -> sb.append(PREFIX_WARD).append(ward.toString()).append(" "));
        descriptor.getAdmissionDate().ifPresent(admissionDate -> sb.append(PREFIX_ADMISSION_DATE)
                .append(admissionDate.toString()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK)
                .append(remark.toString()).append(" "));
        return sb.toString();
    }
}
