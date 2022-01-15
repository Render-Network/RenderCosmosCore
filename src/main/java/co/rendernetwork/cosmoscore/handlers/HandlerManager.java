package co.rendernetwork.cosmoscore.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class HandlerManager {

    private final Map<Class<? extends Handler>, Object> handlers = new HashMap<>();

    public Map<Class<? extends Handler>, Object> getHandlers() {
        return handlers;
    }

    @SuppressWarnings("unchecked")
    public <T extends Handler> T getHandler(Class<? extends Handler> clazz) {
        if (handlers.isEmpty() || handlers.get(clazz) == null) {
            try {
                Handler handler = clazz.getDeclaredConstructor().newInstance();
                handlers.put(clazz, handler);
                return (T) handler;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return (T) handlers.get(clazz);
    }

    public void addHandler(Handler handler) {
        handlers.put(handler.getClass(), handler);
    }

    // old stuff

    private final ChatHandler chatHandler = new ChatHandler();

    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    public ChatHandler getChatHandler() { return getHandler(ChatHandler.class); }
}
