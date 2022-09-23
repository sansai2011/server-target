package com.mysite.core.services.impl;

import com.adobe.target.delivery.v1.model.ChannelType;
import com.adobe.target.delivery.v1.model.Context;
import com.adobe.target.delivery.v1.model.ExecuteRequest;
import com.adobe.target.delivery.v1.model.MboxRequest;
import com.adobe.target.edge.client.Attributes;
import com.adobe.target.edge.client.ClientConfig;
import com.adobe.target.edge.client.TargetClient;
import com.adobe.target.edge.client.model.TargetDeliveryRequest;
import com.mysite.core.services.TargetCallService;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Component(immediate = true, service = TargetCallService.class, property = {"process.label=Takeda Target Call Service",
        Constants.SERVICE_DESCRIPTION + "=Service for Takeda Target Call."})
public class TargetCallServiceImpl implements TargetCallService {

    private static final Logger log = LoggerFactory.getLogger(TargetCallService.class);

    private static final String CLIENT_CODE = "klickincpartner";
    private static final String IMS_ORG_ID = "45A928B75B7D47580A495D31@AdobeOrg";

    public String targetResponse(String mboxId) {
        log.debug("TargetCallService :: #targetResponse :: Start");

        ClientConfig config = ClientConfig.builder()
                .client(CLIENT_CODE)
                .organizationId(IMS_ORG_ID)
                .build();
        TargetClient targetClient = TargetClient.create(config);

        MboxRequest mbox = new MboxRequest().name(mboxId).index(0);
        TargetDeliveryRequest request = TargetDeliveryRequest.builder()
                .context(new Context().channel(ChannelType.WEB))
                .execute(new ExecuteRequest().mboxes(Arrays.asList(mbox)))
                .build();
        Attributes attributes = targetClient.getAttributes(request, mboxId);
        String flag = attributes.getString(mboxId, "flag");
        log.debug("TargetCallService :: #targetResponse :: End");
        return flag;
    }
}
