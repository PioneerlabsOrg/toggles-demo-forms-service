package io.pioneerlabs.toggles.demo.api.rest.v1.forms;

import io.pioneerlabs.toggles.sdks.java.api.TogglesClient;
import io.pioneerlabs.toggles.sdks.java.domain.BusinessObject;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FormFactory {

    @Autowired
    private  final TogglesClient togglesClient;

    private final FormControllerV0 formControllerV0;
    private final FormControllerV1 formControllerV1;
    private final FormControllerV2 formControllerV2;
    private final FormControllerV3 formControllerV3;



    public FormFactory(TogglesClient togglesClient, FormControllerV0 formControllerV0, FormControllerV1 formControllerV1, FormControllerV2 formControllerV2, FormControllerV3 formControllerV3) {
        this.togglesClient = togglesClient;
        this.formControllerV0 = formControllerV0;
        this.formControllerV1 = formControllerV1;
        this.formControllerV2 = formControllerV2;
        this.formControllerV3 = formControllerV3;
    }




    public JSONObject createForm(BusinessObject businessObject) {
        log.info("calling createForm()");

        boolean isPostCodeLookUpEnabled = togglesClient.isFeatureOn("postcode-lookup", businessObject);
        boolean isDocUploadEnabled = togglesClient.isFeatureOn("doc-upload", businessObject);
        boolean isQualityCheckEnabled = togglesClient.isFeatureOn("quality-check", businessObject);


        if(isPostCodeLookUpEnabled) {
            return formControllerV1.createJsonForm();
        } else if(isDocUploadEnabled) {
            return formControllerV2.createJsonForm();
        } else if(isQualityCheckEnabled) {
            return formControllerV3.createJsonForm();
        } else {
            return formControllerV0.createJsonForm();
        }

    }


}
