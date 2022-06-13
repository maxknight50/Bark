package tables;

import java.util.*;

public class Donation {

    private int donationID;
    private double donationAmt;
    private String donationName;
    private String donationDate;

    public Donation(int donationID, double donationAmt, String donoName, String donoDate) {
        this.donationID = donationID;
        this.donationAmt = donationAmt;
        this.donationName = donationName;
        this.donationDate = donationDate;
    }

    public int getDonationID() {
        return donationID;
    }

    public void setDonationID(int donationID) {
        this.donationID = donationID;
    }

    public double getDonationAmt() {
        return donationAmt;
    }

    public void setDonationAmt(double donationAmt) {
        this.donationAmt = donationAmt;
    }

    public String getDonationName() {
        return donationName;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }
}
