package com.hribol.bromium.core.synchronization;

/**
 * Created by hvrigazov on 03.06.17.
 */
public interface SynchronizationEvent {
    String getName();
    boolean isSatisfied();
    void signalizeIsDone();
}
