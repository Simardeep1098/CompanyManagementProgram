package itech2306.assignment;

public class MenuHelper {
	private Menu _menu;
    private DomainHelper _domainHelper;

    public MenuHelper(Menu menu, DomainHelper domainHelper) {
        _menu = menu;
        _domainHelper = domainHelper;
    }

    public void displayInvestorMenu() {
        System.out.println("Choose the type of investor:");
        System.out.println("***************************");
        System.out.println("\u001B[32m");
        System.out.println("1. Individual Investor");
        System.out.println("2. Organizational Investor");
        System.out.println("3. Go back to main menu");
        System.out.println("\u001B[37m");
        System.out.print("Enter your choice: ");
        int investorType = _menu.getUserChoice();
        switch (investorType) {
            case 1:
                _domainHelper.enterInvestorDetails(Investor.InvestorType.INDIVIDUAL);
                break;
            case 2:
                _domainHelper.enterInvestorDetails(Investor.InvestorType.ORGANIZATIONAL);
                break;
            case 3:
                // Go back to main menu
                break;
            default:
                System.out.println("\u001B[31m");
                System.out.println("Invalid choice. Please try again.");
                System.out.println("\u001B[37m");
        }
    }

    public void exitProgram() {
        // Exit the program
                    System.out.println("\u001B[31m");
                    System.out.println("Exiting the program...");
                    System.out.println("\u001B[37m");
    }
}
