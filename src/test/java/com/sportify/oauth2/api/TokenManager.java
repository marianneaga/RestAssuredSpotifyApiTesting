package com.sportify.oauth2.api;

import com.sportify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.sportify.oauth2.api.SpecBuilder.getResponseSpecification;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String accessToken;
    private static Instant expiryTime;

    public synchronized static String getToken() {
        try {
            if (accessToken == null || Instant.now().isAfter(expiryTime)) {
                System.out.println("Renewing token");
                Response response = renewToken();
                accessToken = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else {
                System.out.println("Token is good to use:");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get token!");
        }
        return accessToken;
    }

    private static Response renewToken() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        Response response = RestResource.postAccount(formParams);
        if (response.statusCode() != 200) {
            throw new RuntimeException("Renew token failed");
        }
        return response;
    }
}
