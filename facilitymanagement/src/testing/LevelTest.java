package testing;

import enums.RoomTypeEnum;
import maintainables.Building;
import maintainables.Level;
import maintainables.Room;

/**
 * Die Klasse LevelTest testet die einzelnen Methoden der Level-Klasse.
 * Es werden alle Methoden aufgerufen und die Ergebnisse in der Konsole ausgegeben.
 * 
 * 
 * @author Marten Tietje
 * 
 */
public class LevelTest {
    public static void testLevelMethods() {

        /* Erstellung eines Objekts der Klasse Building, welches für das Level benötigt wird */
        Building building = new Building("SAP", "Dietmar Hopp Allee", "16a", "Walldorf", 5);

        /* Erstellung eines Objekts der Klasse Level */
        Level level = new Level(1, 10, building);

        /* Test getNumber */
        System.out.println("Level Number: " + level.getNumber());

        /* Test getMaxRooms */
        System.out.println("Max Rooms: " + level.getMaxRooms());

        /* Test getLocation */
        System.out.println("Level Location: " + level.getLocation());

        /* Erstellung und Test von Room-Objekten */
        Room room1 = new Room("Raumname", level, RoomTypeEnum.BÜRO);
        Room room2 = new Room("Raumname2", level, RoomTypeEnum.BÜRO);
        

        /* Test addRoom */
        level.addRoom(room1);
        level.addRoom(room2);
        System.out.println("Level Rooms after adding: " + level.getRooms());

        /* Test removeRoom */
        level.removeRoom(room1);
        System.out.println("Level Rooms after removal: " + level.getRooms());

        
        /* Test to String */
        System.out.println("Level to String: " + level.toString());

        System.out.println("LevelTest completed");
    }
}
