package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidatorTests {

    @ParameterizedTest
    @DisplayName("Valid names should return true")
    @ValueSource(strings = {"Jean-Claude", "O'Neill", "Ivan"})
    public void validNamesTest(String name) {
        assertTrue(StudentValidator.validateName(name));
    }

    @ParameterizedTest
    @DisplayName("Invalid names should return false")
    @ValueSource(strings = {"", "A", "Jean--Claude", "O''Neill",  "Ivan99", "Stanisław Oğuz", "~D0MInAt0R~"})
    public void invalidNamesTest(String name) {
        assertFalse(StudentValidator.validateName(name));
    }

    @ParameterizedTest
    @DisplayName("Valid emails should return true")
    @ValueSource(strings = {"mail@mail.mail", "navi.test99@gmail.com", "email.email@email.email", "moon_sun.e@outlook.com"})
    public void validEmailsTest(String email) {
        assertTrue(StudentValidator.validateEmail(email));
    }

    @ParameterizedTest
    @DisplayName("Invalid emails should return false")
    @ValueSource(strings = {"", "email", "*mail~7891@mail.com", "moon.sun.com", "***@mail.com", "mail@.com"})
    public void invalidEmailsTest(String email) {
        assertFalse(StudentValidator.validateEmail(email));
    }

    @ParameterizedTest
    @DisplayName("Valid course point formats should return true")
    @ValueSource(strings = {"1 1 1 1", "1 0 3 0", "0 10 0 10"})
    public void validCoursePointsFormatTest(String pointsFormat) {
        assertTrue(StudentValidator.validatePointsFormat(pointsFormat));
    }

    @ParameterizedTest
    @DisplayName("Invalid course point formats should return false")
    @ValueSource(strings = {"-1 -1 -1 -1", "-1 0 3 0", "0 10 0 10 0 10", "one three zero seven"})
    public void invalidCoursePointsFormatTest(String pointsFormat) {
        assertFalse(StudentValidator.validatePointsFormat(pointsFormat));
    }
}
