package todos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

/*
 * TodoVerwaltung ist die Klasse, die alle Todos verwaltet.
 * Sie ist zuständig für die Persistenz aller Todos.
 * 
 * @author Florian Schmidt
 */
public class TodoVerwaltung implements Serializable {
    private static ArrayList<Todo> todos;

    /*
     * Der Konstruktor der TodoVerwaltung erzeugt eine neue Liste von Todos.
     * Wenn es bereits eine Liste von Todos gibt, wird diese geladen.
     */
    public TodoVerwaltung() {
        todos = new ArrayList<>();
        try {
            loadTodos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Hinzufügen eines Todos (genauer: einer MaintenanceTask oder einem Ticket) zu
     * der Gesamtheit aller Todos.
     * Hinzufügen zu der individuellen Liste für den Mitarbeiter und das
     * Maintainable
     * 
     * @param todo Das Todo, das hinzugefügt werden soll.
     */
    public void addTodo(Todo todo) {
        todo.id = UUID.randomUUID();
        // Zur Todoverwaltung
        todos.add(todo);
        // Zum Mitarbeiter
        if (todo.hasEmployee()) {
            todo.getEmployee().assignTodoToEmployee(todo);
        }
        // Zum Maintainable
        todo.getMaintainable().addTodo(todo);

        saveTodos();
    }

    /*
     * Entfernen eines Todos aus der Gesamtheit aller Todos.
     * Entfernen aus der individuellen Liste für den Mitarbeiter und des
     * Maintainables.
     * Die Änderung wird abgespeichert.
     * 
     * @param todo Das Todo, das entfernt werden soll.
     */
    public void removeTodo(Todo todo) {
        todos.remove(todo);
        if (todo.hasEmployee()) {
            todo.getEmployee().removeTodoFromEmployee(todo);
        }
        todo.getMaintainable().removeTodo(todo);
        saveTodos();
    }

    /*
     * Gibt die Liste von Todos zurück.
     * 
     * @return Die Liste von Todos.
     */
    public ArrayList<Todo> getTodos() {
        loadTodos();
        return todos;
    }

    /*
     * Gibt das Todo mit der übergebenen ID zurück.
     * 
     * @param id Die ID des gesuchten Todos.
     * 
     * @return Das Todo mit der übergebenen ID.
     */
    public static Todo getTodoById(UUID id) {
        loadTodos();
        for (Todo todo : todos) {
            if (todo.id == id) {
                return todo;
            }
        }
        return null;
    }

    public void sortTodos() {
        loadTodos();
        ArrayList<Todo> sortedTodos = new ArrayList<>(todos);
        Collections.sort(sortedTodos);
        todos = sortedTodos;
    }

    /*
     * Die Liste von Todos wird abgespeichert in der todos.txt Datei.
     * Sollte ein Fehler auftreten, wird dieser ausgegeben.
     * 
     * Diese Methode ist statisch, damit sie bei jeder Änderung der Liste aufgerufen
     * werden kann, ohne dass mehrere Objekte der Klasse TodoVerwaltung
     * existieren müssen.
     * 
     * Der Speichervorgang wird so zum Teil der Anwendungslogik und muss nicht über
     * das GUI angestoßen werden.
     */
    public static void saveTodos() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("todos.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(todos);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception i) {
            i.printStackTrace();
        }
    }

    /*
     * Die Liste von Todos wird aus der todo.txt Datei geladen.
     * Sollte ein Fehler auftreten, wird dieser ausgegeben.
     * 
     * Der Ladevorgang wird beim Programmstart bzw. beim Erzeugen eines Objekts der
     * TodoVerwaltung in der GlobalVerwaltung angestoßen. Somit werden beim
     * Programmstart immer alle verfügbaren Daten geladen.
     */
    public static void loadTodos() {

        File file = new File("todos.txt");
        if (!file.exists()) {
            return;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("todos.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            todos = (ArrayList<Todo>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception i) {
            i.printStackTrace();
        }

    };

}