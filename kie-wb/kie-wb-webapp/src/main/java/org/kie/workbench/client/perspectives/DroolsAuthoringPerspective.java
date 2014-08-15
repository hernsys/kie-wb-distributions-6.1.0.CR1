/*
 * Copyright 2013 JBoss by Red Hat.
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
package org.kie.workbench.client.perspectives;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.uberfire.client.annotations.Perspective;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.client.annotations.WorkbenchToolBar;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.PanelType;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.impl.PartDefinitionImpl;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;
import org.uberfire.workbench.model.menu.Menus;
import org.uberfire.workbench.model.toolbar.ToolBar;


@ApplicationScoped
@WorkbenchPerspective(identifier = "org.kie.workbench.client.perspectives.DroolsAuthoringPerspective")
public class DroolsAuthoringPerspective  {


    private static final String KIE_WORKBENCH_EXTENSION = "Kie Workbench Extension";
	private PerspectiveDefinition perspective;
    private Menus menus;
    private ToolBar toolBar;
    
    @PostConstruct
    public void init() {
    }

    @Perspective
    public PerspectiveDefinition getPerspective() {
    	this.perspective = new PerspectiveDefinitionImpl( PanelType.ROOT_LIST );
        this.perspective.setName( KIE_WORKBENCH_EXTENSION );
        perspective.getRoot().addPart(new PartDefinitionImpl( new DefaultPlaceRequest("pepe")));
        return this.perspective;
        
    }

    @WorkbenchMenu
    public Menus getMenus() {
        return this.menus;
    }

    @WorkbenchToolBar
    public ToolBar getToolBar() {
        return this.toolBar;
    }

        
}
