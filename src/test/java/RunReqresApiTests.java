import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static api.ReqresApiSteps.createUser;

public class RunReqresApiTests {
    @Test
    @DisplayName("Create user for Reqres API")
    public void createUserForReqres() throws IOException {
        createUser();
    }
}
