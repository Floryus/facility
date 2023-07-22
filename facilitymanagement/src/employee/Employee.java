package employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import enums.GroupEnum;
import exceptions.WrongUserInputException;
import todos.Todo;

/**
 * Die Klasse Employee repräsentiert einen Mitarbeiter.
 * Sie implementiert das Comparable-Interface, um eine Vergleichsmethode für die
 * Sortierung bereitzustellen.
 * Sie implementiert das Serializable-Interface, um die Objekte durch
 * EmployeeVerwaltung zu speichern.
 * Employees werden genutzt um Aufgaben ihren Mitarbeiter zuzweisen. Ebenso kann
 * ein Mitarbeiter sich seine Aufgaben anzeigen lassen.
 * 
 * @author Florian Schmidt, Alexander Siege
 * 
 */
public class Employee implements Comparable<Employee>, Serializable {
    private UUID id;
    private GroupEnum group;
    private String firstName;
    private String lastName;
    private ArrayList<Todo> todos;

    /**
     * Konstruktor der Employee-Klasse.
     * Erzeugt einen Mitarbeiter mit dem angegebenen Namen und der Gruppe.
     * Die ID wird automatisch generiert, sobald die EmployeeVerwaltung diesen
     * Employee in die Liste aufnimmt.
     *
     * @param firstName Der Vorname des Mitarbeiters.
     * @param lastName  Der Nachname des Mitarbeiters.
     * @param group     Die Gruppe, zu der der Mitarbeiter gehört.
     */
    public Employee(String firstName, String lastName, GroupEnum group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.todos = new ArrayList<>();

    }

    /**
     * Gibt den Namen des Mitarbeiters zurück.
     *
     * @return Der Name des Mitarbeiters.
     */
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Gibt den Vornamen des Mitarbeiters zurück.
     *
     * @return Der Vorname des Mitarbeiters.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gibt den Nachnamen des Mitarbeiters zurück.
     *
     * @return Der Nachname des Mitarbeiters.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gibt die ID des Mitarbeiters zurück.
     *
     * @return Die ID des Mitarbeiters.
     */
    public UUID getId() {
        return this.id;
    }

    /*
     * Setzt die ID des Mitarbeiters, diese UUID wir erzeugt, wenn ein Employee der
     * EmployeeVerwaltung hinzugefügt wird.
     * 
     * @param id Die ID des Mitarbeiters.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Setzt den Vornamen des Mitarbeiters.
     * 
     * @param firstName Der Vorname des Mitarbeiters.
     * @throws WrongUserInputException Wenn der Vorname leer ist.
     */
    public void setFirstName(String firstName) throws WrongUserInputException {
        if (firstName == null || firstName.isEmpty()) {
            throw new WrongUserInputException("Kein Vorname", "Der Vorname darf nicht leer sein.");
        }
        this.firstName = firstName;
    }

    /**
     * Setzt den Nachnamen des Mitarbeiters.
     * 
     * @param lastName Der Nachname des Mitarbeiters.
     * @throws WrongUserInputException Wenn der Nachname leer ist.
     */
    public void setLastName(String lastName) throws WrongUserInputException {
        if (lastName == null || lastName.isEmpty()) {
            throw new WrongUserInputException("Kein Nachname", "Der Nachname darf nicht leer sein.");
        }
        this.lastName = lastName;
    }

    /**
     * Gibt die Gruppe des Mitarbeiters zurück.
     * 
     * @return Die Gruppe des Mitarbeiters.
     */
    public GroupEnum getGroup() {
        return this.group;
    }

    /**
     * Gibt die Aufgaben des Mitarbeiters zurück.
     * 
     * @return Die Aufgaben des Mitarbeiters.
     */
    public ArrayList<Todo> getTodos() {

        return todos;
    }

    /**
     * Fügt eine Aufgabe zu den Aufgaben des Mitarbeiters hinzu.
     * Sollte die Aufgabe bereits einen Mitarbeiter haben, wird dieser entfernt.
     * 
     * @param todo Die Aufgabe, die hinzugefügt werden soll.
     */
    public void assignTodoToEmployee(Todo todo) {
        if (todo.hasEmployee()) {
            todo.getEmployee().removeTodoFromEmployee(todo);
        }
        this.todos.add(todo);
        EmployeeVerwaltung.saveEmployees();
    }

    /**
     * Entfernt eine Aufgabe aus den Aufgaben des Mitarbeiters.
     * 
     * @param todo Die Aufgabe, die entfernt werden soll.
     */
    public void removeTodoFromEmployee(Todo todo) {
        this.todos.remove(todo);
        EmployeeVerwaltung.saveEmployees();
    }

    /**
     * Gibt eine textuelle Repräsentation des Mitarbeiters zurück.
     * Die ID wird angezeigt um Mitarbeiter mit gleichem Namen zu unterscheiden.
     *
     * @return Eine textuelle Repräsentation des Mitarbeiters.
     */
    @Override
    public String toString() {
        String message = this.getName() + " " + this.group + " " + this.id;
        return message;
    }

    /**
     * Vergleicht den Mitarbeiter mit einem anderen Mitarbeiter anhand des
     * Nachnamens (bzw Vornamen).
     * Wird für die Sortierung verwendet.
     *
     * @param other Der andere Mitarbeiter, mit dem verglichen werden soll.
     * @return Eine negative ganze Zahl, wenn der Mitarbeiter vor dem anderen
     *         Mitarbeiter kommt,
     *         eine positive ganze Zahl, wenn der Mitarbeiter nach dem anderen
     *         Mitarbeiter kommt,
     *         oder 0, wenn beide Mitarbeiter gleich sind.
     */
    @Override
    public int compareTo(Employee other) {
        int lastNameComparison = this.lastName.compareTo(other.lastName);
        if (lastNameComparison != 0) {
            // Wenn die Nachnamen nicht gleich sind, sortiere nach dem Nachnamen
            return lastNameComparison;
        } else {
            // Wenn die Nachnamen gleich sind, sortiere nach dem Vornamen
            return this.firstName.compareTo(other.firstName);
        }
    }
}
