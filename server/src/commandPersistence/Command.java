package commandPersistence;

import java.io.Serializable;

public class Command implements Serializable {
    private String key;

    public enum Options {
        ADD,
        SUBTRACT,
        MULTIPLY;
    }

    private Options option;

    private Double modifierValue;

    public Command(String key, Options option, Double modifierValue) {
        this.key = key;
        this.option = option;
        this.modifierValue = modifierValue;
    }

    public Double getModifierValue() {
        return modifierValue;
    }

    public void setModifierValue(Double modifierValue) {
        this.modifierValue = modifierValue;
    }

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Command: [key=" + key);
        sb.append(", option=" + option.name());
        sb.append(", modifierValue=" + modifierValue);
        return sb.toString();
    }
}
