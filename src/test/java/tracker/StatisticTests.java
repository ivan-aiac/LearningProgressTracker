package tracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StatisticTests {

    private final StudentService studentService;
    private CourseStatistics statistics;
    private CourseStatistics expectedStatistics;
    private StudentStatistic topStudentStatistics;
    private static final String TOP_STUDENT_TEST_COURSE = "Java";
    private static final String EXPECTED_TOP_STUDENT_ID = "10001";
    private static final int EXPECTED_TOP_STUDENT_POINTS = 60;
    private static final double EXPECTED_TOP_STUDENT_PERCENTAGE = 10.0;

    public StatisticTests() {
        studentService = new StudentService(new StudentDatabase());
    }

    @BeforeAll
    public void setUp() {
        studentService.addStudent("First Student test1@test.test");
        studentService.addStudent("Second Student test2@test.test");
        studentService.addCoursePoints("10000 10 0 0 1");
        studentService.addCoursePoints("10000 20 0 0 0");
        studentService.addCoursePoints("10001 10 0 0 0");
        studentService.addCoursePoints("10001 20 0 3 1");
        studentService.addCoursePoints("10001 30 2 3 1");
        statistics = studentService.getStatistics();
        expectedStatistics = new CourseStatistics();
        expectedStatistics.setMostPopular("Java, Spring");
        expectedStatistics.setLeastPopular("DSA, Databases");
        expectedStatistics.setHighestActivity("Java");
        expectedStatistics.setLowestActivity("DSA");
        expectedStatistics.setEasiest("Java");
        expectedStatistics.setHardest("Spring");
        topStudentStatistics = studentService.getBestStudentsByCourse(TOP_STUDENT_TEST_COURSE).get(0);
    }

    @Test
    @DisplayName("Most popular course test")
    public void mostPopularTest() {
        assertEquals(expectedStatistics.getMostPopular(), statistics.getMostPopular());
    }

    @Test
    @DisplayName("Least popular course test")
    public void leastPopularTest() {
        assertEquals(expectedStatistics.getLeastPopular(), statistics.getLeastPopular());
    }

    @Test
    @DisplayName("Highest expected activity course test")
    public void highestActivityTest() {
        assertEquals(expectedStatistics.getHighestActivity(), statistics.getHighestActivity());
    }

    @Test
    @DisplayName("Lowest activity course test")
    public void lowestActivityTest() {
        assertEquals(expectedStatistics.getLowestActivity(), statistics.getLowestActivity());
    }

    @Test
    @DisplayName("Easiest course test")
    public void easiestTest() {
        assertEquals(expectedStatistics.getEasiest(), statistics.getEasiest());
    }

    @Test
    @DisplayName("Hardest course test")
    public void hardestTest() {
        assertEquals(expectedStatistics.getHardest(), statistics.getHardest());
    }

    @Test
    @DisplayName("Top student id test")
    public void topStudentIdTest() {
        assertEquals(EXPECTED_TOP_STUDENT_ID, topStudentStatistics.getId());
    }

    @Test
    @DisplayName("Top student points test")
    public void topStudentPointsTest() {
        assertEquals(EXPECTED_TOP_STUDENT_POINTS, topStudentStatistics.getPoints());
    }

    @Test
    @DisplayName("Top student completed percentage test")
    public void topStudentPercentageTest() {
        assertEquals(EXPECTED_TOP_STUDENT_PERCENTAGE, topStudentStatistics.getCompleted());
    }

}
