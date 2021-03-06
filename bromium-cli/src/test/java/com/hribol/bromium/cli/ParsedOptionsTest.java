package com.hribol.bromium.cli;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.hribol.bromium.cli.ParsedOptions.*;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.remote.BrowserType.CHROME;

/**
 * Created by hvrigazov on 09.07.17.
 */
public class ParsedOptionsTest {

    @Test
    public void correctlyParsesMap() {
        Map<String, Object> opts = new HashMap<>();

        String pathToDriver = "/chromedriver";
        String pathToJs = "/runTest-whatever.js";
        String url = "http://runTest/";
        String browserType = CHROME;
        String outputFile = "output.json";
        Integer timeout = 10;
        String application = "app.json";
        String testCase = "case.json";
        String measurements = "measurements.csv";
        Integer precision = 500;
        Integer screen = 1;

        opts.put(DRIVER, pathToDriver);
        opts.put(JS, pathToJs);
        opts.put(URL, url);
        opts.put(BROWSER, browserType);
        opts.put(OUTPUT, outputFile);
        opts.put(TIMEOUT, String.valueOf(timeout));
        opts.put(APPLICATION, application);
        opts.put(CASE, testCase);
        opts.put(PRECISION, String.valueOf(precision));
        opts.put(SCREEN, String.valueOf(screen));
        opts.put(MEASUREMENTS, measurements);

        ParsedOptions parsedOptions = new ParsedOptions(opts);

        assertEquals(pathToDriver, parsedOptions.getPathToDriver());
        assertEquals(pathToJs, parsedOptions.getPathToJS());
        assertEquals(url, parsedOptions.getBaseUrl());
        assertEquals(browserType, parsedOptions.getBrowserType());
        assertEquals(outputFile, parsedOptions.getOutputFile());
        assertEquals(timeout, parsedOptions.getTimeout());
        assertEquals(application, parsedOptions.getPathToApplicationConfiguration());
        assertEquals(testCase, parsedOptions.getPathToTestCase());
        assertEquals(precision, parsedOptions.getMeasurementsPrecisionMilli());
        assertEquals(screen, parsedOptions.getScreenNumber());
        assertEquals(measurements, parsedOptions.getMeasurements());
    }
}
