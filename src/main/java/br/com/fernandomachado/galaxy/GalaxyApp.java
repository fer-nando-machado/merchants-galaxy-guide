package br.com.fernandomachado.galaxy;

import br.com.fernandomachado.galaxy.parser.Parser;
import br.com.fernandomachado.galaxy.parser.regex.simple.SimpleRegexParser;

import java.util.Scanner;

/**
 * Main class.
 */
public class GalaxyApp {

    /**
     * Starts the main execution loop.
     * Reads lines from {@link System#in}, parses them and outputs the results on {@link System#out}.
     * Execution is halted when a blank line is read.
     */
    public static void main(String[] args) {
        Parser parser = new SimpleRegexParser();
        Scanner scanner = new Scanner(System.in);
        for (String input = scanner.nextLine(); !input.isEmpty(); input = scanner.nextLine()) {
            String output = parser.parse(input);
            if (output != null) {
                System.out.println(output);
            }
        }
        scanner.close();
    }


}
