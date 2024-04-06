package io.github.srdjanv.tweakedlib.api.waila;

import java.util.HashSet;
import java.util.List;

//The tooltip function can get called 2 times in one tick
public class DuplicateFilter {
    public static <T> void add(List<T> currentTip, List<T> toolTipToAdd) {
        if (!new HashSet<>(currentTip).containsAll(toolTipToAdd)) {
            currentTip.addAll(toolTipToAdd);
        }
    }
}
