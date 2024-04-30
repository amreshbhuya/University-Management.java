package Amresh01;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UniversityProfileRegistration extends JFrame {
    private JTextField userNameField;
    private JTextField fullNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField phoneNumberField;
    private JTextField dobField;
    private JTextField addressField;
    private JComboBox<String> userTypeComboBox;
    private JPasswordField confirmPasswordField;


    private JLabel profileImageLabel;
    private String selectedImagePath;
    private Set<String> usedUserIds; 

    public UniversityProfileRegistration() {
        setTitle("University Profile Registration");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usedUserIds = new HashSet<>();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(10, 2, 10, 10));

        JLabel userNameLabel = new JLabel("User Name:");
        userNameField = new JTextField();

        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField();

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberField = new JTextField();

        JLabel dobLabel = new JLabel("Date of Birth (yyyy-MM-dd):");
        dobField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();

        JLabel userTypeLabel = new JLabel("User Type:");
        String[] userTypes = {"Student", "Faculty"};
        userTypeComboBox = new JComboBox<>(userTypes);

        JButton uploadImageButton = new JButton("Upload Profile Image");
        uploadImageButton.addActionListener(e -> uploadProfileImage());

        profileImageLabel = new JLabel();
        profileImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> handleRegistration());

        formPanel.add(userNameLabel);
        formPanel.add(userNameField);
        formPanel.add(fullNameLabel);
        formPanel.add(fullNameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(confirmPasswordLabel);
        formPanel.add(confirmPasswordField);
        formPanel.add(phoneNumberLabel);
        formPanel.add(phoneNumberField);
        formPanel.add(dobLabel);
        formPanel.add(dobField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(userTypeLabel);
        formPanel.add(userTypeComboBox);
        formPanel.add(uploadImageButton);
        formPanel.add(profileImageLabel);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(registerButton, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    private void handleRegistration() {
        String userName = userNameField.getText();
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        String phoneNumber = phoneNumberField.getText();
        String dobString = dobField.getText();
        String address = addressField.getText();
        String userType = (String) userTypeComboBox.getSelectedItem();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob;
        try {
            dob = dateFormat.parse(dobString);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Date of Birth format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userName.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || dobString.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (usedUserIds.contains(userName)) {
            JOptionPane.showMessageDialog(this, "User ID already exists. Choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            usedUserIds.add(userName); // Add the new user ID to the set
            saveUserDetailsToFile(userName, fullName, email, password, phoneNumber, dob, address, userType);
            openUniversityFrontPage(userName, userType);
            dispose();
        }
        char[] confirmPasswordChars = confirmPasswordField.getPassword();
    String confirmPassword = new String(confirmPasswordChars);

    if (!password.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(this, "Passwords do not match. Please enter matching passwords.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    }

    private void uploadProfileImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();
            displayProfileImage(selectedImagePath);
        }
    }

    private void displayProfileImage(String imagePath) {
        ImageIcon profileImageIcon = new ImageIcon(imagePath);
        Image scaledImage = profileImageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        profileImageIcon = new ImageIcon(scaledImage);
        profileImageLabel.setIcon(profileImageIcon);
    }

    private void saveUserDetailsToFile(String userName, String fullName, String email, String password, String phoneNumber, Date dob, String address, String userType) {
        String fileName = "user_details.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("User Type: " + userType + "\n");
            writer.write("User Name: " + userName + "\n");
            writer.write("Full Name: " + fullName + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("Phone Number: " + phoneNumber + "\n");
            writer.write("Date of Birth: " + new SimpleDateFormat("yyyy-MM-dd").format(dob) + "\n");
            writer.write("Address: " + address + "\n");
            if (selectedImagePath != null) {
                writer.write("Profile Image: " + selectedImagePath + "\n");
            }
            writer.write("\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openUniversityFrontPage(String userName, String userType) {
        if (userType.equals("Student")) {
            new SambalpurUniversityFrontPageForStudent(userName);
        } else if (userType.equals("Faculty")) {
            new SambalpurUniversityFrontPageForFaculty(userName);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UniversityProfileRegistration::new);
    }
}
