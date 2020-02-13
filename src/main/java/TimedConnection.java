import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;

public class TimedConnection {
    public static void main(String[] args) {

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                //.proxy()
                //.authenticator()
                .build();

        String website = "https://www.google.com";
        Duration timeout = Duration.ofMillis(20000);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(website))
                .timeout(timeout)
                .header("Content-Type", "application/json")
                .build();

        try {
            long startTime = System.nanoTime();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            long elapsedTime = System.nanoTime() - startTime;

            System.out.println("Status code: " + response.statusCode());
            System.out.println("Elapsed seconds: " + elapsedTime/1_000_000_000.0);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
