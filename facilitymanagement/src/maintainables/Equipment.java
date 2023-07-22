package maintainables;

import enums.EquipTypeEnum;

/*
 * Die Klasse Equipment repräsentiert ein Equipment, welches in einem Raum vorhanden ist.
 * Sie implementiert das Maintainable-Interface, um für Todos eine einheltiche Implementierung mit Maintainables zu ermöglichen.
 * 
 * @author Alexander Ansorge, Marten Tietje
 */
public class Equipment extends Maintainable implements Comparable<Equipment> {
    String name;
    EquipTypeEnum type;
    Room room;

    /*
     * Der Konstruktor der Equipment-Klasse erzeugt ein neues Equipment.
     * 
     * @param name Der Name des Equipments.
     * 
     * @param type Der Typ des Equipments.
     * 
     * @param room Der Raum, in dem sich das Equipment befindet.
     */
    public Equipment(String name, EquipTypeEnum type, Room room) {
        this.name = name;
        this.type = type;
        this.room = room;
    }

    /*
     * Gibt den Namen des Equipments zurück.
     * 
     * @return Der Name des Equipments.
     */
    public String getName() {
        return name;
    }

    /*
     * Erzeugt einen String um den Mitarbeitern eine eindeutige Angabe über die Lage
     * zu geben.
     * 
     * @return Die Lage des Equipments.
     */
    @Override
    public String getLocation() {
        return room.getLocation() + ", Equipment " + name;
    }

    /*
     * Vergleicht zwei Equipments miteinander und sortiert sie nach ihrem Typ und
     * Namen.
     * 
     * @param equipment Das Equipment, mit dem verglichen werden soll.
     * 
     * @return 0, wenn die Equipments gleich sind,
     * -1, wenn das Equipment vorher kommen soll und
     * 1, wenn das Equipment später kommen soll.
     */
    public int compareTo(Equipment equipment) {
        // Zuerst nach type sortieren
        int typeComparison = this.type.compareTo(equipment.type);
        if (typeComparison != 0) {
            return typeComparison;
        } else {
            // Wenn type gleich ist, nach name sortieren
            return this.name.compareTo(equipment.name);
        }
    }

    /*
     * Erzeugt einen String, der alle Informationen des Equipments enthält.
     * 
     * @return Ein String, der alle Informationen des Equipments enthält.
     */
    @Override
    public String toString() {
        return ("Equipment " + name + " " + type);
    }

}
