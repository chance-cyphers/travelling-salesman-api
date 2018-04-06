package chance.pants.api.messages;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomChannels {

    @Output("outboundStops")
    MessageChannel outboundStops();

}
