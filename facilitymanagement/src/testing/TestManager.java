package testing;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import employee.Employee;
import enums.GroupEnum;
import enums.PriorityEnum;
import global.GlobalVerwaltung;
import maintainables.Building;
import todos.MaintenanceTask;
import todos.Ticket;
import todos.Todo;

public class TestManager {

    public void run() throws Exception {

        // Run phase 1

        // Run phase 2

        // Run phase 3
        try {
            // Phase 3
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Phase 3 failed");
        }

        Building building = new Building("SAP", "Dietmar Hopp Allee", "16a", "Walldorf", 1);
        GlobalVerwaltung.getBuildingVerwaltung().addBuilding(building);

        Employee employee = new Employee("Thpom", "Schmidt", GroupEnum.CLEANING);
        GlobalVerwaltung.getEmployeeVerwaltung().addEmployee(employee);
        employee = new Employee("Alexander", "Siege", GroupEnum.IT);

        GlobalVerwaltung.getEmployeeVerwaltung().addEmployee(employee);

        Todo ticket = new Ticket("Neues Ticket", "null", building, PriorityEnum.HIGH, GroupEnum.IT,
                LocalDate.now());
        GlobalVerwaltung.getTodoVerwaltung().addTodo(ticket);
        ticket.setEmployee(employee);

        MaintenanceTask task = new MaintenanceTask("Neue Aufgabe", "null", building, PriorityEnum.HIGH,
                GroupEnum.CLEANING, 3, LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(1, 10 + 1)));
        GlobalVerwaltung.getTodoVerwaltung().addTodo(task);

    }
}
