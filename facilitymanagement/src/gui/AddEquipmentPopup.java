package gui;

import enums.EquipTypeEnum;
import maintainables.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEquipmentPopup extends JDialog {
    protected JComboBox<EquipTypeEnum> typeComboBox;

    protected BuildingManagementPanel bp;
    protected Room room;

    public AddEquipmentPopup(BuildingManagementPanel bp, Room room) {
        this.room = room;
        this.bp = bp;
        initComponents();
    }

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
