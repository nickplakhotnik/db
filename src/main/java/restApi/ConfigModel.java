package restApi;

import aquality.selenium.core.utilities.JsonSettingsFile;

public class ConfigModel {

    private String url;
    private String env;

    public ConfigModel(JsonSettingsFile jsonSettingsFile) {
        url = jsonSettingsFile.getValue("/url").toString();
        env = jsonSettingsFile.getValue("/env").toString();
    }

    public String getUrl() {
        return url;
    }

    public String getEnv() {
        return env;
    }
}
