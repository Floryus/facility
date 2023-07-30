package gui;

import enums.EquipTypeEnum;
import enums.EquipConditionEnum;
import global.GlobalVerwaltung;
import maintainables.Room;
import maintainables.equipment.SanitaryEquipment;
import maintainables.equipment.IdManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;

/*
 * AddSanitaryEquipmentPopup ist ein Popup-Fenster zur Erfassung von Sanitärausstattungsdaten.
 * Es erweitert AddGeneralEquipmentPopup und fügt zusätzliche Felder speziell für Sanitärausrüstung hinzu.
 * 
 * @author Alexander Ansorge
 */
public class AddSanitaryEquipmentPopup extends AddGeneralEquipmentPopup {
    private JCheckBox waterConnectionCheckBox; // CheckBox für Wasseranschluss
    private JCheckBox drainageConnectionCheckBox; // CheckBox für Abwasseranschluss
    private JTextField cleaningRequirementField; // Textfeld für Reinigungsbedarf
    private JTextField operatingCompanyField; // Textfeld für Betriebsfirma

    // Konstruktor, der das BuildingManagementPanel und das Raumobjekt initialisiert
    public AddSanitaryEquipmentPopup(BuildingManagementPanel bp, Room room) {
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
        addWaterConnectionField(contentPanel, gbc);
        gbc.gridy++;
        addDrainageConnectionField(contentPanel, gbc);
        gbc.gridy++;
        addCleaningRequirementField(contentPanel, gbc);
        gbc.gridy++;
        addOperatingCompanyField(contentPanel, gbc);
        
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
    private void addWaterConnectionField(JPanel panel, GridBagConstraints gbc) {
        JLabel waterConnectionLabel = new JLabel("Wasseranschluss:");
        panel.add(waterConnectionLabel, gbc);

        gbc.gridx = 1;
        waterConnectionCheckBox = new JCheckBox();
        panel.add(waterConnectionCheckBox, gbc);
        gbc.gridx = 0;
    }

    private void addDrainageConnectionField(JPanel panel, GridBagConstraints gbc) {
        JLabel drainageConnectionLabel = new JLabel("Abwasseranschluss:");
        panel.add(drainageConnectionLabel, gbc);

        gbc.gridx = 1;
        drainageConnectionCheckBox = new JCheckBox();
        panel.add(drainageConnectionCheckBox, gbc);
        gbc.gridx = 0;
    }

    private void addCleaningRequirementField(JPanel panel, GridBagConstraints gbc) {
        JLabel cleaningRequirementLabel = new JLabel("Reinigungsbedarf:");
        panel.add(cleaningRequirementLabel, gbc);

        gbc.gridx = 1;
        cleaningRequirementField = new JTextField(20);
        panel.add(cleaningRequirementField, gbc);
        gbc.gridx = 0;
    }

    private void addOperatingCompanyField(JPanel panel, GridBagConstraints gbc) {
        JLabel operatingCompanyLabel = new JLabel("Betriebsfirma:");
        panel.add(operatingCompanyLabel, gbc);

        gbc.gridx = 1;
        operatingCompanyField = new JTextField(20);
        panel.add(operatingCompanyField, gbc);
        gbc.gridx = 0;
    }

    // Überschriebene Methode, die ausgeführt wird, wenn der "Equipment erstellen" Button gedrückt wird
    @Override
    protected void createButtonActionPerformed(ActionEvent e) {
        // Erzeugen eines neuen SanitaryEquipment-Objekts mit den Werten aus den Eingabefeldern
        SanitaryEquipment equipment = new SanitaryEquipment(
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
                waterConnectionCheckBox.isSelected(),
                drainageConnectionCheckBox.isSelected(),
                Integer.parseInt(cleaningRequirementField.getText()),
                operatingCompanyField.getText());
        room.addEquipment(equipment);

        // Speichern der Gebäudedaten und Aktualisierung des Baumanagementspanels
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();
        bp.reloadTree();

        // Schließen des Popups
        dispose();
    }
}
