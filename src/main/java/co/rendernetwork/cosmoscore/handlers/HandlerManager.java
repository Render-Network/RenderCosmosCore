package co.rendernetwork.cosmoscore.handlers;

public class HandlerManager {

    private final ChatHandler chatHandler = new ChatHandler();

    public ChatHandler getChatHandler() {
        return chatHandler;
    }

}
