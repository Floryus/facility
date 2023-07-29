package maintainables.equipment;

import enums.EquipConditionEnum;
import enums.EquipTypeEnum;
import enums.OwnerEnum;
import java.time.LocalDate;
import maintainables.Equipment;
import maintainables.Room;

/**
 * Die Klasse OfficeEquipment repräsentiert ein Büroausstattungsstück, welches einem bestimmten Mitarbeiter zugeordnet sein kann.
 * Sie erbt von der Klasse Equipment, um alle grundlegenden Funktionen und Attribute eines Equipments zu haben.
 * 
 * @author Alexander Ansorge
 */
public class OfficeEquipment extends Equipment {
    private String employee;
    private OwnerEnum owner;

    /*
     * Der Konstruktor der OfficeEquipment-Klasse erzeugt ein neues Büroausstattungsstück.
     * 
     * @param id Die eindeutige ID des Büroausstattungsstücks.
     * @param name Der Name des Büroausstattungsstücks.
     * @param type Der Typ des Büroausstattungsstücks.
     * @param room Der Raum, in dem sich das Büroausstattungsstück befindet.
     * @param manufacturer Der Hersteller des Büroausstattungsstücks.
     * @param model Das Modell des Büroausstattungsstücks.
     * @param dateOfPurchase Das Kaufdatum des Büroausstattungsstücks.
     * @param lastMaintenanceDate Das Datum der letzten Wartung.
     * @param condition Der aktuelle Zustand des Büroausstattungsstücks.
     * @param functional Ein boolescher Wert, der angibt, ob das Büroausstattungsstück funktioniert oder nicht.
     * @param employee Die Mitarbeiter ID eines zu dem Büroausstattungsstück zugewiesenen Mitarbeiters.
     * @param owner Die Eigentümerschaft des Büroausstattungsstücks (Firma oder Privat).
     */
    public OfficeEquipment(String id, String name, EquipTypeEnum type, Room room, String manufacturer, String model,
                           LocalDate dateOfPurchase, LocalDate lastMaintenanceDate, EquipConditionEnum condition, boolean functional,
                           String employee, OwnerEnum owner) {
        super(id, name, type, room, manufacturer, model, dateOfPurchase, lastMaintenanceDate, condition, functional);
        this.employee = employee;
        this.owner = owner;
    }

    /*
     * Gibt die Mitarbeiter ID des zu dem Büroausstattungsstück zugewiesenen Mitarbeiters zurück.
     *
     * @return Die Mitarbeiter ID des zugewiesenen Mitarbeiters.
     */
    public String getEmployee() {
        return employee;
    }

    /*
     * Setzt die Mitarbeiter ID des zu dem Büroausstattungsstück zugewiesenen Mitarbeiters.
     *
     * @param employee Die neue Mitarbeiter ID des zugewiesenen Mitarbeiters.
     */
    public void setEmployee(String employee) {
        this.employee = employee;
    }

    /*
     * Gibt den Eigentümer des Büroausstattungsstücks zurück (Firma oder Privat).
     *
     * @return Der Eigentümer des Büroausstattungsstücks (Firma oder Privat).
     */
    public OwnerEnum getOwner() {
        return owner;
    }

    /*
     * Setzt den Eigentümer des Büroausstattungsstücks (Firma oder Privat).
     *
     * @param owner Der neue Eigentümer des Büroausstattungsstücks (Firma oder Privat).
     */
    public void setOwner(OwnerEnum owner) {
        this.owner = owner;
    }
}
