package gui;

import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import employee.Employee;
import exceptions.NoSelectedEntryException;
import global.GlobalVerwaltung;
import todos.Todo;

import java.awt.*;
import java.awt.event.ActionListener;

/*
 * Das TodoOverviewPanel ist das Panel, das die Todos eines Mitarbeiters oder alle Todos anzeigt.
 * 
 * @author Florian Schmidt
 */
public class TodoOverviewPanel extends JPanel {

    private JTable todoTable;
    private DefaultTableModel tableModel;
    List<Todo> todos;
    private Employee employee;

    public TodoOverviewPanel() {
        TodoOverviewPanel parent = this;

        // Initialisiere die Tabellenmodelle und -spalten
        String[] columnNames = { "ID", "Titel", "Beschreibung", "Priorität",
                "Erstellungsdatum", "Fälligkeitsdatum", "Ort" };
        tableModel = new DefaultTableModel(columnNames, 0);
        todoTable = new JTable(tableModel);

        // Fülle die Tabelle mit den Ticket-Daten eines Mitarbeiters oder zeige alle an
        if (employee != null) {
            todos = employee.getTodos();
        } else {
            todos = GlobalVerwaltung.getTodoVerwaltung().getTodos();
        }

        Collections.sort(todos);
        for (Todo todo : todos) {
            addTodoToTable(todo);
        }

        // Scrollpane für die Tabelle
        JScrollPane scrollPane = new JScrollPane(todoTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        JButton finishButton = new JButton("Ticket abschließen");
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = todoTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Todo selectedTodo = todos.get(selectedRow);
                    selectedTodo.finish();
                    GlobalVerwaltung.getTodoVerwaltung().saveTodos();
                    updateTodoTable();
                } else {
                    NoSelectedEntryException nsee = new NoSelectedEntryException("Kein Todo ausgewählt.",
                            "Bitte wählen Sie ein Todo aus der Tabelle aus.");
                    new ExceptionDialog(nsee);
                }
            }
        });

        // Auswahl des Mitarbeiters
        JLabel employeeLabel = new JLabel("Mitarbeiter:");
        JComboBox<Employee> employeeComboBox = new JComboBox<Employee>(
                GlobalVerwaltung.getEmployeeVerwaltung().getEmployees().toArray(new Employee[0]));
        employeeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employee = (Employee) employeeComboBox.getSelectedItem();
                updateTodoTable();
            }
        });

        // Füge die Komponenten zum Panel hinzu
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(employeeLabel);
        buttonPanel.add(employeeComboBox);
        buttonPanel.add(finishButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Methode, um ein Todo der Tabelle hinzuzufügen
    private void addTodoToTable(Todo todo) {

        String[] rowData = { todo.getId().toString(), todo.getTitle(), todo.getDescription(),
                todo.getPriority().toString(), todo.getCREATIONDATE().toString(), todo.getDueDate().toString(),
                todo.getLocation() };
        tableModel.addRow(rowData);
    }

    // Methode, um die Tabelle zu aktualisieren
    public void updateTodoTable() {
        // Lösche alle Einträge aus der Tabelle
        tableModel.setRowCount(0);

        // Füge alle Todos wieder hinzu
        if (employee != null) {
            todos = employee.getTodos();
        } else {
            todos = GlobalVerwaltung.getTodoVerwaltung().getTodos();
        }
        Collections.sort(todos);
        for (Todo todo : todos) {
            addTodoToTable(todo);
        }
    }

}
