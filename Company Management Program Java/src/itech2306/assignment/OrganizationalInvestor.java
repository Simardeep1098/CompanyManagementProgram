package itech2306.assignment;

public class OrganizationalInvestor extends Investor {
    private String _name;
    private String _abn;
    private String _authorizedRepresentative;
    private String _contactPhoneNumber;

    public OrganizationalInvestor(String name, String abn, String authorizedRepresentative, String contactPhoneNumber, int sharesPurchased) {
        super(sharesPurchased, (Void) -> name + " " + abn + " " + authorizedRepresentative + " " + contactPhoneNumber);
        setName(name);
        setAbn(abn);
        setAuthorizedRepresentative(authorizedRepresentative);
        setContactPhoneNumber(contactPhoneNumber);
    }

    public String getName() {
        return _name;
    }

    public String getAbn() {
        return _abn;
    }

    public String getAuthorizedRepresentative() {
        return _authorizedRepresentative;
    }

    public String getContactPhoneNumber() {
        return _contactPhoneNumber;
    }

    private void setName(String name) {
        _name = name;
    }

    private void setAbn(String abn) {
        _abn = abn;
    }

    private void setAuthorizedRepresentative(String authorizedRepresentative) {
        _authorizedRepresentative = authorizedRepresentative;
    }

    private void setContactPhoneNumber(String contactPhoneNumber) {
        _contactPhoneNumber = contactPhoneNumber;
    }

    public String getDetails() {
        return "Name: " + getName() + "\nABN: " + getAbn() + "\nAuthorized Representative: " + getAuthorizedRepresentative() + "\nContact Phone Number: " + getContactPhoneNumber() + "\nShares Purchased: " + getSharesPurchased();
    }
}