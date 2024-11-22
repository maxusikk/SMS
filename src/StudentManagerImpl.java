import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagerImpl implements StudentManager {
    private static final String DATABASE_URL = "jdbc:sqlite:students.db"; // Adres bazy danych

    // Metoda do dodawania studenta
    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (studentID, name, age, grade) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getStudentID());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setDouble(4, student.getGrade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metoda do usuwania studenta
    @Override
    public void removeStudent(String studentID) {
        String sql = "DELETE FROM students WHERE studentID = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, studentID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metoda do aktualizowania studenta
    @Override
    public void updateStudent(String studentID) {
        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "Updated Name");  // Można zmieniać dane studenta na dowolne
            preparedStatement.setInt(2, 25);  // Przykładowy wiek
            preparedStatement.setDouble(3, 95.0);  // Przykładowa ocena
            preparedStatement.setString(4, studentID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metoda do wyświetlania wszystkich studentów
    @Override
    public List<Student> displayAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String id = resultSet.getString("studentID");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double grade = resultSet.getDouble("grade");
                students.add(new Student(name, age, grade, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Metoda do sprawdzania, czy student istnieje w bazie
    @Override
    public boolean isStudentExist(String studentID) {
        String sql = "SELECT COUNT(*) FROM students WHERE studentID = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, studentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;  // Jeśli student istnieje, zwróci true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Jeśli student nie istnieje, zwróci false
    }

    // Metoda do obliczania średniej ocen wszystkich studentów w bazie
    @Override
    public double calculateAverageGrade() {
        double sum = 0;
        int count = 0;
        String sql = "SELECT grade FROM students"; // Pobieramy wszystkie oceny studentów
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                sum += resultSet.getDouble("grade"); // Dodajemy ocenę do sumy
                count++; // Zliczamy liczbę studentów
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Jeśli są studenci, obliczamy średnią, w przeciwnym razie zwracamy 0
        return count > 0 ? sum / count : 0;
    }
}
