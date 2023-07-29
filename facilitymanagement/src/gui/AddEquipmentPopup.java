package gui;

import enums.EquipTypeEnum;
import global.GlobalVerwaltung;
import maintainables.Equipment;
import maintainables.equipment.IdManager;
import maintainables.Room;
import enums.EquipConditionEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

/*
 * Das AddEquipmentPopup ist ein Popup, das ein neues Equipment erstellt und dem Raum hinzufügt.
 * Es hat zusätzliche Felder für die erweiterten Attribute von Equipment.
 * 
 * @author: Alexander Ansorge
 */
public class AddEquipmentPopup extends JDialog {
    // Deklaration von Feldern zur Erfassung von Equipment Informationen
    private JTextField nameField, manufacturerField, modelField;
    private JComboBox<EquipTypeEnum> typeComboBox;
    private JComboBox<EquipConditionEnum> conditionComboBox;
    private JSpinner dateOfPurchaseSpinner, lastMaintenanceDateSpinner;
    private JCheckBox functionalCheckBox;

    // Referenzen auf das Gebäudeverwaltungsfenster und den Raum, in dem das neue Equipment platziert wird
    private BuildingManagementPanel bp;
    private Room room;

    public AddEquipmentPopup(BuildingManagementPanel bp, Room room) {
        this.room = room;
        this.bp = bp;
        initComponents(); // Initialisiert die Komponenten der Benutzeroberfläche
    }

    private void initComponents() {
        // Setzt den Titel und das Schließen-Verhalten des Dialogs
        setTitle("Neues Equipment hinzufügen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Erstellung des "Equipment erstellen" Buttons und Hinzufügen des ActionListeners
        JButton createButton = new JButton("Equipment erstellen");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hier wird ein neues Equipment erstellt und zum Raum hinzugefügt

                // Erzeugung eines neuen Equipment-Objekts mit den eingegebenen Daten
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
                room.addEquipment(equipment); // Hinzufügen des neuen Equipments zum Raum

                // Speichern der Gebäudeinformationen
                GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();

                // Aktualisieren der Baumansicht
                bp.reloadTree();

                // Schließen des Dialogfensters
                dispose();
            }
        });

        // Erstellung und Konfiguration des Panels für die Eingabefelder
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Hinzufügen jedes Eingabefelds und entsprechende Konfiguration
        addNameField(contentPanel, gbc);
        addTypeField(contentPanel, gbc);
        addManufacturerField(contentPanel, gbc);
        addModelField(contentPanel, gbc);
        addDateOfPurchaseField(contentPanel, gbc);
        addLastMaintenanceDateField(contentPanel, gbc);
        addConditionField(contentPanel, gbc);
        addFunctionalField(contentPanel, gbc);

        // Hinzufügen des Buttons zum Panel am unteren Rand
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(createButton, gbc);

        // Hinzufügen des Panels zum Dialogfenster
        add(contentPanel, BorderLayout.CENTER);

        // Festlegen des Panels als Inhalt des Dialogs
        setContentPane(contentPanel);
        pack();
    }

    private void addNameField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);
    }

    private void addTypeField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel typeLabel = new JLabel("Typ:");
        panel.add(typeLabel, gbc);
        gbc.gridx = 1;
        typeComboBox = new JComboBox<>(EquipTypeEnum.values());
        panel.add(typeComboBox, gbc);
    }

    private void addManufacturerField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel manufacturerLabel = new JLabel("Hersteller:");
        panel.add(manufacturerLabel, gbc);
        gbc.gridx = 1;
        manufacturerField = new JTextField(20);
        panel.add(manufacturerField, gbc);
    }

    private void addModelField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel modelLabel = new JLabel("Modell:");
        panel.add(modelLabel, gbc);
        gbc.gridx = 1;
        modelField = new JTextField(20);
        panel.add(modelField, gbc);
    }

    private void addDateOfPurchaseField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel dateOfPurchaseLabel = new JLabel("Kaufdatum:");
        panel.add(dateOfPurchaseLabel, gbc);
        gbc.gridx = 1;
        dateOfPurchaseSpinner = new JSpinner(new SpinnerDateModel());
        panel.add(dateOfPurchaseSpinner, gbc);
    }

    private void addLastMaintenanceDateField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lastMaintenanceDateLabel = new JLabel("Letzte Wartung:");
        panel.add(lastMaintenanceDateLabel, gbc);
        gbc.gridx = 1;
        lastMaintenanceDateSpinner = new JSpinner(new SpinnerDateModel());
        panel.add(lastMaintenanceDateSpinner, gbc);
    }

    private void addConditionField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel conditionLabel = new JLabel("Zustand:");
        panel.add(conditionLabel, gbc);
        gbc.gridx = 1;
        conditionComboBox = new JComboBox<>(EquipConditionEnum.values()); // Initialisiere die Zustand-JComboBox
        panel.add(conditionComboBox, gbc);
    }

    private void addFunctionalField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel functionalLabel = new JLabel("Funktional:");
        panel.add(functionalLabel, gbc);
        gbc.gridx = 1;
        functionalCheckBox = new JCheckBox();
        panel.add(functionalCheckBox, gbc);
    }
}
