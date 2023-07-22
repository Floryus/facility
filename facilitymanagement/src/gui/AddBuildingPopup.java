package gui;

import javax.swing.*;

import global.GlobalVerwaltung;
import maintainables.Building;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Das AddBuildingPopup ist ein Popup-Fenster, das das Hinzufügen eines neuen Gebäudes ermöglicht.
 * 
 * @author Florian Schmidt
 */
public class AddBuildingPopup extends JDialog {

    private JTextField nameField;
    private JTextField streetField;
    private JTextField houseNumberField;
    private JTextField cityField;
    private JTextField maxLevelsField;

    private BuildingManagementPanel bp;

    public AddBuildingPopup(BuildingManagementPanel bp) {
        this.bp = bp;
        initComponents();
    }

    private void initComponents() {

        setTitle("Neues Gebäude hinzufügen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Erzeuge den "Gebäude erstellen" Button
        JButton createButton = new JButton("Gebäude erstellen");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Erzeuge ein neues Gebäude mit den Werten aus den Textfeldern
                Building building = new Building(nameField.getText(), streetField.getText(), houseNumberField.getText(),
                        cityField.getText(), Integer.parseInt(maxLevelsField.getText()));
                GlobalVerwaltung.getBuildingVerwaltung().addBuilding(building);

                // Speichere die Änderungen
                GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();

                // Aktualisiere die Baumansicht im BuildingManagementPanel
                bp.reloadTree();

                // Schließe das Popup-Fenster
                dispose();
            }
        });

        // Erzeuge das Panel für die Textfelder
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(nameField = new JTextField(20), gbc);

        // Straße
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(new JLabel("Straße:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(streetField = new JTextField(20), gbc);

        // Hausnummer
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(new JLabel("Hausnummer:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(houseNumberField = new JTextField(20), gbc);

        // Stadt
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(new JLabel("Stadt:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(cityField = new JTextField(20), gbc);

        // Anzahl der Stockwerke
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPanel.add(new JLabel("Anzahl der Stockwerke:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(maxLevelsField = new JTextField(20), gbc);

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
