package itech2306.assignment;

import java.util.function.Function;

public class Investor {
    private int _sharesPurchased = 0;
    private Boolean _voted = false;
    private Function<Void, String> _getFullName;

    public Investor(int sharesPurchased, Function<Void, String> getFullName) {
        setSharesPurchased(sharesPurchased);
        _getFullName = getFullName;
    }

    public int getSharesPurchased() {
        return _sharesPurchased;
    }

    public Boolean getHasVoted() {
        return _voted;
    }

    public String getFullName() {
        return _getFullName.apply(null);
    }

    private void setSharesPurchased(int sharesPurchased) {
        _sharesPurchased = sharesPurchased;
    }

    public void setHasVoted(Boolean voted) {
        _voted = voted;
    }

    public enum InvestorType {
        INDIVIDUAL,
        ORGANIZATIONAL
    }
}
