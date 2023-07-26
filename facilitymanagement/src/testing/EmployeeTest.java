package testing;

import java.time.LocalDate;
import java.util.UUID;

import employee.Employee;
import enums.GroupEnum;
import enums.PriorityEnum;
import global.GlobalVerwaltung;
import maintainables.Building;
import todos.Ticket;
import todos.Todo;

/**
 * Die Klasse EmployeeTest testet die einzelnen Methoden der Employee-Klasse.
 * es werden alle Methoden aufgerufen und die Ergebnisse in der Konsole ausgegeben
 * 
 * 
 * @author Marten Tietje
 * 
 */

public class EmployeeTest {
    public static void testEmployeeMethods() throws Exception{

        /* Erstellung eines Objekts der Klasse Employee */
        Employee employee = new Employee("Thpom", "Schmidt", GroupEnum.CLEANING);
        GlobalVerwaltung.getEmployeeVerwaltung().addEmployee(employee);
        employee = new Employee("Alexander", "Siege", GroupEnum.IT);

        /* Test getFirstName and getLastName */
        System.out.println("First Name: "+employee.getFirstName());
        System.out.println("Last Name: "+employee.getLastName());

         /* Test getId */
         System.out.println("Employee ID: "+employee.getId());

          /* Test setId */
        employee.setId(new UUID(18934, 7489137) );
         System.out.println("set new Employee ID: "+employee.getId());

        /* Test setFirstName */
        employee.setFirstName("Marten");
        System.out.println("set First Name: "+employee.getFirstName());

        /* Test  setLastName*/
        employee.setLastName("Tietje");
        System.out.println("set Last Name: "+employee.getLastName());

        /* Test getGroup */
        System.out.println("Group: "+employee.getGroup());

        /* Test getTodos */
        System.out.println("Todos: "+employee.getTodos());

        /* Test assignTodoToEmployee */
        Todo ticket = new Ticket("testTicket", "TestDescription", new Building("SAP", "Dietmar Hopp Allee", "16a", "Walldorf", 1), PriorityEnum.HIGH, GroupEnum.CLEANING, LocalDate.now());
        employee.assignTodoToEmployee(ticket);
        System.out.println("asssigned Todo: "+employee.getTodos());

       /* Test removeTodoFromEmployee */
       employee.removeTodoFromEmployee(ticket);
       System.out.println("Todos after removal: "+employee.getTodos());

        System.out.println("EmployeeTest completed");

        /* Test to String */
        System.out.println("Employee to String: "+employee.toString());
    }
}
