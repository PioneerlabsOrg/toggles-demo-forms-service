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
    private final FormControllerV4 formControllerV4;
    private final FormControllerV5 formControllerV5;
    private final FormControllerV6 formControllerV6;
    private final FormControllerV7 formControllerV7;

    private BusinessObject businessObject;

    public FormFactory(TogglesClient togglesClient, FormControllerV0 formControllerV0, FormControllerV1 formControllerV1, FormControllerV2 formControllerV2, FormControllerV3 formControllerV3, FormControllerV4 formControllerV4, FormControllerV5 formControllerV5, FormControllerV6 formControllerV6, FormControllerV7 formControllerV7) {
        this.togglesClient = togglesClient;
        this.formControllerV0 = formControllerV0;
        this.formControllerV1 = formControllerV1;
        this.formControllerV2 = formControllerV2;
        this.formControllerV3 = formControllerV3;
        this.formControllerV4 = formControllerV4;
        this.formControllerV5 = formControllerV5;
        this.formControllerV6 = formControllerV6;
        this.formControllerV7 = formControllerV7;
    }




    public JSONObject createForm(BusinessObject businessObject) {
        log.info("calling createForm()");

        boolean isPostCodeLookUpEnabled = togglesClient.isFeatureOn("postcode-lookup", this.businessObject);
        boolean isDocUploadEnabled = togglesClient.isFeatureOn("doc-upload", this.businessObject);
        boolean isQualityCheckEnabled = togglesClient.isFeatureOn("quality-check", this.businessObject);


        if(!isPostCodeLookUpEnabled && isDocUploadEnabled && !isQualityCheckEnabled) {
            return formControllerV1.createJsonForm();
        } else if(!isPostCodeLookUpEnabled && !isDocUploadEnabled && isQualityCheckEnabled) {
            return formControllerV2.createJsonForm();
        } else if(!isPostCodeLookUpEnabled && isDocUploadEnabled && isQualityCheckEnabled) {
            return formControllerV3.createJsonForm();
        } else if(isPostCodeLookUpEnabled && isDocUploadEnabled && isQualityCheckEnabled) {
            return formControllerV4.createJsonForm();
        } else if(isPostCodeLookUpEnabled && isDocUploadEnabled && !isQualityCheckEnabled) {
            return formControllerV5.createJsonForm();
        } else if(isPostCodeLookUpEnabled && !isDocUploadEnabled && isQualityCheckEnabled) {
            return formControllerV6.createJsonForm();
        } else if(isPostCodeLookUpEnabled && !isDocUploadEnabled && !isQualityCheckEnabled) {
            return formControllerV7.createJsonForm();
        } else {
            return formControllerV0.createJsonForm();
        }

    }


}
