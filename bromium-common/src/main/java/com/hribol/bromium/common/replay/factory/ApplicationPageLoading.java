package com.hribol.bromium.common.replay.factory;

import com.hribol.bromium.common.replay.actions.PageLoading;
import com.hribol.bromium.replay.actions.WebDriverAction;
import com.hribol.bromium.replay.execution.application.ApplicationAction;

import java.util.Optional;

/**
 * Loading a page in an application
 */
public class ApplicationPageLoading implements ApplicationAction {

    private String url;
    private String eventName;

    public ApplicationPageLoading(String url, String eventName) {
        this.url = url;
        this.eventName = eventName;
    }

    @Override
    public Optional<WebDriverAction> getPrecondition() {
        return Optional.empty();
    }

    @Override
    public Optional<WebDriverAction> getWebdriverAction() {
        return Optional.of(new PageLoading(url, eventName));
    }

    @Override
    public Optional<WebDriverAction> getPostcondition() {
        return Optional.empty();
    }
}
