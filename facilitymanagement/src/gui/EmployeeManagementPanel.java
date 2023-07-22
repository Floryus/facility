package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import employee.Employee;
import exceptions.NoSelectedEntryException;
import global.GlobalVerwaltung;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionListener;

/*
 * Das EmployeeManagementPanel ist das Panel, das die Mitarbeiterverwaltung enthält.
 * 
 * @author Alexander Siege, Florian Schmidt
 */
public class EmployeeManagementPanel extends JPanel {

    private JTable employeeTable;
    private DefaultTableModel tableModel;
    List<Employee> employees = GlobalVerwaltung.getEmployeeVerwaltung().getEmployees();

    public EmployeeManagementPanel() {

        EmployeeManagementPanel parent = this;
        Collections.sort(employees);

        // Initialisiere die Tabellenmodelle und -spalten
        String[] columnNames = { "ID", "Vorname", "Nachname", "Gruppe", "Anzahl Todos" };
        tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel);

        // Fülle die Tabelle mit den Ticket-Daten
        for (Employee employee : employees) {
            addEmployeeToTable(employee);
        }

        // Scrollpane für die Tabelle
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        // Button zum Bearbeiten der Mitarbeiter
        JButton editButton = new JButton("Mitarbeiter bearbeiten");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = employeeTable.getSelectedRow();
                try {
                    if (selectedRow >= 0) {
                        Employee selectedEmployee = employees.get(selectedRow);
                        if (selectedRow >= 0) {
                            EmployeeEditDialog employeeEditDialog = new EmployeeEditDialog(parent,
                                    selectedEmployee);
                            employeeEditDialog.setVisible(true);

                            // Aktualisiere die Tabelle
                            updateEmployeeTable();
                        } else {
                            throw new NoSelectedEntryException("Kein Mitarbeiter ausgewählt.",
                                    "Bitte wählen Sie ein Todo aus der Tabelle aus.");
                        }
                    }

                } catch (NoSelectedEntryException nsee) {
                    new ExceptionDialog(nsee);
                }
            }
        });

        // Button zum Hinzufügen von Mitarbeitern
        JButton addButton = new JButton("Mitarbeiter hinzufügen");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeAddDialog employeeAddDialog = new EmployeeAddDialog(parent);
                employeeAddDialog.setVisible(true);

                // Aktualisiere die Tabelle
                updateEmployeeTable();
            }
        });

        // Button zum Löschen von Mitarbeitern
        JButton deleteButton = new JButton("Mitarbeiter löschen");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Employee selectedEmployee = employees.get(selectedRow);
                    GlobalVerwaltung.getEmployeeVerwaltung().removeEmployee(selectedEmployee);

                    // Aktualisiere die Tabelle
                    updateEmployeeTable();
                } else {
                    NoSelectedEntryException nsee = new NoSelectedEntryException("Kein Mitarbeiter ausgewählt.",
                            "Bitte wählen Sie ein Todo aus der Tabelle aus.");
                    new ExceptionDialog(nsee);
                }
            }
        });

        // Füge die Komponenten zum Panel hinzu
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    // Methode zum Hinzuüfgen eines Mitarbeiters zur Tabelle
    private void addEmployeeToTable(Employee employee) {
        Object[] rowData = { employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getGroup(),
                employee.getTodos().size() };
        tableModel.addRow(rowData);
    }

    // Methode, um die Tabelle zu aktualisieren
    public void updateEmployeeTable() {
        // Lösche alle Einträge aus der Tabelle
        tableModel.setRowCount(0);

        employees = GlobalVerwaltung.getEmployeeVerwaltung().getEmployees();
        Collections.sort(employees);

        // Füge alle Todos aus der TodoVerwaltung zur Tabelle hinzu
        for (Employee employee : employees) {
            addEmployeeToTable(employee);
        }
    }

}
