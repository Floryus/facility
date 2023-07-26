package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import global.GlobalVerwaltung;
import maintainables.Building;
import maintainables.Level;

/*
 * Das AddLevelPopup ist ein Popup, das ein Formular zum Erstellen einer neuen Etage enthält.
 * 
 * @author Florian Schmidt
 */
public class AddLevelPopup extends JDialog {
    private JTextField maxRoomsField;
    private JTextField buildingField;
    private JTextField numberField;

    private Building building;
    private BuildingManagementPanel bp;

    public AddLevelPopup(BuildingManagementPanel bp, Building building) {
        this.bp = bp;
        this.building = building;
        initComponents();
    }

    private void initComponents() {
        setTitle("Neue Etage hinzufügen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Erzeuge Eingabefelder
        maxRoomsField = new JTextField(5);

        // Erzeuge den "Etage erstellen" Button
        JButton createButton = new JButton("Etage erstellen");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // createLevel
                int maxRooms = Integer.parseInt(maxRoomsField.getText());

                // Erzeuge ein neue Etage und füge sie hinzu
                Level level = new Level(building.getLevels().size(), maxRooms, building);
                building.addLevel(level);

                // Speichere die Änderungen
                GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();

                // Aktualisiere die Baumansicht
                bp.reloadTree();

                // Schließe das Popup-Fenster
                dispose();
            }
        });

        // Erzeuge Panel für die Komponenten
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Gebäude:"));
        buildingField = new JTextField(building.getName());
        buildingField.setEditable(false);
        panel.add(buildingField);

        panel.add(new JLabel("Etage:"));
        numberField = new JTextField(Integer.toString(building.getLevels().size()));
        numberField.setEditable(false);
        panel.add(numberField);

        panel.add(new JLabel("Maximale Anzahl an Räumen:"));
        panel.add(maxRoomsField);

        panel.add(createButton);

        // Setze das Panel als Inhalt des Popups
        setContentPane(panel);
        pack();
    }

}
