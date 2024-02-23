package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.parse.bean.ParseFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Unit test for simple App.
 */
public class PatternTest
    extends TestCase
{
    public void testPattern()
    {
        var address = "http://bazaraki.com";
        var patternRes = "*//bazaraki.com";
        AtomicReference<String> out = new AtomicReference<>();
        ParseFactory.fetchPattern(address).ifPresent(out::set);
        assertEquals(patternRes, out.get());
    }
}
