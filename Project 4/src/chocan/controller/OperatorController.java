package chocan.controller;


import chocan.database.CredentialsDatabase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class OperatorController extends AbstractController {

    private static final String CHOCAN_DIR_ENV_VAR = "CHOCAN_DIR_ENV_VAR";

    public OperatorController(CredentialsDatabase userDatabase) {
        super(userDatabase);
    }

    public void addMember() {
        HashMap<String, String> data = new HashMap<>();
        data.put("role", "member");
        Scanner inputScanner = new Scanner(System.in);
        String input;
        String key;

        System.out.println("Enter the members full name");
        input = inputScanner.nextLine();
        data.put("name", input);

        System.out.println("Enter the members password");
        input = inputScanner.nextLine();
        data.put("password", input);

        System.out.println("Enter the members street address");
        input = inputScanner.nextLine();
        data.put("address", input);

        System.out.println("Enter the members zipcode");
        input = inputScanner.nextLine();
        data.put("zipcode", input);

        System.out.println("Enter the members state abbreviation");
        input = inputScanner.nextLine();
        data.put("state", input);

        key = userDatabase.generateUniqueID();
        System.out.println("The User's Unique ID is: " + key);

        userDatabase.addEntry(key, data);

        inputScanner.close();
    }

    public void updateMember() {
        Scanner inputScanner = new Scanner(System.in);
        HashMap<String, String> data = new HashMap<>();
        System.out.println("Enter the ID of the member you wish to edit");
        String input = inputScanner.nextLine();
        try {
            userDatabase.removeEntry(input);

            String key = input;
            data.put("role", "member");

            System.out.println("Enter the members full name");
            input = inputScanner.nextLine();
            data.put("name", input);

            System.out.println("Enter the members password");
            input = inputScanner.nextLine();
            data.put("password", input);

            System.out.println("Enter the members street address");
            input = inputScanner.nextLine();
            data.put("address", input);

            System.out.println("Enter the members zipcode");
            input = inputScanner.nextLine();
            data.put("zipcode", input);

            System.out.println("Enter the members state abbreviation");
            input = inputScanner.nextLine();
            data.put("state", input);

            userDatabase.addEntry(key, data);

        } catch (IllegalArgumentException  e) {
            System.out.println("User not found are you sure the ID is correct.");
        }

        inputScanner.close();
    }

    public void deleteMember() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the ID of the member you wish to remove");
        String input = inputScanner.nextLine();
        try {
            userDatabase.removeEntry(input);
        } catch (IllegalArgumentException e) {
            System.out.println("User not found are you sure the ID is correct.");
        }

        inputScanner.close();
    }

    public void addProvider() {
        HashMap<String, String> data = new HashMap<>();
        data.put("role", "provider");
        Scanner inputScanner = new Scanner(System.in);
        String input;
        String key;

        System.out.println("Enter the providers full name");
        input = inputScanner.nextLine();
        data.put("name", input);

        System.out.println("Enter the providers password");
        input = inputScanner.nextLine();
        data.put("password", input);

        System.out.println("Enter the providers street address");
        input = inputScanner.nextLine();
        data.put("address", input);

        System.out.println("Enter the providers zipcode");
        input = inputScanner.nextLine();
        data.put("zipcode", input);

        System.out.println("Enter the providers state abbreviation");
        input = inputScanner.nextLine();
        data.put("state", input);

        System.out.println("Enter the providers ID number");
        input = inputScanner.nextLine();

        key = userDatabase.generateUniqueID();
        System.out.println("The User's Unique ID is: " + key);

        userDatabase.addEntry(key, data);

        inputScanner.close();
    }

    public void updateProvider() {
        Scanner inputScanner = new Scanner(System.in);
        HashMap<String, String> data = new HashMap<>();
        System.out.println("Enter the ID of the provider you wish to edit");
        String input = inputScanner.nextLine();
        try {
            userDatabase.removeEntry(input);

            String key = input;
            data.put("role", "provider");

            System.out.println("Enter the providers full name");
            input = inputScanner.nextLine();
            data.put("name", input);

            System.out.println("Enter the providers password");
            input = inputScanner.nextLine();
            data.put("password", input);

            System.out.println("Enter the providers street address");
            input = inputScanner.nextLine();
            data.put("address", input);

            System.out.println("Enter the providers zipcode");
            input = inputScanner.nextLine();
            data.put("zipcode", input);

            System.out.println("Enter the providers state abbreviation");
            input = inputScanner.nextLine();
            data.put("state", input);

            userDatabase.addEntry(key, data);

        } catch (IllegalArgumentException  e) {
            System.out.println("User not found are you sure the ID is correct.");
        }

        inputScanner.close();
    }

    public void deleteProvider() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the ID of the provider you wish to remove");
        String input = inputScanner.nextLine();
        try {
            userDatabase.removeEntry(input);
        } catch (IllegalArgumentException e) {
            System.out.println("User not found are you sure the ID is correct.");
        }
        inputScanner.close();
    }

    public void generateProviderDirectory() {
        Path pdPath;
        if(System.getProperty(CHOCAN_DIR_ENV_VAR) == null) {
            pdPath = Path.of(System.getProperty("java.io.tmpdir"));
        } else {
            pdPath = Path.of(System.getProperty(CHOCAN_DIR_ENV_VAR));
        }

        pdPath = pdPath.resolve("chocan").resolve("providerDirectory.txt");

        File deleteFile = new File(pdPath.toString());
        deleteFile.delete();

        try (FileWriter fstream = new FileWriter(pdPath.toString(), true); BufferedWriter writer = new BufferedWriter(fstream)) {
            writer.write("Service ID: 121110\n");
            writer.write("Service Name: Basic Therapy\n");
            writer.write("Fee: $50\n");

            writer.write("Service ID: 131211\n");
            writer.write("Service Name: Extensive Therapy\n");
            writer.write("Fee: $100\n");

            writer.write("Service ID: 141312\n");
            writer.write("Service Name: Complete Therapy\n");
            writer.write("Fee: $200\n");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }
}
