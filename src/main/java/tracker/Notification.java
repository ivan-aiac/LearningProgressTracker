package tracker;

import java.text.MessageFormat;

public class Notification {
    private static final String PATTERN = "To: {0}{1}Re: Your Learning Progress{1}Hello, {2} {3}! You have accomplished our {4} course!{1}";
    private final String message;

    public Notification(Student student, Course course){
        message = MessageFormat.format(PATTERN, student.getEmail(), System.lineSeparator(),
                student.getFirstName(), student.getLastName(), course.getName());
    }

    @Override
    public String toString() {
        return message;
    }
}
