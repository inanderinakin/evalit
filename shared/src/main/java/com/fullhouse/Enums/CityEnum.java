package com.fullhouse.Enums;

public enum CityEnum {
    DEFAULT(""),
    ISTANBUL("Istanbul"),
    ANKARA("Ankara"),
    IZMIR("Izmir"),
    BURSA("Bursa"),
    ANTALYA("Antalya"),
    KONYA("Konya"),
    ADANA("Adana"),
    SANLIURFA("Sanliurfa"),
    GAZIANTEP("Gaziantep"),
    KOCAELI("Kocaeli"),
    MERSIN("Mersin"),
    DIYARBAKIR("Diyarbakir"),
    HATAY("Hatay"),
    MANISA("Manisa"),
    KAYSERI("Kayseri");

    private String displayedName;
    private CityEnum(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDisplayedName() { return displayedName; }

    public static CityEnum fromDisplayedName(String displayedName) {
        for (CityEnum ce : CityEnum.values()) {
            if(ce.getDisplayedName().equals(displayedName)) return ce;
        }
        return DEFAULT;
    }
}
