package maintainables;

import java.util.ArrayList;
import java.util.UUID;

import global.GlobalVerwaltung;
import todos.Todo;

/*
 * Die Klasse Building repräsentiert ein Gebäude.
 * Sie implementiert das Maintainable-Interface, um für Todos eine einheltiche Implementierung mit Maintainables zu ermöglichen.
 * 
 * @author Florian Schmidt
 */
public class Building extends Maintainable implements Comparable<Building> {

    UUID id;
    String name;
    String street;
    String houseNumber;
    String city;
    int maxLevels;
    ArrayList<Level> levels;

    /*
     * Der Konstruktor der Building-Klasse erzeugt ein neues Gebäude. Wenn dieses
     * der BuildingVerwaltung hinzugefügt wird, erhält es eine einheitliche ID.
     * 
     * @param name Der Name des Gebäudes.
     * 
     * @param street Die Straße, in der sich das Gebäude befindet.
     * 
     * @param houseNumber Die Hausnummer des Gebäudes.
     * 
     * @param city Die Stadt, in der sich das Gebäude befindet.
     * 
     * @param maxLevels Die Anzahl der Stockwerke des Gebäudes.
     */
    public Building(String name, String street, String houseNumber, String city, int maxLevels) {
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.maxLevels = maxLevels;
        this.levels = new ArrayList<>();
    }

    /*
     * Legt die ID fest und speichert die Änderung.
     * 
     * @param id Die ID des Gebäudes.
     */
    public void setId(UUID id) {
        this.id = id;
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();
    }

    /*
     * Erzeugt einen String um den Mitarbeitern eine eindeutige Angabe über die Lage
     * zu geben.
     * 
     * @return Die Adresse des Gebäudes.
     */
    @Override
    public String getLocation() {
        return (street + " " + houseNumber + ", " + city);
    }

    /*
     * Fügt eine Etage dem Gebäude hinzu und speichert die Änderung.
     * 
     * @param level Die Etage, die hinzugefügt werden soll.
     */
    public void addLevel(Level level) {
        levels.add(level);
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();
    }

    /*
     * Entfernt eine Etage aus dem Gebäude und speichert die Änderung.
     * 
     * @param level Die Etage, die entfernt werden soll.
     */
    public void removeLevel(Level level) {
        levels.remove(level);
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();

    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public int getMaxLevels() {
        return maxLevels;
    }

    public ArrayList<Todo> getTodos() {
        return super.getTodos();
    }

    /*
     * Vergleicht die Buildings anhand ihres Namens alphabetisch.
     */
    @Override
    public int compareTo(Building building) {
        return name.compareTo(building.name);
    }

    /*
     * Erzeugt einen String, der den Namen und die Adresse des Gebäudes enthält.
     * 
     * @return Der Name und die Adresse des Gebäudes.
     */
    @Override
    public String toString() {
        return ("Gebäude: " + name);
    }

}
