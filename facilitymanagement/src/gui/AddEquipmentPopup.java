package gui;

import enums.EquipTypeEnum;
import maintainables.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Das AddEquipmentPopup ist ein Dialogfenster, das das Hinzufügen einer neuen Ausrüstung ermöglicht.
 * Es enthält eine JComboBox, um den Ausrüstungstyp auszuwählen, und einen "Weiter"-Button, um fortzufahren.
 *
 * @author Alexander Ansorge
 */
public class AddEquipmentPopup extends JDialog {
    protected JComboBox<EquipTypeEnum> typeComboBox;

    protected BuildingManagementPanel bp;
    protected Room room;

    /**
     * Erstellt ein neues AddEquipmentPopup Dialog.
     *
     * @param bp   Das BuildingManagementPanel, um die Baumansicht zu aktualisieren
     * @param room Der Raum, in dem die Ausrüstung hinzugefügt werden soll
     */
    public AddEquipmentPopup(BuildingManagementPanel bp, Room room) {
        this.room = room;
        this.bp = bp;
        initComponents();
    }

    /**
     * Initialisiert die Komponenten des Dialogs.
     */
    protected void initComponents() {
        setTitle("Equipment Typ auswählen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton continueButton = new JButton("Weiter");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueButtonActionPerformed(e);
            }
        });

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        addTypeField(contentPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(continueButton, gbc);

        add(contentPanel, BorderLayout.CENTER);

        setContentPane(contentPanel);
        pack();
    }

    /**
     * Behandelt das ActionEvent des "Weiter"-Buttons.
     * Öffnet ein neues Dialogfenster basierend auf dem ausgewählten Ausrüstungstyp und schließt das aktuelle Dialogfenster.
     *
     * @param e Das ActionEvent
     */
    protected void continueButtonActionPerformed(ActionEvent e) {
        EquipTypeEnum selectedType = (EquipTypeEnum) typeComboBox.getSelectedItem();

        switch (selectedType) {
            case ALARMSYSTEM, KAMERA, NOTBELEUCHTUNG, SICHERHEITSTÜR, FEUERLÖSCHER, FEUERMELDER:
                JDialog safetyEquipmentPopup = new AddSafetyEquipmentPopup(bp, room);
                safetyEquipmentPopup.setModal(true);
                safetyEquipmentPopup.setVisible(true);
                safetyEquipmentPopup.dispose();
                break;
            case TOILETTE, WASCHBECKEN, URINAL:
                JDialog sanitaryEquipmentPopup = new AddSanitaryEquipmentPopup(bp, room);
                sanitaryEquipmentPopup.setModal(true);
                sanitaryEquipmentPopup.setVisible(true);
                sanitaryEquipmentPopup.dispose();
                break;
            case PROJEKTOR, WHITEBOARD, STUHL, TISCH, PFLANZE:
                JDialog officeEquipmentPopup = new AddOfficeEquipmentPopup(bp, room);
                officeEquipmentPopup.setModal(true);
                officeEquipmentPopup.setVisible(true);
                officeEquipmentPopup.dispose();
                break;
            case DRUCKER, ROUTER, SPRINKLERANLAGE, LAMPE, TELEFONANLAGE, HEIZUNG, KLIMAANLAGE, BELÜFTUNGSSYSTEM:
                JDialog technicalEquipmentPopup = new AddTechnicalEquipmentPopup(bp, room);
                technicalEquipmentPopup.setModal(true);
                technicalEquipmentPopup.setVisible(true);
                technicalEquipmentPopup.dispose();
                break;
            default:
                JDialog equipmentPopup = new AddGeneralEquipmentPopup(bp, room);
                equipmentPopup.setModal(true);
                equipmentPopup.setVisible(true);
                equipmentPopup.dispose();
                break;
        }

        dispose();
    }

    /**
     * Fügt das "Typ" JLabel und die JComboBox für die Ausrüstungstypen zum gegebenen Panel hinzu.
     *
     * @param panel Das Panel, zu dem das Feld hinzugefügt werden soll
     * @param gbc   Das GridBagConstraints-Objekt für das Layout des Panels
     */
    private void addTypeField(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel typeLabel = new JLabel("Typ:");
        panel.add(typeLabel, gbc);
        gbc.gridx = 1;
        typeComboBox = new JComboBox<>(EquipTypeEnum.values());
        panel.add(typeComboBox, gbc);
    }
}
