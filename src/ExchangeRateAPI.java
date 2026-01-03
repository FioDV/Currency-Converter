
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ExchangeRateAPI {


    private static final String API_KEY = System.getenv("EXCHANGE_API_KEY");
    static String URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public static MyCurrency getRates ()  {

        if (API_KEY == null) {
            throw new RuntimeException("API KEY not found.");
        }

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(URL))
            .GET()
            .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();

    Gson gson = new Gson();
    return gson.fromJson(json,MyCurrency.class);
    }
}

