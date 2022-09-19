package srki2k.tweakedlib.util.errorlogging;

import java.util.List;

public interface ICustomLogger {

    boolean doCustomCheck();

    boolean handleRuntimeErrors();

    String modid();

    String[] getConfigs();

    List<String> getErrors();

    void clean();

}
