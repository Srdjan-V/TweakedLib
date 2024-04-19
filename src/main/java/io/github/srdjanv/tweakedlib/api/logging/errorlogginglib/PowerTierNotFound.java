package io.github.srdjanv.tweakedlib.api.logging.errorlogginglib;

import java.util.List;

public class PowerTierNotFound extends RuntimeException {
    private final List<String> missingPowerTiers;

    public PowerTierNotFound(List<String> missingPowerTiers) {
        this.missingPowerTiers = missingPowerTiers;
    }

    @Override
    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder(missingPowerTiers.size());

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("    ====================== Missing Power Tiers ======================");
        stringBuilder.append(System.lineSeparator());
        for (String error : missingPowerTiers) {
            stringBuilder.append("    ").append(error).append(System.lineSeparator());
        }
        return stringBuilder.toString();

    }
}
