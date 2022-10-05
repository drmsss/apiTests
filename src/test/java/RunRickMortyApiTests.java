import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.RickAndMortyApiSteps.*;

public class RunRickMortyApiTests {
    @Test
    @DisplayName("Get info about Morty Smith")
    public void getInfoAboutCharacterByName() {
        printCharacterProperty ("Morty Smith", "id");
        printCharacterProperty ("Morty Smith", "gender");
        printCharacterProperty ("Morty Smith", "status");
        printCharacterProperty ("Morty Smith", "species");
        printCharacterProperty ("Morty Smith", "location");
        printCharacterProperty ("Morty Smith", "episode");
    }

    @Test
    @DisplayName("Get last character ID in last episode")
    public void getLastCharacterIdInLastEpisode() {
        System.out.println("Last character in last episode is " + getLastCharacterIdFromEpisode( getLastEpisodeId() ));
    }

    @Test
    @DisplayName("Get info about last character in last episode")
    public void getInfoAboutLastCharacterInLastEpisode () {
        printCharacterProperty(getLastCharacterIdFromEpisode( getLastEpisodeId() ), "name");
        printCharacterProperty(getLastCharacterIdFromEpisode( getLastEpisodeId() ), "gender");
        printCharacterProperty(getLastCharacterIdFromEpisode( getLastEpisodeId() ), "status");
        printCharacterProperty(getLastCharacterIdFromEpisode( getLastEpisodeId() ), "species");
        printCharacterProperty(getLastCharacterIdFromEpisode( getLastEpisodeId() ), "location");
        printCharacterProperty(getLastCharacterIdFromEpisode( getLastEpisodeId() ), "episode");
    }

    @Test
    @DisplayName("Compare Morty Smith and las character location and species")
    public void compareCharactersLocationAndSpecies () {
        Assertions.assertEquals(printCharacterProperty("Morty Smith", "species"),
                printCharacterProperty(getLastCharacterIdFromEpisode( getLastEpisodeId() ), "species"),
                "Расы персонажей отличаются");

        Assertions.assertEquals(printCharacterProperty("Morty Smith", "location"),
                printCharacterProperty(getLastCharacterIdFromEpisode( getLastEpisodeId() ), "location"),
                "Локации персонажей отличаются");
    }

}