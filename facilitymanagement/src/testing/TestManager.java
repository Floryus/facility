package testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestManager {

    public void run() throws Exception {
        File buildings = new File("buildings.txt");
        File employees = new File("employees.txt");
        File todos = new File("todos.txt");

        File buildingsCopy = new File("buildingsCopy.txt");
        File employeesCopy = new File("employeesCopy.txt");
        File todosCopy = new File("todosCopy.txt");

        try {
            copyFileUsingStream(buildings, buildingsCopy);
            copyFileUsingStream(employees, employeesCopy);
            copyFileUsingStream(todos, todosCopy);
        } catch (IOException e) {
            e.printStackTrace();
        }

        EmployeeTest.testEmployeeMethods();
        BuildingTest.testBuildingMethods();
        EquipmentTest.testEquipmentMethods();
        LevelTest.testLevelMethods();
        BuildingTest.testBuildingMethods();
        System.out.println("all tests were successful");

        copyFileUsingStream(buildingsCopy, buildings);
        copyFileUsingStream(employeesCopy, employees);
        copyFileUsingStream(todosCopy, todos);

    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        System.out.println(source);
        try {
            // TODO: checke if file exists -> if not dont copy
            is = new FileInputStream(source);
            System.out.println(is);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (Exception e) {
            return;
        } finally {
            is.close();
            os.close();
        }
    }
}
