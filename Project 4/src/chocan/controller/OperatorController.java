package chocan.controller;


import chocan.database.CredentialsDatabase;

import java.util.HashMap;
import java.util.Scanner;

public class OperatorController extends AbstractController {

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

        System.out.println("Enter the members ID number");
        key = inputScanner.nextLine();

        userDatabase.addEntry(key, data);

    }

    public void updateMember() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the ID of the member you wish to edit");
        String input = inputScanner.nextLine();
        try {
            HashMap<String, String> data = userDatabase.getEntry(input);

            System.out.println("Enter the members full name");
            input = inputScanner.nextLine();
            data.replace("name", input);

            System.out.println("Enter the members password");
            input = inputScanner.nextLine();
            data.replace("password", input);

            System.out.println("Enter the members street address");
            input = inputScanner.nextLine();
            data.replace("address", input);

            System.out.println("Enter the members zipcode");
            input = inputScanner.nextLine();
            data.replace("zipcode", input);

            System.out.println("Enter the members state abbreviation");
            input = inputScanner.nextLine();
            data.replace("state", input);

        } catch (IllegalArgumentException  e) {
            System.out.println("User not found are you sure the ID is correct.");
        }

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
        key = input + "_provider";

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

    }

    public void updateProvider() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the ID of the provider you wish to edit");
        String input = inputScanner.nextLine();
        try {
            HashMap<String, String> data = userDatabase.getEntry(input);

            System.out.println("Enter the providers full name");
            input = inputScanner.nextLine();
            data.replace("name", input);

            System.out.println("Enter the providers password");
            input = inputScanner.nextLine();
            data.replace("password", input);

            System.out.println("Enter the providers street address");
            input = inputScanner.nextLine();
            data.replace("address", input);

            System.out.println("Enter the providers zipcode");
            input = inputScanner.nextLine();
            data.replace("zipcode", input);

            System.out.println("Enter the providers state abbreviation");
            input = inputScanner.nextLine();
            data.replace("state", input);

        } catch (IllegalArgumentException  e) {
            System.out.println("User not found are you sure the ID is correct.");
        }
    }

    public void deleteProvider() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the ID of the provider you wish to remove");
        String input = inputScanner.nextLine();
        input += "_provider";
        try {
            userDatabase.removeEntry(input);
        } catch (IllegalArgumentException e) {
            System.out.println("User not found are you sure the ID is correct.");
        }
    }

    public void generateProviderDirectory() {

    }
}
