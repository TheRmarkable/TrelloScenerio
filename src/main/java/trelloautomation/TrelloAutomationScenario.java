package trelloautomation;

import io.restassured.response.Response;
import java.util.Random;

public class TrelloAutomationScenario {

    public static void main(String[] args) {
        BoardPage boardPage = new BoardPage();
        CardPage cardPage = new CardPage();
        Random rand = new Random();

        String boardName = "Test Board";
        Response createBoardResponse = boardPage.createBoard(boardName);
        String boardId = createBoardResponse.jsonPath().getString("id");

        String listId = createBoardResponse.jsonPath().getString("idLists[0]");
        Response card1Response = cardPage.createCard("Test Card 1", listId);
        Response card2Response = cardPage.createCard("Test Card 2", listId);

        String card1Id = card1Response.jsonPath().getString("id");
        String card2Id = card2Response.jsonPath().getString("id");

        String[] cardIds = {card1Id, card2Id};
        String randomCardId = cardIds[rand.nextInt(cardIds.length)];
        cardPage.updateCard(randomCardId, "Updated Test Card");

        cardPage.deleteCard(card1Id);
        cardPage.deleteCard(card2Id);

        boardPage.deleteBoard(boardId);

        System.out.println("Scenario completed.");
    }
}

