package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import employee.Employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import enums.GroupEnum;
import enums.PriorityEnum;
import exceptions.CustomException;
import global.GlobalVerwaltung;
import todos.Todo;

/*
 * Der TodoEditDialog ist ein Dialog, der zum Bearbeiten eines Todos verwendet wird.
 * 
 * Er wird aufgerufen, wenn der "Bearbeiten"-Button im TodoSystemPanel gedrückt wird.
 * 
 * @author Florian Schmidt
 */
public class TodoEditDialog extends JDialog {
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField maintainableField;
    private JTextField creationDateField;
    private JTextField dueDateField;
    private JComboBox<Employee> employeeComboBox;
    private JComboBox<PriorityEnum> priorityComboBox;
    private JComboBox<GroupEnum> groupComboBox;

    private JButton cancelButton;
    private JButton saveButton;

    private Todo todo;

    private TodoSystemPanel parent;

    public TodoEditDialog(TodoSystemPanel parent, Todo todo) {

        this.todo = todo;
        this.parent = parent;

        setTitle("Todo bearbeiten");

        // Setze Layout und erstelle die Komponenten
        setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        contentPanel.add(new JLabel("Titel:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        titleField = new JTextField(todo.getTitle(), 20);
        contentPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(new JLabel("Beschreibung:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        descriptionArea = new JTextArea(todo.getDescription(), 5, 20);
        descriptionArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        contentPanel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(new JLabel("Zugeordnetes Maintainable:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        maintainableField = new JTextField(todo.getMaintainable().toString(), 20);
        maintainableField.setEditable(false);
        contentPanel.add(maintainableField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(new JLabel("Erstelldatum:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        creationDateField = new JTextField(todo.getCREATIONDATE().toString(), 20);
        creationDateField.setEditable(false);
        contentPanel.add(creationDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(new JLabel("Fälligkeitsdatum:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dueDateField = new JTextField(todo.getDueDate().toString(), 20);
        contentPanel.add(dueDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(new JLabel("Zuständiger Mitarbeiter:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        employeeComboBox = new JComboBox<>(GlobalVerwaltung.getEmployeeVerwaltung()
                .getEmployeesByGroup(todo.getGroup()).toArray(new Employee[0]));

        employeeComboBox.setSelectedItem(todo.getEmployee());
        contentPanel.add(employeeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(new JLabel("Priorität:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        priorityComboBox = new JComboBox<>(PriorityEnum.values());
        priorityComboBox.setSelectedItem(todo.getPriority());
        contentPanel.add(priorityComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(new JLabel("Gruppe:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        groupComboBox = new JComboBox<>(GroupEnum.values());
        groupComboBox.setSelectedItem(todo.getGroup());
        groupComboBox.setEnabled(false);
        contentPanel.add(groupComboBox, gbc);

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
        // Aktualisiere die Todo-Attribute mit den Werten aus den Textfeldern und
        // ComboBoxen

        try {
            todo.setTitle(titleField.getText());
            todo.setDescription(descriptionArea.getText());
            todo.setDueDate(LocalDate.parse(dueDateField.getText()));
            todo.setEmployee((Employee) employeeComboBox.getSelectedItem());
            todo.setPriority((PriorityEnum) priorityComboBox.getSelectedItem());
        } catch (CustomException e) {
            new ExceptionDialog(e);
            return;
        }

        // Speichere die Änderungen
        GlobalVerwaltung.getTodoVerwaltung().saveTodos();

        // Aktualisiere die Tabelle im TodoSystemPanel
        parent.updateTodoTable();

        // Schließe den Dialog
        dispose();
    }
}
