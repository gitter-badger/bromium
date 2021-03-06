package com.hribol.bromium.core.config;

import com.hribol.bromium.core.generation.GenerationFunctionInformation;

import java.util.Map;

/**
 * A configuration of a web driver action
 */
public class WebDriverActionConfiguration implements GenerationFunctionInformation {
    private String webDriverActionType;
    private Map<String, ParameterConfiguration> parametersConfiguration;

    /**
     * Gets the type of the action. Could be NOTHING, CUSTOM, etc.
     * @return the type of the action
     */
    public String getWebDriverActionType() {
        return webDriverActionType;
    }

    /**
     * Sets the type of the action. Could be NOTHING, CUSTOM, etc.
     * @param webDriverActionType the type of the action
     */
    public void setWebDriverActionType(String webDriverActionType) {
        this.webDriverActionType = webDriverActionType;
    }

    /**
     * Get the configuration of the parameters, which is a map with names of the parameters
     * as keys and with parameter configurations as the values
     * @return map with names of the parameters
     * as keys and with parameter configurations as the values
     */
    public Map<String, ParameterConfiguration> getParametersConfiguration() {
        return parametersConfiguration;
    }

    /**
     * Set the configuration of the parameters, which is a map with names of the parameters
     * as keys and with parameter configurations as the values
     * @param parametersConfiguration map with names of the parameters
     * as keys and with parameter configurations as the values
     */
    public void setParametersConfiguration(Map<String, ParameterConfiguration> parametersConfiguration) {
        this.parametersConfiguration = parametersConfiguration;
    }

    @Override
    public String getKey() {
        return getWebDriverActionType();
    }
}
