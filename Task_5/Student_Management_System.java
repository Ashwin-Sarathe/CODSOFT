import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Student implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private int id;
    private int age;
    private double gpa;
    void setName(String name){
        this.name = name;
    }
    void setId(int id){
        this.id = id;
    }
    void setAge(int age){
        this.age=age;
    }
    void setGpa(double gpa){
        this.gpa=gpa;
    }
    String getName(){
        return name;
    }
    int getId(){
        return id;
    }
     int getAge(){
        return age;
    }
    double getGpa(){
        return gpa;
    }
    @Override
    public String toString(){
        return "ID: "+id+", Name: "+name+", Age: "+age+", GPA: "+gpa;
    }
}
class StudentManager{
    private final List<Student> Students;
    public StudentManager() {
        List<Student> loaded = FileHandler.loadStudents();
        if (loaded != null) {
            this.Students = loaded;
        } else {
            this.Students = new ArrayList<>();
        }
    }
    public synchronized void addStudent(Student s) {
        Students.add(s);
        FileHandler.saveStudents(Students);
    }
    public boolean removeStudentById(int id){
        Iterator<Student> it = Students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getId() == id) {
                it.remove();
                FileHandler.saveStudents(Students);
                return true;
            }
        }
        return false;
    }
    public synchronized Student searchStudentById(int id) {
        for (Student s : Students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
    public synchronized boolean updateStudentById(int id, Student newData) {
        for (Student s : Students) {
            if (s.getId() == id) {
                s.setName(newData.getName());
                s.setAge(newData.getAge());
                s.setGpa(newData.getGpa());
                FileHandler.saveStudents(Students);
                return true;
            }
        }
        return false;
    }
    public synchronized List<Student> getAllStudents() {
        return Students;
    }

}
class FileHandler {
    private static final String FILE_PATH = "students.dat";

    public static void saveStudents(List<Student> students) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    public static List<Student> loadStudents() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Student>) in.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading student data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
public class Student_Management_System {
    private static void addStudent(Scanner sc, StudentManager manager) {
        Student s = new Student();

        System.out.print("Enter Name: ");
        s.setName(sc.nextLine());

        System.out.print("Enter ID: ");
        s.setId(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        while (age < 16 || age > 35) {
            System.out.print("Invalid Age. Enter again: ");
            age = sc.nextInt();
        }
        s.setAge(age);
        sc.nextLine();

        System.out.print("Enter GPA: ");
        double gpa = sc.nextDouble();
        while (gpa < 0.0 || gpa > 10.0) {
            System.out.print("Invalid GPA. Enter again: ");
            gpa = sc.nextDouble();
        }
        s.setGpa(gpa);
        sc.nextLine();

        manager.addStudent(s);
        System.out.println("Student added and saved.");
    }
    private static void viewAllStudents(StudentManager manager) {
        List<Student> all = manager.getAllStudents();
        if (all.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : all) {
                System.out.println(s);
            }
        }
    }
    private static void searchStudentById(Scanner sc, StudentManager manager) {
        System.out.print("Enter ID to search: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student s = manager.searchStudentById(id);
        if (s != null) {
            System.out.println("Student Found: " + s);
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }
    private static void updateStudentById(Scanner sc, StudentManager manager) {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student existing = manager.searchStudentById(id);
        if (existing == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        Student updated = new Student();
        updated.setId(id);

        System.out.print("Enter new Name: ");
        updated.setName(sc.nextLine());

        System.out.print("Enter new Age: ");
        int age = sc.nextInt();
        while (age < 16 || age > 35) {
            System.out.print("Invalid. Enter Age again: ");
            age = sc.nextInt();
        }
        updated.setAge(age);
        sc.nextLine();

        System.out.print("Enter new GPA: ");
        double gpa = sc.nextDouble();
        while (gpa < 0.0 || gpa > 10.0) {
            System.out.print("Invalid. Enter GPA again: ");
            gpa = sc.nextDouble();
        }
        updated.setGpa(gpa);
        sc.nextLine();

        boolean success = manager.updateStudentById(id, updated);
        if (success) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Update failed.");
        }
    }
    private static void removeStudentById(Scanner sc, StudentManager manager) {
        System.out.print("Enter ID to remove: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean removed = manager.removeStudentById(id);
        if (removed) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student by ID");
            System.out.println("5. Remove Student by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent(sc, manager);
                case 2 -> viewAllStudents(manager);
                case 3 -> searchStudentById(sc, manager);
                case 4 -> updateStudentById(sc, manager);
                case 5 -> removeStudentById(sc, manager);
                case 6 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
