package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * Die FullScreenMain-Klasse ist die Startseite des Programms und enthält alle Tabs.
 * 
 * @author Florian Schmidt, Alexander Ansorge
 */
public class FullScreenMain extends JFrame {

    private JPanel buildingManagementPanel;
    private JPanel todoSystemPanel;
    private JPanel employeeManagementPanel;
    private JPanel taskOverviewPanel;
    private JPanel equipmentManagementPanel;

    public FullScreenMain() {
        super("Full Screen Main");
        setUndecorated(true);
        setVisible(true);

        initComponents();
    }

    public void initComponents() {
        JFrame frame = new JFrame("Facility Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JTabbedPane tabbedPane = new JTabbedPane();

        buildingManagementPanel = createBuildingManagementPanel();
        todoSystemPanel = createTodoSystemPanel();
        employeeManagementPanel = createEmployeeManagementPanel();
        taskOverviewPanel = createTaskOverviewPanel();
        equipmentManagementPanel = createEquipmentManagementPanel();

        tabbedPane.addTab("Gebäudeverwaltung", buildingManagementPanel);
        tabbedPane.addTab("Aufgabenverwaltung", todoSystemPanel);
        tabbedPane.addTab("Mitarbeiterverwaltung", employeeManagementPanel);
        tabbedPane.addTab("Aufgabenübersicht", taskOverviewPanel);
        tabbedPane.addTab("Equipmentverwaltung", equipmentManagementPanel);

        // Add a ChangeListener to the tabbed pane
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Alle Tabs werden neu geladen, wenn diese ausgewählt werden
                // Sichert die Aktualität der Daten, wenn ein Panel ein anderes Panel
                // beeinflusst
                reloadTabs();
            }
        });

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private void reloadTabs() {
        buildingManagementPanel = createBuildingManagementPanel();
        todoSystemPanel = createTodoSystemPanel();
        employeeManagementPanel = createEmployeeManagementPanel();
        taskOverviewPanel = createTaskOverviewPanel();
        equipmentManagementPanel = createEquipmentManagementPanel();
    }

    private JPanel createBuildingManagementPanel() {
        return new BuildingManagementPanel();
    }

    private JPanel createTodoSystemPanel() {
        return new TodoSystemPanel();
    }

    private JPanel createEmployeeManagementPanel() {
        return new EmployeeManagementPanel();
    }

    private JPanel createTaskOverviewPanel() {
        return new TodoOverviewPanel();
    }

    private JPanel createEquipmentManagementPanel() {
        return new EquipmentManagementPanel();
    }

}
