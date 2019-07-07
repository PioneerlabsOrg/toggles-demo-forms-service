package io.pioneerlabs.toggles.demo.api.rest.v1.forms;

import com.fasterxml.jackson.core.type.TypeReference;
import io.pioneerlabs.toggles.demo.utils.JsonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/v1/forms")
@Slf4j
@Data
public class FormService {


    private JSONObject jsonObject;

    public FormService() {
        this.jsonObject = createForm();
    }


    public void enablePostCodeLookUp() {
        if(Optional.ofNullable(jsonObject).isPresent()) {
            jsonObject.put("postcode",
                    "PostcodeLookup");
        }
    }


    public void enableDocUpload() {
        if(Optional.ofNullable(jsonObject).isPresent()) {
            jsonObject.put("file",
                    "");
            jsonObject.put("type",
                    "string");
            jsonObject.put("format",
                    "data-url");
            jsonObject.put("title",
                    "Document Upload");
        }

    }

    public void enableQualityCheck() {
        if(Optional.ofNullable(jsonObject).isPresent()) {
            jsonObject.put("submittedMessage",
                    "We are now carrying out a further review of your submitted data and will contact you if we have any further questions.");
        }

    }

    private JSONObject createForm() {
        log.info("calling createJsonForm()");
        String formName = "uk_form-1.json";
        log.info("returning form name {}", formName);
        jsonObject = JsonUtils.createJsonObject(TypeReference.class.getResourceAsStream("/forms/" + formName));
        return jsonObject;
    }
}
