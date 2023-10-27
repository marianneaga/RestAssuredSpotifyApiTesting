package com.sportify.oauth2.api.applicationApi;

import com.sportify.oauth2.api.RestResource;
import com.sportify.oauth2.api.Route;
import com.sportify.oauth2.pojo.Playlist;
import com.sportify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.sportify.oauth2.api.TokenManager.getToken;


public class PlaylistApi {

    @Step
    public static Response post(Playlist requestPlaylist) {
        return RestResource.post(Route.USERS + "/" + ConfigLoader.getInstance().getUserId() + Route.PLAYLISTS, getToken(), requestPlaylist);
    }

    @Step
    public static Response post(Playlist requestPlaylist, String token) {
        return RestResource.post(Route.USERS + "/" +ConfigLoader.getInstance().getUserId() + Route.PLAYLISTS, token, requestPlaylist);
    }

    @Step
    public static Response get(String playlistId) {
        return RestResource.get( Route.PLAYLISTS + "/" + playlistId, getToken());
    }

    @Step
    public static Response put(String playlistId, Playlist requestPlaylist) {
        return RestResource.put(Route.PLAYLISTS + "/" + playlistId, getToken(), requestPlaylist);
    }
}
