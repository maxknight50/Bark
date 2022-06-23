package tables;

import java.util.*;

public class Donation1 {

    public int donationID;
    public String donationAmt;
    public String donationName;
    public Date donationDate;
    int volunteerID;

    public Donation1(int donationID, String donationAmt, String donoName, Date donoDate, int volID) {
        this.donationID = donationID;
        this.donationAmt = donationAmt;
        this.donationName = donoName;
        this.donationDate = donoDate;
        this.volunteerID = volID;
    }

    public int getDonationID() {
        return donationID;
    }

    public void setDonationID(int donationID) {
        this.donationID = donationID;
    }

    public String getDonationAmt() {
        return donationAmt;
    }

    public void setDonationAmt(String donationAmt) {
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
