package trelloautomation;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BoardPage extends TrelloApiBase {

    public Response createBoard(String boardName) {
        return given()
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .queryParam("name", boardName)
                .contentType("application/json")
                .when()
                .post("boards/");
    }

    public Response deleteBoard(String boardId) {
        return given()
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .contentType("application/json")
                .when()
                .delete("boards/" + boardId);
    }

    public String getBoardId(String boardName) {
        Response response = given()
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .when()
                .get("members/me/boards");

        List<Map<String, ?>> boards = response.jsonPath().getList(".");
        for (Map<String, ?> board : boards) {
            if (boardName.equals(board.get("name"))) {
                return (String) board.get("id");
            }
        }
        return null;
    }

}

