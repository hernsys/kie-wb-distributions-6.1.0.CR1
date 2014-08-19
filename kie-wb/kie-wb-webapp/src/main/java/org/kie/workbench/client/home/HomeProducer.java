package org.kie.workbench.client.home;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.kie.workbench.client.resources.i18n.AppConstants;
import org.kie.workbench.common.screens.home.model.HomeModel;
import org.uberfire.client.mvp.PlaceManager;

import com.google.gwt.user.client.Timer;



/**
 * Producer method for the Home Page content
 */
@ApplicationScoped
public class HomeProducer {
	//2000 is correct
    private static final int MLS = 1500;

	private AppConstants constants = AppConstants.INSTANCE;

    private HomeModel model;

    @Inject
    private PlaceManager placeManager;
    
    
    public void init() {
    	Timer timer = new Timer() {
            @Override
            public void run() {
            	model = new HomeModel( constants.homeTheKnowledgeLifeCycle() );
                placeManager.goTo( "org.kie.workbench.client.perspectives.DroolsAuthoringPerspective" );
            }
        };
        timer.schedule(MLS);
    }
    
    
    @Produces
    public HomeModel getModel() {
        return model;
    }

}
