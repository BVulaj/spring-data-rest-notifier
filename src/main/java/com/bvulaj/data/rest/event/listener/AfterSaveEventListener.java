/**
 * 
 */
package com.bvulaj.data.rest.event.listener;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.vnomicscorp.common.event.exception.UndeliverableEventException;

/**
 * @author Brandon Vulaj
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class AfterSaveEventListener extends AbstractRepositoryEventListener {

	@Autowired
	private EntityLinks entityLinks;
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Override
	protected void onAfterSave(Object entity) {
		Link link = entityLinks.linkToCollectionResource(Object.class);
		try {
			URIBuilder builder = new URIBuilder(link.getHref());
			URI subscribeUri = builder.setPath("/topic" + builder.getPath()).build();
			simpMessagingTemplate.convertAndSend(subscribeUri.toString(), entity);
		} catch (URISyntaxException e) {
			throw new UndeliverableEventException(e);
		}
	}
}