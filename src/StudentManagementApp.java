import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class StudentManagementApp {
    private StudentManager manager;

    // Konstruktor aplikacji
    public StudentManagementApp(StudentManager manager) {
        this.manager = manager;
        createAndShowGUI();  // Wywołanie GUI z konstruktora
    }

    // Metoda do tworzenia GUI
    private void createAndShowGUI() {
        JFrame frame = new JFrame("System Zarzadzania Studentami");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Kończy aplikację po zamknięciu okna
        frame.setSize(600, 400);

        // Panel wejściowy
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JLabel idLabel = new JLabel("Numer studenta:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Imie:");
        JTextField nameField = new JTextField();
        JLabel ageLabel = new JLabel("Wiek:");
        JTextField ageField = new JTextField();
        JLabel gradeLabel = new JLabel("Ocena:");
        JTextField gradeField = new JTextField();

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);

        // Panel przycisków
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton addButton = new JButton("Dodaj studenta");
        JButton removeButton = new JButton("Usun studenta");
        JButton updateButton = new JButton("Zaaktualizuj studenta");
        JButton displayButton = new JButton("Wyswietl wszystkich studentow");
        JButton averageButton = new JButton("Oblicz srednia");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(averageButton);

        // Panel wyjściowy
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Obsługa akcji dla przycisków
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = idField.getText();
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    double grade = Double.parseDouble(gradeField.getText());
                    manager.addStudent(new Student(name, age, grade, id));
                    outputArea.append("Dodano studenta: " + name + "\n");

                    // Komunikat po dodaniu studenta
                    JOptionPane.showMessageDialog(frame,
                            "Student dodany poprawnie: " + name,
                            "Sukces!",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Czyszczenie pól
                    idField.setText("");
                    nameField.setText("");
                    ageField.setText("");
                    gradeField.setText("");
                } catch (Exception ex) {
                    outputArea.append("Wystapil blad podczas usuwania studenta: " + ex.getMessage() + "\n");

                    // Komunikat o błędzie
                    JOptionPane.showMessageDialog(frame,
                            "Student nie zostal poprawnie dodany! " + ex.getMessage(),
                            "Blad",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();

                // Sprawdzamy, czy student o danym ID istnieje w bazie
                if (manager.isStudentExist(id)) {
                    manager.removeStudent(id);
                    outputArea.append("Student o ID " + id + " usunieto.\n");

                    // Komunikat po usunięciu studenta
                    JOptionPane.showMessageDialog(frame,
                            "Student o numerze: " + id + "zostal pomyslnie usuniety.",
                            "Sukces!",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Komunikat o błędzie, jeśli student nie istnieje
                    JOptionPane.showMessageDialog(frame,
                            "Student o numerze: " + id + " nie istnieje!",
                            "Blad",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Czyszczenie pól
                idField.setText("");
                nameField.setText("");
                ageField.setText("");
                gradeField.setText("");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();

                // Sprawdzamy, czy student o danym ID istnieje w bazie
                if (manager.isStudentExist(id)) {
                    manager.updateStudent(id);
                    outputArea.append("Aktualizowanie studenta o ID: " + id + "\n");

                    // Komunikat po aktualizacji studenta
                    JOptionPane.showMessageDialog(frame,
                            "Student o numerze: " + id + " zostal zaaktualizowany pomyslnie.",
                            "Sukces!",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Komunikat o błędzie, jeśli student nie istnieje
                    JOptionPane.showMessageDialog(frame,
                            "Student o numerze: " + id + " nie istnieje!.",
                            "Blad",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Czyszczenie pól
                idField.setText("");
                nameField.setText("");
                ageField.setText("");
                gradeField.setText("");
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Wyświetlanie wszystkich studentów w formie JList
                List<Student> students = manager.displayAllStudents();
                String[] studentNames = new String[students.size()];

                // Zbieranie imion i ID studentów
                for (int i = 0; i < students.size(); i++) {
                    studentNames[i] = students.get(i).getName() + " (ID: " + students.get(i).getStudentID() + ")";
                }

                // Tworzenie listy JList i wyświetlanie jej w oknie
                JList<String> studentList = new JList<>(studentNames);
                JScrollPane listScrollPane = new JScrollPane(studentList);
                JOptionPane.showMessageDialog(frame, listScrollPane, "Lista studentow", JOptionPane.INFORMATION_MESSAGE);

                // Dodanie nasłuchiwacza, aby po kliknięciu na studenta wyświetlić szczegóły
                studentList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            int index = studentList.getSelectedIndex();
                            System.out.println("Selected index: " + index); // Debugowanie: sprawdzamy indeks

                            if (index != -1) {
                                // Pobieramy wybranego studenta
                                Student selectedStudent = students.get(index);

                                // Debugowanie: sprawdzamy dane studenta
                                System.out.println("Selected student: " + selectedStudent.getName() + " (ID: " + selectedStudent.getStudentID() + ")");

                                // Wyświetlamy szczegóły studenta
                                JOptionPane.showMessageDialog(frame,
                                        "Student ID: " + selectedStudent.getStudentID() + "\n" +
                                                "Name: " + selectedStudent.getName() + "\n" +
                                                "Age: " + selectedStudent.getAge() + "\n" +
                                                "Grade: " + selectedStudent.getGrade(),
                                        "Student Details",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                });
            }
        });

        averageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obliczamy średnią ocen wszystkich studentów w bazie
                double average = manager.calculateAverageGrade();

                // Wyświetlamy wynik w konsoli
                outputArea.append("Srednia wszystkich studentow: " + average + "\n");

                // Komunikat o obliczonej średniej
                JOptionPane.showMessageDialog(frame,
                        "Srednia wszystkich studentow wynosi: " + average,
                        "Srednia ocen",
                        JOptionPane.INFORMATION_MESSAGE);

                // Czyszczenie pól
                idField.setText("");
                nameField.setText("");
                ageField.setText("");
                gradeField.setText("");
            }
        });

        // Rozmieszczenie paneli w oknie
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Ustawienie widoczności okna
        frame.setVisible(true);
    }
}
