
# System Zarządzania Studentami (SMS)

## Przegląd funkcjonalności

System Zarządzania Studentami (SMS) to aplikacja desktopowa w języku Java, która umożliwia użytkownikom zarządzanie danymi studentów. Aplikacja pozwala na wykonywanie operacji CRUD (tworzenie, odczyt, aktualizacja, usuwanie) na danych studentów przechowywanych w bazie danych SQLite. System oferuje również obliczanie średniej ocen wszystkich studentów.

### Główne funkcjonalności:

- **Dodawanie studenta**: Użytkownik może dodać nowego studenta, wprowadzając jego ID, imię, wiek oraz ocenę.
- **Usuwanie studenta**: Użytkownik może usunąć studenta z bazy danych, podając jego unikalne ID.
- **Aktualizacja danych studenta**: Użytkownik może zaktualizować dane studenta (np. zmiana imienia, wieku, oceny).
- **Wyświetlanie wszystkich studentów**: Użytkownik może wyświetlić listę wszystkich studentów przechowywanych w bazie danych.
- **Obliczanie średniej ocen**: System oblicza i wyświetla średnią ocen wszystkich studentów w bazie danych.

Aplikacja posiada graficzny interfejs użytkownika (GUI) stworzony za pomocą biblioteki **Swing**, który umożliwia łatwą interakcję z systemem.

---

## Instrukcje dotyczące kompilacji i uruchamiania aplikacji

### 1. **Wymagania wstępne**:

- **Java** w wersji 8 lub wyższej.
- **SQLite** (do zarządzania bazą danych).
- **IDE** do kompilacji i uruchamiania aplikacji, np. IntelliJ IDEA, Eclipse.

### 2. **Kroki do kompilacji i uruchamiania**:

1. **Sklonowanie repozytorium**:

   Jeśli nie masz jeszcze repozytorium na swoim komputerze, sklonuj je za pomocą polecenia:

   ```bash
   git clone <url-repozytorium>
   ```

2. **Kompilacja aplikacji**:

   W IDE otwórz projekt i skompiluj wszystkie pliki źródłowe. Jeśli używasz terminala, przejdź do katalogu projektu i uruchom komendę:

   ```bash
   javac -d bin src/*.java
   ```

   Upewnij się, że wszystkie pliki zostały poprawnie skompilowane.

3. **Uruchomienie aplikacji**:

   Aby uruchomić aplikację, uruchom klasę `Main`, która inicjuje aplikację GUI. W IDE kliknij prawym przyciskiem myszy na plik `Main.java` i wybierz opcję **Run**. Jeśli używasz terminala, przejdź do katalogu, w którym znajduje się plik `Main.class` i uruchom komendę:

   ```bash
   java -cp bin Main
   ```

---

## Instrukcje dotyczące konfiguracji bazy danych

Aplikacja korzysta z bazy danych **SQLite**, która jest zautomatyzowana i znajduje się w folderze **LIB** lub w innym folderze w projekcie, zależnie od konfiguracji. Baza danych jest tworzona i obsługiwana automatycznie przez aplikację, więc nie musisz ręcznie tworzyć tabel ani wykonywać zapytań SQL.

### 1. **Zautomatyzowana baza danych**:

Przy każdym uruchomieniu aplikacji:

- Jeśli plik bazy danych `students.db` **nie istnieje**, aplikacja **automatycznie utworzy** ten plik i odpowiednią tabelę w bazie danych.
- Jeśli plik bazy danych już istnieje, aplikacja **odczyta dane** z bazy i załaduje je do systemu.

Baza danych jest przechowywana w głównym katalogu projektu lub w innym folderze projektu, co umożliwia jej łatwą lokalizację i obsługę.

### 2. **Konfiguracja w aplikacji**:

W projekcie znajduje się zautomatyzowana baza danych, której ścieżka jest ustawiona w pliku `StudentManagerImpl.java`. Aplikacja automatycznie nawiązuje połączenie z bazą i zarządza danymi studentów.

Jeśli Twoja baza danych jest w głównym katalogu projektu, upewnij się, że plik bazy danych (np. `students.db`) znajduje się w tym folderze i jest dostępny dla aplikacji.

```java
private static final String DATABASE_URL = "jdbc:sqlite:students.db";
```
---

## Dodatkowe informacje

- **Zabezpieczenia**: Aplikacja nie wymaga dodatkowych zabezpieczeń, ale użytkownik powinien uważać na dane wejściowe, aby nie wprowadzać błędnych wartości.
- **Zależności**: Projekt nie wymaga dodatkowych zewnętrznych bibliotek oprócz standardowej JDK i SQLite.

---

