package io.github.srdjanv.tweakedlib.api.powertier;

import java.util.Objects;

public final class PowerTier implements Comparable<PowerTier> {
    private final int capacity;
    private final int rft;
    private final int id;

    public PowerTier(int capacity, int rft) {
        this.capacity = capacity;
        this.rft = rft;
        this.id = Objects.hash(capacity, rft);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRft() {
        return rft;
    }

    public int getId() {
        return id;
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
        return id;
    }

    @Override
    public int compareTo(PowerTier o) {
        return (int) (((long) capacity * rft / 2) - ((long) o.getCapacity() * o.getRft() / 2));
    }
}
