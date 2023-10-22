import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }
    public String getName() {
        return name;
    }
    public int getRollNumber() {
        return rollNumber;
    }
    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}
class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    public void addStudent(Student student) {
        students.add(student);
    }
    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }
    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }
    public List<Student> getAllStudents() {
        return students;
    }
    public void saveStudentDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student_data.ser"))) {
            oos.writeObject(students);
            System.out.println("Student data saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadStudentDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student_data.ser"))) {
            students = (List<Student>) ois.readObject();
            System.out.println("Student data loaded from file.");
        } catch (FileNotFoundException e) {
            students = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
public class StudentManagement{
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        system.loadStudentDataFromFile();
        while (true) {
            while (true) {
                System.out.println("\nStudent Management System Menu:");
                System.out.println("1. Add Student");
                System.out.println("2. Remove Student");
                System.out.println("3. Search Student");
                System.out.println("4. Display All Students");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter student name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter roll number: ");
                        int rollNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        System.out.print("Enter grade: ");
                        String grade = scanner.nextLine();

                        Student newStudent = new Student(name, rollNumber, grade);
                        system.addStudent(newStudent);
                        System.out.println("Student added successfully.");
                        break;

                    case 2:
                        System.out.print("Enter the roll number of the student to remove: ");
                        int rollNumberToRemove = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        system.removeStudent(rollNumberToRemove);
                        System.out.println("Student removed.");
                        break;

                    case 3:
                        System.out.print("Enter the roll number of the student to search: ");
                        int rollNumberToSearch = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        Student foundStudent = system.searchStudent(rollNumberToSearch);
                        if (foundStudent != null) {
                            System.out.println("Student found: " + foundStudent);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 4:
                        List<Student> allStudents = system.getAllStudents();
                        if (allStudents.isEmpty()) {
                            System.out.println("No students to display.");
                        } else {
                            System.out.println("All Students:");
                            for (Student student : allStudents) {
                                System.out.println(student);
                            }
                        }
                        break;

                    case 5:
                        system.saveStudentDataToFile();
                        System.out.println("Exiting Student Management System.");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                        break;
                }
            }
        }
    }
}
