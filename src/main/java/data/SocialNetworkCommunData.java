package data;

public enum SocialNetworkCommunData implements IData{
    VK("contact-0-value"),
    TELEGRAM("contact-1-value");

    private final String name;

    SocialNetworkCommunData(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

