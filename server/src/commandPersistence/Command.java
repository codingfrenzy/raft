package commandPersistence;

/**
 * Created by prasanthnair on 12/17/16.
 */
public class Command {
    private String key;

    public enum Options {
        ADD,
        SUBTRACT,
        MULTIPLY;
    }

    private Options option;

    public Command(String key, Options option, Float modifierValue) {
        this.key = key;
        this.option = option;
        this.modifierValue = modifierValue;
    }

    public Float getModifierValue() {
        return modifierValue;
    }

    public void setModifierValue(Float modifierValue) {
        this.modifierValue = modifierValue;
    }

    private Float modifierValue;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setOption(Options option) {
        this.option = option;
    }

    public Options getOption() {
        return option;
    }
}
