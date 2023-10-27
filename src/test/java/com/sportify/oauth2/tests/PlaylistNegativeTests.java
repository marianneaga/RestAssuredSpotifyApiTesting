package com.sportify.oauth2.tests;

import com.sportify.oauth2.api.StatusCode;
import com.sportify.oauth2.api.applicationApi.PlaylistApi;
import com.sportify.oauth2.pojo.Error;
import com.sportify.oauth2.pojo.Playlist;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.sportify.oauth2.utils.FakerUtils.generateDescription;
import static com.sportify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify OAuth2.0")
@Feature("Playlist API; Negative Scenarios")
public class PlaylistNegativeTests {

    @Step
    public void assertError(Error responseError, StatusCode statusCode){
        assertThat(responseError.getInnerError().getStatus(), equalTo(statusCode.getCode()));
        assertThat(responseError.getInnerError().getMessage(), equalTo(statusCode.getMessage()));
    }

    @Story("Create a new playlist with null Name value")
    @Description("This is a description")
    @Test(description = "Negative Test Case: Not able to Create Playlist With a Null Name Value")
    public void notBeAbleToCreatePlaylistWithNullName() {
        Playlist requestPlaylist = PlaylistTests.playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        PlaylistTests.assertStatusCode(response.statusCode(), StatusCode.CODE_400);
        assertError(response.as(Error.class), StatusCode.CODE_400);
    }

    @Story("Create a new playlist with an expired token")
    @Description("This is a description")
    @Test(description = "Negative Test Case: Not able to Create Playlist With An Expired Token")
    public void notBeAbleToCreatePlaylistWithExpiredToken() {
        Playlist requestPlaylist = PlaylistTests.playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist, "123456");
        PlaylistTests.assertStatusCode(response.statusCode(), StatusCode.CODE_401);
        assertError(response.as(Error.class), StatusCode.CODE_401);
    }
}
