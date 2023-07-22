package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import employee.Employee;
import exceptions.CustomException;
import global.GlobalVerwaltung;
import todos.Todo;

/*
 * Der EmployeeEditDialog ist ein Dialog, der es ermöglicht, einen Mitarbeiter zu bearbeiten.
 * 
 * @author Florian Schmidt
 */
public class EmployeeEditDialog extends JDialog {

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField groupField;
    private JTextField todoCountField;
    private JList<Todo> todoList;

    private JButton cancelButton;
    private JButton saveButton;

    private Employee employee;

    private EmployeeManagementPanel parent;

    public EmployeeEditDialog(EmployeeManagementPanel parent, Employee employee) {

        this.employee = employee;
        this.parent = parent;

        setTitle("Mitarbeiter bearbeiten");

        // Setze Layout und erstelle die Komponenten
        setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        contentPanel.add(new JLabel("Vorname:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        firstNameField = new JTextField(employee.getFirstName(), 20);
        contentPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(new JLabel("Nachname:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lastNameField = new JTextField(employee.getLastName(), 20);
        contentPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(new JLabel("Gruppe:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        groupField = new JTextField(employee.getGroup().toString(), 20);
        groupField.setEditable(false);
        contentPanel.add(groupField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(new JLabel("Anzahl Todos:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        todoCountField = new JTextField(String.valueOf(employee.getTodos().size()), 20);
        todoCountField.setEditable(false);
        contentPanel.add(todoCountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(new JLabel("Todos:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        todoList = new JList<>(employee.getTodos().toArray(new Todo[0]));
        JScrollPane todoScrollPane = new JScrollPane(todoList);
        todoScrollPane.setPreferredSize(new Dimension(200, 100));
        contentPanel.add(todoScrollPane, gbc);

        add(contentPanel, BorderLayout.CENTER);

        // Button-Panel am unteren Rand
        JPanel buttonPanel = new JPanel();
        cancelButton = new JButton("Abbrechen");
        saveButton = new JButton("Speichern");
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Füge den ActionListener für den Abbrechen-Button hinzu
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Schließe den Dialog ohne Änderungen zu speichern
            }
        });

        // Füge den ActionListener für den Speichern-Button hinzu
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges(); // Schließe den Dialog und speichere die Änderungen
            }
        });

        pack();

    }

    // Methode, um die Änderungen zu speichern (wird im ActionListener des
    // "Speichern"-Buttons aufgerufen)

    private void saveChanges() {
        // Aktualisiere die Employee-Attribute mit den Werten aus den Textfeldern und
        // ComboBoxen

        try {
            employee.setFirstName(firstNameField.getText());
            employee.setLastName(lastNameField.getText());
        } catch (CustomException e) {
            new ExceptionDialog(e);
            return;
        }

        // Speichere die Änderungen
        GlobalVerwaltung.getEmployeeVerwaltung().saveEmployees();

        // Aktualisiere die Tabelle im TodoSystemPanel
        parent.updateEmployeeTable();

        // Schließe den Dialog
        dispose();
    }
}
