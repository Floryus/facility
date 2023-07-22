package exceptions;

/*
 * Die Klasse CustomException ist die Basisklasse für alle selbstentwickelten Exceptions.
 * 
 * Sie enthält die Attribute title und explanation, die den Titel und die Erklärung der Exception enthalten.
 * Sie wird von der Klasse Exception abgeleitet.
 * Sie wird im GUI verwendet, um Exceptions in einem Dialog anzuzeigen.
 * 
 * @author Florian Schmidt
 */
public class CustomException extends Exception {

    private String title;
    private String explanation;

    public CustomException(String title, String explanation) {
        this.title = title;
        this.explanation = explanation;
    }

    public String getTitle() {
        return title;
    }

    public String getExplanation() {
        return explanation;
    }
}
