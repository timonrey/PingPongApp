import com.fasterxml.jackson.databind.JsonNode;
import com.sun.prism.shader.Solid_ImagePattern_Loader;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

public class GameMonitor {

    Players nicola;
    Players philipp;
    Game spiel;

    public GameMonitor () {
        this.nicola = new Players();
        this.philipp = new Players();
        this.spiel = new Game(nicola, philipp);
    }

    public void buttonPress(JsonNode clickPayload) {
        String button = clickPayload.get("button").asText();
        int actionType = clickPayload.get("actionType").asInt();
        System.out.println(button);
        if (button.equals("1")) {
            Message buttonClick = new Message(nicola, actionType);
            //System.out.println(button);
            spiel.buttonWasPressedWithValue(buttonClick);
        } else if (button.equals("2")) {
            Message buttonclick = new Message(philipp, actionType);
            spiel.buttonWasPressedWithValue(buttonclick);
        }

        System.out.println(String.format("%s vs %s", nicola.getter(), philipp.getter()));
    }
}
