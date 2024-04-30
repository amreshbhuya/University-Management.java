package Amresh01;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SambalpurUniversityFrontPageForStudent extends SambalpurUniversityFrontPage {

    private String userName;
    private JLabel profileImageLabel;

    public SambalpurUniversityFrontPageForStudent(String userProfile) {
        super();
        this.userName = userProfile;
        setTitle("Sambalpur University - Student Portal");

        JButton viewProfileButton = new JButton("View Profile");
        viewProfileButton.addActionListener(e -> viewStudentProfile());

        JButton viewGradesButton = new JButton("View Grades");
        viewGradesButton.addActionListener(e -> viewStudentGrades());

        JButton uploadImageButton = new JButton("Upload Profile Image");
        uploadImageButton.addActionListener(e -> uploadProfileImage());

        profileImageLabel = new JLabel();
        profileImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel studentPanel = new JPanel();
        studentPanel.add(viewProfileButton);
        studentPanel.add(viewGradesButton);
        studentPanel.add(uploadImageButton);

        add(profileImageLabel, BorderLayout.WEST);
        add(studentPanel, BorderLayout.SOUTH);
    }

    private void viewStudentProfile() {
        String userDetails = getUserDetails(userName);
        JTextArea profileTextArea = new JTextArea(userDetails);
        profileTextArea.setEditable(true);

        JScrollPane scrollPane = new JScrollPane(profileTextArea);

        JFrame profileFrame = new JFrame("Student Profile - " + userName);
        profileFrame.add(scrollPane);
        profileFrame.setSize(800, 600);
        profileFrame.setVisible(true);
    }

    private void viewStudentGrades() {
        String userName = this.userName; 
        String grades = getStudentGrades(userName);

        JTextArea gradesTextArea = new JTextArea(grades);
        gradesTextArea.setEditable(true);

        JScrollPane scrollPane = new JScrollPane(gradesTextArea);

        JFrame gradesFrame = new JFrame("Student Grades - " + userName);
        gradesFrame.add(scrollPane);
        gradesFrame.setSize(600, 400);
        gradesFrame.setVisible(true);
    }

    private String getStudentGrades(String userName) {
    
        String fileName = "student_grades.txt";
        StringBuilder grades = new StringBuilder();

        try {
            Files.lines(Paths.get(fileName))
                    .filter(line -> line.contains("User Name: " + userName))
                    .findFirst()
                    .ifPresent(grades::append);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return grades.toString();
    }

    private void uploadProfileImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            Path selectedImagePath = fileChooser.getSelectedFile().toPath();
            displayProfileImage(selectedImagePath.toString());
            saveProfileImagePath(userName, selectedImagePath.toString());
            JOptionPane.showMessageDialog(this, "Profile Image Uploaded!", "Profile Image", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void displayProfileImage(String imagePath) {
        ImageIcon profileImageIcon = new ImageIcon(imagePath);
        Image scaledImage = profileImageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        profileImageIcon = new ImageIcon(scaledImage);
        profileImageLabel.setIcon(profileImageIcon);
    }

    private void saveProfileImagePath(String userName, String imagePath) {
        
        String fileName = "user_details.txt";

        try {
            String userDetails = Files.readString(Paths.get(fileName));
            userDetails = userDetails.replace("User Name: " + userName, "User Name: " + userName + "\nProfile Image: " + imagePath);
            Files.writeString(Paths.get(fileName), userDetails);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUserDetails(String userName) {
        
        String fileName = "user_details.txt";
        StringBuilder userDetails = new StringBuilder();

        try {
            Files.lines(Paths.get(fileName))
                    .filter(line -> line.contains("User Name: " + userName))
                    .findFirst()
                    .ifPresent(userDetails::append);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userDetails.toString();
    }
}
