package enums;

/*
 * Ein Enum zur Darstellung des Zustands eines Equipments.
 *
 * @author: Alexander Ansorge
 */
public enum EquipConditionEnum {
    LIKE_NEW("Wie neu"),
    GOOD("Gut"),
    AVERAGE("Mittel"),
    POOR("Schlecht");

    private final String condition;

    EquipConditionEnum(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return this.condition;
    }

    // Diese Methode wird dazu verwendet, den Zustand des Equipments als String darzustellen.
    @Override
    public String toString() {
        return this.condition;
    }
}
