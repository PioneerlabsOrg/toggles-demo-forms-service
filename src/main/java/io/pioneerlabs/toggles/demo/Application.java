package io.pioneerlabs.toggles.demo;

import io.pioneerlabs.toggles.sdks.java.api.TogglesClient;
import io.pioneerlabs.toggles.sdks.java.examples.ExampleKeys;
import io.pioneerlabs.toggles.sdks.java.security.TogglesCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class Application {




    public static void main(String[] args) {
        SpringApplication.run(Application.class,
                args);
    }

    @Bean
    public TogglesClient togglesClient(){
        TogglesCredentials togglesCredentials = TogglesCredentials.builder()
                .accessKey("gkz99hzzj4D-WTDYQVenFJi2")
                .secretKey("quhLeTpoxC_kRBGX@mm!s2JM")
                .build();

        TogglesClient togglesClient = TogglesClient.builder()
                .sdkKey("sdk-e26cfe22-2279-42c6-8b66-ac764f97128c")
                .groupIdProjectKey("dfd42104-a20f-4b46-8e4d-01cd7f206b2c")
                .featureKeys(Features.getFeatures())
                .togglesCredentials(togglesCredentials)
                .subscribe()
                .build();

        return togglesClient;
    }


}