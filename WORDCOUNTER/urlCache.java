package WORDCOUNTER;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Object to retrieve data from a URL. Loads data into cache as necessary.
 */
public class urlCache {
    private static final long CACHE_INVALIDATION_TIME = 60 * 60; //60sec * 60min = 1hr, cache invalidation time
    private Map<String, urlTuple> urlMap;

    public urlCache() {
        this.urlMap = new HashMap<String, urlTuple>();
    }


    //Below are engineering thoughts on synchronization...

    //I choose to make the whole process of getting the url resource synchronized
    //A finer grained alternative might allowing downloads to be parallel, this would involve doing a check to
    //initialize a download at all and then after the download is done re-initialize the lock and store
    //the downloaded resource if it hasn't been recently downloaded recently.

    //My approach has the advantage of never duplicating downloads, which if you're optimizing for data transfer is
    //slightly better, at the cost of large potential speed if the cache isn't used frequently

    /**
     * Returns string associated with URL resource. Contains cache that eliminates the need for redundant downloads
     * of the same resource. Cache is invalidated and re-downloaded if url was downloaded over an hour ago.
     * I'm assuming I have enough memory to mange this cache in a hash table
     *
     * @param url Name of the URL to get resource from
     * @return returns string that is the resource in the URL
     */
    public synchronized String getResourceFromURL(String url) {
        long currentTime = System.currentTimeMillis() / 1000L;

        //This might be overly clever, using java logic short circuiting
        //to test if there is a cache hit, and if the cache is out of date in the same
        //check.
        if ((!this.urlMap.containsKey(url) ||
                ((currentTime - this.urlMap.get(url).timestamp) > CACHE_INVALIDATION_TIME))) {
            saveResourceFromURL(url);
        }

        return (String) this.urlMap.get(url).resource;
    }

    /**
     * Downloads a resource and saves it into the hash table
     *
     * @param url Name of the URL to get resource from
     */
    private void saveResourceFromURL(String url) {
        urlTuple resourceTuple;
        String resource;


        try {
            resource = this.readStringFromURL(url);
        } catch (java.io.IOException e) {
            resource = "";
        }

        //This is exactly when the url was accessed, not going to include download time
        long currentTime = System.currentTimeMillis() / 1000L;
        resourceTuple = new urlTuple(currentTime, resource);
        this.urlMap.put(url, resourceTuple);

    }

    /**
     * Returns downloaded URL resource. This actually downloads whats behind the URL.
     *
     * @param url Name of the URL to get resource from
     * @return returns downloaded URL resource
     * @throws java.io.IOException
     */
    private String readStringFromURL(String url) throws IOException
    {
        Scanner scanner = new Scanner(new URL(url).openStream(),
                StandardCharsets.UTF_8.toString());

        scanner.useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";

    }

}
