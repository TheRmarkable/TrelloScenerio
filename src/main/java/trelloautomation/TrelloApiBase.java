package trelloautomation;
import configs.ConfigLoader;
import io.restassured.RestAssured;
public class TrelloApiBase {

    static ConfigLoader configLoader = new ConfigLoader();
    static String trelloToken = configLoader.getTrelloToken();
    static String trelloKey = configLoader.getTrelloKey();
    protected static final String BASE_URL = "https://trello.com/w/berktestfortestinium";
    protected static final String KEY = trelloKey;
    protected static final String TOKEN = trelloToken;


    static {
        RestAssured.baseURI = BASE_URL;
    }
}

