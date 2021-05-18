package nl.manthos.punishmenu.panel;

public enum Panel {

    HOME_PANEL("home-panel"),
    MUTE_PANEL("mute-panel");

    private final String key;

    private Panel(String key){
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
