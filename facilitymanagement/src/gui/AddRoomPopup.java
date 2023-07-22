package gui;

import javax.swing.*;

import enums.RoomTypeEnum;
import global.GlobalVerwaltung;
import maintainables.Level;
import maintainables.Room;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Das AddRoomPopup ist ein Popup, das ein Formular zum Erstellen eines neuen Raums enthält.
 * 
 * @author Florian Schmidt
 */
public class AddRoomPopup extends JDialog {

    private JTextField nameField;
    private JComboBox<RoomTypeEnum> roomTypeComboBox;

    private Level level;
    private BuildingManagementPanel bp;

    public AddRoomPopup(BuildingManagementPanel bp, Level level) {
        this.bp = bp;
        this.level = level;
        initComponents();
    }

    private void initComponents() {
        setTitle("Neuen Raum hinzufügen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Erzeuge den "Gebäude erstellen" Button
        JButton createButton = new JButton("Raum erstellen");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Erzeuge einen neuen Raum
                Room room = new Room(nameField.getText(), level, (RoomTypeEnum) roomTypeComboBox.getSelectedItem());
                level.addRoom(room);

                // Speichere die Änderungen
                GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();

                // Aktualisiere die Baumansicht
                bp.reloadTree();

                // Schließe das Popup-Fenster
                dispose();
            }
        });

        // Erzeuge das Panel für die Eingabefelder
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(nameField = new JTextField(20), gbc);

        // Raumtyp
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(new JLabel("Raumtyp:"), gbc);
        gbc.gridx = 1;
        roomTypeComboBox = new JComboBox<>(RoomTypeEnum.values());
        contentPanel.add(roomTypeComboBox, gbc);

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
