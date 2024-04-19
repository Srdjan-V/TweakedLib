package io.github.srdjanv.tweakedlib.api.logging.errorlogginglib;

import org.apache.logging.log4j.Logger;

import java.util.List;

public interface ICustomLogger {

    /**
     * This should return true if it found errors.
     */
    boolean startupChecks();

    /**
     * This should return true if it found passable errors at runtime.
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
     * Returns a list of errors found by startupChecks() or runtimeChecks();
     */
    List<String> getErrors();

    /**
     * You should call clear() on your list if your logger has runtime and startup checks
     */
    void clean();

}
