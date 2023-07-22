package todos;

import java.time.LocalDate;

import enums.GroupEnum;
import enums.PriorityEnum;
import exceptions.WrongUserInputException;
import global.GlobalVerwaltung;
import maintainables.Maintainable;

/**
 * Die Klasse Task erbt von der Klasse Todo und repräsentiert eine Aufgabe mit
 * Wartungsaspekt. Diese Aufagbe wird wiederholt ausgeführt.
 * 
 * @author Marten Tietje, Florian Schmidt
 */
public class MaintenanceTask extends Todo {

    LocalDate nextDueDate;
    int intervalInDays;

    /**
     * Der Konstruktor der Klasse Task erzeugt eine neue Aufgabe.
     * 
     * @param title          Der Titel der Aufgabe.
     * @param description    Die Beschreibung der Aufgabe.
     * @param object         Das Maintainable, auf das sich die Aufgabe bezieht.
     * @param priority       Die Priorität der Aufgabe.
     * @param group          Die Gruppe, der die Aufgabe zugeordnet ist.
     * @param intervalInDays Der Intervall in Tagen, in dem die Aufgabe wiederholt
     *                       werden soll.
     * @param firstDueDate   Das Datum, an dem die Aufgabe zum ersten Mal fällig
     *                       ist.
     */
    public MaintenanceTask(String title, String description, Maintainable object, PriorityEnum priority,
            GroupEnum group, int intervalInDays, LocalDate firstDueDate) throws Exception {
        super(title, description, object, priority, group, firstDueDate);
        if (intervalInDays <= 0) {
            throw new WrongUserInputException("Falsche Eingabe", "Das Intervall muss größer als 0 sein.");
        } else {
            this.intervalInDays = intervalInDays;
        }
        this.nextDueDate = firstDueDate;
    }

    /*
     * Diese Funktion wird aufgerufen, wenn eine MaintenanceTask fertiggestellt
     * wird. Auf Grundlage des Intervals und des Tages der Ausführung wird der
     * nächste Zeitpunkt berechhnet.
     * Die Änderung wird abgespeichert.
     * Der zugeordnete Mitarbeiter bleibt erhalten.
     */
    public void finish() {
        nextDueDate = LocalDate.now().plusDays(intervalInDays);
        dueDate = nextDueDate;
        GlobalVerwaltung.getTodoVerwaltung().saveTodos();
    }

}
