package chance.pants.api.messages;

import chance.pants.api.domain.Stop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

@Component
@Slf4j
public class StopMessageSource {

    private CustomChannels channels;

    @Autowired
    public StopMessageSource(CustomChannels channels) {
        this.channels = channels;
    }

    public void publishNewStop(Stop stop) {
        StopMessage stopMessage = new StopMessage(StopMessage.NEW_STOP, stop);

        log.info("sending message: {}", stopMessage.toString());
        MessageChannel messageChannel = channels.outboundStops();
        messageChannel.send(MessageBuilder
                        .withPayload(stop)
                        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                        .build());
    }

}
