/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.internal.artifacts.ivyservice.resolveengine.graph.builder;

import com.google.common.collect.Maps;
import org.gradle.api.artifacts.ModuleIdentifier;

import java.util.Map;

/**
 * Maintains the pending state of all modules seen during graph traversal.
 */
public class PendingDependenciesState {
    private static final PendingDependencies NOT_PENDING = PendingDependencies.notPending();

    private final Map<ModuleIdentifier, PendingDependencies> pendingDependencies = Maps.newHashMap();

    public PendingDependencies getPendingDependencies(ModuleIdentifier module) {
        PendingDependencies pendingDependencies = this.pendingDependencies.get(module);
        if (pendingDependencies == null) {
            pendingDependencies = PendingDependencies.pending();
            this.pendingDependencies.put(module, pendingDependencies);
        }
        return pendingDependencies;
    }

    public PendingDependencies notPending(ModuleIdentifier module) {
        return pendingDependencies.put(module, NOT_PENDING);
    }
}
