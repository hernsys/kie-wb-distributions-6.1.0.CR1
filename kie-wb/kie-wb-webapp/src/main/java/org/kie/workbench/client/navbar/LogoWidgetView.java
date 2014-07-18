/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.client.navbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import org.kie.workbench.client.resources.AppResource;
import org.uberfire.client.workbench.widgets.menu.PespectiveContextMenusPresenter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * A stand-alone (i.e. devoid of Workbench dependencies) View
 */
public class LogoWidgetView
        extends Composite
        implements RequiresResize,
                   LogoWidgetPresenter.View {

    interface ViewBinder
            extends
            UiBinder<Panel, LogoWidgetView> {

    }

    private static ViewBinder uiBinder = GWT.create( ViewBinder.class );

    @UiField(provided = true)
    public Image logo;


    @PostConstruct
    public void init() {
        logo = new Image( AppResource.INSTANCE.images().logoP3Small() );
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    @Override
    public void onResize() {
    }

}