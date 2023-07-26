package testing;

public class TestManager {

    public void run() throws Exception {
        EmployeeTest.testEmployeeMethods();
        BuildingTest.testBuildingMethods();
        EquipmentTest.testEquipmentMethods();
        LevelTest.testLevelMethods();
        BuildingTest.testBuildingMethods();
        System.out.println("all tests were successful");
    }
}
