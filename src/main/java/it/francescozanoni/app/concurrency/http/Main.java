package it.francescozanoni.app.concurrency.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// @see https://howtodoinjava.com/java/multi-threading/executorservice-invokeall

/**
 * Extract IBAN codes from several web pages.
 */
public class Main {

    private static final String[] URLS = {
            "https://www.citadele.lt/en/support/online-banking/examples/",
            "https://it.iban.com/struttura",
            "https://transferwise.com/gb/iban/example?all=true#structure_and_examples",
            "https://bank.codes/iban/examples/",
            "http://iban.co.uk/examples.html",
            "https://www.danskebank.ie/en-ie/Personal/Day-to-day/Payments/helpfulInformation/Pages/What-is-IBAN-SWIFT-BIC.aspx",
            "https://www.hl.co.uk/investment-services/currency-service/ways-to-convert-currency/bank-transfer-information/iban-example",
            "https://ssl.ibanrechner.de/sample_accounts.html",
            "https://countrywisecodes.com/morocco/verify-iban-structure/MA64011519000001205000534921"
    };
    private static final String IBAN_PATTERN = "[A-Z]{2} ?[0-9]{2} ?[A-Z0-9 ]{4,}";
    private static final int IBAN_MAX_LENGTH = 34;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(Main.URLS.length);

        List<Callable<String>> taskList = new ArrayList<>();

        // Create all web page download tasks.
        for (String url : Main.URLS) {
            taskList.add(Main.getTask(url));
        }

        // Run all tasks and wait for all to complete.
        List<Future<String>> resultList = executor.invokeAll(taskList);

        // Alternative: throw a java.util.concurrent.CancellationException if it takes more than 5 seconds.
        // List<Future<String>> resultList = executor.invokeAll(taskList, 5, TimeUnit.SECONDS);

        executor.shutdown();

        // @see https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
        Pattern pattern = Pattern.compile(Main.IBAN_PATTERN);

        ArrayList<String> ibans = new ArrayList<>();

        // Extract unique IBANs from all downloaded web pages.
        for (Future<String> future : resultList) {
            String result = future.get();
            Matcher matcher = pattern.matcher(result);
            while (matcher.find()) {
                String iban = Main.getCleanIban(matcher.group());
                if (!ibans.contains(iban)) {
                    ibans.add(iban);
                }
            }
        }

        ibans.sort(String::compareTo);

        System.out.println(ibans);

    }

    /**
     * Create a task that downloads the content of the input URL via HTTP.
     *
     * @param url URL of the web page to download
     * @return download task
     */
    public static Callable<String> getTask(String url) {

        return () -> {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("downloaded " + url);
            return response.body();
        };

    }

    /**
     * Clean an IBAN string to standard form.
     *
     * @param iban raw IBAN code e.g. "DK50 0040 0440 1162 43 "
     * @return standardized IBAN code e.g. "DK5000400440116243"
     * @todo return non-empty strings only if IBAN matches its country pattern
     */
    public static String getCleanIban(String iban) {
        iban = iban.replaceAll("[^0-9A-Z]+", "");
        if (iban.length() > Main.IBAN_MAX_LENGTH) {
            iban = iban.substring(0, Main.IBAN_MAX_LENGTH);
        }
        return iban;
    }

}