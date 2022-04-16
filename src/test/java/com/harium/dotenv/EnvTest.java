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
        Assert.assertEquals("", Env.get("UNDEFINED"));
    }

    @Test
    public void testAvoidComments() {
       Assert.assertNull(Env.get("#COMMENT"));
    }

    @Test
    public void testAvoidCommentsAfterSpace() {
        Assert.assertNull("", Env.get("#OTHER_COMMENT"));
    }

    @Test
    public void testAvoidCommentsInValue() {
        Assert.assertEquals("", Env.get("EMPTY_VALUE"));
    }

    @Test
    public void testReInit() {
        Env.params.put("KEY1", "val1");
        Assert.assertEquals("val1", Env.get("KEY1"));

        Env.path("/home");
        Assert.assertNull(Env.get("KEY1"));
    }

}