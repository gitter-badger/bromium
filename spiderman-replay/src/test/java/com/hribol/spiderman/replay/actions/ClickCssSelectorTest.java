package com.hribol.spiderman.replay.actions;

import com.hribol.spiderman.replay.filters.ReplayFiltersFacade;
import com.hribol.spiderman.replay.actions.ClickCssSelector;
import com.hribol.spiderman.replay.actions.WebDriverAction;
import com.hribol.spiderman.replay.filters.ReplayRequestFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by hvrigazov on 07.05.17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        ReplayRequestFilter.class,
        Object.class,
        ReplayFiltersFacade.class,
        ClickCssSelector.class
})
public class ClickCssSelectorTest {

    @Test
    public void canClickIfThereAreSuitableElements() throws InterruptedException {
        WebElement webElement = mock(WebElement.class);
        String cssSelector = ".test-element";
        String eventName = "CLICK_SOMETHING";
        boolean expectsHttpRequest = false;

        By elementLocator = By.cssSelector(cssSelector);
        WebDriver webDriver = mock(WebDriver.class);
        when(webDriver.findElement(elementLocator)).thenReturn(webElement);

        ReplayRequestFilter replayRequestFilter = PowerMockito.mock(ReplayRequestFilter.class);
        PowerMockito.doNothing().when(replayRequestFilter).wait();

        ReplayFiltersFacade facade = mock(ReplayFiltersFacade.class);
        when(facade.getRequestFilter()).thenReturn(replayRequestFilter);

        WebDriverAction action = new ClickCssSelector(cssSelector, eventName, expectsHttpRequest);

        action.execute(webDriver, facade);

        verify(webElement).click();
        assertEquals(eventName, action.getName());
        assertEquals(expectsHttpRequest, action.expectsHttpRequest());

    }
}
