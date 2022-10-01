package srki2k.tweakedlib.api.logging.errorlogginglib;

import org.apache.logging.log4j.Logger;

import java.util.List;

public interface ICustomLogger {

    boolean startupChecks();

    boolean runtimeChecks();

    boolean discardLoggerAfterStartup();

    Logger getModLogger();

    String[] getConfigs();

    List<String> getErrors();

    void clean();

}
