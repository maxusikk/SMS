import javax.swing.SwingUtilities; // import SwingUtilities

public class Main {
    public static void main(String[] args) {
        // Uruchomienie GUI na odpowiednim wątku
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Tworzenie instancji managera i GUI
                StudentManager manager = new StudentManagerImpl();
                new StudentManagementApp(manager); // Tworzenie aplikacji z GUI
            }
        });
    }
}
