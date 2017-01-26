package commandPersistence;

import java.util.HashMap;

public class StateMachine {
    private Command command;

    private static HashMap<String, Long> stateMachine;

    static {
        stateMachine = new HashMap<>();
        
        // TODO: upon start, load the commandLogManager contents into the stateMachine and execute the commands. This is so that when the server restarts, we can pick up where it was left off.
    }

    public synchronized void update(Command command) throws Exception {
        String key = command.getKey();
        Command.Options option = command.getOption();
        long modifier = command.getModifierValue();

        if (key == null || key.length() == 0) {
            throw new Exception("Invalid key: " + key);
        }

        long currentValue;
        if (stateMachine.containsKey(key)) {
            currentValue = stateMachine.get(key);
        } else {
            currentValue = 0;
        }

        switch (option) {
            case ADD:
                currentValue = currentValue + modifier;
                stateMachine.put(key, currentValue);
                break;
            case SUBTRACT:
                currentValue = currentValue - modifier;
                stateMachine.put(key, currentValue);
                break;
            case MULTIPLY:
                currentValue = currentValue * modifier;
                stateMachine.put(key, currentValue);
                break;
        }
    }

    public long getValue(String key) {
        return stateMachine.get(key);
    }

    // method to write to file

    // method to read from file
}
