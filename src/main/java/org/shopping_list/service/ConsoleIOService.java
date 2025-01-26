package org.shopping_list.service;

import org.shopping_list.api.IOService;

import java.util.Scanner;

public class ConsoleIOService implements IOService {
    private Scanner scanner;

    public ConsoleIOService() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void printLine(String line) {
        System.out.println(line);
    }

    @Override
    public void close() {
        scanner.close();
    }
}
