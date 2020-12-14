package it.francescozanoni.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(name = "checksum",
         mixinStandardHelpOptions = true,
         version = "checksum 4.0",
         description = "Prints the checksum (MD5 by default) of a file to STDOUT.")
class MyCommand implements Callable<Integer> {

    @Parameters(index = "0",
                description = "The file whose checksum to calculate.")
    private File file;

    @Option(names = {"-a", "--algorithm"},
            description = "MD5, SHA-1, SHA-256, ...")
    private final String algorithm = "MD5";

    public static void main(String... args) {

        int exitCode = new CommandLine(new MyCommand()).execute(args);

        System.exit(exitCode);

    }

    @Override
    public Integer call() throws Exception {

        byte[] fileContents = Files.readAllBytes(file.toPath());
        byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);

        System.out.printf("%0" + (digest.length * 2) + "x%n", new BigInteger(1, digest));

        return 0;

    }
}