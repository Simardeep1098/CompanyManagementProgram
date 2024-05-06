package itech2306.assignment;


public class MainProgram {

	public static void main(String[] args) {
		Menu menu = new Menu();
		DomainHelper domainHelper = new DomainHelper();
		MenuHelper menuHelper = new MenuHelper(menu, domainHelper);
		int choice;

		do {
			menu.displayMainMenu();
			choice = menu.getUserChoice();

			switch (choice) {
				case 1:
					domainHelper.enterCompanyDetails();
					break;
				case 2:
					domainHelper.displayCompanyList();
					break;
				case 3:
					menuHelper.displayInvestorMenu();
					break;
				case 4:
					domainHelper.displayInvestorList();
					break;
				case 5:
					domainHelper.declareDividend();
					break;
				case 6:
					domainHelper.VotingTopics();
					break;
				case 7:
					domainHelper.shareHolderInformation();
					break;
				case 8:
					domainHelper.addInvestorsFromFile();
					break;
				case 9:
					menuHelper.exitProgram();
					break;
				default:
					System.out.println("\u001B[31m");
					System.out.println("Invalid choice. Please try again.");
					System.out.println("\u001B[37m");
			}

		} while (choice != 11);

		menu.close();
	}
}