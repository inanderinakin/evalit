package com.fullhouse.Enums;

/**
 * The enum Category enum.
 */
public enum CategoryEnum {
    /**
     * Default category enum.
     */
    DEFAULT(""),
    /**
     * Hygıene category enum.
     */
    HYGIENE("Hygiene"),
    /**
     * The Value for money.
     */
    VALUE_FOR_MONEY("Value for Money"),
    /**
     * The Locatıon accessıbılıty.
     */
    LOCATION_ACCESSIBILITY("Location Accessibility"),
    /**
     * The Workıng hours accessıbılıty.
     */
    WORKING_HOURS_ACCESSIBILITY("Working Hours Accessibility"),
    /**
     * The Onlıne accessıbılıty.
     */
    ONLINE_ACCESSIBILITY("Online Accessibility"),
    /**
     * The Servıce qualıty.
     */
    SERVICE_QUALITY("Service Quality"),
    /**
     * The Receptıon performance.
     */
    RECEPTION_PERFORMANCE("Reception Performance"),
    /**
     * The Waıtıng tıme.
     */
    WAITING_TIME("Waiting Time"),
    /**
     * The Polıcy transparency.
     */
    POLICY_TRANSPARENCY("Policy Transparency"),
    /**
     * Hospıtabılıty category enum.
     */
    HOSPITABILITY("Hospitability"),
    /**
     * The Attentıve servıce.
     */
    ATTENTIVE_SERVICE("Attentive Service"),
    /**
     * The Personalızed servıce.
     */
    PERSONALIZED_SERVICE("Personalized Service"),
    /**
     * The Servıce speed.
     */
    SERVICE_SPEED("Service Speed"),
    /**
     * The Expectatıon realıty matchıng.
     */
    EXPECTATION_REALITY_MATCHING("Expectation Reality Matching"),
    /**
     * Consıstency category enum.
     */
    CONSISTENCY("Consistency"),
    /**
     * Comfort category enum.
     */
    COMFORT("Comfort"),
    /**
     * The Dıgıtal experıence.
     */
    DIGITAL_EXPERIENCE("Digital Experience"),
    /**
     * Atmosphere category enum.
     */
    ATMOSPHERE("Atmosphere"),
    /**
     * Convenıent category enum.
     */
    CONVENIENT("Convenient"),
    /**
     * The Complaınt management.
     */
    COMPLAINT_MANAGEMENT("Complaint Management"),
    /**
     * The General satısfactıon.
     */
    GENERAL_SATISFACTION("General Satisfaction");

    private String displayedName;

    private CategoryEnum(String displayedName) {
        this.displayedName = displayedName;
    }

    /**
     * Gets displayed name.
     *
     * @return the displayed name
     */
    public String getDisplayedName() { return displayedName; }

    /**
     * From displayed name category enum.
     *
     * @param displayedName the displayed name
     * @return the category enum
     */
    public static CategoryEnum fromDisplayedName(String displayedName) {
        for (CategoryEnum ce : CategoryEnum.values()) {
            if(ce.getDisplayedName().equals(displayedName)) return ce;
        }
        return DEFAULT;
    }

    /**
     * From value string.
     *
     * @param value the value
     * @return the string
     */
    public static String fromValue(String value) {
        for (CategoryEnum ce : CategoryEnum.values()) {
            if(ce.name().equals(value)) return ce.getDisplayedName();
        }
        return DEFAULT.getDisplayedName();
    }
}
