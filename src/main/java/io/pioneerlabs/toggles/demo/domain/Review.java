package io.pioneerlabs.toggles.demo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Builder
@Data
@Slf4j
public class Review {

    private String country;
    private String cin;

}
