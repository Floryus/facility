package gui;

import enums.EquipTypeEnum;
import enums.EquipConditionEnum;
import enums.OwnerEnum;
import global.GlobalVerwaltung;
import maintainables.Room;
import maintainables.equipment.OfficeEquipment;
import maintainables.equipment.IdManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;

/*
 * AddOfficeEquipmentPopup ist ein Popup-Fenster, das zur Erfassung von Büroausstattungsdaten verwendet wird.
 * Es erbt von AddGeneralEquipmentPopup und fügt zusätzliche Felder für Büroausstattung hinzu.
 */
public class AddOfficeEquipmentPopup extends AddGeneralEquipmentPopup {
    private JTextField employeeField; // Textfeld für die Mitarbeiter ID
    private JComboBox<OwnerEnum> ownerComboBox; // ComboBox für das Eigentum

    // Konstruktor, der das BuildingManagementPanel und das Raumobjekt initialisiert
    public AddOfficeEquipmentPopup(BuildingManagementPanel bp, Room room) {
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

        // Hinzufügen des Mitarbeiterfeldes und des Eigentümerfeldes zum Panel
        addEmployeeField(contentPanel, gbc);
        gbc.gridy++;
        addOwnerField(contentPanel, gbc);
        gbc.gridy++;

        // Aktualisierung der Größe des Dialogs
        pack();
    }

    // Methode zum Abrufen der letzten Zeilennummer in einem GridBagLayout
    private int getLastGridY() {
        LayoutManager layout = getContentPane().getLayout();
        if (layout instanceof GridBagLayout) {
            GridBagLayout gbl = (GridBagLayout) layout;
            int[][] dim = gbl.getLayoutDimensions();
            int rows = dim[1].length;
            return rows;
        }
        return 0;
    }

    // Methode zum Hinzufügen des Mitarbeiterfeldes zum Panel
    private void addEmployeeField(JPanel panel, GridBagConstraints gbc) {
        JLabel employeeLabel = new JLabel("Mitarbeiter ID:");
        panel.add(employeeLabel, gbc);

        gbc.gridx = 1;
        employeeField = new JTextField(20);
        panel.add(employeeField, gbc);
        gbc.gridx = 0;
    }

    // Methode zum Hinzufügen des Eigentümerfeldes zum Panel
    private void addOwnerField(JPanel panel, GridBagConstraints gbc) {
        JLabel ownerLabel = new JLabel("Eigentum:");
        panel.add(ownerLabel, gbc);

        gbc.gridx = 1;
        ownerComboBox = new JComboBox<>(OwnerEnum.values());
        panel.add(ownerComboBox, gbc);
        gbc.gridx = 0;
    }

    // Überschriebene Methode, die ausgeführt wird, wenn der "Equipment erstellen" Button gedrückt wird
    @Override
    protected void createButtonActionPerformed(ActionEvent e) {
        // Erzeugen eines neuen OfficeEquipment-Objekts mit den Werten aus den Eingabefeldern
        OfficeEquipment equipment = new OfficeEquipment(
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
                employeeField.getText(),
                (OwnerEnum) ownerComboBox.getSelectedItem());
        room.addEquipment(equipment);

        // Speichern der Änderungen und Aktualisieren des Baums
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();
        bp.reloadTree();
        dispose();
    }
}
