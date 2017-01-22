package db_testing.pluto.hasherr.com.db_testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-west-2:7267bf4e-7197-42f4-b411-a71af21233a6", // Identity Pool ID
                Regions.US_WEST_2 // Region
        );

        DynamoDBTool dbTool = new DynamoDBTool(credentialsProvider);

        // Testing User Log-In
//        Log.wtf("Expected Result: True | Actual Result", Boolean.toString(dbTool.logInUser("s_javed", "password123")));
//        Log.wtf("Expected Result: False | Actual Result", Boolean.toString(dbTool.logInUser("s_javed", "password125")));

        // Testing getUser
//        Log.wtf("Expected Result: Sam Javed | Actual Result", dbTool.getUser("s_javed").getFullName());
//        Log.wtf("Expected Result: NULL out/failure | Actual Result", dbTool.getUser("blaaa").getFullName());

//        // Testing saveUser
//        Log.wtf("Expected Result: Sam's things change.", ""); // Update Test
//        User user = new User();
//        user.setUsername("s_javed");
//        user.setFullName("Sam Javed");
//        user.setPassword("password123");
//        user.setCheckingsAccountID("PLACEHOLDER");
//        user.setSavingsAccountID("LINK-TO-SAM");
//        user.setCheckingsBalance(110.36);
//        user.setSavingsBalance(130.70);
//        dbTool.saveUser(user);

        // Testing getAllBoxesOfAccount
//        ArrayList<Box> accountBoxes = dbTool.getAllBoxesOfAccount("LINK-TO-SAM"); // Get all of Sam's boxes.
//        for (Box b : accountBoxes)
//            Log.wtf(b.getTitle(), Double.toString(b.getBalance()));
    }
}
