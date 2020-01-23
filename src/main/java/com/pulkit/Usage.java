package com.pulkit;

import com.pulkit.cache.CacheClient;

import static java.lang.System.out;

public class Usage {
    public static void main(String[] args) {
        CacheClient cacheClient = new CacheClient();
        cacheClient.createNonRepeatingElementCache();
        out.println("Testing first non repeating implementation");
        testCacheLibrary(cacheClient);

        CacheClient anotherCacheClient = new CacheClient();
        anotherCacheClient.createLRUCache();
        out.println("Testing LRU cache implementation");
        testCacheLibrary(anotherCacheClient);
    }

    private static void testCacheLibrary(CacheClient client) {
        out.println("Trying new test case");
        int[] stream1 = {1, 2, 3, 4, 5, 6, 7, 9};
        testCacheLibrary(stream1, client);

        out.println("Trying new test case");
        int[] stream2 = {1, 2, 3, 4, 3, 6, 2, 1};
        testCacheLibrary(stream2, client);

        out.println("Trying new test case");
        int[] stream3 = {1, 2, 1, 3, 3, 2, 6, 7};
        testCacheLibrary(stream3, client);

        out.println("Trying new test case");
        int[] stream4 = {1, 2, 1, 3, 3, 2, 6, 7, 6};
        testCacheLibrary(stream4, client);

        out.println("Trying new test case");
        int[] stream5 = {1, 1, 3, 3, 3, 6, 6, 7};
        testCacheLibrary(stream5, client);
    }

    private static void testCacheLibrary(int stream[], CacheClient client) {
        for (Integer element : stream) {
            addElement(client, element);
            out.print("Getting element " + client.getElement());
            out.println(" ");
        }
        clearCache(client);
    }

    private static void clearCache(CacheClient client) {
        out.println("========Purging cache========");
        client.clearCache();
    }

    private static void addElement(CacheClient client, Integer element) {
        out.print("Adding " + element);
        out.print(", ");
        client.addElement(element);
    }
}