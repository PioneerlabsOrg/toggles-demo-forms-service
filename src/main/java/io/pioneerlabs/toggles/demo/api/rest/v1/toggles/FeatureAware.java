package io.pioneerlabs.toggles.demo.api.rest.v1.toggles;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

public interface FeatureAware {


    @GetMapping(path = "{cin}/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<ServerSentEvent<String>> subscribe(
            @PathVariable("cin") String cin,
            @RequestHeader(name = "last-event-id", required = false) String lastEventId);
}
