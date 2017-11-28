import com.fasterxml.jackson.databind.JsonNode;

public class GameMonitor {
    public void buttonPress(JsonNode clickPayload) {
        String button = clickPayload.get("button").asText();
        String actionType = clickPayload.get("actionType").asText();

        System.out.println(String.format("Button %s sent the action %s", button, actionType));
    }
}
