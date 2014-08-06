package org.kie.workbench.client.home;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jbpm.dashboard.renderer.service.DashboardURLBuilder;
import org.kie.workbench.client.resources.i18n.AppConstants;
import org.kie.workbench.common.screens.home.model.HomeModel;
import org.kie.workbench.common.screens.home.model.ModelUtils;
import org.kie.workbench.common.screens.home.model.Section;
import org.kie.workbench.common.screens.home.model.SectionEntry;
import org.kie.workbench.common.services.security.KieWorkbenchACL;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.mvp.Command;

import com.google.common.collect.ImmutableSet;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;

import static org.kie.workbench.client.security.KieWorkbenchFeatures.*;

/**
 * Producer method for the Home Page content
 */
@ApplicationScoped
public class HomeProducer {

    private AppConstants constants = AppConstants.INSTANCE;

    private HomeModel model;

    @Inject
    private PlaceManager placeManager;

    
    public void init() {
    	Window.alert("HomeProducer");
        /*model = new HomeModel( constants.homeTheKnowledgeLifeCycle() );
        if(!isCorrectAsset()){
            placeManager.goTo( "org.kie.workbench.client.perspectives.DroolsAuthoringPerspective" );
        }*/
    }
    
    @Produces
    public HomeModel getModel() {
        return model;
    }

}
