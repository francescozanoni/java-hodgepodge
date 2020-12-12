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

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(Main.URLS.length);

        List<Callable<String>> taskList = new ArrayList<>();

        // Create all web page download tasks.
        for (String url : Main.URLS) {
            taskList.add(Main.getTask(url));
        }

        // Run all tasks.
        List<Future<String>> resultList = executor.invokeAll(taskList);

        executor.shutdown();

        // @see https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
        Pattern pattern = Pattern.compile(Main.IBAN_PATTERN);

        ArrayList<String> ibans = new ArrayList<>();

        // Extract unique IBANs from all downloaded web pages.
        for (Future<String> future : resultList) {
            String result = future.get();
            Matcher matcher = pattern.matcher(result);
            while (matcher.find()) {
                String iban = matcher.group();
                if (!ibans.contains(iban)) {
                    ibans.add(iban);
                }
            }
        }

        System.out.println(ibans);

    }

    public static Callable<String> getTask(String url) {

        return () -> {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("downloaded " + url);
            return response.body();
        };

    }

}