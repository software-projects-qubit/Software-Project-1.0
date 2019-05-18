package com.wits.witssrcconnect.services;

import android.content.Context;

import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

public class ServerCommunicatorTest {

    private Context c = getTargetContext();
    @Test
    public void updateMessages() {
        try {
            runOnUiThread(()->{
                ServerCommunicator.showLoadingDialog(c);
                ServerCommunicator.updateMessages();
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}