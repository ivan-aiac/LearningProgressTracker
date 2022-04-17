package tracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NotificationTests {

    private final StudentService studentService;
    private List<Notification> notifications;
    private final int EXPECTED_NOTIFICATIONS = 2;
    private final int EXPECTED_NOTIFIED_STUDENTS = 1;

    public NotificationTests() {
        studentService = new StudentService(new StudentDatabase());
    }

    @BeforeAll
    public void setUp() {
        studentService.addStudent("John Doe johnd@email.net");
        studentService.addStudent("Jane Spark jspark@yahoo.com");
        studentService.addCoursePoints("10000 600 400 0 0");
        notifications = studentService.getStudentsFinishedCoursesNotifications();
    }

    @Test
    @DisplayName("Notification list should have the correct number of notifications")
    public void notificationsTest(){
        assertEquals(EXPECTED_NOTIFICATIONS, notifications.size());
    }

    @Test
    @DisplayName("Number of notified students should be correct")
    public void notifiedStudentsTest() {
        assertEquals(EXPECTED_NOTIFIED_STUDENTS, studentService.getLastNumberOfNotifiedStudents());
    }

}
