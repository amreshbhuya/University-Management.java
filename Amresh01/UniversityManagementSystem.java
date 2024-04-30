package Amresh01;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Student implements Serializable {
    private static final long serialVersionUID = 2L;

    private String name;
    private String Roll;
    private String department;

    public Student(String name, String department, String Roll) {
        this.name = name;
        this.Roll = Roll;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    
    public String getDepartment() {
        return department;
    }
    public String getRoll() {
        return Roll;
    }

}

public class UniversityManagementSystem extends JFrame {

    private static final String FILE_PATH = "students.dat";

    private List<Student> students = new ArrayList<>();

    public UniversityManagementSystem() {
        setTitle("Engineering University Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loadStudents();

        JPanel mainPanel = new JPanel(new BorderLayout());

        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton addStudentButton = createButton("Add Student", e -> addStudent());
        JButton removeStudentButton = createButton("Remove Student", e -> removeStudent());
        JButton viewStudentsButton = createButton("View Students", e -> viewStudents(outputTextArea));
        JButton searchStudentButton = createButton("Search Student", e -> searchStudent(outputTextArea));
        JButton saveExitButton = createButton("Save & Exit", e -> saveAndExit());
        JButton sortStudentsButton = createButton("Sort Students", e -> sortStudents());

        JButton statisticsButton = createButton("Statistics", e -> showStatistics(outputTextArea));
        buttonPanel.add(statisticsButton);

        buttonPanel.add(addStudentButton);
        buttonPanel.add(removeStudentButton);
        buttonPanel.add(viewStudentsButton);
        buttonPanel.add(searchStudentButton);
        buttonPanel.add(sortStudentsButton);
        buttonPanel.add(saveExitButton);

        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        return button;
    }

    private void showStatistics(JTextArea outputTextArea) {
        int totalStudents = students.size();
        long distinctDepartments = students.stream().map(Student::getDepartment).distinct().count();

        outputTextArea.setText("Statistics:\n\n");
        outputTextArea.append("Total Students: " + totalStudents + "\n");
        outputTextArea.append("Distinct Departments: " + distinctDepartments + "\n");

        // You can add more statistics as needed
    }
    private void addStudent() {
        try {
            String name = promptForInput("Enter student name:");
            validateInput(name, "Student name");

            String department = promptForInput("Enter student department:");
            validateInput(department, "Department");

            String Roll = promptForInput("Enter Student Roll number:");
            validateRollNumber(Roll);

            validateDuplicateRoll(Roll);

            Student student = new Student(name, department, Roll);
            students.add(student);

            showMessage("Student added successfully!");
            saveStudents();
        } catch (NumberFormatException e) {
            showError("Invalid roll number. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }

    private void validateRollNumber(String Roll) {
        if (!Roll.matches("\\d{2}(BTCSE|MCA|LBTCSE|BTECE|BTAML|BTEEE|LBTECE|LBTAML|LBTEEE)[A-Z0-9]{0,2}\\d{2}")) {
            throw new IllegalArgumentException("Invalid Roll number format. Please use the format: YYBBDDDRR, YYDDDRR, or YYLBBDDDRR");
          }
    }

    private void validateDuplicateRoll(String Roll) {
        if (students.stream().anyMatch(student -> student.getRoll().equals(Roll))) {
            throw new IllegalArgumentException("Student with Roll number " + Roll + " already exists.");
        }
    }

    private void validateInput(String input, String fieldName) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
    }

    private void removeStudent() {
        String studentName = promptForInput("Enter student name to remove:");
        int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove the student: " + studentName + "?", "Confirm Removal",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            students.removeIf(student -> student.getName().equalsIgnoreCase(studentName));

            showMessage("Student removed successfully!");
            saveStudents();
        }
    }

    private String promptForInput(String message) {
        return JOptionPane.showInputDialog(this, message);
    }

    private void viewStudents(JTextArea outputTextArea) {
        outputTextArea.setText("Student Information:\n\n");

        if (students.isEmpty()) {
            showMessage("No students available.");
        } else {
            students.forEach(student -> displayStudentInfo(outputTextArea, student));
        }
    }

    private void searchStudent(JTextArea outputTextArea) {
        String searchQuery = promptForInput("Enter student name or department to search:");
        List<Student> searchResults = searchStudents(searchQuery);

        if (searchResults.isEmpty()) {
            showMessage("No matching students found.");
        } else {
            outputTextArea.setText("Search Results:\n\n");
            searchResults.forEach(student -> displayStudentInfo(outputTextArea, student));
        }
    }

    private List<Student> searchStudents(String query) {
        String lowerCaseQuery = query.toLowerCase();
        return students.stream()
                .filter(student -> student.getName().toLowerCase().contains(lowerCaseQuery)
                        || student.getDepartment().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }

    private void sortStudents() {
        Object[] options = {"Sort by Name", "Sort by Roll Number", "Sort by Department"};
        int choice = JOptionPane.showOptionDialog(this, "Select sorting criteria:", "Sort Students",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            Collections.sort(students, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        } else if (choice == 1) {
            Collections.sort(students, (s1, s2) -> s1.getRoll().compareToIgnoreCase(s2.getRoll()));
        } else if (choice == 2) {
            Collections.sort(students, (s1, s2) -> s1.getDepartment().compareToIgnoreCase(s2.getDepartment()));
        }

        showMessage("Students sorted successfully!");
        saveStudents();
    }

    private void displayStudentInfo(JTextArea outputTextArea, Student student) {
        outputTextArea.append("Name: " + student.getName() +
                "\nRoll no: " + student.getRoll() +
                "\nDepartment: " + student.getDepartment() +
                "\n-------------------------\n");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(students);
        } catch (IOException e) {
            showError("Error saving students: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            students = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
           
        }
    }

    private void saveAndExit() {
        saveStudents();
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UniversityManagementSystem::new);
    }
}
