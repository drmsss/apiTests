package api;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;


public class RickAndMortyApiSteps {

    public static String printCharacterProperty (String characterName, String characterProperty) {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                //.log().all()
                .when()
                    .get("character/?name="+characterName)
                .then()//.log().all()
                    .extract().response();

        JSONObject characterInfoBody = (JSONObject) new JSONObject(response.getBody().asString()).getJSONArray("results").get(0);


        if (characterProperty=="location") {
            String characterLocationName = characterInfoBody.getJSONObject("location").get("name").toString();
            System.out.println(characterName + " location is " + characterLocationName);
            return characterLocationName;

        } else if (characterProperty=="episode") {
            int characterEpisodesNumber = characterInfoBody.getJSONArray("episode").length();
            String lastEpisodeWithCharacterUrl = characterInfoBody.getJSONArray("episode").get(characterEpisodesNumber-1).toString();
            int lastEpisodeWithCharacterId = Integer.parseInt(lastEpisodeWithCharacterUrl.substring(lastEpisodeWithCharacterUrl.lastIndexOf("/")+1));
            System.out.println("Last episode with " + characterName + " is Episode " + lastEpisodeWithCharacterId);
            return Integer.toString(lastEpisodeWithCharacterId);

        } else {
            String characterPropertyValue = characterInfoBody.get(characterProperty).toString();
            System.out.println(characterName + " " + characterProperty + " is " + characterPropertyValue);
            return characterPropertyValue;

        }

    }

    public static String printCharacterProperty (int IdOfCharacter, String characterProperty) {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                //.log().all()
                .when()
                    .get("character/"+IdOfCharacter)
                .then()//.log().all()
                    .extract().response();

        JSONObject characterInfoBody = (JSONObject) new JSONObject(response.getBody().asString());

        if (characterProperty=="location") {
            String characterLocationName = characterInfoBody.getJSONObject("location").get("name").toString();
            System.out.println(IdOfCharacter + " location is " + characterLocationName);
            return characterLocationName;

        } else if (characterProperty=="episode") {
            int characterEpisodesNumber = characterInfoBody.getJSONArray("episode").length();
            String lastEpisodeWithCharacterUrl = characterInfoBody.getJSONArray("episode").get(characterEpisodesNumber-1).toString();
            int lastEpisodeWithCharacterId = Integer.parseInt(lastEpisodeWithCharacterUrl.substring(lastEpisodeWithCharacterUrl.lastIndexOf("/")+1));
            System.out.println("Last episode with " + IdOfCharacter + " is Episode " + lastEpisodeWithCharacterId);
            return Integer.toString(lastEpisodeWithCharacterId);

        } else {
            String characterPropertyValue = characterInfoBody.get(characterProperty).toString();
            System.out.println(IdOfCharacter + " " + characterProperty + " is " + characterPropertyValue);
            return characterPropertyValue;
        }
    }


    public static int getLastEpisodeId() {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                //.log().all()
                .when()
                    .get("episode")
                .then()//.log().all()
                    .extract().response();

        JSONObject allEpisodesBody = (JSONObject) new JSONObject(response.getBody().asString());
        int lastEpisodeId = Integer.parseInt(allEpisodesBody.getJSONObject("info").get("count").toString());
        return lastEpisodeId;
    }

    public static int getLastCharacterIdFromEpisode(int episodeId) {
        Specification.setSpec(Specification.requestSpec(), Specification.responseSpec());
        Response response = given()
                //.log().all()
                .when()
                    .get("episode/"+episodeId)
                .then()//.log().all()
                    .extract().response();

        JSONArray charactersInLastEpisode = new JSONObject(response.getBody().asString()).getJSONArray("characters");
        int charactersInLastEpisodeNumber = charactersInLastEpisode.length();
        String lastCharacterInLastEpisodeUrl = charactersInLastEpisode.get(charactersInLastEpisodeNumber-1).toString();
        int lastCharacterInLastEpisodeId = Integer.parseInt(lastCharacterInLastEpisodeUrl.substring(lastCharacterInLastEpisodeUrl.lastIndexOf("/")+1));
        return lastCharacterInLastEpisodeId;
    }



}
