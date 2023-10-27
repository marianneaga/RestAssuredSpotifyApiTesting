package com.sportify.oauth2.tests;

import com.sportify.oauth2.api.StatusCode;
import com.sportify.oauth2.api.applicationApi.PlaylistApi;
import com.sportify.oauth2.pojo.Playlist;
import com.sportify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.sportify.oauth2.utils.FakerUtils.generateDescription;
import static com.sportify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify OAuth2.0")
@Feature("Playlist API; Positive Scenarios")
public class PlaylistTests {

    @Step
    public static Playlist playlistBuilder(String name, String description, boolean _public) {
        return Playlist.builder()
                .name(name)
                .description(description)
                ._public(_public)
                .build();
    }

    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }
    @Step
    public static void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.getCode()));
    }
    @Story("Create a new playlist")
    @Description("This is a description")
    @Test(description = "Test Case: Create a New Playlist")
    public void createPlaylist() {
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Story("Get a playlist based on playlist ID")
    @Description("This is a description")
    @Test(description = "Test Case: Get a Playlist Based On a Specific ID")
    public void getPlaylist() {
        Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist description", false);
        Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Story("Update a playlist based on playlist ID")
    @Description("This is a description")
    @Test(description = "Test Case: Update a Playlist Based On a Specific ID")
    public void updatePlaylist() {
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.put(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }
}
