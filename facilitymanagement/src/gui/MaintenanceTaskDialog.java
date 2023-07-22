package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import enums.GroupEnum;
import enums.PriorityEnum;
import exceptions.WrongUserInputException;
import global.GlobalVerwaltung;
import gui.BuildingManagementPanel.BuildingTreeNode;
import gui.BuildingManagementPanel.EquipmentTreeNode;
import gui.BuildingManagementPanel.LevelTreeNode;
import gui.BuildingManagementPanel.RoomTreeNode;
import maintainables.Maintainable;
import todos.MaintenanceTask;

/*
 * Das MaintenanceTaskDialog ist ein Popup-Fenster, das erscheint, wenn ein neues Todo erstellt werden soll.
 * 
 * @author Florian Schmidt
 */
public class MaintenanceTaskDialog extends JDialog {

    private Maintainable maintainable;

    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField maintainableField;
    private JComboBox<PriorityEnum> priorityComboBox;
    private JComboBox<GroupEnum> groupComboBox;
    private JTextField intervalField;
    private JTextField dueDateField;

    public MaintenanceTaskDialog(Object object) {
        castMaintainable(object);
        initComponents();

    }

    private void initComponents() {

        setTitle("Neue Wartungsaufgabe hinzufügen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Erzeuge den "Wartungsaufgabe erstellen" Button
        JButton createButton = new JButton("Wartungsaufgabe erstellen");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int interval = -1;

                // Erzeuge ein neue MaintenanceTask mit den Werten aus den Textfeldern und
                // ComboBoxen
                try {
                    LocalDate dueDate = LocalDate.parse(dueDateField.getText());
                    try {
                        interval = Integer.parseInt(intervalField.getText());
                    } catch (NumberFormatException e1) {
                        throw new WrongUserInputException("Falsche Eingabe",
                                "Bitte geben Sie eine positive Zahl als Interval ein.");
                    }
                    MaintenanceTask mTask = new MaintenanceTask(titleField.getText(), descriptionArea.getText(),
                            maintainable, (PriorityEnum) priorityComboBox.getSelectedItem(),
                            (GroupEnum) groupComboBox.getSelectedItem(), interval, dueDate);

                    GlobalVerwaltung.getTodoVerwaltung().addTodo(mTask);
                } catch (WrongUserInputException e1) {
                    new ExceptionDialog(e1);
                    return;
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                // Speichere die Änderungen
                GlobalVerwaltung.getTodoVerwaltung().saveTodos();

                // Schließe das Popup-Fenster
                dispose();
            }
        });

        // Erzeuge das Panel für die Eingabefelder
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Titel
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Titel:"), gbc);
        gbc.gridx = 1;
        titleField = new JTextField(30);
        contentPanel.add(titleField, gbc);

        // Beschreibung
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(new JLabel("Beschreibung:"), gbc);
        gbc.gridx = 1;
        descriptionArea = new JTextArea(5, 30);
        contentPanel.add(descriptionArea, gbc);

        // Objekt
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(new JLabel("Objekt:"), gbc);
        gbc.gridx = 1;
        maintainableField = new JTextField(30);
        maintainableField.setText(maintainable.toString());
        maintainableField.setEnabled(false);
        contentPanel.add(maintainableField, gbc);

        // Priorität
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPanel.add(new JLabel("Priorität:"), gbc);
        gbc.gridx = 1;
        priorityComboBox = new JComboBox<>(PriorityEnum.values());
        contentPanel.add(priorityComboBox, gbc);

        // Gruppe
        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPanel.add(new JLabel("Gruppe:"), gbc);
        gbc.gridx = 1;
        groupComboBox = new JComboBox<>(GroupEnum.values());
        contentPanel.add(groupComboBox, gbc);

        // Fälligkeitsdatum
        gbc.gridx = 0;
        gbc.gridy = 6;
        contentPanel.add(new JLabel("Fälligkeitsdatum(yyyy-mm-dd):"), gbc);
        gbc.gridx = 1;
        dueDateField = new JTextField(10);
        contentPanel.add(dueDateField, gbc);

        // Interval
        gbc.gridx = 0;
        gbc.gridy = 7;
        contentPanel.add(new JLabel("Interval in Tagen:"), gbc);
        gbc.gridx = 1;
        intervalField = new JTextField(10);
        contentPanel.add(intervalField, gbc);

        // Button zum Erstellen des Tickets
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        contentPanel.add(createButton, gbc);

        add(contentPanel);

        setContentPane(contentPanel);
        pack();

    }

    private void castMaintainable(Object object) {

        // Cast das Object zu einem Maintainable
        if (object instanceof BuildingTreeNode) {
            object = ((BuildingTreeNode) object).getBuilding();
            maintainable = (Maintainable) object;
        } else if (object instanceof LevelTreeNode) {
            object = ((LevelTreeNode) object).getLevel();
            maintainable = (Maintainable) object;
        } else if (object instanceof RoomTreeNode) {
            object = ((RoomTreeNode) object).getRoom();
            maintainable = (Maintainable) object;
        } else if (object instanceof EquipmentTreeNode) {
            object = ((EquipmentTreeNode) object).getEquipment();
            maintainable = (Maintainable) object;
        } else {
            System.out.println("TodoCreateDialog: Object ist kein Maintainable");
            return;
        }

    }

}
