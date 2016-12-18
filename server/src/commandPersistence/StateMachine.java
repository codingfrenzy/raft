package commandPersistence;

import java.util.HashMap;

/**
 * Created by prasanthnair on 12/17/16.
 */
public class StateMachine {
    private Command command;

    private static HashMap<String, Float> stateMachine;

    static {
        stateMachine = new HashMap<>();
    }

    public synchronized Float update(Command command) {
        String key = command.getKey();
        Command.Options option = command.getOption();
        Float modifier = command.getModifierValue();

        if (key == null || key.length() == 0) {
            return null;
        }
        if (modifier == null) {
            return null;
        }

        Float currentValue;
        if (stateMachine.containsKey(key)) {
            currentValue = stateMachine.get(key);
        } else {
            currentValue = new Float(0);
        }

        switch (option) {
            case ADD:
                currentValue = currentValue.floatValue() + modifier.floatValue();
                stateMachine.put(key, currentValue);
                break;
            case SUBTRACT:
                currentValue = currentValue.floatValue() - modifier.floatValue();
                stateMachine.put(key, currentValue);
                break;
            case MULTIPLY:
                currentValue = currentValue.floatValue() * modifier.floatValue();
                stateMachine.put(key, currentValue);
                break;
        }

        return getValue(key);
    }

    public Float getValue(String key) {
        return stateMachine.get(key);
    }

    // method to write to file

    // method to read from file
}
