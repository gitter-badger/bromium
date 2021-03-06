package com.hribol.bromium.common.replay.actions;

import com.hribol.bromium.common.replay.InstanceBasedAutomationResultBuilder;
import com.hribol.bromium.common.synchronization.JSPrecondition;
import com.hribol.bromium.replay.ReplayingState;
import com.hribol.bromium.replay.actions.WebDriverAction;
import com.hribol.bromium.replay.actions.ActionWithJSPrecondition;
import com.hribol.bromium.replay.execution.WebDriverActionExecutionException;
import com.hribol.bromium.core.synchronization.EventSynchronizer;
import com.hribol.bromium.core.synchronization.SynchronizationEvent;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeoutException;

/**
 * Created by hvrigazov on 21.05.17.
 */
public abstract class ActionWithJSPreconditionBase implements ActionWithJSPrecondition, WebDriverAction {

    @Override
    public void execute(WebDriver driver, ReplayingState replayingState, EventSynchronizer eventSynchronizer) {
        String hashCodeToWaitFor = String.valueOf(getJSEventToWaitFor().hashCode());
        SynchronizationEvent synchronizationEvent = new JSPrecondition(hashCodeToWaitFor, eventSynchronizer, replayingState);
        try {
            eventSynchronizer.awaitUntil(synchronizationEvent);
        } catch (InterruptedException | TimeoutException e) {
            throw new WebDriverActionExecutionException("Exception while awaiting JS precondition", e, new InstanceBasedAutomationResultBuilder());
        }

        executeAfterJSPreconditionHasBeenSatisfied(driver, replayingState);
    }

    public abstract void executeAfterJSPreconditionHasBeenSatisfied(WebDriver driver, ReplayingState replayingState);

}
