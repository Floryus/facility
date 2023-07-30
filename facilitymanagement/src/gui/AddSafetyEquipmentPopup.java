package gui;

import enums.EquipTypeEnum;
import enums.EquipConditionEnum;
import global.GlobalVerwaltung;
import maintainables.Room;
import maintainables.equipment.SafetyEquipment;
import maintainables.equipment.IdManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;

/*
 * AddSafetyEquipmentPopup ist ein Popup-Fenster, das zur Erfassung von Sicherheitsausstattungsdaten verwendet wird.
 * Es erbt von AddGeneralEquipmentPopup und fügt zusätzliche Felder für Sicherheitsausstattung hinzu.
 * 
 * @author Alexander Ansorge
 */
public class AddSafetyEquipmentPopup extends AddGeneralEquipmentPopup {
    private JTextField batteryLifeField; // Textfeld für die Batterielebensdauer
    private JTextField connectivityField; // Textfeld für die Konnektivität
    private JTextField rangeField; // Textfeld für die Reichweite
    private JTextField logPathField; // Textfeld für den Log-Speicherort

    // Konstruktor, der das BuildingManagementPanel und das Raumobjekt initialisiert
    public AddSafetyEquipmentPopup(BuildingManagementPanel bp, Room room) {
        super(bp, room);
    }

    // Überschriebene Methode zum Initialisieren der Komponenten des Popups
    @Override
    protected void initComponents() {
        super.initComponents(); // Aufruf der Superklassenmethode, um die allgemeinen Komponenten zu initialisieren

        // Erstellen eines GridBagLayout Constraints-Objekts
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = getLastGridY();

        // Referenz auf das ContentPanel des Popups
        JPanel contentPanel = (JPanel) getContentPane();

        // Hinzufügen der zusätzlichen Felder zum Panel
        addBatteryLifeField(contentPanel, gbc);
        gbc.gridy++;
        addConnectivityField(contentPanel, gbc);
        gbc.gridy++;
        addRangeField(contentPanel, gbc);
        gbc.gridy++;
        addLogPathField(contentPanel, gbc);
        
        // Aktualisierung der Größe des Dialogs
        pack();
    }

    // Methode zum Abrufen der letzten Zeilennummer in einem GridBagLayout
    private int getLastGridY() {
        LayoutManager layout = getContentPane().getLayout();
        if (layout instanceof GridBagLayout) {
            GridBagLayout gbl = (GridBagLayout)layout;
            int[][] dim = gbl.getLayoutDimensions();
            int rows = dim[1].length;
            return rows;
        }
        return 0; 
    }

    // Methoden zum Hinzufügen der zusätzlichen Felder zum Panel
    private void addBatteryLifeField(JPanel panel, GridBagConstraints gbc) {
        JLabel batteryLifeLabel = new JLabel("Batterielebensdauer:");
        panel.add(batteryLifeLabel, gbc);

        gbc.gridx = 1;
        batteryLifeField = new JTextField(20);
        panel.add(batteryLifeField, gbc);
        gbc.gridx = 0;
    }

    private void addConnectivityField(JPanel panel, GridBagConstraints gbc) {
        JLabel connectivityLabel = new JLabel("Konnektivität:");
        panel.add(connectivityLabel, gbc);

        gbc.gridx = 1;
        connectivityField = new JTextField(20);
        panel.add(connectivityField, gbc);
        gbc.gridx = 0;
    }

    private void addRangeField(JPanel panel, GridBagConstraints gbc) {
        JLabel rangeLabel = new JLabel("Reichweite:");
        panel.add(rangeLabel, gbc);

        gbc.gridx = 1;
        rangeField = new JTextField(20);
        panel.add(rangeField, gbc);
        gbc.gridx = 0;
    }

    private void addLogPathField(JPanel panel, GridBagConstraints gbc) {
        JLabel logPathLabel = new JLabel("Log-Speicherort:");
        panel.add(logPathLabel, gbc);

        gbc.gridx = 1;
        logPathField = new JTextField(20);
        panel.add(logPathField, gbc);
        gbc.gridx = 0;
    }

    // Überschriebene Methode, die ausgeführt wird, wenn der "Equipment erstellen" Button gedrückt wird
    @Override
    protected void createButtonActionPerformed(ActionEvent e) {
        // Erzeugen eines neuen SafetyEquipment-Objekts mit den Werten aus den Eingabefeldern
        SafetyEquipment equipment = new SafetyEquipment(
                IdManager.generateNewId(),
                nameField.getText(),
                (EquipTypeEnum) typeComboBox.getSelectedItem(),
                room,
                manufacturerField.getText(),
                modelField.getText(),
                LocalDate.ofInstant(((java.util.Date) dateOfPurchaseSpinner.getValue()).toInstant(), ZoneId.systemDefault()),
                LocalDate.ofInstant(((java.util.Date) lastMaintenanceDateSpinner.getValue()).toInstant(), ZoneId.systemDefault()),
                (EquipConditionEnum) conditionComboBox.getSelectedItem(),
                functionalCheckBox.isSelected(),
                Integer.parseInt(batteryLifeField.getText()),
                connectivityField.getText(),
                Integer.parseInt(rangeField.getText()),
                logPathField.getText());
        room.addEquipment(equipment);

        // Speichern der Änderungen und Aktualisieren des Baums
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();
        bp.reloadTree();
        dispose();
    }
}
