package io.pioneerlabs.toggles.demo.api.rest.v1;

import io.pioneerlabs.toggles.demo.api.rest.v1.forms.FormFactory;
import io.pioneerlabs.toggles.demo.api.rest.v1.toggles.FeatureAware;
import io.pioneerlabs.toggles.demo.domain.Review;
import io.pioneerlabs.toggles.sdks.java.api.TogglesClient;
import io.pioneerlabs.toggles.sdks.java.domain.BusinessObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/v1/forms")
@Slf4j
@Data
public class FormApi implements FeatureAware {


    private final TogglesClient togglesClient;
    private final FormFactory formFactory;
    private BusinessObject businessObject;

    @Autowired
    public FormApi(TogglesClient togglesClient, FormFactory formFactory) {
        this.togglesClient = togglesClient;
        this.formFactory = formFactory;
    }

    @GetMapping("/country/{country}/{cin}")
    public ResponseEntity<String> createForm(@PathVariable String cin,
                                             @PathVariable String country) {


        Review review = Review.builder()
                .country(country)
                .cin(cin)
                .build();

        //create business object for toggles
        this.businessObject = new BusinessObject("review",
                () -> review);



        return ResponseEntity
                .ok()
                .body(formFactory.createForm(businessObject).toJSONString());

    }

    @Override
    public Flux<ServerSentEvent<String>> subscribe(
            @PathVariable("cin") String cin,
            @RequestHeader(name = "last-event-id", required = false) String lastEventId) {

        return Flux.create(fluxSink -> {
            togglesClient.onChange(e -> {
                log.info("fluxSink onChange {}", e);
                fluxSink.next(this.formFactory.createForm(this.businessObject));
            });
        })
                .log()
                .map(sequence -> ServerSentEvent.<String> builder()
                        .id(UUID.randomUUID().toString())
                        .event("form-event")
                        .data(String.valueOf(sequence))
                        .build());
    }







}
