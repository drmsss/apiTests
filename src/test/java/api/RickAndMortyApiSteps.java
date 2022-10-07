package api;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;


public class RickAndMortyApiSteps {
    public static JSONObject getCharacterObject(String characterName) {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                .log().all()
                .when()
                    .get("/character/?name="+characterName)
                .then().log().all()
                    .extract().response();

        JSONObject characterObject = (JSONObject) new JSONObject(response.getBody().asString()).getJSONArray("results").get(0);
        return characterObject;
    }

    public static JSONObject getCharacterObject(int characterId) {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                .log().all()
                .when()
                    .get("/character/"+characterId)
                .then().log().all()
                    .extract().response();

        JSONObject characterObject = (JSONObject) new JSONObject(response.getBody().asString());
        return characterObject;
    }


    public static String printCharacterProperty (JSONObject characterObject, String characterProperty) {

        String characterName = characterObject.getString("name");

        if (characterProperty=="location") {
            String characterLocationName = characterObject.getJSONObject("location").get("name").toString();
            System.out.println(characterName + " location is " + characterLocationName);
            return characterLocationName;

        } else if (characterProperty=="episode") {
            int characterEpisodesNumber = characterObject.getJSONArray("episode").length();
            String lastEpisodeWithCharacterUrl = characterObject.getJSONArray("episode").get(characterEpisodesNumber-1).toString();
            int lastEpisodeWithCharacterId = Integer.parseInt(lastEpisodeWithCharacterUrl.substring(lastEpisodeWithCharacterUrl.lastIndexOf("/")+1));
            System.out.println("Last episode with " + characterName + " is Episode " + lastEpisodeWithCharacterId);
            return Integer.toString(lastEpisodeWithCharacterId);

        } else {
            String characterPropertyValue = characterObject.get(characterProperty).toString();
            System.out.println(characterName + " " + characterProperty + " is " + characterPropertyValue);
            return characterPropertyValue;

        }
    }

    public static int getLastEpisodeId() {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                .log().all()
                .when()
                    .get("episode")
                .then().log().all()
                    .extract().response();

        JSONObject allEpisodesBody = (JSONObject) new JSONObject(response.getBody().asString());
        int lastEpisodeId = Integer.parseInt(allEpisodesBody.getJSONObject("info").get("count").toString());
        return lastEpisodeId;
    }

    public static int getLastCharacterIdFromEpisode(int episodeId) {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                .log().all()
                .when()
                    .get("episode/"+episodeId)
                .then().log().all()
                    .extract().response();

        JSONArray charactersInLastEpisode = new JSONObject(response.getBody().asString()).getJSONArray("characters");
        int charactersInLastEpisodeNumber = charactersInLastEpisode.length();
        String lastCharacterInLastEpisodeUrl = charactersInLastEpisode.get(charactersInLastEpisodeNumber-1).toString();
        int lastCharacterInLastEpisodeId = Integer.parseInt(lastCharacterInLastEpisodeUrl.substring(lastCharacterInLastEpisodeUrl.lastIndexOf("/")+1));
        return lastCharacterInLastEpisodeId;
    }



}
