package com.fullhouse.Enums;

/**
 * The enum City enum.
 */
public enum CityEnum {
    /**
     * Default city enum.
     */
    DEFAULT(""),
    /**
     * Istanbul city enum.
     */
    ISTANBUL("Istanbul"),
    /**
     * Ankara city enum.
     */
    ANKARA("Ankara"),
    /**
     * Izmır city enum.
     */
    IZMIR("Izmir"),
    /**
     * Bursa city enum.
     */
    BURSA("Bursa"),
    /**
     * Antalya city enum.
     */
    ANTALYA("Antalya"),
    /**
     * Konya city enum.
     */
    KONYA("Konya"),
    /**
     * Adana city enum.
     */
    ADANA("Adana"),
    /**
     * Sanlıurfa city enum.
     */
    SANLIURFA("Sanliurfa"),
    /**
     * Gazıantep city enum.
     */
    GAZIANTEP("Gaziantep"),
    /**
     * Kocaelı city enum.
     */
    KOCAELI("Kocaeli"),
    /**
     * Mersın city enum.
     */
    MERSIN("Mersin"),
    /**
     * Dıyarbakır city enum.
     */
    DIYARBAKIR("Diyarbakir"),
    /**
     * Hatay city enum.
     */
    HATAY("Hatay"),
    /**
     * Manısa city enum.
     */
    MANISA("Manisa"),
    /**
     * Kayserı city enum.
     */
    KAYSERI("Kayseri");

    private String displayedName;
    private CityEnum(String displayedName) {
        this.displayedName = displayedName;
    }

    /**
     * Gets displayed name.
     *
     * @return the displayed name
     */
    public String getDisplayedName() { return displayedName; }

    /**
     * From displayed name city enum.
     *
     * @param displayedName the displayed name
     * @return the city enum
     */
    public static CityEnum fromDisplayedName(String displayedName) {
        for (CityEnum ce : CityEnum.values()) {
            if(ce.getDisplayedName().equals(displayedName)) return ce;
        }
        return DEFAULT;
    }
}
