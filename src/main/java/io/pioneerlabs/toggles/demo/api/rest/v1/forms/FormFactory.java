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

    private final FormService formService;



    public FormFactory(TogglesClient togglesClient, FormService formService) {
        this.togglesClient = togglesClient;
        this.formService = formService;

    }


    public JSONObject createForm(BusinessObject businessObject) {
        log.info("calling createForm()");

        boolean isPostCodeLookUpEnabled = togglesClient.isFeatureOn("postcode-lookup", businessObject);
        boolean isDocUploadEnabled = togglesClient.isFeatureOn("doc-upload", businessObject);
        boolean isQualityCheckEnabled = togglesClient.isFeatureOn("quality-check", businessObject);

        if(isPostCodeLookUpEnabled) {
            formService.enablePostCodeLookUp();
        } else if(isDocUploadEnabled) {
            formService.enableDocUpload();
        } else if(isQualityCheckEnabled) {
            formService.enableQualityCheck();
        }

        return formService.getJsonObject();


    }


}
