package maintainables.equipment;

import enums.EquipConditionEnum;
import enums.EquipTypeEnum;
import maintainables.Equipment;
import maintainables.Room;
import java.time.LocalDate;

/*
 * Die Klasse SafetyEquipment erbt von Equipment und repräsentiert ein Sicherheitsequipment.
 * 
 * @author Alexander Ansorge
 */
public class SafetyEquipment extends Equipment {
    private int batteryLife;
    private String connectivity;
    private int range;
    private String logPath;

    /*
     * Der Konstruktor der SafetyEquipment-Klasse erzeugt ein neues SafetyEquipment.
     * @param name Der Name des Equipments.
     * @param type Der Typ des Equipments.
     * @param room Der Raum, in dem sich das Equipment befindet.
     * @param manufacturer Der Hersteller des Equipments.
     * @param installationDate Das Installationsdatum des Equipments.
     * @param batteryLife Die Batterielebensdauer des Equipments.
     * @param connectivity Der Konnektivitätstyp des Equipments.
     * @param range Die Reichweite des Equipments.
     * @param logPath Der Speicherort für die Logs des Equipments.
     */
    public SafetyEquipment(String id, String name, EquipTypeEnum type, Room room, String manufacturer, String model,
                           LocalDate dateOfPurchase, LocalDate lastMaintenanceDate, EquipConditionEnum condition, boolean functional,
                           int batteryLife, String connectivity, int range, String logPath) {
        super(id, name, type, room, manufacturer, model, dateOfPurchase, lastMaintenanceDate, condition, functional);
        this.batteryLife = batteryLife;
        this.connectivity = connectivity;
        this.range = range;
        this.logPath = logPath;
    }

    /*
     * Gibt die verbleibende Batterielebensdauer des Equipments zurück.
     * 
     * @return Die verbleibende Batterielebensdauer des Equipments.
     */
    public int getBatteryLife() {
        return batteryLife;
    }

    /*
     * Legt die verbleibende Batterielebensdauer des Equipments fest und speichert die Änderung.
     * 
     * @param batteryLife Die verbleibende Batterielebensdauer des Equipments.
     */
    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    /*
     * Gibt den Konnektivitätstyp des Equipments zurück.
     * 
     * @return Der Konnektivitätstyp des Equipments.
     */
    public String getConnectivity() {
        return connectivity;
    }

    /*
     * Legt den Konnektivitätstyp des Equipments fest und speichert die Änderung.
     * 
     * @param connectivity Der Konnektivitätstyp des Equipments.
     */
    public void setConnectivity(String connectivity) {
        this.connectivity = connectivity;
    }

    /*
     * Gibt den Bereich oder die Reichweite des Equipments zurück.
     * 
     * @return Der Bereich oder die Reichweite des Equipments.
     */
    public int getRange() {
        return range;
    }

    /*
     * Legt den Bereich oder die Reichweite des Equipments fest und speichert die Änderung.
     * 
     * @param range Der Bereich oder die Reichweite des Equipments.
     */
    public void setRange(int range) {
        this.range = range;
    }

    /*
     * Gibt den Ort, an dem die Logs für das Gerät gespeichert werden, zurück.
     * 
     * @return Der Ort, an dem die Logs für das Gerät gespeichert werden.
     */
    public String getLogPath() {
        return logPath;
    }

    /*
     * Legt den Ort, an dem die Logs für das Gerät gespeichert werden, fest und speichert die Änderung.
     * 
     * @param logPath Der Ort, an dem die Logs für das Gerät gespeichert werden.
     */
    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
}
