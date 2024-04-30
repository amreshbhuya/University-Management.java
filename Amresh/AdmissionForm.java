package Amresh;
import javax.swing.*;
import Amresh01.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class AdmissionForm extends  JFrame  implements ActionListener {

    private JTextField nameField, jeeRankField, mark12Field, mark10Field, fatherNameField, collegeNameField, schoolNameField,
            jeeRollNumberField, addressField, postField, blockField, distField, stateField, pinCodeField,
            mobileNumberField;
    private JComboBox<String> admissionTypeComboBox;
    private JComboBox<String> countryTypeComboBox;
    private JComboBox<String> genderTypeComboBox;
    private JComboBox<String> categoryTypeComboBox;
    private JComboBox<String> religionTypeComboBox;
    private JComboBox<String> courseTypeComboBox;
    private JComboBox<String> paymentTypeComboBox;
    private JComboBox<String> hostelFrameComboBox;
    private JComboBox<String> dayComboBox, monthComboBox, yearComboBox;
    private JPanel dobPanel; 
    private JCheckBox acceptAllCheckBox;
    private JTextArea printTextArea;
    private JLabel photoLabel; // Added label for displaying the photo
    private JTextArea finalDetailsTextArea;
    private JButton backButton;
    

    public AdmissionForm() {
         
       
        setTitle("Admission Form - Sambalpur University");
        setSize(2000, 2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(13, 2));


        add(new JLabel("Candidate Name(In capital):"));
        nameField = new JTextField();
        add(nameField);
    

        add(new JLabel("Gender:"));
        genderTypeComboBox = new JComboBox<>(new String[]{"MALE","FEMALE"});
        add(genderTypeComboBox);
    
            add(new JLabel("Date of Birth:"));
    
            dobPanel = new JPanel(); 
            dobPanel.setLayout(new FlowLayout()); 
    
            // Day ComboBox
            dayComboBox = new JComboBox<>();
            for (int i = 1; i <= 31; i++) {
                dayComboBox.addItem(String.valueOf(i));
            }
            dobPanel.add(dayComboBox);
    
            // Month ComboBox
            monthComboBox = new JComboBox<>(new String[]{ "January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"});
            dobPanel.add(monthComboBox);
    
            // Year ComboBox
            yearComboBox = new JComboBox<>();
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = currentYear - 60; i <= currentYear; i++) {
                yearComboBox.addItem(String.valueOf(i));
            }
            dobPanel.add(yearComboBox);
    
            add(dobPanel); // Add Date of Birth panel to the JFrame
    

        add(new JLabel("Category:"));
        categoryTypeComboBox = new JComboBox<>(new String[]{"GENERAL","OBC","SC","ST"});
        add(categoryTypeComboBox);

        add(new JLabel("Religion:"));
        religionTypeComboBox = new JComboBox<>(new String[]{"HINDU","CHRISTIAN","MUSLIM","ISLAM","SIKH","BUDDHISM","JAINISM"});
        add(religionTypeComboBox);

        

        add(new JLabel("JEE Main Rank:"));
        jeeRankField = new JTextField();
        add(jeeRankField);

        add(new JLabel("12th Marks(%)/Diploma mark(%):"));
        mark12Field = new JTextField();
        add(mark12Field);

        add(new JLabel("10th Marks(%):"));
        mark10Field = new JTextField();
        add(mark10Field);

        add(new JLabel("Father's Name"));
        fatherNameField = new JTextField();
        add(fatherNameField);

        add(new JLabel("College Name:"));
        collegeNameField = new JTextField();
        add(collegeNameField);

        add(new JLabel("10th School Name:"));
        schoolNameField = new JTextField();
        add(schoolNameField);

        add(new JLabel("JEE Main Roll Number:"));
        jeeRollNumberField = new JTextField();
        add(jeeRollNumberField);

        add(new JLabel("Country:"));
        countryTypeComboBox = new JComboBox<>(new String[]{"India","Bangladesh", "Pakistan", "Nepal", "Bhutan", "China", "Myanmar", "Sri Lanka","United States of America", "United Kingdom", "Canada", "Australia", "Russia", "Japan", "Germany"});
        add(countryTypeComboBox);

        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        add(new JLabel("Post:"));
        postField = new JTextField();
        add(postField);

        add(new JLabel("Block:"));
        blockField = new JTextField();
        add(blockField);

        add(new JLabel("District:"));
        distField = new JTextField();
        add(distField);

        add(new JLabel("State:"));
        stateField = new JTextField();
        add(stateField);

        add(new JLabel("Pin Code:"));
        pinCodeField = new JTextField();
        add(pinCodeField);

        add(new JLabel("Mobile Number:"));
        mobileNumberField = new JTextField();
        add(mobileNumberField);

        add(new JLabel("Admission Type:"));
        admissionTypeComboBox = new JComboBox<>(new String[]{"BTech", "MTech", "Lateral Entry BTech","MCA"});
        add(admissionTypeComboBox);

        add(new JLabel("Intrested Course:"));
        courseTypeComboBox = new JComboBox<>(new String[]{"Computer Science & Engineering","Electrical Engineering","ECE (Electronics and Communications Engineering)","Electronics & Communication Engineering (ECE)","(CSE-AIML)Computer Science & Engineering specialization in Artificial Intelligence and Machine Learning","Computer Science & Engineering specialization with cyber security"});
        add(courseTypeComboBox);
        
         JButton uploadPhotoButton = new JButton("Upload Photo");
        uploadPhotoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    displaySelectedPhoto(selectedFile);
                }
            }
        });
        add(uploadPhotoButton);

        photoLabel = new JLabel();
        add(photoLabel);

    
        acceptAllCheckBox = new JCheckBox("Accept All Conditions\n");
        add(acceptAllCheckBox);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);
       

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        add(clearButton);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> handleBackButton());
        add(backButton, BorderLayout.SOUTH);

        finalDetailsTextArea = new JTextArea(30, 50);
        finalDetailsTextArea.setEditable(false);

        pack();
        setVisible(true);
       
    }
    private void handleBackButton() {
       
       SwingUtilities.invokeLater(SambalpurUniversityFrontPage::new);
        
        this.setVisible(false);
    }

    private void displaySelectedPhoto(File file) {
        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        photoLabel.setIcon(new ImageIcon(image));
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {

            boolean accepted = acceptAllCheckBox.isSelected();
            if (isRequiredFieldEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            

        if (!accepted) {
            JOptionPane.showMessageDialog(this, "Please accept all conditions before submission!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
            String fileName = "AppliedStudent.txt";
            String selectedFileName = "SelectedStudents.txt";

            String name = nameField.getText();
            String genderType = (String) genderTypeComboBox.getSelectedItem();
            String categoryType = (String) categoryTypeComboBox.getSelectedItem();
            String religionType = (String) religionTypeComboBox.getSelectedItem();
            String courseType = (String) courseTypeComboBox.getSelectedItem();
            String day = (String) dayComboBox.getSelectedItem();
            String month = (String) monthComboBox.getSelectedItem();
            String year = (String) yearComboBox.getSelectedItem();
            String dob = day + "-" + month + "-" + year;
            String jeeRank = jeeRankField.getText();
            String mark12 = mark12Field.getText();
            String mark10 = mark10Field.getText();
            String fatherName = fatherNameField.getText();
            String collegeName = collegeNameField.getText();
            String schoolName = schoolNameField.getText();
            String jeeRollNumber = jeeRollNumberField.getText();
            String countryType = (String) countryTypeComboBox.getSelectedItem();
            String address = addressField.getText() + ", " + postField.getText() + ", " + blockField.getText() + ", "
                    + distField.getText() + ", " + stateField.getText() + ", " + pinCodeField.getText();
            String mobileNumber = mobileNumberField.getText();
            String admissionType = (String) admissionTypeComboBox.getSelectedItem();
            

            // Logic for selecting students based on criteria (JEE rank, 12th and 10th marks)
            boolean selected = false;
           
        
        
            if (!jeeRank.isEmpty() && !mark12.isEmpty() && !mark10.isEmpty()) {
                int jeeRankValue = Integer.parseInt(jeeRank);
                double averageMarks = (Double.parseDouble(mark12) + Double.parseDouble(mark10)) / 2.0;

                
                if ((admissionType.equals("BTech") && jeeRankValue <= 1000 && averageMarks >= 80) ||
                        (admissionType.equals("MTech") && jeeRankValue <= 1000 && averageMarks >= 75) ||
                        (admissionType.equals("Lateral Entry BTech") && jeeRankValue <= 1500 && averageMarks >= 70)||
                        (admissionType.equals("MCA") && jeeRankValue <= 1800 && averageMarks >= 80)) {
                    selected = true;
                    JOptionPane.showMessageDialog(this, "Form submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(this, "congratulations you are eligible for admisstion", "Eligible", JOptionPane.INFORMATION_MESSAGE);
                   
                    JOptionPane.showMessageDialog(this, "BTech/Leteral BTech : 96,000 \n MTech : 98,000 \n MCA : 90,000 ", "Fee structer", JOptionPane.INFORMATION_MESSAGE);
                   
                      showPaymentForm();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Form submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(this, "Oops! you are not eligible for admisstion", "NOT Eligible", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            String studentInfo = "\n\nCandidate Name: "+ name  + "\nGender: " + genderType + "\nCategory: " + categoryType  + "\nRILIGION : " + religionType +"\nDate of Birth: " + dob + "\nJEE Rank: " + jeeRank
                    + "\n12th Marks: " + mark12 + "\n10th Marks: " + mark10 + "\nFather's Name: " + fatherName
                    + "\nCollege Name: " + collegeName + "\nSchool Name: " + schoolName + "\nJEE Roll Number: "
                    + jeeRollNumber + "\nCountry type: " + countryType +"\nAddress: " + address + "\nMobile Number: " + mobileNumber
                    + "\nAdmission Type: " + admissionType + "\n Intrested Course: " + courseType ;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
                    PrintWriter selectedWriter = new PrintWriter(new BufferedWriter(new FileWriter(selectedFileName, true)))) {
                writer.write(studentInfo);
                writer.newLine();
               
                if (selected) {
                    selectedWriter.println(studentInfo + " - SELECTED");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            
            clearFields();
            
            

        } 
        else if (e.getActionCommand().equals("Clear")) {
            clearFields();
        }
    
    }
    private void clearFields() {
        nameField.setText("");
        jeeRankField.setText("");
        mark12Field.setText("");
        mark10Field.setText("");
        fatherNameField.setText("");
        collegeNameField.setText("");
        schoolNameField.setText("");
        jeeRollNumberField.setText("");
        addressField.setText("");
        postField.setText("");
        blockField.setText("");
        distField.setText("");
        stateField.setText("");
        pinCodeField.setText("");
        mobileNumberField.setText("");
        
    }
    public void showHostelAdmissionForm() {
        JFrame hostelFrame = new JFrame("Hostel Admission Details");
        hostelFrame.setSize(400, 300);
        hostelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        hostelFrame.setLayout(new GridLayout(9, 2));

       JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"MALE", "FEMALE"});
        hostelFrame.add(new JLabel("Gender:"));
        hostelFrame.add(genderComboBox);

    
        JTextField hostelNameField = new JTextField();
        hostelFrame.add(new JLabel("Hostel Name:"));
        hostelFrame.add(hostelNameField);
    
        JTextField roomNumberField = new JTextField();
        hostelFrame.add(new JLabel("Room Number:"));
        hostelFrame.add(roomNumberField);
    
        JTextField hostelFeeField = new JTextField();
        hostelFrame.add(new JLabel("Hostel Fee:"));
        hostelFrame.add(hostelFeeField);
        
        JTextField accountNumberField = new JTextField();
         hostelFrame.add(new JLabel("Your Account number :"));
         hostelFrame.add(accountNumberField);

         hostelFrame.add(new JLabel("Payment Mode:"));
         hostelFrameComboBox = new JComboBox<>(new String[]{"UPI:7854998757@axl", "Account Transfer:35181881560(SBI)", "Through Mobile No:7854998757"});
         hostelFrame.add( hostelFrameComboBox);
        
        JTextField transitionIDField = new JTextField();
         hostelFrame.add(new JLabel("Transition ID:"));
         hostelFrame.add(transitionIDField);

    
        JCheckBox hostelRequiredCheckBox = new JCheckBox("Hostel Accommodation Required?");
    hostelFrame.add(hostelRequiredCheckBox);

    JButton hostelSubmitButton = new JButton("Submit Hostel Admission");
    hostelSubmitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String hostelName = hostelNameField.getText();
            String roomNumber = roomNumberField.getText();
            String hostelFee = hostelFeeField.getText();
            String gender = (String) genderComboBox.getSelectedItem(); // Get selected gender
            boolean hostelRequired = hostelRequiredCheckBox.isSelected(); // Check whether hostel required or not
            String accountNumber = accountNumberField.getText();
            String transitionID= transitionIDField.getText();
            String paymentType = (String) paymentTypeComboBox.getSelectedItem();

            String hostelInfo = "\n\n Hostel Name: " + hostelName + "\n Room Number: " + roomNumber + "\n Hostel Fee: " + hostelFee + "\n Gender: " + gender+"\n Payment Method: " + paymentType + "\nAccount Number:"+accountNumber+"\nTransition number"+transitionID;
            if (hostelRequired) { 
                hostelInfo += "\n Hostel Accommodation Required: Yes";
            } else {
                hostelInfo += "\n Hostel Accommodation Required: No";
            }
            storeHostelDetails(hostelInfo);
            
            
            JOptionPane.showMessageDialog(hostelFrame, "Hostel admission submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            hostelFrame.dispose();
            showPrintableForm();
        }
    });
        JButton hostelCancelButton = new JButton("Cancel");
        hostelCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hostelFrame.dispose(); // Close hostel admission form on cancel
            }
        });
    
        hostelFrame.add(hostelSubmitButton);
        hostelFrame.add(hostelCancelButton);

        hostelFrame.setVisible(true);
        
    }
    
    private void storeHostelDetails(String hostelInfo) {
        String fileName = "student_hostel_details.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(hostelInfo);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private void showPaymentForm() {
        JFrame paymentFrame = new JFrame("Student Payment Details");
        paymentFrame.setSize(400, 300);
        paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentFrame.setLayout(new GridLayout(7, 2));

        JTextField studentNameField = new JTextField();
        paymentFrame.add(new JLabel("Account Holder's Name:"));
        paymentFrame.add(studentNameField);

        paymentFrame.add(new JLabel("Payment Mode:"));
        paymentTypeComboBox = new JComboBox<>(new String[]{"UPI:7854998757@axl", "Account Transfer:35181881560(SBI)", "Through Mobile No:7854998757"});
        paymentFrame.add(paymentTypeComboBox);
        
        

        JTextField accountNumberField = new JTextField();
        paymentFrame.add(new JLabel("Your Account number :"));
        paymentFrame.add(accountNumberField);

        JTextField paymentAmountField = new JTextField();
        paymentFrame.add(new JLabel("Payment Amount:"));
        paymentFrame.add(paymentAmountField);

        JTextField transitionIDField = new JTextField();
        paymentFrame.add(new JLabel("Transition ID:"));
        paymentFrame.add(transitionIDField);


        JButton paymentSubmitButton = new JButton("Submit Payment");
        paymentSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentName = studentNameField.getText();
            
                String paymentAmount = paymentAmountField.getText();
                
                String accountNumber = accountNumberField.getText();

                String transitionID= transitionIDField.getText();


                String paymentType = (String) paymentTypeComboBox.getSelectedItem();

                // Perform actions to store payment details in a file
                String paymentInfo = "\n\n Accound Holder's Name: " + studentName + "\n Payment Amount: " + paymentAmount + "\n Payment Method: " + paymentType + "\nAccount Number:"+accountNumber+"\nTransition number"+transitionID;
                storePaymentDetails(paymentInfo);

                JOptionPane.showMessageDialog(paymentFrame, "Payment submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(paymentFrame, "congratulations! welcome to Sambalpur University", "welcome", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(paymentFrame, "MALE : VHR(BTECH,MCA,MTECH)\n       : AHR(LETERAL ENTRY BTECH)\n\nFEMALE : MHR(FOR ALL)\n\nFEE STRUCTURE :\n\nHOSTEL SEAT FEE  : 10,000.00 RUPPESS\nSECURITY FEE     :    2,000.00 RUPEES\nTOTAL HOSTEL FEE : 12,000.00 RUPEES", "HOSTEL NAME AND FEE STRUCTURE", JOptionPane.INFORMATION_MESSAGE);

                paymentFrame.dispose(); // Close payment form after submission
                showHostelAdmissionForm();

            }
        });

        JButton paymentCancelButton = new JButton("Cancel");
        paymentCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paymentFrame.dispose(); // Close payment form on cancel
            }
        });

        paymentFrame.add(paymentSubmitButton);
        paymentFrame.add(paymentCancelButton);

        paymentFrame.setVisible(true);
        
    }

    private void storePaymentDetails(String paymentInfo) {
        String fileName = "student_payments.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(paymentInfo);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showPrintableForm() {
        JFrame printFrame = new JFrame("Printable Form");
        printFrame.setSize(600, 600);
        printFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        JPanel printPanel = new JPanel(new BorderLayout());
    
        printTextArea = new JTextArea(30, 50);
        printTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(printTextArea);
        printPanel.add(scrollPane, BorderLayout.CENTER);
    
        JButton printButton = new JButton("Print");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    printTextArea.print(); // Print the text area content
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    
        printPanel.add(printButton, BorderLayout.SOUTH);

        // Adding photo, payment details, and hostel details to the printable form
        ImageIcon icon = (ImageIcon) photoLabel.getIcon();
        if (icon != null) {
            JLabel photoLabelPrint = new JLabel(icon);
            printPanel.add(photoLabelPrint, BorderLayout.NORTH);
        }
        String recentSelectedStudentDetails = getRecentSelectedStudentDetails();
    if (!recentSelectedStudentDetails.isEmpty()) {
        printTextArea.append("\n\nCandidate's Details\n");
        printTextArea.append(recentSelectedStudentDetails);
    }
    
        String paymentInfo = "Payment Details:\n" + getPaymentDetails();
    printTextArea.append("\n\n" + paymentInfo);
    String hostelInfo = "Hostel Details:\n" + getHostelDetails();
    printTextArea.append("\n\n" + hostelInfo);
    
        printFrame.add(printPanel);
        printFrame.setVisible(true);
    }
    
    private String getHostelDetails() {
        // Fetch hostel details from your stored file or store it directly as per your application logic
        // Example logic to read from the file
        StringBuilder hostelDetails = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("student_hostel_details.txt"))) {
            String line;
        ArrayList<String> recentLines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            recentLines.add(line);
            if (recentLines.size() > 6) {
                recentLines.remove(0);
            }
        }
        for (String recentLine : recentLines) {
            hostelDetails.append(recentLine).append("\n");
        }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return hostelDetails.toString();
    }
    private String getPaymentDetails() {

        StringBuilder paymentDetails = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("student_payments.txt"))) {
            String line;
        ArrayList<String> recentLines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            recentLines.add(line);
            if (recentLines.size() > 5) {
                recentLines.remove(0);
            }
        }
        for (String recentLine : recentLines) {
           paymentDetails.append(recentLine).append("\n");
        }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return paymentDetails.toString();
    }        
    private String getRecentSelectedStudentDetails() {
        StringBuilder selectedStudentDetails = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("SelectedStudents.txt"))) {
        String line;
        ArrayList<String> recentLines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            recentLines.add(line);
            if (recentLines.size() > 17) {
                recentLines.remove(0);
            }
        }
        for (String recentLine : recentLines) {
           selectedStudentDetails.append(recentLine).append("\n");
        }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return selectedStudentDetails.toString();
    }
    private boolean isRequiredFieldEmpty() {
        return nameField.getText().isEmpty() ||
                jeeRankField.getText().isEmpty() ||
                mark12Field.getText().isEmpty() ||
                mark10Field.getText().isEmpty() ||
                fatherNameField.getText().isEmpty() ||
                collegeNameField.getText().isEmpty() ||
                schoolNameField.getText().isEmpty() ||
                jeeRollNumberField.getText().isEmpty() ||
                addressField.getText().isEmpty() ||
                postField.getText().isEmpty() ||
                blockField.getText().isEmpty() ||
                distField.getText().isEmpty() ||
                stateField.getText().isEmpty() ||
                pinCodeField.getText().isEmpty() ||
                mobileNumberField.getText().isEmpty();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdmissionForm();
            }
        });
    }
}