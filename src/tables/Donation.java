package tables;

import java.util.*;

public class Donation {

    private int donationID;
    private double donationAmt;
    private String donationName;
    private Date donationDate;
    int volunteerID;

    public Donation(int donationID, double donationAmt, String donoName, Date donoDate, int volID) {
        this.donationID = donationID;
        this.donationAmt = donationAmt;
        this.donationName = donationName;
        this.donationDate = donationDate;
        this.volunteerID = volID;
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

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public int getVolID() {
        return volunteerID;
    }

    public void setVolID(int volID) {
        this.volunteerID = volID;
    }
}
