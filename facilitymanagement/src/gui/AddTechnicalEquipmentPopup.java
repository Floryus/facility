package gui;

import enums.EquipTypeEnum;
import enums.EquipConditionEnum;
import global.GlobalVerwaltung;
import maintainables.Room;
import maintainables.equipment.TechnicalEquipment;
import maintainables.equipment.IdManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

/*
 * AddTechnicalEquipmentPopup ist ein Popup-Fenster zur Erfassung von technischen Gerätedaten.
 * Es erweitert AddGeneralEquipmentPopup und fügt zusätzliche Felder speziell für technische Geräte hinzu.
 * 
 * @author Alexander Ansorge
 */
public class AddTechnicalEquipmentPopup extends AddGeneralEquipmentPopup {
    private JTextField powerRatingField; // Textfeld für Leistung (in Watt)
    private JTextField connectivityField; // Textfeld für Verbindungsoptionen
    private JTextField compatibleDevicesField; // Textfeld für kompatible Geräte
    private JTextField softwareVersionField; // Textfeld für Software-Version

    // Konstruktor, der das BuildingManagementPanel und das Raumobjekt initialisiert
    public AddTechnicalEquipmentPopup(BuildingManagementPanel bp, Room room) {
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
        addPowerRatingField(contentPanel, gbc);
        gbc.gridy++;
        addConnectivityField(contentPanel, gbc);
        gbc.gridy++;
        addCompatibleDevicesField(contentPanel, gbc);
        gbc.gridy++;
        addSoftwareVersionField(contentPanel, gbc);
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

    // Methoden zum Hinzufügen der zusätzlichen Felder zum Panel
    private void addPowerRatingField(JPanel panel, GridBagConstraints gbc) {
        JLabel powerRatingLabel = new JLabel("Leistung (in Watt):");
        panel.add(powerRatingLabel, gbc);

        gbc.gridx = 1;
        powerRatingField = new JTextField(20);
        panel.add(powerRatingField, gbc);
        gbc.gridx = 0;
    }

    private void addConnectivityField(JPanel panel, GridBagConstraints gbc) {
        JLabel connectivityLabel = new JLabel("Verbindungsoptionen:");
        panel.add(connectivityLabel, gbc);

        gbc.gridx = 1;
        connectivityField = new JTextField(20);
        panel.add(connectivityField, gbc);
        gbc.gridx = 0;
    }

    private void addCompatibleDevicesField(JPanel panel, GridBagConstraints gbc) {
        JLabel compatibleDevicesLabel = new JLabel("Kompatible Geräte (Komma getrennt):");
        panel.add(compatibleDevicesLabel, gbc);

        gbc.gridx = 1;
        compatibleDevicesField = new JTextField(20);
        panel.add(compatibleDevicesField, gbc);
        gbc.gridx = 0;
    }

    private void addSoftwareVersionField(JPanel panel, GridBagConstraints gbc) {
        JLabel softwareVersionLabel = new JLabel("Software-Version:");
        panel.add(softwareVersionLabel, gbc);

        gbc.gridx = 1;
        softwareVersionField = new JTextField(20);
        panel.add(softwareVersionField, gbc);
        gbc.gridx = 0;
    }

    // Überschriebene Methode, die ausgeführt wird, wenn der "Equipment erstellen" Button gedrückt wird
    @Override
    protected void createButtonActionPerformed(ActionEvent e) {
        // Umwandlung der kompatiblen Geräte von einer kommagetrennten Zeichenkette in eine Liste
        List<String> compatibleDevices = Arrays.asList(compatibleDevicesField.getText().split(","));

        // Erzeugen eines neuen TechnicalEquipment-Objekts mit den Werten aus den Eingabefeldern
        TechnicalEquipment equipment = new TechnicalEquipment(
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
                Integer.parseInt(powerRatingField.getText()),
                connectivityField.getText(),
                compatibleDevices,
                softwareVersionField.getText());
        room.addEquipment(equipment);

        // Speichern der Gebäudedaten und Aktualisierung des Baumanagementspanels
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();
        bp.reloadTree();

        // Schließen des Popups
        dispose();
    }
}
