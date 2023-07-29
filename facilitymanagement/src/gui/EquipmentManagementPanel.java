package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import maintainables.Building;
import maintainables.Equipment;
import maintainables.Level;
import maintainables.Room;
import global.GlobalVerwaltung;

public class EquipmentManagementPanel extends JPanel {

    private JTable equipmentTable;
    private DefaultTableModel tableModel;
    ArrayList<Equipment> equipment = new ArrayList<>();

    public EquipmentManagementPanel() {

        String[] columnNames = { "ID", "Name", "Type", "Raum", "Hersteller", "Model",
                "Kaufdatum", "Letzte Wartung", "Zustand", "Funktionalität" };
        tableModel = new DefaultTableModel(columnNames, 0);
        equipmentTable = new JTable(tableModel);

        updateEquipment();

        if (equipment != null) {
            for (Equipment equip : equipment) {
                addEquipmentToTable(equip);
            }
        }

        JScrollPane scrollPane = new JScrollPane(equipmentTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Buttons or other components could be added here, similar to your Todos panel.
    }

    private void addEquipmentToTable(Equipment equip) {

        String[] rowData = { equip.getId(), equip.getName(), equip.getType().toString(), equip.getRoom().toString(),
                equip.getManufacturer(), equip.getModel(), equip.getDateOfPurchase().toString(),
                equip.getLastMaintenanceDate().toString(), equip.getCondition().toString(),
                Boolean.toString(equip.isFunctional()) };
        tableModel.addRow(rowData);
    }

    public void updateEquipmentTable() {
        tableModel.setRowCount(0);

        if (equipment != null) {
            updateEquipment();

        }

        for (Equipment equip : equipment) {
            addEquipmentToTable(equip);
        }
    }

    public void updateEquipment() {
        // Query for all equipments
        ArrayList<Building> buildings = GlobalVerwaltung.getBuildingVerwaltung().getBuildings();
        for (Building building : buildings) {
            for (Level level : building.getLevels()) {
                for (Room room : level.getRooms()) {
                    for (Equipment equip : room.getEquipment()) {
                        equipment.add(equip);
                    }
                }
            }
        }
    }

}
