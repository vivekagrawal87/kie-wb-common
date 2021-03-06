/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.bpmn.backend.converters.tostunner.properties;

import java.util.Optional;

import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.di.BPMNPlane;
import org.kie.workbench.common.stunner.bpmn.backend.converters.customproperties.CustomElement;
import org.kie.workbench.common.stunner.bpmn.backend.converters.tostunner.DefinitionResolver;
import org.kie.workbench.common.stunner.bpmn.definition.property.simulation.SimulationSet;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.ScriptTypeValue;

public class ScriptTaskPropertyReader extends TaskPropertyReader {

    private final ScriptTask task;

    public ScriptTaskPropertyReader(ScriptTask task, BPMNPlane plane, DefinitionResolver definitionResolver) {
        super(task, plane, definitionResolver);
        this.task = task;
    }

    public ScriptTypeValue getScript() {
        return new ScriptTypeValue(
                Scripts.scriptLanguageFromUri(task.getScriptFormat()),
                Optional.ofNullable(task.getScript()).orElse(null)
        );
    }

    public SimulationSet getSimulationSet() {
        return definitionResolver.resolveSimulationParameters(task.getId())
                .map(SimulationSets::of)
                .orElse(new SimulationSet());
    }

    public boolean isAsync() {
        return CustomElement.async.of(element).get();
    }
}
