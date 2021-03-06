package com.hribol.bromium.common.generation.record;

import com.google.inject.Inject;
import com.hribol.bromium.common.generation.common.FunctionRegistryBase;
import com.hribol.bromium.common.generation.helper.NameWebDriverActionConfiguration;
import com.hribol.bromium.common.generation.record.invocations.RecorderFunctionInvocation;
import com.hribol.bromium.core.config.WebDriverActionConfiguration;

/**
 * Created by hvrigazov on 07.06.17.
 */
public class RecorderFunctionRegistry extends FunctionRegistryBase<
        NameWebDriverActionConfiguration,
        RecorderFunctionInvocation,
        WebDriverActionConfiguration> {

    @Inject
    public RecorderFunctionRegistry(BaseRecorderFunctionFactory recorderFunctionFactory) {
        super(recorderFunctionFactory);
    }

}
