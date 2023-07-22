package exceptions;

/*
 * Die Klasse WrongUserInputException ist eine CustomException, die geworfen wird, wenn ein Nutzer ungültige Eingabe im GUI macht.
 * 
 * @author Florian Schmidt
 */
public class WrongUserInputException extends CustomException {

    public WrongUserInputException(String title, String explanation) {
        super(title, explanation);
    }
}
