package com.vmw.rmqclient.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Author: kunitin
 * Created: 13/12/22
 * Info: credential for OAuth2 App
 **/
@Data
public class OAuthAppProperties {

    @NotNull(message = "OAuth2 clientID can not be null")
    @NotEmpty(message = "OAuth2 clientID can not be empty")
    String clientID;

    @NotNull(message = "OAuth2 clientSecret can not be null")
    @NotEmpty(message = "OAuth2 clientSecret can not be empty")
    String clientSecret;

    OauthClientType grantType;
}
