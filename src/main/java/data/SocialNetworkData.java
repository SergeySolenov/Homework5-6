package data;

public enum SocialNetworkData implements IData{
    VK("contact-0-value"),
    TELEGRAM("contact-1-value");

    private final String name;

    SocialNetworkData(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

