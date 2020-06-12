package com.example.ginoamaury.ufpsradio.util;


/**
 * author carlos22ivan
 * author gino
 */
public class Variable {

    private final static String RADIO_URL = "http://live3.rcnmundo.com/lafmmedellin.mp3";

    /**
     * @return en una cadena la url del stream de la ufps
     */
    public static String getRadioUrl() {
        return RADIO_URL;
    }
}
