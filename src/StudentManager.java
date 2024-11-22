import java.util.List;

public interface StudentManager {
    void addStudent(Student student); // Dodanie studenta
    void removeStudent(String studentID); // Usuwanie studenta
    void updateStudent(String studentID); // Aktualizacja studenta
    List<Student> displayAllStudents(); // Wyświetlenie wszystkich studentów
    boolean isStudentExist(String studentID); // Sprawdzanie, czy student istnieje
    double calculateAverageGrade(); // Obliczanie średniej ocen
}
