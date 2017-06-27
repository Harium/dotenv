package com.harium.dotenv;

import org.junit.Assert;
import org.junit.Test;

public class EnvTest {

    @Test
    public void testLoadParams() {
        Assert.assertEquals("WORLD", Env.get("HELLO"));
    }

}