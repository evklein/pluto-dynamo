package db_testing.pluto.hasherr.com.db_testing;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;

@DynamoDBTable(tableName = "Users")
public class User {
    private String username;
    private String fullName;
    private String password;
    private String checkingsAccountID;
    private String savingsAccountID;
    private double checkingsBalance;
    private double savingsBalance;

    @DynamoDBHashKey(attributeName = "user_name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBIndexHashKey(attributeName = "name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDBAttribute(attributeName = "checkings_account_id")
    public String getCheckingsAccountID() {
        return checkingsAccountID;
    }

    public void setCheckingsAccountID(String checkingsAccountID) {
        this.checkingsAccountID = checkingsAccountID;
    }

    @DynamoDBAttribute(attributeName = "savings_account_id")
    public String getSavingsAccountID() {
        return savingsAccountID;
    }

    public void setSavingsAccountID(String savingsAccountID) {
        this.savingsAccountID = savingsAccountID;
    }

    @DynamoDBAttribute(attributeName = "checkings_balance")
    public double getCheckingsBalance() {
        return checkingsBalance;
    }

    public void setCheckingsBalance(double checkingsBalance) {
        this.checkingsBalance = checkingsBalance;
    }

    @DynamoDBAttribute(attributeName = "savings_balance")
    public double getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }
}