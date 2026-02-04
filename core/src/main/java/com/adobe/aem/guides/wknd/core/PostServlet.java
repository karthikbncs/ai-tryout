package com.adobe.aem.guides.wknd.core;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;

@Component(
        service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=Custom POST Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=/bin/wknd/test",
                "sling.servlet.extensions=json"
        }
)
public class PostServlet extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        response.setContentType("application/json");
        response.setStatus(200);
    }
}
