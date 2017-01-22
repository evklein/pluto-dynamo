package db_testing.pluto.hasherr.com.db_testing;

import android.util.Log;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Evan on 1/21/17.
 */

public class DynamoDBTool {
    private AmazonDynamoDBClient dbClient;
    private DynamoDBMapper mapper;

    public DynamoDBTool(CognitoCachingCredentialsProvider credentialsProvider) {
        dbClient = Region.getRegion(Regions.US_WEST_2).createClient(AmazonDynamoDBClient.class,
                        credentialsProvider, new ClientConfiguration());
        mapper = new DynamoDBMapper(dbClient);
    }

    public boolean logInUser(final String username, final String password) {
        final AtomicBoolean isValidUser = new AtomicBoolean(false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
                PaginatedScanList<User> allUsers = mapper.scan(User.class, scanExpression);

                for (User u : allUsers) {
                    if (u.getUsername().equals(username)) {
                        if (u.getPassword().equals(password)) {
                            isValidUser.set(true);
                        }
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return isValidUser.get();
    }

    public User getUser(final String username) {
        RunnableFuture runnable = new FutureTask(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return mapper.load(User.class, username, new DynamoDBMapperConfig(
                        DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
            }
        });

        new Thread(runnable).start();
        User user = null;
        try {
            user = (User) runnable.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void saveUser(final User user) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mapper.save(user, new DynamoDBMapperConfig(
                        DynamoDBMapperConfig.SaveBehavior.CLOBBER));
            }
        };
        new Thread(runnable).start();
    }

    public ArrayList<Box> getAllBoxesOfAccount(final String accountID) {
        RunnableFuture runnable = new FutureTask(new Callable<ArrayList<Box>>() {
           @Override
            public ArrayList<Box> call() throws Exception {
               ArrayList<Box> accountBoxes = new ArrayList<>();
               DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
               PaginatedScanList<Box> allBoxes = mapper.scan(Box.class, scanExpression);
               for (Box b : allBoxes) {
                   if (b.getAccountID().equals(accountID))
                       accountBoxes.add(b);
               }
               return accountBoxes;
           }
        });
        new Thread(runnable).start();

        ArrayList<Box> accountBoxes = null;
        try {
            accountBoxes = (ArrayList<Box>) runnable.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return accountBoxes;
    }

    public void saveBox(final Box box) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mapper.save(box, new DynamoDBMapperConfig(
                        DynamoDBMapperConfig.SaveBehavior.CLOBBER));
            }
        };
        new Thread(runnable).start();
    }
}
