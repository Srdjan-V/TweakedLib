package io.github.srdjanv.tweakedlib.api.waila;

import java.util.Collection;

//This is used to remove the FE CapabilityEnergy info added by waila in HUDHandlerEnergy
public class FEFilter {

    public static void filter(Collection<String> c) {
        c.removeIf(FEFilter::match);
    }

    public static boolean match(String s) {
        return s.endsWith("FE") && s.contains("/");
    }
}
