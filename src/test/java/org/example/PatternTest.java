package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.parse.bean.ParseFactory;
import org.example.web_bazaraki.HtmlUnitParseService;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Unit test for simple App.
 */
public class PatternTest
    extends TestCase
{
    public void testServicesInstances() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            int epoch = 1000;
            while(epoch --> 0){
               var service = HtmlUnitParseService.getInstance();
            }
        });

        Thread thread2 = new Thread(() -> {
            int epoch = 1000;
            while(epoch --> 0){
                var service = HtmlUnitParseService.getInstance();
            }
        });

        Thread thread3 = new Thread(() -> {
            int epoch = 1000;
            while(epoch --> 0){
                var service = HtmlUnitParseService.getInstance();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

    }
    public void testPattern()
    {
        var address = "http://bazaraki.com";
        var patternRes = "*//bazaraki.com";
        AtomicReference<String> out = new AtomicReference<>();
        ParseFactory.fetchPattern(address).ifPresent(out::set);
        assertEquals(patternRes, out.get());
    }
}
