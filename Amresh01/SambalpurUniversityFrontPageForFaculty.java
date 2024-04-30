package Amresh01;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SambalpurUniversityFrontPageForFaculty extends SambalpurUniversityFrontPage {

    private String userName;
    private JLabel profileImageLabel;

    public SambalpurUniversityFrontPageForFaculty(String userProfile) {
        super();
        this.userName = userProfile;
        setTitle("Sambalpur University - Faculty Portal");

        JButton viewProfileButton = new JButton("View Profile");
        viewProfileButton.addActionListener(e -> viewFacultyProfile());

        JButton uploadGradesButton = new JButton("Upload Grades");
        uploadGradesButton.addActionListener(e -> uploadFacultyGrades());

        JButton uploadImageButton = new JButton("Upload Profile Image");
        uploadImageButton.addActionListener(e -> uploadProfileImage());

        profileImageLabel = new JLabel();
        profileImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel facultyPanel = new JPanel();
        facultyPanel.add(viewProfileButton);
        facultyPanel.add(uploadGradesButton);
        facultyPanel.add(uploadImageButton);

        add(profileImageLabel, BorderLayout.WEST);
        add(facultyPanel, BorderLayout.SOUTH);
    }

    private void viewFacultyProfile() {
        String userDetails = getUserDetails(userName);
        JTextArea profileTextArea = new JTextArea(userDetails);
        profileTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(profileTextArea);

        JFrame profileFrame = new JFrame("Faculty Profile - " + userName);
        profileFrame.add(scrollPane);
        profileFrame.setSize(800, 600);
        profileFrame.setVisible(true);
    }

    private void uploadFacultyGrades() {
    JTextField courseField = new JTextField();
    JTextField gradeField = new JTextField();

    Object[] message = {
            "Course:", courseField,
            "Grade:", gradeField
    };

    int option = JOptionPane.showConfirmDialog(this, message, "Upload Grades", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
        String course = courseField.getText();
        String grade = gradeField.getText();

        if (course.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            
            saveFacultyGrades(userName, course, grade);
            JOptionPane.showMessageDialog(this, "Grades Uploaded!", "Upload Grades", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

private void saveFacultyGrades(String userName, String course, String grade) {
    
    String fileName = "faculty_grades.txt";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
        writer.write("Faculty Name: " + userName + "\n");
        writer.write("Course: " + course + "\n");
        writer.write("Grade: " + grade + "\n\n");
    } catch (IOException e) {
        e.printStackTrace();
    }
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
