/*
 * Copyright 2014 JBoss Inc
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
package org.kie.integration.eap.maven.configuration;

/**
 * Represents a configuration module dependencies for the Maven plugin..
 *
 */
public class EAPConfigurationModuleDependency
{
    /**
     * The module name.
     *
     * @parameter
     * @required
     */
    private String name;

    /**
     * The module slot.
     *
     * @parameter
     * @required
     */
    private String slot;

    /**
     * The module dependencies.
     *
     * @parameter
     */
    private String dependencies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getDependencies() {
        return dependencies;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }
}
