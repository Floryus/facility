package gui;

import javax.swing.*;

import enums.EquipTypeEnum;
import global.GlobalVerwaltung;
import maintainables.Equipment;
import maintainables.Room;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Das AddEquipmentPopup ist ein Popup, das ein neues Equipment erstellt und dem Raum hinzufügt.
 * 
 * @author: Florian Schmidt
 */
public class AddEquipmentPopup extends JDialog {
    private JTextField nameField;
    private JComboBox<EquipTypeEnum> typeComboBox;

    private BuildingManagementPanel bp;
    private Room room;

    public AddEquipmentPopup(BuildingManagementPanel bp, Room room) {
        this.room = room;
        this.bp = bp;
        initComponents();
    }

    private void initComponents() {

        setTitle("Neues Equipment hinzufügen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Erzeuge den "Gebäude erstellen" Button
        JButton createButton = new JButton("Etage erstellen");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // createLevel

                // Erzeuge ein neues Equipment und füge sie hinzu
                Equipment equipment = new Equipment(nameField.getText(), (EquipTypeEnum) typeComboBox.getSelectedItem(),
                        room);
                room.addEquipment(equipment);

                // Speichere die Änderungen
                GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();

                // Aktualisiere die Baumansicht
                bp.reloadTree();

                // Schließe das Popup-Fenster
                dispose();
            }
        });

        // Erzeuge das Panel für die Eingabefelder
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Name:");
        contentPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        contentPanel.add(nameField, gbc);

        // Typ
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel typeLabel = new JLabel("Typ:");
        contentPanel.add(typeLabel, gbc);
        gbc.gridx = 1;
        typeComboBox = new JComboBox<>(EquipTypeEnum.values());
        contentPanel.add(typeComboBox, gbc);

        // Button-Panel am unteren Rand
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(createButton, gbc);

        add(contentPanel, BorderLayout.CENTER);

        // Setze das Panel als Inhalt des Popups
        setContentPane(contentPanel);
        pack();

    }

}
