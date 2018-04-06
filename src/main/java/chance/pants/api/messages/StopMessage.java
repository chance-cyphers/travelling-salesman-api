package chance.pants.api.messages;

import chance.pants.api.domain.Stop;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class StopMessage {

    public static final String NEW_STOP = "NEW_STOP";

    private String messageType;
    private long id;
    private String name;
    private double x;
    private double y;

    public StopMessage(String messageType, Stop stop) {
        this.messageType = messageType;
        this.id = stop.getId();
        this.name = stop.getName();
        this.x = stop.getX();
        this.y = stop.getY();
    }

}
