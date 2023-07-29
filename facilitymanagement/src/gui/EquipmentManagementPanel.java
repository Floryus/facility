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

/*
 * Das EquipmentManagementPanel ist das Panel, das die Geräteverwaltung enthält.
 * 
 * @author: Alexander Ansorge
 */
public class EquipmentManagementPanel extends JPanel {

    private JTable equipmentTable;
    private DefaultTableModel tableModel;
    ArrayList<Equipment> equipment = new ArrayList<>();

    public EquipmentManagementPanel() {

        // Initialisiere die Tabellenmodelle und -spalten
        String[] columnNames = { "ID", "Name", "Type", "Raum", "Hersteller", "Model",
                "Kaufdatum", "Letzte Wartung", "Zustand", "Funktionalität" };
        tableModel = new DefaultTableModel(columnNames, 0);
        equipmentTable = new JTable(tableModel);

        // Aktualisiere die Geräteinformationen und fülle die Tabelle mit den Daten
        updateEquipment();
        if (equipment != null) {
            for (Equipment equip : equipment) {
                addEquipmentToTable(equip);
            }
        }

        // Erstelle ScrollPane für die Tabelle und setze die Dimensionen
        JScrollPane scrollPane = new JScrollPane(equipmentTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        // Layout und Komponenten zum Panel hinzufügen
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    // Methode zum Hinzufügen eines Geräts zur Tabelle
    private void addEquipmentToTable(Equipment equip) {
        String[] rowData = { equip.getId(), equip.getName(), equip.getType().toString(), equip.getRoom().toString(),
                equip.getManufacturer(), equip.getModel(), equip.getDateOfPurchase().toString(),
                equip.getLastMaintenanceDate().toString(), equip.getCondition().toString(),
                Boolean.toString(equip.isFunctional()) };
        tableModel.addRow(rowData);
    }

    // Methode, um die Gerätetabelle zu aktualisieren
    public void updateEquipmentTable() {
        tableModel.setRowCount(0);

        if (equipment != null) {
            updateEquipment();
        }

        for (Equipment equip : equipment) {
            addEquipmentToTable(equip);
        }
    }

    // Methode, um die Geräteinformationen zu aktualisieren
    public void updateEquipment() {
        // Abfrage für alle Geräte
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
