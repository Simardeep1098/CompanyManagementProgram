package itech2306.assignment;

public class IndividualInvestor extends Investor {

    private String _firstName;
    private String _lastName;

    public IndividualInvestor(String firstName, String lastName, int sharesPurchased) {
        super(sharesPurchased, (Void) -> firstName + " " + lastName);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    private void setFirstName(String firstName) {
        _firstName = firstName;
    }

    private void setLastName(String lastName) {
        _lastName = lastName;
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

}
