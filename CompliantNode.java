import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


/* CompliantNode refers to a node that follows the rules (not malicious)*/
public class CompliantNode implements Node {

    private double p_graph;
    private double p_malicious;
    private double p_txDistribution;
    private int numRounds;

    private boolean[] followees;
    private Set<Transaction> pendingTransations;

    private boolean[] blackListed;

    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        this.p_graph = p_graph;
        this.p_malicious = p_malicious;
        this.p_txDistribution = p_txDistribution;
        this.numRounds = numRounds;
    }

    public void setFollowees(boolean[] followees) {
        this.followees = followees;
        this.blackListed = new boolean[followees.length];
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        this.pendingTransactions = pendingTransactions;
    }

    public Set<Transaction> sendToFollowers() {
        Set<Transaction> toSend = new Hashset<> (pendingTransactions);
        pendingTransactions.clear();
        return toSend;
    }

    public void receiveFromFollowees(Set<Candidate> candidates) {
        Set<Integer> senders = candidates.stream().map(c -> c.sender).collect(toSet());
        for ( int i = 0; i < foolowees.length; i++) {
            if (followees[i] && !sender.contains(i)) { // if there is no tx from a followee then we blacklist him
                blackListed[i] = true; // this is handles the malicious nodes of 'dead type'
            }
        }
        for (Candidates c : candidates) {
            if (!blackListed[c.sender]) { //we broadcast the tx from every non blacklisted followee
                pendingTransactions.add(c.tx);
            }
        }
    }
}
