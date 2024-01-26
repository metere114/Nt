package origin;

import java.sql.Time;

public class Lessons {
    private int id;
    private String lesson;
    private Time start;  // changed to 'Time' instead of 'int'
    private Time finish; // changed to 'Time' instead of 'int'
    private String student;
    private int grade;

    public Lessons() {
        // Parameter without constructor
    }

    // Modified constructor to accept 'Time' types
    public Lessons(int id, String lesson, Time start, Time finish, String student, int grade) {
        this.id = id;
        this.lesson = lesson;
        this.start = start;
        this.finish = finish;
        this.student = student;
        this.grade = grade;
    }

    // Getter and Setter, modified to handle 'Time' types
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLesson() { return lesson; }
    public void setLesson(String lesson) { this.lesson = lesson; }

    public Time getStart() { return start; } // Changed to 'Time' type
    public void setStart(Time start) { this.start = start; } // Changed to 'Time' type

    public Time getFinish() { return finish; } // Changed to 'Time' type
    public void setFinish(Time finish) { this.finish = finish; } // Changed to 'Time' type

    public String getStudent() { return student; }
    public void setStudent(String student) { this.student = student; }

    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }

    @Override
    public String toString() {
        // Update 'toString' method to display 'Time' types
        return "Lessons{" +
                "id=" + id +
                ", lesson='" + lesson + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", student='" + student + '\'' +
                ", grade=" + grade +
                '}';
    }
}
