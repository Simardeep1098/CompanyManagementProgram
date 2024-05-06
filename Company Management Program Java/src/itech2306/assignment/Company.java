package itech2306.assignment;
import java.util.ArrayList;


public class Company {
    private String name;
    private int sharesAlreadyIssued;
    private String firstShareholderName;
    private int totalNewShares;
    private double pricePerShare;
    private int minSharesForInvestor;
    private int maxSharesForInvestor;
    private Boolean openVotes;
    private ArrayList<Investor> investor = new ArrayList<Investor>();
    private ArrayList<Votes> votes = new ArrayList<Votes>();

    public Company(String name, int sharesAlreadyIssued, String firstShareholderName, int totalNewShares,
            double pricePerShare, int minSharesForInvestor, int maxSharesForInvestor) {
        
        setName(name);
        setSharesAlreadyIssued(sharesAlreadyIssued);
        setFirstShareholderName(firstShareholderName);
        setTotalNewShares(totalNewShares);
        setPricePerShare(pricePerShare);
        setMinSharesForInvestor(minSharesForInvestor);
        setMaxSharesForInvestor(maxSharesForInvestor);
    }

    public String getName() {
        return name;
    }

    public int getSharesAlreadyIssued() {
        return sharesAlreadyIssued;
    }
    
    public String getFirstShareholderName() {
        return firstShareholderName;
    }
    
    public int getTotalNewShares() {
        return totalNewShares;
    }
    
    public double getPricePerShare() {
        return pricePerShare;
    }
    
    public int getMinSharesForInvestor() {
        return minSharesForInvestor;
    }
    
    public int getMaxSharesForInvestor() {
        return maxSharesForInvestor;
    }
    
    public ArrayList<Investor> getInvestor() {
        return investor;
    }

    public ArrayList<Votes> getVotes() {
        return votes;
    }

    public Boolean getOpenVotes() {
        return openVotes;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setSharesAlreadyIssued(int sharesAlreadyIssued) {
        this.sharesAlreadyIssued = sharesAlreadyIssued;
    }

    public void setFirstShareholderName(String firstShareholderName) {
        this.firstShareholderName = firstShareholderName;
    }

    public void setTotalNewShares(int totalNewShares) {
        this.totalNewShares = totalNewShares;
    }

    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public void setMinSharesForInvestor(int minSharesForInvestor) {
        this.minSharesForInvestor = minSharesForInvestor;
    }

    public void setMaxSharesForInvestor(int maxSharesForInvestor) {
        this.maxSharesForInvestor = maxSharesForInvestor;
    }

    public void setInvestor(Investor investor) {
        this.investor.add(investor);
    }


    public void addInvestor(Investor investor, Company company){
        company.setInvestor(investor);
    }

    public void setVotes(Votes votes) {
        this.votes.add(votes);
    }

    public void addVotes(Votes votes, Company company){
        company.setVotes(votes);
    }

    public void clearVoteTopics(Company company) {
        company.votes.clear();
    }
    
    public void setOpenVotes(Boolean openVotes) {
        this.openVotes = openVotes;
    }
}