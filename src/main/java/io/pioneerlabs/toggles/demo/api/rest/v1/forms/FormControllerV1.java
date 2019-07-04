package io.pioneerlabs.toggles.demo.api.rest.v1.forms;

import com.fasterxml.jackson.core.type.TypeReference;
import io.pioneerlabs.toggles.demo.domain.Review;
import io.pioneerlabs.toggles.demo.utils.JsonUtils;
import io.pioneerlabs.toggles.sdks.java.api.TogglesClient;
import io.pioneerlabs.toggles.sdks.java.domain.BusinessObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/v1/forms")
@Slf4j
@Data
public class FormControllerV1 implements FormController {



    @Override
    public JSONObject createJsonForm() {
        log.info("calling createJsonForm()");
        String formName = "uk_form_with_doc_upload-1.json";
        log.info("returning form name {}", formName);
        return JsonUtils.createJsonObject(TypeReference.class.getResourceAsStream("/forms/" + formName));
    }









}
