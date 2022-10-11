package srki2k.tweakedlib.api.logging.errorlogginglib;

public class PowerTierNotFound extends RuntimeException {
    public PowerTierNotFound(String check_the_logs) {
        super(check_the_logs);
    }
}
