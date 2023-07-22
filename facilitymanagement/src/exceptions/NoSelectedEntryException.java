package exceptions;

/*
 * Die Klasse NoSelectedEntryException ist eine CustomException, die geworfen wird, wenn kein Eintrag aus einer Tabelle ausgewählt wurde.
 * 
 * @author Florian Schmidt
 */
public class NoSelectedEntryException extends CustomException {
    public NoSelectedEntryException(String title, String explanation) {
        super(title, explanation);
    }
}
