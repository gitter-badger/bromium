package com.hribol.spiderman.core.execution.factory;

import com.hribol.spiderman.core.actions.WebDriverAction;
import com.hribol.spiderman.core.parsers.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The base of most {@link WebDriverActionFactory}. It includes built-in {@link WebDriverActionParameterParser}s
 * for built-in actions, and has a method {@link #addCustom()} which subclasses
 * can implement by adding more {@link WebDriverActionParameterParser} to the {@link #parsersRegistry}, thus
 * making it possible to easily add custom actions.
 */
public abstract class WebDriverActionFactoryBase implements WebDriverActionFactory {

    /**
     * The registry which by given webdriverActionType gets the
     * appropriate {@link WebDriverActionParameterParser}.
     */

    private Map<String, WebDriverActionParameterParser> parsersRegistry;

    private final String CLICK_CSS_SELECTOR = "CLICK_CSS_SELECTOR";
    private final String CLICK_CLASS_BY_TEXT = "CLICK_CLASS_BY_TEXT";
    private final String TYPE_TEXT_IN_ELEMENT_FOUND_BY_CSS_SELECTOR = "TYPE_TEXT_IN_ELEMENT_FOUND_BY_CSS_SELECTOR";
    private final String PAGE_LOADING = "PAGE_LOADING";
    private final String baseURL;

    public WebDriverActionFactoryBase(String baseURL) {
        this.baseURL = baseURL;
        parsersRegistry = new HashMap<>();
        addPredefined();
        addCustom();
    }

    private void addPredefined() {
        add(CLICK_CLASS_BY_TEXT, new ClickClassByTextParser());
        add(CLICK_CSS_SELECTOR, new ClickCssSelectorParser());
        add(TYPE_TEXT_IN_ELEMENT_FOUND_BY_CSS_SELECTOR, new TypeTextInElementFoundByCssSelectorParser());
        add(PAGE_LOADING, new PageLoadingParser(baseURL));
    }

    /**
     * If you want to add custom action, you must extend this class and in this method
     * add your new {@link WebDriverActionParameterParser}s using {@link #add(String, WebDriverActionParameterParser)}
     */
    protected abstract void addCustom();

    /**
     * Adds a {@link WebDriverActionParameterParser} to the registry for a given webDriverActionType
     * @param webDriverActionType the web driver action type
     * @param parameterParser the parser that will be added
     */
    protected void add(String webDriverActionType, WebDriverActionParameterParser parameterParser) {
        this.parsersRegistry.put(webDriverActionType, parameterParser);
    }

    @Override
    public WebDriverAction create(String webDriverActionType, Map<String, String> parameters, boolean expectsHttpRequest) {
        return parsersRegistry.get(webDriverActionType).create(parameters, expectsHttpRequest);
    }
}
