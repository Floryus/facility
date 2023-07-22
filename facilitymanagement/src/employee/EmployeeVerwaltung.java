package employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import enums.GroupEnum;
import exceptions.WrongUserInputException;

/**
 * Die Klasse EmployeeVerwaltung verwaltet eine Liste von Mitarbeitern.
 * 
 * @author Florian Schmidt
 * 
 */
public class EmployeeVerwaltung implements Serializable {
    private static ArrayList<Employee> employees;

    /**
     * Konstruktor der EmployeeVerwaltung-Klasse.
     * Erzeugt eine leere Liste von Mitarbeitern.
     * Falls es bereits eine Liste von Mitarbeitern gibt, wird diese geladen.
     */
    public EmployeeVerwaltung() {
        employees = new ArrayList<>();
        try {
            loadEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Gibt die Liste aller Mitarbeiter zurück.
     *
     * @return Die Liste aller Mitarbeiter.
     */
    public ArrayList<Employee> getEmployees() {
        loadEmployees();
        return employees;
    }

    /**
     * Fügt einen Mitarbeiter zur Liste hinzu.
     *
     * @param employee Der hinzuzufügende Mitarbeiter.
     * @throws WrongUserInputException Wenn der Mitarbeiter nicht hinzugefügt werden
     *                                 kann, aufgrund von falschen Eingaben.
     */
    public void addEmployee(Employee employee) throws WrongUserInputException {
        if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
            throw new WrongUserInputException("Kein Vorname", "Der Vorname darf nicht leer sein.");
        }
        if (employee.getLastName() == null || employee.getLastName().isEmpty()) {
            throw new WrongUserInputException("Kein Nachname", "Der Nachname darf nicht leer sein.");
        }
        if (employee.getGroup() == null) {
            throw new WrongUserInputException("Keine Gruppe", "Bitte wählen Sie eine Gruppe aus.");
        }
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        saveEmployees();
    }

    /**
     * Entfernt einen Mitarbeiter aus der Liste.
     *
     * @param employee Der zu entfernende Mitarbeiter.
     */
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        saveEmployees();
    }

    /**
     * Sucht einen Mitarbeiter anhand seiner ID in der Liste.
     *
     * @param id Die ID des gesuchten Mitarbeiters.
     * @return Der Mitarbeiter mit der angegebenen ID oder null, wenn kein
     *         Mitarbeiter gefunden wurde.
     */
    public Employee getEmployeeById(UUID id) {
        loadEmployees();
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Sucht einen Mitarbeiter anhand seines Namens in der Liste.
     * Wird vor allem für die Suche in der GUI verwendet.
     *
     * @param name Der Name des gesuchten Mitarbeiters.
     * @return Der Mitarbeiter mit dem angegebenen Namen oder null, wenn kein
     *         Mitarbeiter gefunden wurde.
     */
    public Employee getEmployeeByName(String name) {
        loadEmployees();
        for (Employee employee : employees) {
            if (employee.getName() == name) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Gibt eine Liste von Mitarbeitern zurück, die der angegebenen Gruppe
     * entsprechen.
     *
     * @param group Die Gruppe, nach der gesucht werden soll.
     * @return Eine Liste von Mitarbeitern, die der angegebenen Gruppe entsprechen.
     */
    public ArrayList<Employee> getEmployeesByGroup(GroupEnum group) {
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getGroup() == group) {
                result.add(employee);
            }
        }
        return result;
    }

    /**
     * Sortiert die Liste der Mitarbeiter nach ihrem Namen.
     *
     * @return Eine sortierte Liste der Mitarbeiter nach ihrem Namen.
     */
    public ArrayList<Employee> sortEmployeesByName() {
        ArrayList<Employee> sortedEmployees = new ArrayList<>(employees);
        Collections.sort(sortedEmployees);
        return sortedEmployees;
    }

    /*
     * Die Liste von Employees wird abgespeichert in der employees.txt Datei.
     * Sollte ein Fehler auftreten, wird dieser ausgegeben.
     * 
     * Diese Methode ist statisch, damit sie bei jeder Änderung der Liste aufgerufen
     * werden kann, ohne dass mehrere Objekte der Klasse EmployeeVerwaltung
     * existieren müssen.
     * 
     * Der Speichervorgang wird so zum Teil der Anwendungslogik und muss nicht über
     * das GUI angestoßen werden.
     */
    public static void saveEmployees() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("employees.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employees);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception i) {
            i.printStackTrace();
        }

    }

    /*
     * Die Liste von Employees wird aus der employees.txt Datei geladen.
     * Sollte ein Fehler auftreten, wird dieser ausgegeben.
     * 
     * Der Ladevorgang wird beim Programmstart bzw. beim Erzeugen eines Objekts der
     * EmployeeVerwaltung in der GlobalVerwaltung angestoßen. Somit werden beim
     * Programmstart immer alle verfügbaren Daten geladen.
     */
    public static void loadEmployees() {
        File file = new File("employees.txt");
        if (!file.exists()) {
            return;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("employees.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employees = (ArrayList<Employee>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception i) {
            i.printStackTrace();
        }

    }
}
