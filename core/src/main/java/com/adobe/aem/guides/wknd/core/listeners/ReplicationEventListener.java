package com.adobe.aem.guides.wknd.core.listeners;

import com.day.cq.replication.ReplicationAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.settings.SlingSettingsService;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.day.cq.commons.Externalizer.AUTHOR;

@Component(service = EventHandler.class, immediate = true, property = {Constants.SERVICE_DESCRIPTION + "=Replication Event Listener ", EventConstants.EVENT_TOPIC + "=" + ReplicationAction.EVENT_TOPIC})
@Designate(ocd = ReplicationEventListener.EventListenerPageActivationListenerConfiguration.class)
public class ReplicationEventListener implements EventHandler {
    @ObjectClassDefinition(name = "Replication Event Listener")
    public @interface EventListenerPageActivationListenerConfiguration {
        @AttributeDefinition(name = "Enabled", description = "Replication Event Listener is enabled", type = AttributeType.BOOLEAN) boolean isEnabled() default true;
    }

    @Reference
    @SuppressWarnings("deprecation")
    private SlingSettingsService slingSettingsService;

    private static final Logger LOG = LoggerFactory.getLogger(ReplicationEventListener.class);
    private boolean enabled = false;

    @Activate
    @Modified
    protected void activate(EventListenerPageActivationListenerConfiguration config) {
        enabled = config.isEnabled();
        LOG.info("Event Handler is enabled::: {}", enabled);
    }

    @Override
    public void handleEvent(Event event) {
        if (enabled && slingSettingsService.getRunModes().contains(AUTHOR)) {
            ReplicationAction action = ReplicationAction.fromEvent(event);
            if (action != null) {
                LOG.info("Replication action {} occured on {} ", action.getType().getName(), action.getPath());
                if (StringUtils.containsIgnoreCase(action.getPath(), "/content-fragments/")) {
                    LOG.info("Content Fragment Activated? ::: YES");
                } else {
                    LOG.info("Content Fragment Activated? ::: NO");
                }
            }
        }
    }
}
