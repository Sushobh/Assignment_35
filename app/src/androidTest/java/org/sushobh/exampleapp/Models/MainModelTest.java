package org.sushobh.exampleapp.Models;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class MainModelTest {


    private MainModel mainModel;

    @Before
    public void setUp() throws Exception {
         mainModel = new MainModel();
    }

    @Test
    public void checkThatDependencyIsIntitializedWorks() throws Exception {
        assertNotNull(mainModel.retrofit);
        assertNotNull(mainModel.appDatabase);
    }

    @Test
    public void testGooglePageFetch() throws Exception {

        mainModel.getGooglePage().getGooglePage("http://google.com").subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                          assertNotNull(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        assertNotNull(throwable);
                    }
                });


        Thread.sleep(10000);
    }

    @Test
    public void checkThatDatabaseWorksFine(){
        mainModel.addDummyUser();
        assertEquals(2, mainModel.getAllUsers().size());

    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme