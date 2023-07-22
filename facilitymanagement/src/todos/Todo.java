package todos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import employee.Employee;
import enums.GroupEnum;
import enums.PriorityEnum;
import exceptions.WrongUserInputException;
import maintainables.Maintainable;

/**
 * Die abstrakte Klasse Todo stellt eine Basisklasse für Aufgaben
 * (MaintenanceTask und Ticket) dar.
 * 
 * 
 * @author Florian Schmidt, Marten Tietje
 */

public abstract class Todo implements Comparable<Todo>, Serializable {

    UUID id;
    String title;
    String description;
    Maintainable maintainable;
    Employee employee;
    PriorityEnum priority;
    GroupEnum group;
    final LocalDate CREATIONDATE;
    LocalDate dueDate;

    /*
     * Der Konstruktor der Todo-Klasse erzeugt ein neues Todo. Wenn dieses der
     * TodoVerwaltung hinzugefügt wird, erhält es eine einheitliche ID.
     * 
     * @param title Der Titel des Todos.
     * 
     * @param description Die Beschreibung des Todos.
     * 
     * @param maintainable Das Maintainable, zu dem das Todo gehört.
     * 
     * @param priority Die Priorität des Todos.
     * 
     * @param group Die Gruppe des Todos.
     * 
     * @param dueDate Das Fälligkeitsdatum des Todos.
     * 
     * @throws WrongUserInputException Wenn das Fälligkeitsdatum vor dem
     * Erstellungsdatum liegt.
     */
    public Todo(String title, String description, Maintainable maintainable, PriorityEnum priority, GroupEnum group,
            LocalDate dueDate) throws WrongUserInputException {
        this.title = title;
        this.description = description;
        this.maintainable = maintainable;
        this.priority = priority;
        this.group = group;
        this.CREATIONDATE = LocalDate.now();
        if (dueDate.isBefore(this.CREATIONDATE)) {
            throw new WrongUserInputException("Fehlerhafte Eingabe beim Datum",
                    "Das Fälligkeitsdatum einer Aufgabe muss nach dem Erstellungsdatum liegen.");
        } else {
            this.dueDate = dueDate;
        }
    }

    /*
     * Gibt zurück, ob die Aufgabe einem Mitarbeiter zugewiesen ist.
     * 
     * @return true, wenn die Aufgabe einem Mitarbeiter zugewiesen ist
     */
    public boolean hasEmployee() {
        if (this.employee == null) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Setzt den verantwortlichen Mitarbeiter und fügt dieses Element zu der Liste
     * von Aufgaben des Mitarbeiters hinzu. Ebenso wird diese Änderung gespeichert.
     * 
     * Falls der Mitarbeiter nicht in der selben Gruppe ist, wie das Todo, wird eine
     * Fehler geworfen.
     */
    public void setEmployee(Employee employee) {
        if (employee.getGroup() != this.group) {
            throw new IllegalArgumentException("Employee must be in the same group as the todo");
        } else {
            this.employee = employee;
            employee.assignTodoToEmployee(this);
            TodoVerwaltung.saveTodos();
        }

    }

    /*
     * Setzt das Fälligkeitsdatum und speichert die Änderung.
     * 
     * @param dueDate Das Fälligkeitsdatum des Todos.
     * 
     * @throws WrongUserInputException Wenn das Fälligkeitsdatum vor dem
     * Erstellungsdatum liegt.
     */
    public void setDueDate(LocalDate dueDate) throws WrongUserInputException {
        if (dueDate.isBefore(this.CREATIONDATE)) {
            throw new WrongUserInputException("Fehlerhafte Eingabe beim Datum",
                    "Das Fälligkeitsdatum einer Aufgabe muss nach dem Erstellungsdatum liegen.");
        } else {
            this.dueDate = dueDate;
        }
    }

    /*
     * Gibt den Ort des Maintainables zurück.
     * 
     * @return Den Ort des Maintainables.
     */
    public String getLocation() {
        return this.maintainable.getLocation();
    }

    /*
     * Gibt den Mitarbeiter des Todos zurück.
     * 
     * @return Den Mitarbeiter des Maintainables.
     */
    public Employee getEmployee() {
        return employee;
    }

    /*
     * Gibt den Titel des Todos zurück.
     * 
     * @return Den Titel des Todos.
     */
    public String getTitle() {
        return title;
    }

    /*
     * Gibt die Beschreibung des Todos zurück.
     * 
     * @return Die Beschreibung des Todos.
     */
    public String getDescription() {
        return description;
    }

    /*
     * Gibt die Priorität des Todos zurück.
     * 
     * @return Die Priorität des Todos.
     */
    public PriorityEnum getPriority() {
        return priority;
    }

    /*
     * Gibt die Gruppe des Todos zurück.
     * 
     * @return Die Gruppe des Todos.
     */
    public GroupEnum getGroup() {
        return group;
    }

    /*
     * Gibt das Maintainable des Todos zurück.
     * 
     * @return Das Maintainable des Todos.
     */
    public Maintainable getMaintainable() {
        return maintainable;
    }

    /*
     * Gibt die ID des Todos zurück.
     * 
     * @return Die ID des Todos.
     */
    public UUID getId() {
        return id;
    }

    /*
     * Gibt das Fälligkeitsdatum des Todos zurück.
     * 
     * @return Das Fälligkeitsdatum des Todos.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /*
     * Gibt das Erstellungsdatum des Todos zurück.
     * 
     * @return Das Erstellungsdatum des Todos.
     */
    public LocalDate getCREATIONDATE() {
        return CREATIONDATE;
    }

    /*
     * Todos werden zunächst nach ihrem Datum geordnet und falls das Datum gleich
     * ist, nach ihrer Priorität
     */
    public int compareTo(Todo todo) {
        int dateComparison = this.dueDate.compareTo(todo.dueDate);

        if (dateComparison != 0) {
            return dateComparison;
        } else {
            return this.priority.compareTo(todo.priority);
        }
    }

    /*
     * Gibt eine String-Repräsentation des Todos zurück.
     * 
     * @return Eine String-Repräsentation des Todos.
     */
    public String toString() {
        return ("TODO:" + this.title + " for " + this.maintainable + " with Priority " + this.priority + " in Group "
                + this.group + " needs to be done by " + this.dueDate);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    /*
     * finished() sollte nicht auf einem Todo aufgerufen werden, sondern auf einer
     * der Subklassen Ticket oder MaintenanceTask
     * 
     * @throws UnsupportedOperationException
     */
    public void finish() {
        throw new UnsupportedOperationException(
                "finish() should not get invoked on a Todo, rather an subclasses Ticket or MaintenanceTask");
    }

}
