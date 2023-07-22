package maintainables;

import java.util.ArrayList;

import global.GlobalVerwaltung;

/*
 * Die Klasse Level repräsentiert ein Stockwerk eines Gebäudes.
 * Sie implementiert das Maintainable-Interface, um für Todos eine einheltiche Implementierung mit Maintainables zu ermöglichen.
 * 
 * @Author Florian Schmidt
 */
public class Level extends Maintainable implements Comparable<Level> {
    int number;
    int maxRooms;
    Building building;
    ArrayList<Room> rooms;

    /*
     * Der Konstruktor der Level-Klasse erzeugt ein neues Stockwerk.
     * 
     * @param number Die Nummer des Stockwerks.
     * 
     * @param maxRooms Die maximale Anzahl an Räumen, die das Stockwerk haben kann.
     * 
     * @param building Das Gebäude, in dem sich das Stockwerk befindet.
     */
    public Level(int number, int maxRooms, Building building) {
        this.number = number;
        this.maxRooms = maxRooms;
        this.building = building;
        this.rooms = new ArrayList<>();
    }

    /*
     * Fügt einen Raum hinzu und speichert die Änderung.
     * 
     * @param room Der Raum, der hinzugefügt werden soll.
     */
    public void addRoom(Room room) {
        rooms.add(room);
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();
    }

    /*
     * Entfernt einen Raum und speichert die Änderung.
     * 
     * @param room Der Raum, der entfernt werden soll.
     */
    public void removeRoom(Room room) {
        rooms.remove(room);
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();
    }

    /*
     * Gibt die Liste von Räumen zurück.
     * 
     * @return Die Liste von Räumen.
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /*
     * Erzeugt einen String um den Mitarbeitern eine eindeutige Angabe über die Lage
     * zu geben.
     * 
     * @return Die Lage des Levels.
     */
    @Override
    public String getLocation() {
        return building.getLocation() + ", Level " + number;
    }

    public int getMaxRooms() {
        return maxRooms;
    }

    public int getNumber() {
        return number;
    }

    /*
     * Vergleicht zwei Levels miteinander und sortiert sie nach ihrer Nummer.
     * 
     * @param level Das Level, mit dem verglichen werden soll.
     * 
     * @return 0, wenn die Levels gleich sind, -1, wenn das Level vorher kommen soll
     * und 1, wenn das Level später kommen soll.
     */
    @Override
    public int compareTo(Level level) {
        return number - level.number;
    }

    @Override
    public String toString() {
        return ("Etage " + number + " in " + building.toString());
    }

}
