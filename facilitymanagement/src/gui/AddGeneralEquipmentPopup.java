package gui;

import enums.EquipTypeEnum;
import enums.EquipConditionEnum;
import global.GlobalVerwaltung;
import maintainables.Equipment;
import maintainables.equipment.IdManager;
import maintainables.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;

/*
 * AddGeneralEquipmentPopup ist ein Popup-Fenster, das zur Erfassung allgemeiner Ausrüstungsdaten verwendet wird.
 *
 * @author Alexander Ansorge
 */
public class AddGeneralEquipmentPopup extends JDialog {
    // Erstellung von Textfeldern und ComboBoxes für die Eingabe von Ausrüstungsdaten
    protected JTextField nameField, manufacturerField, modelField;
    protected JComboBox<EquipTypeEnum> typeComboBox;
    protected JComboBox<EquipConditionEnum> conditionComboBox;
    protected JSpinner dateOfPurchaseSpinner, lastMaintenanceDateSpinner;
    protected JCheckBox functionalCheckBox;

    protected BuildingManagementPanel bp;
    protected Room room;

    // Konstruktor, der das BuildingManagementPanel und das Raumobjekt initialisiert
    public AddGeneralEquipmentPopup(BuildingManagementPanel bp, Room room) {
        this.room = room;
        this.bp = bp;
        initComponents();
    }

    // Methode zum Initialisieren der Komponenten des Popups
    protected void initComponents() {
        setTitle("Neues Equipment hinzufügen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Erstellung und Konfiguration des "Equipment erstellen" Buttons
        JButton createButton = new JButton("Equipment erstellen");
        createButton.addActionListener(e -> createButtonActionPerformed(e));

        // Erstellung und Konfiguration des Panels für die Eingabefelder
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Hinzufügen von Eingabefeldern zum Panel
        addNameField(contentPanel, gbc);
        addTypeField(contentPanel, gbc);
        addManufacturerField(contentPanel, gbc);
        addModelField(contentPanel, gbc);
        addDateOfPurchaseField(contentPanel, gbc);
        addLastMaintenanceDateField(contentPanel, gbc);
        addConditionField(contentPanel, gbc);
        addFunctionalField(contentPanel, gbc);

        // Hinzufügen des "Equipment erstellen" Buttons zum Panel
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(createButton, gbc);

        // Hinzufügen des Panels zum Popup
        add(contentPanel, BorderLayout.CENTER);

        setContentPane(contentPanel);
        pack();
    }

    // Aktion, die ausgeführt wird, wenn der "Equipment erstellen" Button gedrückt wird
    protected void createButtonActionPerformed(ActionEvent e) {
        // Erzeugen eines neuen Equipment-Objekts mit den Werten aus den Eingabefeldern
        Equipment equipment = new Equipment(
                IdManager.generateNewId(),
                nameField.getText(),
                (EquipTypeEnum) typeComboBox.getSelectedItem(),
                room,
                manufacturerField.getText(),
                modelField.getText(),
                LocalDate.ofInstant(((java.util.Date) dateOfPurchaseSpinner.getValue()).toInstant(),
                        ZoneId.systemDefault()),
                LocalDate.ofInstant(((java.util.Date) lastMaintenanceDateSpinner.getValue()).toInstant(),
                        ZoneId.systemDefault()),
                (EquipConditionEnum) conditionComboBox.getSelectedItem(),
                functionalCheckBox.isSelected());
        room.addEquipment(equipment);

        // Speichern der Änderungen und Aktualisieren des Baums
        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();

        bp.reloadTree();
        dispose();
    }

    // Methode zum Hinzufügen des Namensfeldes zum Panel
    private void addNameField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);
    }

    // Methode zum Hinzufügen des Typfeldes zum Panel
    private void addTypeField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel typeLabel = new JLabel("Typ:");
        panel.add(typeLabel, gbc);
        gbc.gridx = 1;
        typeComboBox = new JComboBox<>(EquipTypeEnum.values());
        panel.add(typeComboBox, gbc);
    }

    // Methode zum Hinzufügen des Herstellerfeldes zum Panel
    private void addManufacturerField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel manufacturerLabel = new JLabel("Hersteller:");
        panel.add(manufacturerLabel, gbc);
        gbc.gridx = 1;
        manufacturerField = new JTextField(20);
        panel.add(manufacturerField, gbc);
    }

    // Methode zum Hinzufügen des Modellfeldes zum Panel
    private void addModelField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel modelLabel = new JLabel("Modell:");
        panel.add(modelLabel, gbc);
        gbc.gridx = 1;
        modelField = new JTextField(20);
        panel.add(modelField, gbc);
    }

    // Methode zum Hinzufügen des Kaufdatumsfeldes zum Panel
    private void addDateOfPurchaseField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel purchaseDateLabel = new JLabel("Kaufdatum:");
        panel.add(purchaseDateLabel, gbc);
        gbc.gridx = 1;
        dateOfPurchaseSpinner = new JSpinner(new SpinnerDateModel());
        panel.add(dateOfPurchaseSpinner, gbc);
    }

    // Methode zum Hinzufügen des Feldes für das letzte Wartungsdatum zum Panel
    private void addLastMaintenanceDateField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lastMaintenanceLabel = new JLabel("Letzter Wartungstermin:");
        panel.add(lastMaintenanceLabel, gbc);
        gbc.gridx = 1;
        lastMaintenanceDateSpinner = new JSpinner(new SpinnerDateModel());
        panel.add(lastMaintenanceDateSpinner, gbc);
    }

    // Methode zum Hinzufügen des Zustandsfeldes zum Panel
    private void addConditionField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel conditionLabel = new JLabel("Zustand:");
        panel.add(conditionLabel, gbc);
        gbc.gridx = 1;
        conditionComboBox = new JComboBox<>(EquipConditionEnum.values());
        panel.add(conditionComboBox, gbc);
    }

    // Methode zum Hinzufügen des Funktionsfeldes zum Panel
    private void addFunctionalField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel functionalLabel = new JLabel("Funktionsfähig:");
        panel.add(functionalLabel, gbc);
        gbc.gridx = 1;
        functionalCheckBox = new JCheckBox();
        panel.add(functionalCheckBox, gbc);
    }
}
