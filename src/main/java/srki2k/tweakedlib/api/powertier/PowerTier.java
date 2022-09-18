package srki2k.tweakedlib.api.powertier;

public final class PowerTier {
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

}
