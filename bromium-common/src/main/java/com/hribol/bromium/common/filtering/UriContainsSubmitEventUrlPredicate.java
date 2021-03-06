package com.hribol.bromium.common.filtering;

import io.netty.handler.codec.http.HttpRequest;

import java.util.function.Predicate;

import static com.hribol.bromium.core.ConventionConstants.SUBMIT_EVENT_URL;

/**
 * Created by hvrigazov on 02.08.17.
 */
public class UriContainsSubmitEventUrlPredicate implements Predicate<HttpRequest> {
    @Override
    public boolean test(HttpRequest httpRequest) {
        return httpRequest.getUri().contains(SUBMIT_EVENT_URL);
    }
}
