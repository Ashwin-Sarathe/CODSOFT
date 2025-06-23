import java.util.ArrayList;
import java.util.Scanner;

class Student{
    String name;
    ArrayList<Double> marks;
    Student(String n, ArrayList<Double> d){
        name = n;
        marks = d;
    }
}
class gradeCalculator{
    double total_marks = 0.0;
    double average_marks = 0.0;
    String grade = "";

    double calculateTotalMarks(Student stu){
        for(int i=0; i<stu.marks.size(); i++) {
            Double element = stu.marks.get(i);
            total_marks += element;
        }
        return total_marks;
    }

    double calculateAverage(Student stu){
        average_marks = total_marks/stu.marks.size();
        return average_marks;
    }

    String calculateGrade(){
        if(average_marks>90)
            grade = "O";
        else if(average_marks>80)
            grade = "A+";
        else if(average_marks>70)
            grade = "A";
        else if(average_marks>60)
            grade = "B+";
        else if(average_marks>50)
            grade = "B";
        else if(average_marks>40)
            grade = "C";
        else if(average_marks>=33)
            grade = "D";
        else
            grade="F";
        return grade;
    }

    void displayResults(Student stu){
        System.out.println("YOUR RESULT");
        System.out.println("NAME OF STUDENT: "+stu.name);
        System.out.println("TOTAL MARKS: "+total_marks);
        System.out.println("AVERAGE MARKS: "+average_marks);
        System.out.println("GRADE OBTAINED: "+grade);
    }
}
public class Student_Grade_Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> marks = new ArrayList<>();
        System.out.println("Enter the Name of Student: ");
        String name = sc.next();
        System.out.println("Enter the Total Number of Subjects:");
        int num_of_subjects = sc.nextInt();
        if(num_of_subjects < 0){
            System.out.println("Invalid Input");
            System.exit(1);
        }
        System.out.println("Enter the marks in each subject below: (out of 100)");
        for(int i=1; i<=num_of_subjects; i++){
            System.out.println("Enter the marks in Subject "+i+ ":");
            double ip = sc.nextDouble();
            if(ip>100 || ip<0){
                System.out.println("Invalid Input");
                System.exit(1);
            }
            marks.add(ip);
        }
        Student s1 = new Student(name, marks);
        gradeCalculator s1_grade = new gradeCalculator();
        s1_grade.calculateTotalMarks(s1);
        s1_grade.calculateAverage(s1);
        s1_grade.calculateGrade();
        s1_grade.displayResults(s1);

    }
}
