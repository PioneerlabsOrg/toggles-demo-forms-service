package io.pioneerlabs.toggles.demo.api.rest.v1;

import io.pioneerlabs.toggles.demo.Features;
import io.pioneerlabs.toggles.demo.api.rest.v1.forms.FormService;
import io.pioneerlabs.toggles.sdks.java.api.TogglesClient;
import io.pioneerlabs.toggles.sdks.java.domain.BusinessObject;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeaturesService {

    private final FormService formService;
    private final TogglesClient togglesClient;

    @Autowired
    public FeaturesService(FormService formService, TogglesClient togglesClient) {
        this.formService = formService;
        this.togglesClient = togglesClient;
    }

    public JSONObject getFormFeature(BusinessObject businessObject){

        boolean isDocUploadOn = togglesClient.isFeatureOn(Features.DOC_UPLOAD.toString(),
                businessObject);
        boolean isQualityCheckOn = togglesClient.isFeatureOn(Features.QUALITY_CHECK.toString(),
                businessObject);
        boolean isPostCodeLookUp = togglesClient.isFeatureOn(Features.POSTCODE_LOOKUP.toString(),
                businessObject);

        if(isDocUploadOn) {
            formService.enableDocUpload();
        }

        if(isPostCodeLookUp) {
            formService.enablePostCodeLookUp();
        }

        if(isQualityCheckOn) {
            formService.enableQualityCheck();
        }

        return formService.getJsonObject();

    }

}
