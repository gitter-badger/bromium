package com.hribol.bromium.common.replay.actions;

import com.hribol.bromium.core.synchronization.EventSynchronizer;
import com.hribol.bromium.replay.ReplayingState;
import com.hribol.bromium.replay.actions.WebDriverAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by hvrigazov on 18.05.17.
 */
public class TextOfElementFoundByCssSelectorToBe implements WebDriverAction {

    private final String cssSelector;
    private final String text;
    private final String eventName;
    private final Integer timeout;

    public TextOfElementFoundByCssSelectorToBe(String cssSelector, String text, String eventName, Integer timeout) {
        this.cssSelector = cssSelector;
        this.text = text;
        this.eventName = eventName;
        this.timeout = timeout;
    }

    @Override
    public void execute(WebDriver driver, ReplayingState state, EventSynchronizer eventSynchronizer) {
        By by = By.cssSelector(cssSelector);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.textToBe(by, text));
    }

    @Override
    public String getName() {
        return eventName;
    }

    @Override
    public boolean expectsHttpRequest() {
        return false;
    }
}
