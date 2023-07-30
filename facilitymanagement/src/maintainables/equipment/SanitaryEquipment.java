package maintainables.equipment;

import maintainables.Equipment;
import enums.EquipConditionEnum;
import enums.EquipTypeEnum;
import java.time.LocalDate;
import maintainables.Room;;

/*
 * Die Klasse SanitaryEquipment repräsentiert spezifische Sanitärausstattung, die in einem Raum vorhanden ist.
 * Sie erbt von der Klasse Equipment und fügt spezifische Eigenschaften für Sanitärausstattung hinzu.
 *
 * @author Alexander Ansorge
 */
public class SanitaryEquipment extends Equipment {
    private boolean waterConnection;
    private boolean drainageConnection;
    private int cleaningRequirement;
    private String operatingCompany;

    /*
     * Der Konstruktor der SanitaryEquipment-Klasse erzeugt ein neues SanitaryEquipment.
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
     * @param waterConnection Ein boolescher Wert, der angibt, ob das Equipment einen Wasseranschluss hat oder nicht.
     * @param drainageConnection Ein boolescher Wert, der angibt, ob das Equipment einen Abwasseranschluss hat oder nicht.
     * @param cleaningRequirement Der Reinigungsbedarf des Equipments (0 für nie, 1 für monatlich, 2 für wöchentlich, 3 für täglich).
     * @param operatingCompany Das Unternehmen, das für das Equipment verantwortlich ist.
     */
    public SanitaryEquipment(String id, String name, EquipTypeEnum type, Room room, String manufacturer, String model,
                             LocalDate dateOfPurchase, LocalDate lastMaintenanceDate, EquipConditionEnum condition, boolean functional,
                             boolean waterConnection, boolean drainageConnection, int cleaningRequirement, String operatingCompany) {
        super(id, name, type, room, manufacturer, model, dateOfPurchase, lastMaintenanceDate, condition, functional);
        this.waterConnection = waterConnection;
        this.drainageConnection = drainageConnection;
        this.cleaningRequirement = cleaningRequirement;
        this.operatingCompany = operatingCompany;
    }

    // Getter und Setter für die neuen Eigenschaften

    /*
     * Gibt den Zustand der Wasseranschluss zurück.
     * 
     * @return Der Zustand der Wasseranschluss.
     */
    public boolean getWaterConnection() {
        return waterConnection;
    }

    /*
     * Legt den Zustand der Wasseranschluss fest und speichert die Änderung.
     * 
     * @param waterConnection Der Zustand der Wasseranschluss.
     */
    public void setWaterConnection(boolean waterConnection) {
        this.waterConnection = waterConnection;
    }

    /*
     * Gibt den Zustand der Abwasseranschluss zurück.
     * 
     * @return Der Zustand der Abwasseranschluss.
     */
    public boolean getDrainageConnection() {
        return drainageConnection;
    }

    /*
     * Legt den Zustand der Abwasseranschluss fest und speichert die Änderung.
     * 
     * @param drainageConnection Der Zustand der Abwasseranschluss.
     */
    public void setDrainageConnection(boolean drainageConnection) {
        this.drainageConnection = drainageConnection;
    }

    /*
     * Gibt das Reinigungsbedarf zurück.
     * 
     * @return Das Reinigungsbedarf.
     */
    public int getCleaningRequirement() {
        return cleaningRequirement;
    }

    /*
     * Legt das Reinigungsbedarf fest und speichert die Änderung.
     * 
     * @param cleaningRequirement Das Reinigungsbedarf.
     */
    public void setCleaningRequirement(int cleaningRequirement) {
        this.cleaningRequirement = cleaningRequirement;
    }

    /*
     * Gibt die Betriebsfirma zurück.
     * 
     * @return Die Betriebsfirma.
     */
    public String getOperatingCompany() {
        return operatingCompany;
    }

    /*
     * Legt die Betriebsfirma fest und speichert die Änderung.
     * 
     * @param operatingCompany Die Betriebsfirma.
     */
    public void setOperatingCompany(String operatingCompany) {
        this.operatingCompany = operatingCompany;
    }
}
