package Amresh01;
import Amresh.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class SambalpurUniversityFrontPage extends JFrame {
    private ArrayList<String> allNotices = new ArrayList<>();
    private final String adminPassword = "Amresh@123"; 
    private HashMap<String, String> boysHostels;
    private HashMap<String, String> girlsHostels;
    private JButton backButton;


    public SambalpurUniversityFrontPage()  {
        setTitle("Sambalpur University");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel logoPanel = new JPanel();
        ImageIcon logo = new ImageIcon("C:\\\\Users\\\\amres\\\\OneDrive\\\\Pictures\\\\Documents\\\\Sambalpur_University_logo.png"); // Replace with your logo file path
        JLabel logoLabel = new JLabel(logo);
        logoPanel.add(logoLabel);

        
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        

        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(e -> showAbout());

        JButton addNoticeButton = new JButton("Add Notice");
        addNoticeButton.addActionListener(e -> handleAddNotice());

        buttonsPanel.add(addNoticeButton);

        JButton universityManagementButton = new JButton("University Management");
        universityManagementButton.addActionListener(e -> openUniversityManagementSystem());
        buttonsPanel.add(universityManagementButton);

        
        JButton facultyButton = new JButton("Faculty");
        facultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFacultyPage(); 
            }
        });
        JButton noticeButton = new JButton("Notice Board");
        noticeButton.addActionListener(e -> displayNotices()); 
        buttonsPanel.add(noticeButton);


        JButton hostelsButton = new JButton("Hostels");
        hostelsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHostelsPage(); 
            }
        });
        JButton antiRaggingButton = new JButton("Anti-Ragging");
        antiRaggingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAntiRaggingInfo(); 
            }
        });

        JButton studentAdmissionButton = new JButton("Student Admission");
        studentAdmissionButton.addActionListener(e -> handleStudentAdmission());

        JButton hostelAdmissionButton = new JButton("Hostel Admission");
        hostelAdmissionButton.addActionListener(e -> handleHostelAdmission());

        JButton galleryButton = new JButton("Gallery");
        galleryButton.addActionListener(e -> showGallery());

        JButton placementButton = new JButton("Placement");
        placementButton.addActionListener(e -> showPlacement());

        backButton = new JButton("Back");
        backButton.addActionListener(e -> handleBackButton());
        
        
    
        buttonsPanel.add(studentAdmissionButton);
        buttonsPanel.add(hostelAdmissionButton);
        buttonsPanel.add(galleryButton);
        buttonsPanel.add(placementButton);
        buttonsPanel.add(aboutButton);
        buttonsPanel.add(facultyButton);
        buttonsPanel.add(noticeButton);
        buttonsPanel.add(hostelsButton);
        buttonsPanel.add(antiRaggingButton);
        buttonsPanel.add(backButton);
        
        add(logoPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);
       
        

        setVisible(true);
    }

    private void handleBackButton() {
       
        SwingUtilities.invokeLater(UniversityProfileRegistration::new);
        
        this.setVisible(false);
    }

    private void openUniversityManagementSystem() {

        String password = JOptionPane.showInputDialog(this, "Enter password to access University Management System:");
    
    // Replace "YourPasswordHere" with the actual password you want to set
    if (password != null && password.equals(adminPassword)) {
        UniversityManagementSystem managementSystem = new UniversityManagementSystem();
        managementSystem.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Invalid password!", "Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }


   
    private void handleStudentAdmission() {
        
        AdmissionForm admissionForm = new AdmissionForm();
        admissionForm.setVisible(true);
    }

    private void handleHostelAdmission() {
        
        AdmissionForm admissionForm = new AdmissionForm();
        admissionForm.showHostelAdmissionForm();
    }

    
    private void showGallery() {
        JFrame galleryFrame = new JFrame("Gallery");
        galleryFrame.setSize(2000, 2000);
        galleryFrame.setLayout(new GridLayout(2, 2));
    
        String galleryFolderPath = "C:\\Users\\amres\\OneDrive\\Pictures\\faculty"; // Change this to your actual gallery folder
    
        File galleryFolder = new File(galleryFolderPath);
        File[] imageFiles = galleryFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));
    
        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                galleryFrame.add(imageLabel);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error loading gallery images.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        galleryFrame.setVisible(true);
    }
    

    private void showPlacement() {
        
        JOptionPane.showMessageDialog(this, "We will add the information soon", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    private void showAbout() {
        JFrame aboutFrame = new JFrame("About Sambalpur University");
        aboutFrame.setSize(1000, 600);
        aboutFrame.setLayout(new BorderLayout());
    
        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setEditable(false);
        aboutTextArea.setLineWrap(true);
        aboutTextArea.setWrapStyleWord(true);
    
        try (BufferedReader reader = new BufferedReader(new FileReader("about_sambalpur_university.txt"))) {
            StringBuilder aboutInfo = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                aboutInfo.append(line).append("\n");
            }
            aboutTextArea.setText(aboutInfo.toString());
        } catch (IOException e) {
            aboutTextArea.setText("Error loading about information: " + e.getMessage());
        }
    
        aboutFrame.add(new JScrollPane(aboutTextArea), BorderLayout.CENTER);
    
        aboutFrame.setVisible(true);
    }
    

    private void openFacultyPage() {
        
        HashMap<String, String[]> facultyDetails = new HashMap<>();
        
        
        facultyDetails.put("Prof. Basant Kumar Mohanty (Director)", new String[]{"C:\\Users\\amres\\OneDrive\\Pictures\\WhatsApp Image 2024-02-03 at 13.04.52_4949d8b4.jpg", 
                        
                        "Directer of SUIIT \n The Sambalpur University Institute of Information Technology (SUIIT) was established in 2010 to provide education and undertake research in information technology. Over more than a decade, SUIIT has emerged as a leading technical institution in Odisha.\n" + //
                        "\n" + //
                        "SUIIT is providing quality technical education to students and is positioned to contribute to advancing technology solutions through research and innovation. The Institute is committed to creating an ambiance for nurturing innovation, creativity, and excellence within its students. Besides technical education, SUIIT promote interdisciplinary orientation with due opportunities for co-curricular, extra-curricular, and intellectual activities focused on nurturing the individual. SUIIT is located in the cluster of Post-Graduate Departments of Sambalpur University, offering higher degree programs in Science and Technology, Humanity, Social Sciences, and Literature. SUIIT is ideally placed to use the decades of rich academic experience and achievements of Sambalpur University to nurture young minds to take up industry challenges and contribute to the digital economy through innovative solutions.\n" + //
                        "\n" + //
                        "It is a great honor to serve as the Director of Sambalpur University Institute of Information Technology. I am sure and confident that with the support and commitment of qualified and experienced faculty members, staff, and supportive administration, we shall be able to achieve our vision and contribute to the development of the region and the progress of the country."});
        facultyDetails.put("Name : Dr. Sudarson Jena\n" 
                        , new String[]{"C:\\Users\\amres\\OneDrive\\Pictures\\WhatsApp Image 2024-02-03 at 13.13.16_d05456f6.jpg", "Dr. Sudarson Jena\n" + //
                                                        "\n" + //
                                                        "Department: Computer Science Engineering & Application\n" + //
                                                        "Designation: Associate Professor & HOD\n" + //
                                                        "Teaching Experience: 21 Years\n" + //
                                                        "Phone No: +919618172201\t\n" + //
                                                        "Email: sjena@suiit.ac.in "});
        facultyDetails.put("Name : Dr. Rasmikanta Pati\n"
                        , new String[]{"c:\\Users\\amres\\OneDrive\\Pictures\\WhatsApp Image 2024-02-03 at 13.14.56_49e06cce.jpg", "Name : Dr. Rasmikanta Pati\n" + //
                        "Department: Basic Science & Humanities\n" + //
                        "Designation: Assistant Professor & HOD\n" + //
                        "Teaching Experience: 10 Years\n" + //
                        "Phone No: +91 88950 42446\t\n" + //
                        "Email: rkpati@suiit.ac.in "});
        facultyDetails.put("Amresh04", new String[]{"C:\\Users\\amres\\OneDrive\\Pictures\\Amresh.pic.jpg", "Name : Amresh Bhuyan \n"//
                        });
        facultyDetails.put("Amresh05", new String[]{"C:\\Users\\amres\\OneDrive\\Pictures\\Amresh.pic.jpg", "Assistant Professor in Computer Science. Specializes in software development."});
        facultyDetails.put("Amresh06", new String[]{"C:\\Users\\amres\\OneDrive\\Pictures\\Amresh.pic.jpg", "Assistant Professor in Computer Science. Specializes in software development."});
        facultyDetails.put("Amresh07", new String[]{"C:\\Users\\amres\\OneDrive\\Pictures\\Amresh.pic.jpg", "Assistant Professor in Computer Science. Specializes in software development."});
        facultyDetails.put("Amresh08", new String[]{"C:\\Users\\amres\\OneDrive\\Pictures\\Amresh.pic.jpg", "Assistant Professor in Computer Science. Specializes in software development."});
        
    
        JPanel facultyPanel = new JPanel(new GridLayout(facultyDetails.size(), 6));
    
        // Iterate through faculty information to display their names, images, and about information
        for (String facultyName : facultyDetails.keySet()) {
            String[] info = facultyDetails.get(facultyName);
            String imagePath = info[0];
            String about = info[1];
    
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            JLabel nameLabel = new JLabel(facultyName);
    

            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    displayFacultyDetails(facultyName, about); 
                }
            });
    
            nameLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    displayFacultyDetails(facultyName, about); 
                }
            });

            facultyPanel.add(imageLabel);
            facultyPanel.add(nameLabel);
        }
    
        
        JScrollPane scrollPane = new JScrollPane(facultyPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
        
        JFrame facultyFrame = new JFrame("Faculty Information");
        facultyFrame.add(scrollPane);
        facultyFrame.setSize(1000, 600);
        facultyFrame.setVisible(true);
    }
    
    
    private void displayFacultyDetails(String facultyName, String about) {
        JTextArea detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.setLineWrap(true);
        detailsTextArea.setWrapStyleWord(true);
        detailsTextArea.setText("Prof. Basant Kumar Mohanty (Director)" + facultyName + "\nAbout: " + about);
        detailsTextArea.setText("Name:Dr. Sudarson Jena\n" + facultyName + "\nAbout: " + about);
        detailsTextArea.setText("Name : Dr. Rasmikanta Pati\n" + facultyName + "\nAbout:BTech  " + about);
        detailsTextArea.setText("Name:" + facultyName + "\nAbout:BTech " + about);
        detailsTextArea.setText("Name:" + facultyName + "\nAbout:BTech  " + about);
        detailsTextArea.setText("Name:" + facultyName + "\nAbout:BTech " + about);
        detailsTextArea.setText("Name:" + facultyName + "\nAbout:BTech " + about);
        detailsTextArea.setText("Name:" + facultyName + "\nAbout: " + about);
        
        
        JScrollPane detailsScrollPane = new JScrollPane(detailsTextArea);
    
        
        JFrame detailsFrame = new JFrame("Faculty Details - " + facultyName);
        detailsFrame.add(detailsScrollPane);
        detailsFrame.setSize(1000, 600);
        detailsFrame.setVisible(true);
    }
    
    private void openHostelsPage() {
        boysHostels = new HashMap<>();
        girlsHostels = new HashMap<>();

        boysHostels.put("BHASKAR HALL OF RESIDENCE", "Admission Fee: Rs. 12,000");
        boysHostels.put("ARYABHATA HALL OF RESIDENCE", "Admission Fee: Rs. 12,000");
        boysHostels.put("VHR", "Admission Fee: Rs. 12,000");

        girlsHostels.put("MHR", "Admission Fee: Rs. 12,000");

        displayHostelInformation();
    }

    private void displayHostelInformation() {
        JTextArea boysHostelTextArea = new JTextArea(10, 30);
        JTextArea girlsHostelTextArea = new JTextArea(10, 30);

        boysHostelTextArea.setEditable(false);
        girlsHostelTextArea.setEditable(false);

        boysHostelTextArea.append("Boys Hostel Information:\n\n");
        for (String hostel : boysHostels.keySet()) {
            boysHostelTextArea.append(hostel + "\n" + boysHostels.get(hostel) + "\n\n");
        }

        girlsHostelTextArea.append("Girls Hostel Information:\n\n");
        for (String hostel : girlsHostels.keySet()) {
            girlsHostelTextArea.append(hostel + "\n" + girlsHostels.get(hostel) + "\n\n");
        }

        JPanel hostelPanel = new JPanel(new GridLayout(1, 2));
        hostelPanel.add(new JScrollPane(boysHostelTextArea));
        hostelPanel.add(new JScrollPane(girlsHostelTextArea));

        JFrame hostelFrame = new JFrame("Hostel Information");
        hostelFrame.add(hostelPanel);
        hostelFrame.setSize(800, 600);
        hostelFrame.setVisible(true);
    }

    private void openAntiRaggingInfo() {
        JPanel antiRaggingPanel = new JPanel(new GridLayout(2, 1));
    
        JButton antiRaggingButton = new JButton("Anti-Ragging");
        antiRaggingButton.addActionListener(e -> showAntiRaggingInfo());
        antiRaggingPanel.add(antiRaggingButton);
    
        JButton reportRaggingButton = new JButton("Report Ragging Incident");
        reportRaggingButton.addActionListener(e -> reportRaggingIncident());
        antiRaggingPanel.add(reportRaggingButton);
    
        JOptionPane.showMessageDialog(this, antiRaggingPanel, "Anti-Ragging", JOptionPane.PLAIN_MESSAGE);
    }
    
    private void showAntiRaggingInfo() {
        
        JOptionPane.showMessageDialog(this, "Anti-Ragging Information: \nPlease adhere to the anti-ragging policies.", "Anti-Ragging Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void reportRaggingIncident() {
        JTextField rollNumberField = new JTextField(10);
        JTextField nameField = new JTextField(20);
        JTextField mobileNumberField = new JTextField(15);
        JTextArea noteArea = new JTextArea(5, 20);

        JPanel reportPanel = new JPanel(new GridLayout(4, 1));
        reportPanel.add(new JLabel("Roll Number:"));
        reportPanel.add(rollNumberField);
        reportPanel.add(new JLabel("Name:"));
        reportPanel.add(nameField);
        reportPanel.add(new JLabel("Mobile Number:"));
        reportPanel.add(mobileNumberField);
        reportPanel.add(new JLabel("Note:"));
        reportPanel.add(new JScrollPane(noteArea));

        int option = JOptionPane.showConfirmDialog(this, reportPanel, "Report Ragging Incident", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String rollNumber = rollNumberField.getText();
            String name = nameField.getText();
            String mobileNumber = mobileNumberField.getText();
            String note = noteArea.getText();

            saveReportToFile(rollNumber, name, mobileNumber, note);
        }
    }

    private void saveReportToFile(String rollNumber, String name, String mobileNumber, String note) {
        String fileName = "ragging_reports.txt"; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            
            writer.write("Roll Number: " + rollNumber + "\n");
            writer.write("Name: " + name + "\n");
            writer.write("Mobile Number: " + mobileNumber + "\n");
            writer.write("Note: " + note + "\n\n");

            String reportInfo = "Reported Information:\nRoll Number: " + rollNumber + "\nName: " + name + "\nMobile Number: " + mobileNumber + "\nNote: " + note;
        JOptionPane.showMessageDialog(this, reportInfo, "Reported Ragging Incident", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving the report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void displayNotices() {
        JTextArea noticesTextArea = new JTextArea();
        noticesTextArea.setEditable(false);
        noticesTextArea.setLineWrap(true);
        noticesTextArea.setWrapStyleWord(true);
    
        StringBuilder allNoticesText = new StringBuilder();
        allNoticesText.append("Existing Notices:\n");
        for (String notice : allNotices) {
            allNoticesText.append(notice).append("\n");
        }
        noticesTextArea.setText(allNoticesText.toString());
    
        JScrollPane scrollPane = new JScrollPane(noticesTextArea);
    
        JFrame noticesFrame = new JFrame("Notices");
        noticesFrame.add(scrollPane);

        if (addedImageNotice != null) {
            JLabel imageLabel = new JLabel(addedImageNotice);
            noticesFrame.add(imageLabel, BorderLayout.SOUTH); // Display the added image at the bottom
        }
        noticesFrame.setSize(400, 400);
        noticesFrame.setVisible(true);
    }
    private void handleAddNotice() {
        String password = JOptionPane.showInputDialog(this, "Enter admin password:");
        if (password != null && password.equals(adminPassword)) {
            JPanel noticePanel = new JPanel(new GridLayout(2, 1));

            JTextArea noticeTextArea = new JTextArea(10, 30);
            noticeTextArea.setLineWrap(true);
            noticeTextArea.setWrapStyleWord(true);
            noticePanel.add(new JScrollPane(noticeTextArea));

            JButton addImageButton = new JButton("Add Image");
            addImageButton.addActionListener(e -> handleImageNotice());
            noticePanel.add(addImageButton);

            int option = JOptionPane.showConfirmDialog(this, noticePanel, "Add Notice", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String textNotice = noticeTextArea.getText();
                if (!textNotice.isEmpty()) {
                    allNotices.add(textNotice);
                    JOptionPane.showMessageDialog(this, "Text Notice Added Successfully!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private ImageIcon addedImageNotice; 
    private ImageIcon resizeImage(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    private void handleImageNotice() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon selectedImage = new ImageIcon(selectedFile.getAbsolutePath());
            
            ImageIcon resizedImage = resizeImage(selectedImage, 200, 200); 
            
            addedImageNotice = resizedImage;
            JOptionPane.showMessageDialog(this, "Image Notice Added!", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SambalpurUniversityFrontPage::new);
    }
}