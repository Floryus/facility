package maintainables;

import enums.EquipConditionEnum;
import enums.EquipTypeEnum;
import java.time.LocalDate;

/*
 * Die Klasse Equipment repräsentiert ein Equipment, welches in einem Raum vorhanden ist.
 * Sie implementiert das Maintainable-Interface, um für Todos eine einheltiche Implementierung mit Maintainables zu ermöglichen.
 * 
 * @author Alexander Ansorge
 */
public class Equipment extends Maintainable implements Comparable<Equipment> {
    private String id;
    private String name;
    private EquipTypeEnum type;
    private Room room;
    private String manufacturer;
    private String model;
    private LocalDate dateOfPurchase;
    private LocalDate lastMaintenanceDate;
    private EquipConditionEnum condition;
    protected boolean functional;

    /*
     * Der Konstruktor der Equipment-Klasse erzeugt ein neues Equipment.
     * 
     * @param id Die eindeutige ID des Equipments.
     * @param name Der Name des Equipments.
     * @param type Der Typ des Equipments.
     * @param room Der Raum, in dem sich das Equipment befindet.
     * @param manufacturer Der Hersteller des Equipments.
     * @param model Das Modell des Equipments.
     * @param dateOfPurchase Das Kaufdatum des Equipments.
     * @param lastMaintenanceDate Das Datum der letzten Wartung.
     * @param condition Der aktuelle Zustand des Equipments.
     * @param functional Ein boolescher Wert, der angibt, ob das Equipment funktioniert oder nicht.
     */
    public Equipment(String id, String name, EquipTypeEnum type, Room room, String manufacturer, String model,
                     LocalDate dateOfPurchase, LocalDate lastMaintenanceDate, EquipConditionEnum condition, boolean functional) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.room = room;
        this.manufacturer = manufacturer;
        this.model = model;
        this.dateOfPurchase = dateOfPurchase;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.condition = condition;
        this.functional = functional;
    }

    /*
     * Gibt die ID des Equipments zurück.
     *
     * @return Die ID des Equipments.
     */
    public String getId() {
        return id;
    }

    /*
     * Setzt die ID des Equipments.
     *
     * @param id Die neue ID des Equipments.
     */
    public void setId(String id) {
        this.id = id;
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
     * Setzt den Namen des Equipments.
     *
     * @param name Der neue Name des Equipments.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Gibt den Typ des Equipments zurück.
     * 
     * @return Der Typ des Equipments.
     */
    public EquipTypeEnum getType() {
        return type;
    }

    /*
     * Setzt den Typ des Equipments.
     *
     * @param type Der neue Typ des Equipments.
     */
    public void setType(EquipTypeEnum type) {
        this.type = type;
    }

    /*
     * Gibt den Raum zurück, in dem sich das Equipment befindet.
     * 
     * @return Der Raum, in dem sich das Equipment befindet.
     */
    public Room getRoom() {
        return room;
    }

    /*
     * Setzt den Raum, in dem sich das Equipment befindet.
     *
     * @param room Der neue Raum für das Equipment.
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /*
     * Gibt den Hersteller des Equipments zurück.
     *
     * @return Der Hersteller des Equipments.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /*
     * Setzt den Hersteller des Equipments.
     *
     * @param manufacturer Der neue Hersteller des Equipments.
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /*
     * Gibt das Modell des Equipments zurück.
     *
     * @return Das Modell des Equipments.
     */
    public String getModel() {
        return model;
    }

    /*
     * Setzt das Modell des Equipments.
     *
     * @param model Das neue Modell des Equipments.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /*
     * Gibt das Kaufdatum des Equipments zurück.
     *
     * @return Das Kaufdatum des Equipments.
     */
    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    /*
     * Setzt das Kaufdatum des Equipments.
     *
     * @param dateOfPurchase Das neue Kaufdatum des Equipments.
     */
    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    /*
     * Gibt das Datum der letzten Wartung des Equipments zurück.
     *
     * @return Das Datum der letzten Wartung des Equipments.
     */
    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    /*
     * Setzt das Datum der letzten Wartung des Equipments.
     *
     * @param lastMaintenanceDate Das neue Datum der letzten Wartung.
     */
    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    /*
     * Gibt den Zustand des Equipments zurück.
     *
     * @return Der Zustand des Equipments.
     */
    public EquipConditionEnum getCondition() {
        return condition;
    }

    /*
     * Setzt den Zustand des Equipments.
     *
     * @param condition Der neue Zustand des Equipments.
     */
    public void setCondition(EquipConditionEnum condition) {
        this.condition = condition;
    }

    /*
     * Gibt zurück, ob das Equipment funktional ist oder nicht.
     *
     * @return True, wenn das Equipment funktional ist, sonst false.
     */
    public boolean isFunctional() {
        return functional;
    }

    /*
     * Setzt, ob das Equipment funktional ist oder nicht.
     *
     * @param functional Der neue Funktionszustand des Equipments.
     */
    public void setFunctional(boolean functional) {
        this.functional = functional;
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
     * -1, wenn das Equipment vor dem zu vergleichenden Equipment einsortiert werden
     * soll, 1, wenn das Equipment nach dem zu vergleichenden Equipment einsortiert
     * werden soll.
     */
    @Override
    public int compareTo(Equipment equipment) {
        int compare = type.compareTo(equipment.getType());
        if (compare == 0) {
            compare = name.compareTo(equipment.getName());
        }
        return compare;
    }
}
