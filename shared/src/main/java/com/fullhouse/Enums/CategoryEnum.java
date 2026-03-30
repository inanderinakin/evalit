package com.fullhouse.Enums;

public enum CategoryEnum {
    DEFAULT(""),
    HYGIENE("Hygiene"),
    VALUE_FOR_MONEY("Value for Money"),
    LOCATION_ACCESSIBILITY("Location Accessibility"),
    WORKING_HOURS_ACCESSIBILITY("Working Hours Accessibility"),
    ONLINE_ACCESSIBILITY("Online Accessibility"),
    SERVICE_QUALITY("Service Quality"),
    RECEPTION_PERFORMANCE("Reception Performance"),
    WAITING_TIME("Waiting Time"),
    POLICY_TRANSPARENCY("Policy Transparency"),
    HOSPITABILITY("Hospitability"),
    ATTENTIVE_SERVICE("Attentive Service"),
    PERSONALIZED_SERVICE("Personalized Service"),
    SERVICE_SPEED("Service Speed"),
    EXPECTATION_REALITY_MATCHING("Expectation Reality Matching"),
    CONSISTENCY("Consistency"),
    COMFORT("Comfort"),
    DIGITAL_EXPERIENCE("Digital Experience"),
    ATMOSPHERE("Atmosphere"),
    CONVENIENT("Convenient"),
    COMPLAINT_MANAGEMENT("Complaint Management"),
    GENERAL_SATISFACTION("General Satisfaction");

    private String displayedName;

    private CategoryEnum(String displayedName) {
        this.displayedName = displayedName;
    }
    public String getDisplayedName() { return displayedName; }

    public static CategoryEnum fromDisplayedName(String displayedName) {
        for (CategoryEnum ce : CategoryEnum.values()) {
            if(ce.getDisplayedName().equals(displayedName)) return ce;
        }
        return DEFAULT;
    }
}
