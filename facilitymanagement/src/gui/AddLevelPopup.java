package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import global.GlobalVerwaltung;
import maintainables.Building;
import maintainables.Level;

/*
 * Das AddLevelPopup ist ein Popup, das ein Formular zum Erstellen einer neuen Etage enthält.
 * 
 * @author Florian Schmidt, Alexander Ansorge
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
        numberField = new JTextField(Integer.toString(building.getLevels().size()));

        // Erzeuge den "Etage erstellen" Button
        JButton createButton = new JButton("Etage erstellen");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Füge Inputs ein
                int maxRooms = Integer.parseInt(maxRoomsField.getText());
                int levelNumber = Integer.parseInt(numberField.getText());

                // Überprüfe ob Levels bereits existieren
                for (Level existingLevel : building.getLevels()) {
                    if (existingLevel.getNumber() == levelNumber) {
                        JOptionPane.showMessageDialog(null, "Eine Etage mit dieser Nummer existiert bereits.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Erstelle ein neues Level und füge es hinzu
                Level level = new Level(levelNumber, maxRooms, building);
                building.addLevel(level);

                // Speichere die Änderungen
                GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();

                // Anpassung des Baums
                bp.reloadTree();

                // Schließen des Fensters
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
        numberField.setEditable(true);
        panel.add(numberField);

        panel.add(new JLabel("Maximale Anzahl an Räumen:"));
        panel.add(maxRoomsField);

        panel.add(createButton);

        // Setze das Panel als Inhalt des Popups
        setContentPane(panel);
        pack();
    }
}
