package com.harium.dotenv;

import org.junit.Assert;
import org.junit.Test;

public class EnvTest {

    @Test
    public void testLoadParams() {
        Assert.assertEquals("WORLD", Env.get("HELLO"));
    }

    @Test
    public void testEmptyParams() {
        Assert.assertNull(Env.get("UNDEFINED"));
    }

    @Test
    public void testAvoidComments() {
       Assert.assertNull(Env.get("#COMMENT"));
    }

}