package com.wits.witssrcconnect.managers;

import org.junit.Test;

import static org.junit.Assert.*;

public class SecurityManagerTest {

    @Test
    public void SHA1() {
        String expected = "dc724af18fbdd4e59189f5fe768a5f8311527050";
        String actual = SecurityManager.SHA1("testing");
        assertEquals(expected, actual);
    }
}