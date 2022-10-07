import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.RickAndMortyApiSteps.*;

public class RunRickMortyApiTests {

    @Test
    @DisplayName("Get info about Morty Smith")
    public void getInfoAboutCharacterByName() {
        JSONObject mortySmithInfo = getCharacterObject("Morty Smith");
        printCharacterProperty(mortySmithInfo, "id");
        printCharacterProperty(mortySmithInfo, "gender");
        printCharacterProperty(mortySmithInfo, "status");
        printCharacterProperty(mortySmithInfo, "species");
        printCharacterProperty(mortySmithInfo, "location");
        printCharacterProperty(mortySmithInfo, "episode");
    }

    @Test
    @DisplayName("Get last character ID in last episode")
    public void getLastCharacterIdInLastEpisode() {
        System.out.println("Last character in last episode is " + getLastCharacterIdFromEpisode( getLastEpisodeId() ));
    }

    @Test
    @DisplayName("Get info about last character in last episode")
    public void getInfoAboutLastCharacterInLastEpisode () {
        JSONObject lastCharacterInLastEpisodeInfo = getCharacterObject(getLastCharacterIdFromEpisode( getLastEpisodeId() ));
        printCharacterProperty(lastCharacterInLastEpisodeInfo, "id");
        printCharacterProperty(lastCharacterInLastEpisodeInfo, "gender");
        printCharacterProperty(lastCharacterInLastEpisodeInfo, "status");
        printCharacterProperty(lastCharacterInLastEpisodeInfo, "species");
        printCharacterProperty(lastCharacterInLastEpisodeInfo, "location");
        printCharacterProperty(lastCharacterInLastEpisodeInfo, "episode");
    }

    @Test
    @DisplayName("Compare two characters location and species")
    public void compareCharactersLocationAndSpecies () {
        JSONObject mortySmithInfo = getCharacterObject("Morty Smith");
        JSONObject lastCharacterInLastEpisodeInfo = getCharacterObject(getLastCharacterIdFromEpisode( getLastEpisodeId() ));

        Assertions.assertEquals(printCharacterProperty(mortySmithInfo, "species"),
                printCharacterProperty(lastCharacterInLastEpisodeInfo, "species"),
                "Расы персонажей отличаются");

        Assertions.assertEquals(printCharacterProperty(mortySmithInfo, "location"),
                printCharacterProperty(lastCharacterInLastEpisodeInfo, "location"),
                "Локации персонажей отличаются");
    }

}