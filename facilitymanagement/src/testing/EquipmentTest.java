package testing;

import java.time.LocalDate;

import enums.EquipTypeEnum;
import enums.OwnerEnum;
import enums.RoomTypeEnum;
import global.GlobalVerwaltung;
import maintainables.Building;
import maintainables.Equipment;
import maintainables.Level;
import maintainables.Room;
import maintainables.equipment.OfficeEquipment;

/**
 * Die Klasse EquipmentTest testet die einzelnen Methoden der Equipment-Klasse.
 * Es werden alle Methoden aufgerufen und die Ergebnisse in der Konsole
 * ausgegeben.
 * 
 * 
 * @author Marten Tietje
 * 
 */
public class EquipmentTest {
    public static void testEquipmentMethods() {
        Building building = new Building("Google", "abc Straße", "36", "berlin", 25);
        GlobalVerwaltung.getBuildingVerwaltung().addBuilding(building);
        Level level = new Level(1, 30, building);

        /*
         * Erstellung eines Objekts der Klasse Room, welches für das Equipment benötigt
         * wird
         */
        Room room = new Room("Raumname", level, RoomTypeEnum.BÜRO);

        /* Erstellung eines Objekts der Klasse Equipment */
        Equipment equipment = new OfficeEquipment("1", "projector", EquipTypeEnum.PROJEKTOR, room, "manufacturer",
                "model", LocalDate.now(), LocalDate.now(), null, true, "1", OwnerEnum.FIRMA);

        /* Test getName */
        System.out.println("Equipment Name: " + equipment.getName());

        /* Test getLocation */
        System.out.println("Equipment Location: " + equipment.getLocation());

        /* Test compareTo */
        Equipment equipment2 = new OfficeEquipment("1", "projector", EquipTypeEnum.PROJEKTOR, room, "manufacturer",
                "model", LocalDate.now(), LocalDate.now(), null, true, "1", OwnerEnum.FIRMA);
        int comparison = equipment.compareTo(equipment2);
        System.out.println("Comparison result: " + comparison);

        /* Test to String */
        System.out.println("Equipment to String: " + equipment.toString());

        System.out.println("EquipmentTest completed");

    }
}
