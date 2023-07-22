package gui;

import javax.swing.*;

import exceptions.CustomException;

/*
 * Der ExceptionDialog ist ein Dialog, der eine Fehlermeldung anzeigt.
 * 
 * @author Florian Schmidt
 */
public class ExceptionDialog extends JDialog {

    public ExceptionDialog(CustomException exception) {
        setTitle("Fehler: " + exception.getTitle());
        setModal(true); // Blockiert den Hauptdialog, bis dieser Dialog geschlossen wird
        setSize(400, 200);
        setLocationRelativeTo(null); // Zentriert den Dialog auf dem Bildschirm

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Textbereich zur Anzeige der Fehlererklärung
        JTextArea textArea = new JTextArea(exception.getMessage());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        // Okay-Button zum Schließen des Dialogs
        JButton okButton = new JButton("Okay");
        okButton.addActionListener(e -> dispose()); // Schließt den Dialog bei Button-Klick
        panel.add(okButton);

        getContentPane().add(panel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Schließt den Dialog und gibt den Ressourcen frei
        setVisible(true); // Zeigt den Dialog an
    }
}
