package maintainables.equipment;

import enums.EquipConditionEnum;
import enums.EquipTypeEnum;
import java.time.LocalDate;
import java.util.List;
import maintainables.Equipment;
import maintainables.Room;

/*
 * Die Klasse TechnicalEquipment repräsentiert technische Ausstattungen, welche in einem Raum vorhanden sind.
 * Sie erbt von der Equipment-Klasse, um für Todos eine einheitliche Implementierung mit Equipments zu ermöglichen.
 * 
 * @author Alexander Ansorge
 */
public class TechnicalEquipment extends Equipment {
    private int powerRating;
    private String connectivity;
    private List<String> compatibleDevices;
    private String softwareVersion;

    /*
     * Der Konstruktor der TechnicalEquipment-Klasse erzeugt eine neue technische Ausstattung.
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
     * @param powerRating Die Leistungsbewertung des Equipments (in Watt).
     * @param connectivity Die Konnektivitätsoptionen des Equipments (z.B. Wi-Fi, Ethernet, Bluetooth).
     * @param compatibleDevices Eine Liste von Geräten, die mit dem Equipment kompatibel sind.
     * @param softwareVersion Die Softwareversion des Equipments, falls zutreffend.
     */
    public TechnicalEquipment(String id, String name, EquipTypeEnum type, Room room, String manufacturer, String model,
                              LocalDate dateOfPurchase, LocalDate lastMaintenanceDate, EquipConditionEnum condition, boolean functional,
                              int powerRating, String connectivity, List<String> compatibleDevices, String softwareVersion) {
        super(id, name, type, room, manufacturer, model, dateOfPurchase, lastMaintenanceDate, condition, functional);
        this.powerRating = powerRating;
        this.connectivity = connectivity;
        this.compatibleDevices = compatibleDevices;
        this.softwareVersion = softwareVersion;
    }

    /*
     * Gibt die Leistungsbewertung des Equipments zurück.
     *
     * @return Die Leistungsbewertung des Equipments (in Watt).
     */
    public int getPowerRating() {
        return powerRating;
    }

    /*
     * Setzt die Leistungsbewertung des Equipments.
     *
     * @param powerRating Die neue Leistungsbewertung des Equipments (in Watt).
     */
    public void setPowerRating(int powerRating) {
        this.powerRating = powerRating;
    }

    /*
     * Gibt die Konnektivitätsoptionen des Equipments zurück.
     *
     * @return Die Konnektivitätsoptionen des Equipments (z.B. Wi-Fi, Ethernet, Bluetooth).
     */
    public String getConnectivity() {
        return connectivity;
    }

    /*
     * Setzt die Konnektivitätsoptionen des Equipments.
     *
     * @param connectivity Die neuen Konnektivitätsoptionen des Equipments (z.B. Wi-Fi, Ethernet, Bluetooth).
     */
    public void setConnectivity(String connectivity) {
        this.connectivity = connectivity;
    }

    /*
     * Gibt eine Liste von Geräten zurück, die mit dem Equipment kompatibel sind.
     *
     * @return Eine Liste von Geräten, die mit dem Equipment kompatibel sind.
     */
    public List<String> getCompatibleDevices() {
        return compatibleDevices;
    }

    /*
     * Setzt die Liste von Geräten, die mit dem Equipment kompatibel sind.
     *
     * @param compatibleDevices Die neue Liste von Geräten, die mit dem Equipment kompatibel sind.
     */
    public void setCompatibleDevices(List<String> compatibleDevices) {
        this.compatibleDevices = compatibleDevices;
    }

    /*
     * Gibt die Softwareversion des Equipments zurück, falls zutreffend.
     *
     * @return Die Softwareversion des Equipments, falls zutreffend.
     */
    public String getSoftwareVersion() {
        return softwareVersion;
    }

    /*
     * Setzt die Softwareversion des Equipments.
     *
     * @param softwareVersion Die neue Softwareversion des Equipments, falls zutreffend.
     */
    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }
}