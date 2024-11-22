public class Student {
    // Atrybuty
    private String name;
    private int age;
    private double grade;
    private String studentID;

    // Konstruktor
    public Student(String name, int age, double grade, String studentID) {
        if (age <= 0) {
            throw new IllegalArgumentException("Wiek musi byc liczba dodatnia!");
        }
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Ocena musi byc w przedziale miedzy 0.0 a 100.0.");
        }
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.studentID = studentID;
    }

    // Gettery i settery
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Wiek musi byc liczba dodatnia!");
        }
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Ocena musi byc w przedziale miedzy 0.0 a 100.0.");
        }
        this.grade = grade;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // Metoda do wyświetlania informacji
    public void displayInfo() {
        System.out.println("Numer studenta: " + studentID);
        System.out.println("Imie: " + name);
        System.out.println("Wiek: " + age);
        System.out.println("Ocena: " + grade);
    }
}
