import java.util.*;

abstract class Question {
    private String text;
    private int points;

    Question(String text, int points) {
        this.text = text;
        this.points = points;
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    MultipleChoiceQuestion(String text, int points, String correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    TrueFalseQuestion(String text, int points, boolean correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer.trim()) == correctAnswer;
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String title;
    private List<Question> questions = new ArrayList<>();

    Examination(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Question, String> answers = new LinkedHashMap<>();
    private boolean submitted;

    Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        System.out.println(examination.getTitle() + " started by " + student.getName() + ".");
    }

    public void answer(int questionNumber, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }

        List<Question> questions = examination.getQuestions();

        if (questionNumber < 1 || questionNumber > questions.size()) {
            System.out.println("Invalid question number.");
            return;
        }

        Question question = questions.get(questionNumber - 1);
        answers.put(question, answer);
        System.out.println("Answer recorded for Question " + questionNumber + ".");
    }

    public void submit() {
        if (submitted) {
            System.out.println("Examination has already been submitted.");
            return;
        }

        submitted = true;
        System.out.println(examination.getTitle() + " submitted by " + student.getName() + ".");

        int total = 0;
        int maximum = 0;
        int index = 1;

        for (Question question : examination.getQuestions()) {
            maximum += question.getPoints();
            String answer = answers.getOrDefault(question, "");
            boolean correct = question.evaluate(answer);
            int score = correct ? question.getPoints() : 0;
            total += score;

            System.out.println("Result: Question " + index + ": "
                    + (correct ? "Correct" : "Incorrect") + " (" + score + " points)");
            index++;
        }

        System.out.println("Total score: " + total + "/" + maximum + ".");
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");
        exam.addQuestion(new MultipleChoiceQuestion(
                "Question 1: Select the correct option.", 5, "C"));
        exam.addQuestion(new TrueFalseQuestion(
                "Question 2: Is Java a procedural-only language?", 5, false));

        Attempt attempt = new Attempt(student, exam);
        attempt.answer(1, "C");
        attempt.answer(2, "True");
        attempt.submit();
        attempt.answer(1, "A");
    }
}
