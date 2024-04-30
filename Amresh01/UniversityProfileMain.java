package Amresh01;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;



class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String hashedPassword;
    private String userType;

    public UserProfile(String userId, String password, String userType) {
        this.userId = userId;
        this.hashedPassword = hashPassword(password);
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserType() {
        return userType;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean verifyPassword(String password) {
        return hashedPassword.equals(hashPassword(password));
    }
}

class UniversityProfileView {
    private JFrame frame;
    private JTextField userIdField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    
    public UniversityProfileView() {
        frame = new JFrame("University Profile System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 2));

        frame.add(new JLabel("User ID:"));
        userIdField = new JTextField();
        frame.add(userIdField);

        frame.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        frame.add(passwordField);

        

        frame.add(new JLabel("User Type:"));
        String[] userTypes = {"Student", "Faculty"};
        userTypeComboBox = new JComboBox<>(userTypes);
        frame.add(userTypeComboBox);
    }

    
    public String getUserId() {
        return userIdField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());

        
    }

    public String getUserType() {
        return (String) userTypeComboBox.getSelectedItem();
    }

    public void clearInputFields() {
        userIdField.setText("");
        passwordField.setText("");
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public void addActionCreateProfile(ActionListener listener) {
        JButton createProfileButton = new JButton("Create Profile");
        createProfileButton.addActionListener(listener);
        frame.add(createProfileButton);
    }

    public void addActionLogin(ActionListener listener) {
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(listener);
        frame.add(loginButton);
    }
    public void show() {
        frame.setVisible(true);
    }
}

class UniversityProfileController {
    private List<UserProfile> userProfiles;
    private UniversityProfileView view;
    private UserProfile currentUser;

    public UniversityProfileController() {
        userProfiles = new ArrayList<>();
        view = new UniversityProfileView();
        loadUserProfilesFromFile();

        view.addActionCreateProfile(new CreateProfileAction());
        view.addActionLogin(new LoginAction());

        view.show();
    }

    private void loadUserProfilesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user_profiles.dat"))) {
            userProfiles = (List<UserProfile>) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            view.showMessage("Error loading user profiles.");
        }
    }

    private void saveUserProfilesToFile() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_profiles.txt"))) {
                    oos.writeObject(userProfiles);
                } catch (IOException e) {
                    e.printStackTrace();
                    view.showMessage("Error saving user profiles.");
                }
                return null;
            }
        };

        worker.execute();
    }

    private class CreateProfileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userId = view.getUserId();
            String password = view.getPassword();
            String userType = view.getUserType();

            if (userId.isEmpty() || password.isEmpty()) {
                view.showMessage("User ID and Password cannot be empty.");
                return;
            }

            if (!isValidUserId(userId)) {
                view.showMessage("User ID can only contain alphanumeric characters.");
                return;
            }

            for (UserProfile profile : userProfiles) {
                if (profile.getUserId().equals(userId)) {
                    view.showMessage("User ID already exists. Choose a different one...!");
                    return;
                }
            }

            UserProfile newUser = new UserProfile(userId, password, userType);
            userProfiles.add(newUser);
            saveUserProfilesToFile();
            view.clearInputFields();
            view.showMessage("Profile created successfully.");
    
            SwingUtilities.invokeLater(UniversityProfileRegistration::new);
            
            
        }

        private boolean isValidUserId(String userId) {
            return userId.matches("[a-zA-Z0-9]+");
        }
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userId = view.getUserId();
            String password = view.getPassword();

            if (userId.isEmpty() || password.isEmpty()) {
                view.showMessage("User ID and Password cannot be empty.");
                return;
            }

            UserProfile userProfile = findUserProfileById(userId);

            if (userProfile != null) {
                if (userProfile.verifyPassword(password)) {
                    currentUser = userProfile;
                    view.showMessage("Login successful. Welcome, " + userProfile.getUserType() + "!");
                    SwingUtilities.invokeLater(UniversityProfileRegistration::new);
                } else {
                    view.showMessage("Invalid Password.");
                }
            } else {
                view.showMessage("User ID not found.");
            }
        }
    }

    private UserProfile findUserProfileById(String userId) {
        for (UserProfile profile : userProfiles) {
            if (profile.getUserId().equals(userId)) {
                return profile;
            }
        }
        return null;
    }
}

public class UniversityProfileMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UniversityProfileController();
            }
        });
    }
}
