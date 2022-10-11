package srki2k.tweakedlib.api.logging.errorlogginglib;

import org.apache.logging.log4j.Logger;

import java.util.List;

public interface ICustomLogger {

    /**
     * This should return true if it found errors.
     */
    boolean startupChecks();

    /**
     * This should return true if it found errors at startup.
     * This will be called at startup if it's enabled in the configs,
     * or if you request a non-existent powerTier.
     */
    boolean runtimeChecks();


    /**
     * Return true if this logger doesn't have any runtime checks.
     */
    boolean discardLoggerAfterStartup();

    /**
     * Return your mods logger
     */
    Logger getModLogger();

    /**
     * Return your mods configs, may be employ array
     */
    String[] getConfigs();

    /**
     * Returns a lost of errors found by startupChecks() or runtimeChecks();
     */
    List<String> getErrors();

    /**
     * You should call clear() on your list if your logger has runtime and startup checks
     */
    void clean();

}
