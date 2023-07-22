package gui;

import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import exceptions.NoSelectedEntryException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import global.GlobalVerwaltung;
import todos.Todo;

/*
 * Das TodoSystemPanel ist das Panel, das das TodoSystem enthält.
 * Aufbau:
 * Tabelle aller Tickets.
 * Zeigt die Attribute der Tickets an.
 * Enthält einen Button um in jeder Zeile, sodass ein Ticket bearbeitet werden kann.
 * Enthält ein Dropdown um einen Mitarbeiter zuzuweisen.
 * 
 * @author Florian Schmidt
 */
public class TodoSystemPanel extends JPanel {

    private JTable todoTable;
    private DefaultTableModel tableModel;
    List<Todo> todos = GlobalVerwaltung.getTodoVerwaltung().getTodos();

    public TodoSystemPanel() {

        TodoSystemPanel parent = this;
        Collections.sort(todos);

        // Initialisiere die Tabellenmodelle und -spalten
        String[] columnNames = { "ID", "Titel", "Beschreibung", "Zugeordneter Mitarbeiter", "Priorität", "Gruppe",
                "Erstellungsdatum", "Fälligkeitsdatum" };
        tableModel = new DefaultTableModel(columnNames, 0);
        todoTable = new JTable(tableModel);

        // Fülle die Tabelle mit den Ticket-Daten
        for (Todo todo : todos) {
            addTodoToTable(todo);
        }

        // Scrollpane für die Tabelle
        JScrollPane scrollPane = new JScrollPane(todoTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        // Button zum Bearbeiten der Tickets
        JButton editButton = new JButton("Ticket bearbeiten");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = todoTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Todo selectedTodo = todos.get(selectedRow);
                    TodoEditDialog todoEditDialog = new TodoEditDialog(parent, selectedTodo);
                    todoEditDialog.setVisible(true);

                    // Aktualisiere die Tabelle
                    updateTodoTable();
                } else {
                    NoSelectedEntryException nsee = new NoSelectedEntryException("Kein Todo ausgewählt.",
                            "Bitte wählen Sie ein Todo aus der Tabelle aus.");
                    new ExceptionDialog(nsee);
                }
            }
        });

        // Füge die Komponenten zum Panel hinzu
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(editButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Methode zum Hinzufügen eines Todos zur Tabelle
    private void addTodoToTable(Todo todo) {
        String employeeName = "";
        if (todo.hasEmployee()) {
            employeeName = todo.getEmployee().getName();
        } else {
            employeeName = "Kein Mitarbeiter zugewiesen";
        }

        Object[] rowData = {
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                employeeName,
                todo.getPriority(),
                todo.getGroup(),
                todo.getCREATIONDATE(),
                todo.getDueDate()
        };
        tableModel.addRow(rowData);
    }

    // Methode zum Aktualisieren der Todos-Tabelle
    public void updateTodoTable() {
        tableModel.setRowCount(0); // Lösche alle Zeilen
        todos = GlobalVerwaltung.getTodoVerwaltung().getTodos(); // Lade die Todos erneut
        Collections.sort(todos); // Sortiere die Todos
        // Füge die Tickets erneut zur Tabelle hinzu
        for (Todo todo : todos) {
            addTodoToTable(todo);
        }
    }

}
