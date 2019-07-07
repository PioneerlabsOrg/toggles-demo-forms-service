package io.pioneerlabs.toggles.demo;

import java.util.EnumSet;
import java.util.stream.Collectors;

public enum Features {

    POSTCODE_LOOKUP("postcode-lookup"),
    DOC_UPLOAD("doc-upload"),
    QUALITY_CHECK("quality_check");

    private final String featureName;

    Features(String featureName){
        this.featureName = featureName;
    }

    public static String[] getFeatures(){
        return EnumSet.allOf(Features.class).stream().toArray(String[]::new);

    }
}
