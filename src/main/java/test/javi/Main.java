package test.javi;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import test.javi.histrix.CommandCache;
import test.javi.histrix.CommandFallback;
import test.javi.histrix.CommandGood;

public class Main {

    public static void main(String [] args){
        commandWorkingProperly();
        commandWithFallback();
        commandCache();
    }

    private static void commandWithFallback() {
        CommandFallback bad = new CommandFallback("goodTest");
        String badResult = bad.execute();
        System.out.println(badResult);
    }

    private static void commandWorkingProperly() {
        CommandGood good = new CommandGood("badTest");
        String goodResult = good.execute();
        System.out.println(goodResult);
    }

    private static void commandCache() {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        CommandCache cache = new CommandCache("cacheTest");
        String cacheResult = cache.execute();
        System.out.println(cacheResult);
        System.out.println("Is cache " + cache.isResponseFromCache());

        CommandCache cache2 = new CommandCache("cacheTest");
        String secondResult = cache2.execute();
        System.out.println(secondResult);
        System.out.println("Is cache " + cache2.isResponseFromCache());

        context.shutdown();

        HystrixRequestContext.initializeContext();

        CommandCache cache3 = new CommandCache("cacheTest");
        String thirdResult = cache3.execute();
        System.out.println(thirdResult);
        System.out.println("Is cache " + cache3.isResponseFromCache());

    }
}
