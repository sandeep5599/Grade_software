import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of assignments and exams
        int numHomework = 8;
        int numQuizzes = 5;

        // Weightage for each category
        double homeworkWeight = 0.15;
        double quizWeight = 0.05;
        double midtermWeight = 0.25;
        double finalExamWeight = 0.3;
        double projectWeight = 0.25;

        // Input scores
        System.out.println("Assume Evey Test is out of 100\n");
        
        double homeworkScore = getValidScore("Homework", numHomework);
        double quizScore = getValidScore("Quiz", numQuizzes);
        double midtermScore = getValidScore("Midterm Exam");
        double finalExamScore = getValidScore("Final Exam");
        double projectScore = getValidScore("Final Project");

        // Calculate total grade
        double totalGrade = calculateTotalGrade(homeworkScore, quizScore, midtermScore, finalExamScore, projectScore,
                homeworkWeight, quizWeight, midtermWeight, finalExamWeight, projectWeight);

        // Determine letter grade
        char letterGrade = calculateLetterGrade(totalGrade);

        // Display results
        System.out.println("Total Grade: " + totalGrade);
        System.out.println("Letter Grade: " + letterGrade);

        scanner.close();
    }

    // Function to get valid scores from the user (between 0 and 100)
    private static double getValidScore(String category) {
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        double score;

        do {
            try {
                if (count != 0) {
                    System.out.print(".........Enter Again (out of 100) : ");
                } else {
                    System.out.print("Enter " + category + " score: ");
                }

                score = scanner.nextDouble();
                count++;

                if (!isValidScore(score)) {
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.nextLine(); // consume the invalid input
                score = -1; // set to an invalid value to force re-entry
            }

        } while (score < 0 || score > 100);

        return score;
    }

    // Overloaded function for quizzes and homework
    private static double getValidScore(String category, int count) {
        Scanner scanner = new Scanner(System.in);
        double total = 0;
        for (int i = 1; i <= count; i++) {
            total += getValidScore(category + " " + i);
        }
        return total / count;
    }

    // Function to validate the score (between 0 and 100)
    private static boolean isValidScore(double score) {
        return score >= 0 && score <= 100;
    }

    // Function to calculate the total grade
    private static double calculateTotalGrade(double homework, double quizzes, double midterm, double finalExam,
            double project, double hwWeight, double quizWeight, double midtermWeight, double finalExamWeight,
            double projectWeight) {
        return (homework * hwWeight) + (quizzes * quizWeight) + (midterm * midtermWeight) + (finalExam * finalExamWeight)
                + (project * projectWeight);
    }

    // Function to calculate the letter grade
    private static char calculateLetterGrade(double totalGrade) {
        if (totalGrade >= 90) {
            return 'A';
        } else if (totalGrade >= 80) {
            return 'B';
        } else if (totalGrade >= 70) {
            return 'C';
        } else if (totalGrade >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
