package testing;

import java.util.UUID;

import employee.EmployeeVerwaltung;
import global.GlobalVerwaltung;
import maintainables.Building;
import maintainables.BuildingVerwaltung;
import maintainables.Level;

/**
 * Die Klasse BuildingTest testet die einzelnen Methoden der Building-Klasse.
 * Es werden alle Methoden aufgerufen und die Ergebnisse in der Konsole ausgegeben.
 * 
 * 
 * @author Marten Tietje
 * 
 */
public class BuildingTest {
    public static void testBuildingMethods() {

        /* Erstellung von Testobjekten der Klasse Building */
        Building building = new Building("HWR", "Alt-Friedrichsfelde", "60", "Berlin", 5);
        GlobalVerwaltung.getBuildingVerwaltung().addBuilding(building);
        building = new Building("SAP", "Dietmar Hopp Allee", "16a", "Walldorf", 5);
        GlobalVerwaltung.getBuildingVerwaltung().addBuilding(building);
        
        /* Test getName */
        System.out.println("Building Name: " + building.getName());

        /* Test getId */
        System.out.println("Building ID: " + building.getId());

        /* Test setId */
        building.setId(UUID.randomUUID());
        System.out.println("Set new Building ID: " + building.getId());

        /* Test getLocation */
        System.out.println("Building Location: " + building.getLocation());

        /* Test getMaxLevels */
        System.out.println("Max Levels: " + building.getMaxLevels());

        /* Test getLevels */
        System.out.println("Building Levels: " + building.getLevels());

        /* Erstellung und Test von Level-Objekten */
        Level level1 = new Level(3, 10, building);
        Level level2 = new Level(2, 15, building);


        /* Test addLevel */
        building.addLevel(level1);
        building.addLevel(level2);
        System.out.println("Building Levels after adding: " + building.getLevels());

        /* Test removeLevel */
        building.removeLevel(level1);
        System.out.println("Building Levels after removal: " + building.getLevels());

        /* Test getTodos */
        System.out.println("Building Todos: " + building.getTodos());
        
        /* Test to String */
        System.out.println("Building to String: " + building.toString());

        System.out.println("BuildingTest completed");


    }
}
