package com.arcbees.gaestudio.server.email;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.arcbees.gaestudio.server.exception.Utf8Exception;
import com.arcbees.gaestudio.server.velocity.VelocityWrapper;
import com.arcbees.gaestudio.server.velocity.VelocityWrapperFactory;
import com.google.inject.Inject;

public class ConfirmRegistrationEmailBuilder {
    private final static String templateLocation =
            "com/arcbees/gaestudio/server/velocitytemplates/messages/confirmregistration.vm";
    private final VelocityWrapper velocityWrapper;

    @Inject
    ConfirmRegistrationEmailBuilder(VelocityWrapperFactory velocityWrapperFactory) {
        this.velocityWrapper = velocityWrapperFactory.create(templateLocation);
    }

    public String generateBody(String tokenId, String redirectionUri) {
        velocityWrapper.put("redirectionUri", encode(redirectionUri));
        velocityWrapper.put("tokenId", tokenId);

        return velocityWrapper.generate();
    }

    private String encode(String uri) {
        try {
            return URLEncoder.encode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new Utf8Exception();
        }
    }
}
