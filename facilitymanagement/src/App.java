
import employee.EmployeeVerwaltung;
import global.GlobalVerwaltung;
import gui.FullScreenMain;
import maintainables.BuildingVerwaltung;
import maintainables.equipment.EquipmentVerwaltung;
import testing.TestManager;
import todos.TodoVerwaltung;

public class App {
    public static void main(String[] args) throws Exception {

        // Initialisierung der Verwaltungen
        EmployeeVerwaltung employeeVerwaltung = GlobalVerwaltung.getEmployeeVerwaltung();
        TodoVerwaltung todoVerwaltung = GlobalVerwaltung.getTodoVerwaltung();
        BuildingVerwaltung buildingVerwaltung = GlobalVerwaltung.getBuildingVerwaltung();
        EquipmentVerwaltung equipmentVerwaltung = GlobalVerwaltung.getEquipmentVerwaltung();

        // Erzeugen von Testdaten - Dieser wird in der finalen Abgabe auskommentiert, da
        // wir sonst die Datenbank vollmüllen
        TestManager testManager = new TestManager();
        testManager.run();

        // Anzeigen des GUI
        FullScreenMain gui = new FullScreenMain();
        gui.setVisible(true);

    }

}
