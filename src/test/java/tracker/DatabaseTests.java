package tracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DatabaseTests {

    private final StudentService studentService;
    private static final String TEST_ID = "10000";

    public DatabaseTests() {
        studentService = new StudentService(new StudentDatabase());
    }

    @BeforeAll
    public void setUp() {
        studentService.addStudent("First Student test1@test.test");
    }

    @Test
    @DisplayName("Add student with the same email should return false")
    public void sameEmailTest() {
        assertFalse(studentService.addStudent("Test Student test1@test.test"));
    }

    @ParameterizedTest
    @DisplayName("Course points should be correct after being added")
    @CsvSource({"1 1 1 1,1 1 1 1", "9 9 9 9,10 10 10 10", "0 90 0 0,10 100 10 10"})
    public void addPointsTest(String points, String expected) {
        studentService.addCoursePoints(String.format("%s %s", TEST_ID, points));
        int[] studentPoints = studentService.getStudentPoints(TEST_ID).get().values().stream()
                .mapToInt(v -> v).toArray();
        int[] expectedPoints = Arrays.stream(expected.split(" "))
                .mapToInt(Integer::parseInt).toArray();
        assertArrayEquals(expectedPoints, studentPoints);
    }

}