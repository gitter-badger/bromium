package com.hribol.bromium.common.generation.record.invocations;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.text.IsEqualIgnoringWhiteSpace.equalToIgnoringWhiteSpace;

/**
 * Created by hvrigazov on 02.07.17.
 */
public class ClickCssSelectorRecorderFunctionInvocationTest {

    @Test
    public void createsJsCodeSuitableForInjection() {
        String cssSelector = "#ico";
        String eventName = "something";
        ClickCssSelectorRecorderFunctionInvocation clickCssSelectorRecorderFunctionInvocation =
                new ClickCssSelectorRecorderFunctionInvocation(cssSelector, eventName);

        assertTrue(equalToIgnoringWhiteSpace("ClickCssSelector(\"#ico\", \"something\")")
        .matches(clickCssSelectorRecorderFunctionInvocation.getJavascriptCode()));
    }
}
