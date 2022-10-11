package srki2k.tweakedlib.api.powertier;

import java.util.Objects;

public final class PowerTier implements Comparable<PowerTier> {
    private final int capacity;
    private final int rft;

    public PowerTier(int capacity, int rft) {
        this.capacity = capacity;
        this.rft = rft;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRft() {
        return rft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerTier powerTier = (PowerTier) o;
        return capacity == powerTier.capacity && rft == powerTier.rft;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, rft);
    }

    @Override
    public int compareTo(PowerTier o) {
        return (capacity * rft / 2) - (o.getCapacity() * o.getRft() / 2);
    }
}
