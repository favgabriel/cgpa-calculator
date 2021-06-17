package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Marks {
    private SimpleStringProperty subject;
    private SimpleIntegerProperty credit;
    private SimpleDoubleProperty score;
    private SimpleStringProperty grade;

    public Marks(String subject,
                 int credit,
                 double score,
                 String grade) {
        this.subject = new SimpleStringProperty(subject) ;
        this.credit = new SimpleIntegerProperty(credit);
        this.score =new SimpleDoubleProperty(score) ;
        this.grade = new SimpleStringProperty(grade);
    }

    public String getSubject() {
        return subject.get ( );
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set (subject);
    }

    public int getCredit() {
        return credit.get ( );
    }

    public SimpleIntegerProperty creditProperty() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit.set (credit);
    }

    public double getScore() {
        return score.get ( );
    }

    public SimpleDoubleProperty scoreProperty() {
        return score;
    }

    public void setScore(double score) {
        this.score.set (score);
    }

    public String getGrade() {
        return grade.get ( );
    }

    public SimpleStringProperty gradeProperty() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade.set (grade);
    }
}
