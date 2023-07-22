package todos;

import java.time.LocalDate;

import enums.GroupEnum;
import enums.PriorityEnum;
import global.GlobalVerwaltung;
import maintainables.Maintainable;

/*
 * 
 * Die Klasse Task erbt von der Klasse Todo und repräsentiert eine Aufgabe.
 * Diese Aufagbe wird einmalig ausgeführt und ist dann abgeschlossen.
 * 
 * @author Marten Tietje, Florian Schmidt
 */
public class Ticket extends Todo {

    LocalDate dueDate;

    /*
     * Der Konstruktor der Task-Klasse erzeugt eine neue Aufgabe.
     * 
     * @param title Der Titel der Aufgabe.
     * 
     * @param description Die Beschreibung der Aufgabe.
     * 
     * @param object Das Maintainable, auf das sich die Aufgabe bezieht.
     * 
     * @param priority Die Priorität der Aufgabe.
     * 
     * @param group Die Gruppe, der die Aufgabe zugeordnet ist.
     * 
     * @param dueDate Das Datum, bis zu dem die Aufgabe erledigt sein muss.
     */
    public Ticket(String title, String description, Maintainable object, PriorityEnum priority, GroupEnum group,
            LocalDate dueDate) throws Exception {
        super(title, description, object, priority, group, dueDate);
        this.dueDate = dueDate;
    }

    /*
     * Beeendet die Aufgabe.
     * Die Aufgabe wird von der Liste des Mitarbeiters entfernt.
     * Die Aufgabe wird aus der Liste aller Todos entfernt.
     */
    public void finish() {
        // Das Ticket vom Mitarbeiter entfernen
        this.getEmployee().removeTodoFromEmployee(this);
        // Das Ticket aus der Liste aller Todos entfernen
        GlobalVerwaltung.getTodoVerwaltung().removeTodo(this);
        // Die Änderungen speichern
        GlobalVerwaltung.getTodoVerwaltung().saveTodos();
    }
}
