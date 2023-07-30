package testing;

import java.time.LocalDate;

import enums.EquipConditionEnum;
import enums.EquipTypeEnum;
import enums.RoomTypeEnum;
import maintainables.Building;
import maintainables.Equipment;
import maintainables.Level;
import maintainables.Room;
import maintainables.equipment.SanitaryEquipment;
import maintainables.equipment.TechnicalEquipment;

/**
 * Die Klasse RoomTest testet die einzelnen Methoden der Room-Klasse.
 * Es werden alle Methoden aufgerufen und die Ergebnisse in der Konsole
 * ausgegeben.
 * 
 * 
 * @author Marten Tietje
 * 
 */
public class RoomTest {
    public static void testRoomMethods() {

        /*
         * Erstellung eines Objekts der Klasse Level, welches für das Room benötigt wird
         */
        Level level = new Level(1, 10, new Building("SAP", "Dietmar Hopp Allee", "16a", "Walldorf", 5));

        /* Erstellung eines Objekts der Klasse Room */
        Room room = new Room("Conference Room", level, RoomTypeEnum.BESPRECHUNGSRAUM);

        /* Test getName */
        System.out.println("Room Name: " + room.getName());

        /* Test getRoomType */
        System.out.println("Room Type: " + room.getRoomType());

        /* Test getLocation */
        System.out.println("Room Location: " + room.getLocation());

        /*
         * Erstellung und Test von Equipment-Objekten
         */
        Equipment equipment1 = new SanitaryEquipment("1", "Toilet", EquipTypeEnum.TOILETTE, room, "manufacturer",
                "model", LocalDate.of(2017, 10, 14), LocalDate.of(2023, 06, 13), EquipConditionEnum.LIKE_NEW, true,
                true, true, 1, "SAP");
        Equipment equipment2 = new TechnicalEquipment("2", "Beamer", EquipTypeEnum.PROJEKTOR, room, "manufacturer",
                "model", LocalDate.of(2017, 10, 14), LocalDate.of(2023, 06, 13), EquipConditionEnum.LIKE_NEW, true,
                100, "WLAN", null, null);

        /* Test addEquipment */
        room.addEquipment(equipment1);
        room.addEquipment(equipment2);
        System.out.println("Room Equipment after adding: " + room.getEquipment());

        /* Test removeEquipment */
        room.removeEquipment(equipment1);
        System.out.println("Room Equipment after removal: " + room.getEquipment());

        /* Test to String */
        System.out.println("Room to String: " + room.toString());

        System.out.println("RoomTest completed");
    }
}
