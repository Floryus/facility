package global;

import employee.EmployeeVerwaltung;
import maintainables.BuildingVerwaltung;
import todos.TodoVerwaltung;
import maintainables.equipment.EquipmentVerwaltung;;

/* GlobalVerwaltung bietet getter für die zentralen Verwaltungsklassen.
 * Diese können nun von überall statisch aufgerufen werden.
 * So lässt sich eine einfache Implementierung von gleichen Objekten in mehreren Verwaltungen realisieren.
 * Z.B. werden Todos ihrem Maintainable und der TodoVerwaltung hinzugefügt, können aber auch vom Mitarbeiter aus aufgerufen werden.
 * 
 * Die GlobalVerwaltung hat also die Aufgabe, die einzelnen Verwaltungsobjekte zu erzeugen, zu verwalten und sie für alle anderen 
 * Methoden zugänglich zu machen.
 * 
 * @author Florian Schmidt, Alexander Ansorge
 */
public class GlobalVerwaltung {
    private static BuildingVerwaltung buildingVerwaltung = new BuildingVerwaltung();
    private static EmployeeVerwaltung employeeVerwaltung = new EmployeeVerwaltung();
    private static TodoVerwaltung todoVerwaltung = new TodoVerwaltung();
    private static EquipmentVerwaltung equipmentVerwaltung = new EquipmentVerwaltung();

    public static BuildingVerwaltung getBuildingVerwaltung() {
        return buildingVerwaltung;
    }

    public static EmployeeVerwaltung getEmployeeVerwaltung() {
        return employeeVerwaltung;
    }

    public static TodoVerwaltung getTodoVerwaltung() {
        return todoVerwaltung;
    }

    public static EquipmentVerwaltung getEquipmentVerwaltung() {
        return equipmentVerwaltung;
    }

}
