package maintainables;

import java.io.Serializable;
import java.util.ArrayList;

import todos.Todo;
import todos.TodoVerwaltung;

/*
 * Die Klasse Maintainable repräsentiert ein Maintainable (Gebäude, Etage, Room, Equipment).
 * Die Klasse ist abstract, weil sie nicht direkt instanziiert werden soll, sondern nur über die erbenden Klassen.
 * 
 * @author Florian Schmidt
 */
public abstract class Maintainable implements Serializable {

    ArrayList<Todo> todos;

    /*
     * Der Konstruktor der Maintainable-Klasse erzeugt ein neues Maintainable.
     * 
     * @param name Der Name des Maintainables.
     */
    public Maintainable() {
        todos = new ArrayList<>();
    }

    /*
     * Fügt ein Todo einem Maintainable hinzu.
     * Speichert diese Zuweisung in der TodoVerwaltung.
     * 
     * @param todo Das Todo, das hinzugefügt werden soll.
     */
    public void addTodo(Todo todo) {
        todos.add(todo);
        TodoVerwaltung.saveTodos();
    }

    /*
     * Entfernt ein Todo von einem Maintainable.
     * Speichert diese Änderung in der TodoVerwaltung.
     * 
     * @param todo Das Todo, das entfernt werden soll.
     */
    public void removeTodo(Todo todo) {
        todos.remove(todo);
        TodoVerwaltung.saveTodos();
    }

    /*
     * Gibt die Liste von Todos zurück.
     * 
     * @return Die Liste von Todos.
     */
    public ArrayList<Todo> getTodos() {
        return todos;
    }

    /*
     * Wirft eine Exception, wenn das Maintainable nicht durch die erbenden Klassen
     * eine eigene getLocation-Methode hat.
     * 
     * @return Die Lage des Maintainables.
     */
    public String getLocation() {
        throw new UnsupportedOperationException();
    }

    /*
     * Wirft eine Exception, wenn das Maintainable nicht durch die erbenden Klassen
     * eine eigene toString-Methode hat.
     * 
     * @return Das Maintainable als String.
     */
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
