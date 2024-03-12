package ru.antonsibgatulin;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ru.antonsibgatulin.entity.user.UserData;
import ru.antonsibgatulin.model.api.IApiRequest;
import ru.antonsibgatulin.model.api.IApiRequestAdapter;
import ru.antonsibgatulin.model.api.alone.AloneApiImpl;
import ru.antonsibgatulin.model.api.multiply.MultiplyApiImpl;
import ru.antonsibgatulin.model.user.IUser;
import ru.antonsibgatulin.model.user.UserImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AloneApiRequestTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AloneApiRequestTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws InterruptedException {


        // aloneTest();
        multiplyTest();
    }


    public void aloneTest() throws InterruptedException {
        final Integer[] count = {0};
        IApiRequestAdapter alone = new AloneApiImpl(TimeUnit.SECONDS, 1);

        ExecutorService executor = Executors.newFixedThreadPool(10);


        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    alone.createDocument(App.generateTestDocument(), "signature");

                    synchronized (count[0]) {
                        count[0] += 1;
                    }
                }
            });
            executor.submit(thread);
        }

        executor.shutdown();
        Thread.sleep(12000L);
        assertTrue(count[0] == 10);
    }

    public void multiplyTest() throws InterruptedException {

        final Integer[] count = {0};
        IApiRequest multiply = new MultiplyApiImpl(TimeUnit.SECONDS, 1);

        ExecutorService executor = Executors.newFixedThreadPool(10);


        for (int i = 0; i < 5; i++) {
            IUser iUser = new UserImpl();
            UserData userData = new UserData(String.valueOf(1));
            iUser.setUserData(userData);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    multiply.createDocument(App.generateTestDocument(), "signature", iUser);

                    synchronized (count[0]) {
                        count[0] += 1;
                    }
                }
            });
            executor.submit(thread);
        }


        for (int i = 0; i < 5; i++) {
            IUser iUser = new UserImpl();
            UserData userData = new UserData(String.valueOf(2));
            iUser.setUserData(userData);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    multiply.createDocument(App.generateTestDocument(), "signature", iUser);

                    synchronized (count[0]) {
                        count[0] += 1;
                    }
                }
            });
            executor.submit(thread);
        }

        executor.shutdown();
        Thread.sleep(20000L);
        assertTrue(count[0] == 10);

    }
}