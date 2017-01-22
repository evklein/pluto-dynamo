package db_testing.pluto.hasherr.com.db_testing;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "Boxes")
public class Box {
    private String boxID;
    private String accountID;
    private String title;
    private double balance;
    private double weeklyTransferAmount;
    private double goalAmount;

    @DynamoDBHashKey(attributeName = "box_id")
    public String getBoxID() {
        return boxID;
    }

    public void setBoxID(String boxID) {
        this.boxID = boxID;
    }

    @DynamoDBAttribute(attributeName = "account_id")
    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "balance")
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @DynamoDBAttribute(attributeName = "weekly_transfer_amount")
    public double getWeeklyTransferAmount() {
        return weeklyTransferAmount;
    }

    public void setWeeklyTransferAmount(double weeklyTransferAmount) {
        this.weeklyTransferAmount = weeklyTransferAmount;
    }

    @DynamoDBAttribute(attributeName = "goal_amount")
    public double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(double goalAmount) {
        this.goalAmount = goalAmount;
    }
}
