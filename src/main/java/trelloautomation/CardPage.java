package trelloautomation;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CardPage extends TrelloApiBase {

    public Response createCard(String cardName, String listId) {
        Response response = given()
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .queryParam("name", cardName)
                .queryParam("idList", listId)
                .contentType("application/json")
                .when()
                .post("cards/");
        return response;
    }

    public Response updateCard(String cardId, String newName) {
        Response response = given()
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .queryParam("name", newName)
                .contentType("application/json")
                .when()
                .put("cards/" + cardId);
        return response;
    }

    public Response deleteCard(String cardId) {
        Response response = given()
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .contentType("application/json")
                .when()
                .delete("cards/" + cardId);
        return response;
    }

    public String getCardId(String boardId, String cardName) {
        Response response = given()
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .when()
                .get("boards/{boardId}/cards", boardId);

        List<Map<String, ?>> cards = response.jsonPath().getList(".");
        for (Map<String, ?> card : cards) {
            if (cardName.equals(card.get("name"))) {
                return (String) card.get("id");
            }
        }
        return null;
    }

}

