package itech2306.assignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import itech2306.assignment.Company;
import itech2306.assignment.IndividualInvestor;
import itech2306.assignment.Investor;
import itech2306.assignment.OrganizationalInvestor;
import itech2306.assignment.Votes;


public class DomainHelper {
    private Scanner scanner;
    private ArrayList<Company> companies = new ArrayList<Company>();
    private static int companyCount;
    
    public DomainHelper() {
        scanner = new Scanner(System.in);
    }
    
    // This method is used to display the main menu
    public void enterCompanyDetails() {
            System.out.println("Enter Company Details");

            System.out.print("Company Name: ");
            //check for empty string || null || company name already exists
            String companyName = scanner.nextLine();
            while (companyName == null || companyName.isEmpty() || companyExists(companyName)) {
                if (companyName == null || companyName.isEmpty()) {
                    System.out.println("Company name cannot be empty.");
                } else {
                    System.out.println("Company name already exists.");
                }
                System.out.print("Company Name: ");
                companyName = scanner.nextLine();
            }

            int sharesAlreadyIssued = 0;
            while (sharesAlreadyIssued <= 0) {
                try {
                    System.out.print("Number of Shares Already Issued: ");
                    sharesAlreadyIssued = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (sharesAlreadyIssued <= 0) {
                        System.out.println("Number of shares already issued must be greater than 0.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Consume invalid input
                }
            }
            
            System.out.print("First Shareholder Name: ");
            String firstShareholderName = scanner.nextLine();
            //check for integer || empty string || null
            while (firstShareholderName == null || firstShareholderName.isEmpty() || isInteger(firstShareholderName)) {
                if (firstShareholderName == null || firstShareholderName.isEmpty()) {
                    System.out.println("Shareholder name cannot be empty.");
                } else {
                    System.out.println("Shareholder name cannot be a number.");
                }
                System.out.print("First Shareholder Name: ");
                firstShareholderName = scanner.nextLine();
            }
            
            int totalNewShares = 0;
            while (totalNewShares <= 0) {
                try {
                    System.out.print("Total Number of New Shares: ");
                    totalNewShares = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    
                    if (totalNewShares <= 0) {
                        System.out.println("Total number of new shares must be greater than 0.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Consume invalid input
                }
            }
            
            double pricePerShare = 0.0;
            while (pricePerShare <= 0) {
                try {
                    System.out.print("Price per Share: ");
                    pricePerShare = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    
                    if (pricePerShare <= 0) {
                        System.out.println("Price per share must be greater than 0.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Consume invalid input
                }
            }
            
            int minSharesForInvestor = 0;
            while (minSharesForInvestor <= 0) {
                try {
                    System.out.print("Minimum Number of New Shares for Initial Investor: ");
                    minSharesForInvestor = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    
                    if (minSharesForInvestor <= 0) {
                        System.out.println("Minimum number of new shares for initial investor must be greater than 0.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Consume invalid input
                }
            }
            
            int maxSharesForInvestor = 0;
            while (maxSharesForInvestor <= minSharesForInvestor) {
                try {
            System.out.print("Maximum Number of New Shares for Initial Investor: ");
            maxSharesForInvestor = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
            if (maxSharesForInvestor <= minSharesForInvestor) {
                System.out.println("Maximum number of new shares for initial investor must be greater than the minimum number of shares.");
            }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        // Create a new Company object
        Company company = new Company(companyName, sharesAlreadyIssued, firstShareholderName, totalNewShares,
        pricePerShare, minSharesForInvestor, maxSharesForInvestor);

        // Add the company to the list of companies
        try{
            companies.add(company);
            companyCount++;
        } catch (NullPointerException e) {
            System.out.println("Company name cannot be empty.");
        }
        
        System.out.println("Company details recorded successfully.");
    }

    // This method is used to display the list of all companies
    public void displayCompanyList() {
        if (companyCount == 0) {
            System.out.println("No companies added yet.");
        } else {
            System.out.println("\u001B[32m");
            System.out.println("List of Companies");
            System.out.println();
            for (int i = 0; i < companyCount; i++) {
                Company company = companies.get(i);
                System.out.println((i + 1) + ". " + company.getName() + " [" + company.getSharesAlreadyIssued()
                        + " shares issued]");
            }
            System.out.println("\u001B[37m");
        }
    }
    
    // This method is used to enter an investor's details and allocate shares to them for a company
    public void enterInvestorDetails(Investor.InvestorType investorType) {
        if (companyCount == 0) {
            System.out.println();
            System.out.println("\u001B[31m No companies added yet.\u001B[0m");
            return;
        }
        
        // Display the list of companies
        displayCompanyList();

        System.out.print("Enter the number of the company you want to invest in: ");
        
        //create a while loops if the user enters a number that is not in the list or a string
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume invalid input
        }
        
        int companyNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        if (companyNumber < 1 || companyNumber > companyCount) {
            System.out.println("Invalid company number. Please try again.");
            return;
        }
        
        Company company = companies.get(companyNumber - 1);
        int availableShares = company.getSharesAlreadyIssued();
        
        if (availableShares < company.getMinSharesForInvestor()) {
            System.out.println("No shares available for this company.");
            return;
        }

        double pricePerShare = company.getPricePerShare();
        int minShares = company.getMinSharesForInvestor();
        int maxShares = Math.min(company.getMaxSharesForInvestor(), availableShares);
        
        System.out.println("Share Offer:");
        System.out.println("Price per Share: " + pricePerShare);
        System.out.println("Minimum Number of Shares: " + minShares);
        System.out.println("Maximum Number of Shares: " + maxShares);
        
        
        int sharesToBuy = 0;
        while (sharesToBuy < minShares || sharesToBuy > maxShares) {
            try {
                System.out.print("Enter the number of shares you want to obtain: ");
                sharesToBuy = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                
                if (sharesToBuy < minShares || sharesToBuy > maxShares) {
                    System.out.println("Invalid number of shares. Please enter a number between the minimum and maximum allowed shares.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        
        Investor investor ;
        if (investorType == Investor.InvestorType.INDIVIDUAL) {
            investor = enterIndividualInvestorDetails(company, minShares, maxShares, sharesToBuy);
        } else {
            investor = enterCompanyInvestorDetails(company, minShares, maxShares, sharesToBuy);
        }
        System.out.println(investor);
        System.out.print("Confirm purchase (Y/N): ");
        String confirm = scanner.nextLine();

        while (!confirm.equalsIgnoreCase("Y") && !confirm.equalsIgnoreCase("N")) {
            System.out.println("Invalid input. Please enter Y or N.");
            confirm = scanner.nextLine();
        }
        
        if (confirm.equalsIgnoreCase("Y")) {
            // Update investor's share allocation
            company.addInvestor(investor, company);
            System.out.println("Shares issued successfully.");
        }else if (confirm.equalsIgnoreCase("N")){
            System.out.println("Shares not issued.");
        } else {
            System.out.println("Invalid input. Please enter Y or N.");
        }
    }
    
    // This method is used to display the list of all investors
    public void displayInvestorList() {
        if (companyCount == 0) {
            System.out.println("No companies added yet.");
            return;
        }
        
        System.out.println("Enter the number of the company to see the investor list:");
        displayCompanyList();
        
        System.out.print("Enter the number of the company: ");
        int companyNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        if (companyNumber < 1 || companyNumber > companyCount) {
            System.out.println("Invalid company number. Please try again.");
            return;
        }

        Company company = companies.get(companyNumber - 1);
        System.out.println("Investors for the company: " + company.getName());

        ArrayList<Investor> investors = company.getInvestor();
        double totalShares = company.getSharesAlreadyIssued();

        if(investors.size() == 0) {
            System.out.println("No investors for this company.");
            return;
        }
        
        for (int i = 0; i < investors.size(); i++) {
            Investor investor = (Investor) investors.get(i);
            double sharesOwned = investor.getSharesPurchased();
            double percentage = (sharesOwned / totalShares) * 100;
            String formattedPercentage = String.format("%.1f", percentage);
            
            System.out.println((i + 1) + ". " + investor.getFullName() + " [" + sharesOwned + " shares, " + formattedPercentage + "%]");
        }
        
        System.out.println("Total shares issued to shareholders: " + totalShares);
    }
    
    // This method is used to declare a dividend for a company
    public void declareDividend() {
        if (companyCount == 0) {
            System.out.println();
            System.out.println("\u001B[31m No companies added yet.\u001B[0m");
            return;
        }
        
        System.out.println("Which company is declaring a dividend?");
        displayCompanyList();
        
        System.out.print("Enter the number of the company: ");
        int companyNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        
        if (companyNumber < 1 || companyNumber > companyCount) {
            System.out.println("Invalid company number. Please try again.");
        }
        
        Company company = companies.get(companyNumber - 1);
        System.out.print("What is the dividend amount per share that " + company.getName() + " is paying to its investors: ");
        double dividendAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        
        // Calculate dividend amounts for each shareholder
        List<Investor> investors = company.getInvestor();
        ArrayList<Double> dividendAmounts = new ArrayList<>();

        for (Investor investor : investors) {
            double sharesOwned = investor.getSharesPurchased();
            double amount = sharesOwned * dividendAmount;
            dividendAmounts.add(amount);
        }
        
        // Display the dividend amounts
        System.out.println("The following amounts will need to be paid to the investors:");
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        
        for (int i = 0; i < investors.size(); i++) {
            Investor investor = investors.get(i);
            double amount = dividendAmounts.get(i);
            String formattedAmount = currencyFormat.format(amount);
            
            System.out.println(formattedAmount + " - " + investor.getFullName());
        }
        
        // Calculate and display the total amount being paid
        double totalAmount = 0;
        for (Double amount : dividendAmounts) {
            totalAmount += amount;
        }
        
        String formattedTotalAmount = currencyFormat.format(totalAmount);
        System.out.println("Total amount being paid: " + formattedTotalAmount);
    }
    
    // This method is used to record a vote for a company
    public Void VotingTopics() {

        if (companyCount == 0) {
            System.out.println();
            System.out.println("\u001B[31m No companies added yet.\u001B[0m");
            return null;
        }

        System.out.println("Set up Vote Topics");
        System.out.println("Please select the company that is holding an AGM:");
        // Display the list of companies
        displayCompanyList();

        //create a while loops if the user enters a number that is not in the list or a string
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume invalid input
        }

        int companyNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (companyNumber < 1 || companyNumber > companyCount) {
            System.out.println("Invalid company number. Please try again.");
            return null;
        }

        // Retrieve the selected company
        Company selectedCompany = companies.get(companyNumber - 1);
        if (selectedCompany == null) {
            System.out.println("Invalid company number. Please try again.");
            return null;
        }

        scanner.nextLine(); // Consume newline character

        System.out.print("How many vote topics are required for the " + selectedCompany.getName() + " AGM? ");
        int numTopics = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        selectedCompany.clearVoteTopics(selectedCompany);
        
        for (int i = 1; i <= numTopics; i++) {
            System.out.print("Please enter the topic for vote " + i + ": ");
            String topic = scanner.nextLine();
            Votes voteTopic = new Votes();
            selectedCompany.addVotes(voteTopic, selectedCompany);
        }
        selectedCompany.setOpenVotes(true);

        System.out.println("The vote topic(s) are now set up ready for shareholders to vote.");
        return null;
    }
    
    
    public void shareHolderInformation() {
        System.out.println("Display Shareholder Information");

        // Check if any companies exist
        if (companyCount == 0) {
            System.out.println();
            System.out.println("\u001B[31m No companies added yet.\u001B[0m");
            return;
        }

        System.out.println("Please select the company:");
        displayCompanyList();
    }


    public void addInvestorsFromFile() {

        if (companyCount == 0) {
        System.out.println();
        System.out.println("\u001B[31mNo companies added yet.\u001B[0m");
        return;
        }   

        // Display the list of companies
        displayCompanyList();

        System.out.print("Enter the number of the company you want to invest in: ");

        // Create a while loop if the user enters a number that is not in the list or a string
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume invalid input
        }

        int companyNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

        while(companyNumber < 1 || companyNumber > companies.size()) {
            System.out.println("Invalid company number. Please try again.");
            companyNumber = scanner.nextInt();
        }

        // Retrieve the selected company
        Company selectedCompany = companies.get(companyNumber - 1);
        while(selectedCompany == null) {
            System.out.println("No company found. Please try again.");
            while (!scanner.hasNextInt()) {

                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
            companyNumber = scanner.nextInt();
        }

        // Ask the user to provide the file name
        System.out.println("Enter the name of the file:");
        String fileName = scanner.nextLine();

        // Create a while loop until a valid file is entered
        while (true) {
            try {
                // Check if the file exists in the root directory of the main application
                Path filePath = Paths.get(fileName);
                if (!Files.exists(filePath)) {
                    throw new IllegalArgumentException("File does not exist. Please try again.");
                }

                // Read the file and process the records
                List<String> records = readRecordsFromFile(filePath);

                // Process each record and add investors to the selected company
                int numRecords = Integer.parseInt(records.get(0));
                int currentIndex = 1;

                for (int i = 0; i < numRecords; i++) {
                    int recordType = Integer.parseInt(records.get(currentIndex));
                    currentIndex++;

                    switch (recordType) {
                        case 1:
                            //check if next record is an integer or string and add investors name or shares accordingly
                            String data = records.get(currentIndex);
                            if (data.matches("\\d+")) {
                                int shares = Integer.parseInt(data);
                                currentIndex++;
                                String investorName = records.get(currentIndex);
                                String firstName = investorName.split(" ")[0];
                                String lastName = investorName.split(" ")[1];
                                currentIndex++;

                                // Create an individual investor object and add it to the selected company
                                IndividualInvestor individualInvestor = new IndividualInvestor(firstName, lastName, shares);
                                selectedCompany.addInvestor(individualInvestor, selectedCompany);
                            } else {
                                String investorName = records.get(currentIndex);
                                String firstName = investorName.split(" ")[0];
                                String lastName = investorName.split(" ")[1];
                                currentIndex++;
                                int shares = Integer.parseInt(records.get(currentIndex));
                                currentIndex++;

                                // Create an individual investor object and add it to the selected company
                                IndividualInvestor individualInvestor = new IndividualInvestor(firstName, lastName, shares);
                                selectedCompany.addInvestor(individualInvestor, selectedCompany);
                            }
                            break;
                        case 2:
                            int orgShares = Integer.parseInt(records.get(currentIndex));
                            currentIndex++;
                            String orgName = records.get(currentIndex);
                            currentIndex++;
                            String abn = records.get(currentIndex);
                            currentIndex++;
                            String contactPerson = records.get(currentIndex);
                            currentIndex++;
                            String contactPhone = records.get(currentIndex);
                            currentIndex++;

                            // Create an organizational investor object and add it to the selected company
                            OrganizationalInvestor orgInvestor = new OrganizationalInvestor(orgName, abn, contactPerson, contactPhone, orgShares);
                            selectedCompany.addInvestor(orgInvestor, selectedCompany);
                            break;
                        default:
                            System.out.println("Invalid record type");
                            break;
                    }
                }

                // Exit the loop if the file is processed successfully
                System.out.println();
                System.out.println("\u001B[32m");
                System.out.println("Numbers of investors added: " + numRecords);
                System.out.println("\u001B[37m");
                System.out.println();
                
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing file records: " + e.getMessage());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Enter the name of the file:");
                    fileName = scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Error reading file: " + e.getMessage());
                    break;
                }
            }
    }
    
    private List<String> readRecordsFromFile(Path fileName) {
        List<String> records = new ArrayList<>();

        try{
            records = Files.readAllLines(fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return records;
    }

    private Investor enterIndividualInvestorDetails(Company company, int minShares, int maxShares, int sharesToBuy) {
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        
        IndividualInvestor investor = new IndividualInvestor(firstName, lastName, sharesToBuy);
        return investor;
    }
    
    private Investor enterCompanyInvestorDetails(Company company, int minShares, int maxShares, int sharesToBuy) {

        System.out.print("Enter the name of the organization: ");
        String name = scanner.nextLine();

        System.out.print("Enter the ABN of the organization: ");
        String abn = scanner.nextLine();

        System.out.print("Enter the name of the authorized representative: ");
        String authorizedRepresentative = scanner.nextLine();

        System.out.print("Enter the contact phone number: ");
        String contactPhoneNumber = scanner.nextLine();

        OrganizationalInvestor investor = new OrganizationalInvestor(name, abn, authorizedRepresentative,
                contactPhoneNumber, sharesToBuy);
        return investor;
    }
    
    private Boolean companyExists(String c) {
        for (Company company : companies) {
            if (company.getName().equals(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
