package origin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LessonsTest {

    private Lessons lesson;
    private final int id = 1;
    private final String lessonName = "Math";
    private final String startTime = "08:00:00";
    private final String finishTime = "09:00:00";
    private final String studentName = "John Doe";
    private final int grade = 5;

    @BeforeEach
    void setUp() {
        lesson = new Lessons(id, lessonName, startTime, finishTime, studentName, grade);
    }

    @Test
    void testConstructor() {
        assertNotNull(lesson, "Lesson object should not be null");
    }

    @Test
    void testGetters() {
        assertEquals(id, lesson.getId(), "Getter for id failed");
        assertEquals(lessonName, lesson.getLesson(), "Getter for lesson name failed");
        assertEquals(startTime, lesson.getStart(), "Getter for start time failed");
        assertEquals(finishTime, lesson.getFinish(), "Getter for finish time failed");
        assertEquals(studentName, lesson.getStudent(), "Getter for student name failed");
        assertEquals(grade, lesson.getGrade(), "Getter for grade failed");
    }

    @Test
    void testSetters() {
        int newId = 2;
        String newLessonName = "Science";
        String newStartTime = "10:00:00";
        String newFinishTime = "11:00:00";
        String newStudentName = "Alice Smith";
        int newGrade = 4;

        lesson.setId(newId);
        lesson.setLesson(newLessonName);
        lesson.setStart(newStartTime);
        lesson.setFinish(newFinishTime);
        lesson.setStudent(newStudentName);
        lesson.setGrade(newGrade);

        assertEquals(newId, lesson.getId(), "Setter for id failed");
        assertEquals(newLessonName, lesson.getLesson(), "Setter for lesson name failed");
        assertEquals(newStartTime, lesson.getStart(), "Setter for start time failed");
        assertEquals(newFinishTime, lesson.getFinish(), "Setter for finish time failed");
        assertEquals(newStudentName, lesson.getStudent(), "Setter for student name failed");
        assertEquals(newGrade, lesson.getGrade(), "Setter for grade failed");
    }

    @Test
    void testToString() {
        String expectedString = "Lessons{" +
                "id=" + id +
                ", lesson='" + lessonName + '\'' +
                ", start=" + startTime +
                ", finish=" + finishTime +
                ", student='" + studentName + '\'' +
                ", grade=" + grade +
                '}';
        assertEquals(expectedString, lesson.toString(), "toString method failed");
    }
}
