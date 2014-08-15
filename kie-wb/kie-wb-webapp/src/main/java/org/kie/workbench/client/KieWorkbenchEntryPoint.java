/*
 * Copyright 2012 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.kie.workbench.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.guvnor.common.services.shared.config.AppConfigService;
import org.guvnor.common.services.shared.config.ApplicationPreferences;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.kie.workbench.client.home.HomeProducer;
import org.kie.workbench.client.resources.i18n.AppConstants;
import org.kie.workbench.common.services.security.KieWorkbenchACL;
import org.kie.workbench.common.services.security.KieWorkbenchPolicy;
import org.kie.workbench.common.services.shared.security.KieWorkbenchSecurityService;
import org.kie.workbench.common.widgets.client.resources.RoundedCornersResource;
import org.uberfire.client.UberFirePreferences;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.mvp.Command;
import org.uberfire.security.Identity;
import org.uberfire.security.Role;
import org.uberfire.workbench.model.menu.MenuFactory;
import org.uberfire.workbench.model.menu.MenuItem;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestBox.DefaultSuggestionDisplay;
import com.google.gwt.user.client.ui.TextBox;


/**
 *
 */
@EntryPoint
public class KieWorkbenchEntryPoint {

    private AppConstants constants = AppConstants.INSTANCE;

    @Inject
    private PlaceManager placeManager;

    @Inject
    public Identity identity;

    @Inject
    private Caller<AppConfigService> appConfigService;

    @Inject
    private KieWorkbenchACL kieACL;

    @Inject
    private Caller<KieWorkbenchSecurityService> kieSecurityService;
    
    @Inject
    private HomeProducer homeProducer;

    private SuggestBox actionText;
    private TextBox textSuggestBox;
    DefaultSuggestionDisplay suggestionDisplay;
    Map<String, String> actions = new HashMap<String, String>();

    @AfterInitialization
    public void startApp() {
        kieSecurityService.call( new RemoteCallback<String>() {
            public void callback( final String str ) {
                KieWorkbenchPolicy policy = new KieWorkbenchPolicy( str );
                kieACL.activatePolicy( policy );
                loadPreferences();
                loadStyles();
                hideLoadingPopup();
                homeProducer.init();
            }
        } ).loadPolicy();
    }

    private void loadPreferences() {
        UberFirePreferences.setProperty( "org.uberfire.client.workbench.widgets.listbar.context.disable", true );
        appConfigService.call( new RemoteCallback<Map<String, String>>() {
            @Override
            public void callback( final Map<String, String> response ) {
                ApplicationPreferences.setUp( response );
            }
        } ).loadPreferences();
    }

    private void loadStyles() {
        //Ensure CSS has been loaded
        //ShowcaseResources.INSTANCE.showcaseCss().ensureInjected();
        RoundedCornersResource.INSTANCE.roundCornersCss().ensureInjected();
    }

    private List<? extends MenuItem> getRoles() {
        final List<MenuItem> result = new ArrayList<MenuItem>( identity.getRoles().size() );
        for ( final Role role : identity.getRoles() ) {
            if ( !role.getName().equals( "IS_REMEMBER_ME" ) ) {
                result.add( MenuFactory.newSimpleItem( constants.Role() + ": " + role.getName() ).endMenu().build().getItems().get( 0 ) );
            }
        }
        result.add( MenuFactory.newSimpleItem( constants.LogOut() ).respondsWith( new Command() {
            @Override
            public void execute() {
                redirect( GWT.getModuleBaseURL() + "uf_logout" );
            }
        } ).endMenu().build().getItems().get( 0 ) );

        return result;
    }

    //Fade out the "Loading application" pop-up
    private void hideLoadingPopup() {
        final Element e = RootPanel.get( "loading" ).getElement();

        new Animation() {
            @Override
            protected void onUpdate( double progress ) {
                e.getStyle().setOpacity( 1.0 - progress );
            }

            @Override
            protected void onComplete() {
                e.getStyle().setVisibility( Style.Visibility.HIDDEN );
            }
        }.run( 500 );
    }

    public static native void redirect( String url )/*-{
        $wnd.location = url;
    }-*/;

}
