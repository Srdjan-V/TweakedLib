package srki2k.tweakedlib.api.logging.errorlogginglib;

import java.util.List;

public interface ICustomLogger {

    boolean doCustomCheck();

    boolean handleRuntimeErrors();

    boolean discardLoggerAfterStartup();

    boolean logErrorToUsersInGameWithCT();

    String getMODID();

    String[] getConfigs();

    List<String> getErrors();

    void clean();

}
