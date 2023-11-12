package chocan.controller;

import chocan.database.AbstractDatabase;
import chocan.database.CredentialsDatabase;

import java.util.HashMap;
import java.util.Scanner;

public class OperatorController extends AbstractController {

    public OperatorController(CredentialsDatabase userDatabase) {
        super(userDatabase);
    }

    private void addMember() {
        HashMap<String, String> data = new HashMap<>();
        data.put("role", "member");
        Scanner inputScanner = new Scanner(System.in);
        String input;
        String key;

        System.out.println("Enter the members full name");
        input = inputScanner.nextLine();
        data.put("name", input);
        key = input + "_member";

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

    }

    private void deleteMember() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the name of the member you wish to remove");
        String input = inputScanner.nextLine();
        input += "_member";
        try {
            userDatabase.removeEntry(input);
        } catch (IllegalArgumentException e) {
            System.out.println("User not found are you sure the name is correct.");
        }
    }

    private void updateMember(String memberName) {

    }

    private void addProvider() {
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

    private void deleteProvider(String providerName) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the name of the provider you wish to remove");
        String input = inputScanner.nextLine();
        input += "_provider";
        try {
            userDatabase.removeEntry(input);
        } catch (IllegalArgumentException e) {
            System.out.println("User not found are you sure the name is correct.");
        }
    }

    private void updateProvider(String providerName) {

    }
}
