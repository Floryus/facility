package gui;

import enums.EquipTypeEnum;
import enums.EquipConditionEnum;
import global.GlobalVerwaltung;
import maintainables.Room;
import maintainables.equipment.SafetyEquipment;
import maintainables.equipment.IdManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;

public class AddSafetyEquipmentPopup extends AddGeneralEquipmentPopup {
    private JTextField batteryLifeField;
    private JTextField connectivityField;
    private JTextField rangeField;
    private JTextField logPathField;

    public AddSafetyEquipmentPopup(BuildingManagementPanel bp, Room room) {
        super(bp, room);
    }

    @Override
    protected void initComponents() {
        super.initComponents(); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = getLastGridY();

        JPanel contentPanel = (JPanel) getContentPane();

        addBatteryLifeField(contentPanel, gbc);
        gbc.gridy++;

        addConnectivityField(contentPanel, gbc);
        gbc.gridy++;

        addRangeField(contentPanel, gbc);
        gbc.gridy++;

        addLogPathField(contentPanel, gbc);
        
        pack();
    }

    private int getLastGridY() {
        LayoutManager layout = getContentPane().getLayout();
        if (layout instanceof GridBagLayout) {
            GridBagLayout gbl = (GridBagLayout)layout;
            int[][] dim = gbl.getLayoutDimensions();
            int rows = dim[1].length;
            return rows;
        }
        return 0; 
    }

    private void addBatteryLifeField(JPanel panel, GridBagConstraints gbc) {
        JLabel batteryLifeLabel = new JLabel("Batterielebensdauer:");
        panel.add(batteryLifeLabel, gbc);

        gbc.gridx = 1;
        batteryLifeField = new JTextField(20);
        panel.add(batteryLifeField, gbc);
        gbc.gridx = 0;
    }

    private void addConnectivityField(JPanel panel, GridBagConstraints gbc) {
        JLabel connectivityLabel = new JLabel("Konnektivität:");
        panel.add(connectivityLabel, gbc);

        gbc.gridx = 1;
        connectivityField = new JTextField(20);
        panel.add(connectivityField, gbc);
        gbc.gridx = 0;
    }

    private void addRangeField(JPanel panel, GridBagConstraints gbc) {
        JLabel rangeLabel = new JLabel("Reichweite:");
        panel.add(rangeLabel, gbc);

        gbc.gridx = 1;
        rangeField = new JTextField(20);
        panel.add(rangeField, gbc);
        gbc.gridx = 0;
    }

    private void addLogPathField(JPanel panel, GridBagConstraints gbc) {
        JLabel logPathLabel = new JLabel("Log-Speicherort:");
        panel.add(logPathLabel, gbc);

        gbc.gridx = 1;
        logPathField = new JTextField(20);
        panel.add(logPathField, gbc);
        gbc.gridx = 0;
    }

    @Override
    protected void createButtonActionPerformed(ActionEvent e) {
        SafetyEquipment equipment = new SafetyEquipment(
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
                Integer.parseInt(batteryLifeField.getText()),
                connectivityField.getText(),
                Integer.parseInt(rangeField.getText()),
                logPathField.getText());
        room.addEquipment(equipment);

        GlobalVerwaltung.getBuildingVerwaltung().saveBuildings();
        bp.reloadTree();
        dispose();
    }
}
