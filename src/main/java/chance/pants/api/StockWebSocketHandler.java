package chance.pants.api;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class StockWebSocketHandler extends TextWebSocketHandler {

    private BroadcastHandler broadcastHandler;

    @Autowired
    public StockWebSocketHandler(BroadcastHandler broadcastHandler) {
        this.broadcastHandler = broadcastHandler;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        broadcastHandler.add(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        broadcastHandler.remove(session);
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }
}
