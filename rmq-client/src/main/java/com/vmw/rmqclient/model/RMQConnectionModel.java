package com.vmw.rmqclient.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Author: kunitin
 * Created: 14/12/22
 * Info: Info of RMQ to connect
 **/
@Data
public class RMQConnectionModel {

    @NotNull(message = "RMQ instance URL can not be null")
    @NotEmpty(message = "RMQ instance URL can not be empty")
    String rmqClusterUrl;

    @Max(value = 5672)
    @Min(value = 5671)
    Integer rmqClusterPort;

    OAuthAppProperties oAuth2AppProp;

    private String orgId;
}
