package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import maintainables.Equipment;
import global.GlobalVerwaltung;

public class EquipmentManagementPanel extends JPanel {

    private JTable equipmentTable;
    private DefaultTableModel tableModel;
    List<Equipment> equipment;

    public EquipmentManagementPanel() {
        EquipmentManagementPanel parent = this;

        String[] columnNames = {"ID", "Name", "Type", "Room", "Manufacturer", "Model",
                "Date of Purchase", "Last Maintenance Date", "Condition", "Functional"};
        tableModel = new DefaultTableModel(columnNames, 0);
        equipmentTable = new JTable(tableModel);

        equipment = GlobalVerwaltung.getEquipmentVerwaltung().getEquipment();

        for (Equipment equip : equipment) {
            addEquipmentToTable(equip);
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
                Boolean.toString(equip.isFunctional())};
        tableModel.addRow(rowData);
    }

    public void updateEquipmentTable() {
        tableModel.setRowCount(0);

        equipment = GlobalVerwaltung.getEquipmentVerwaltung().getEquipment();

        for (Equipment equip : equipment) {
            addEquipmentToTable(equip);
        }
    }
}
