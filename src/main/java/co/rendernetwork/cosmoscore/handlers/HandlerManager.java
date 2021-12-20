package co.rendernetwork.cosmoscore.handlers;

import co.rendernetwork.cosmoscore.playerdata.PlayerDataHandler;

public class HandlerManager {

    private final ChatHandler chatHandler = new ChatHandler();
    private final PlayerDataHandler playerDataHandler = new PlayerDataHandler();

    public ChatHandler getChatHandler() {
        return chatHandler;
    }

    public PlayerDataHandler getPlayerData() {
        return playerDataHandler;
    }

}
