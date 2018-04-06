package WORDCOUNTER;/*

** WORDCOUNTER.WORDCOUNTER.JAVA

**

** Library object that, given a url and set of keywords, returns the number of times
** any of these keywords appear in the resource identified by the URL.
** Only full-word matches should "hit".
** Assume that words are delimited by regex word-boundary characters (i.e. '\b')
**
** The library must support multi-threaded access.
** Once URL resources are loaded, the URL data should be cached for subsequent requests.
** A request should refresh any cached URL data if the data is at least an hour old.
**
** Should stand alone with no additional dependencies beyond what is in the JRE.

*/

import java.util.*;

public class WORDCOUNTER {
    private urlCache urlCache;

    /**
     * Empty Constructor just to initialize the cache
     */
    public WORDCOUNTER() {
        this.urlCache = new urlCache();
    }

    /**
     * Returns number of keywords in resource identified by URL.
     * Words are case sensitive, and are only identified when by regex word-boundary characters ('\b')
     * this method is thread-safe.
     *
     * URL resource that can't be accessed fails silently and assumes no keyword matches.
     * Refresh possible after an hour
     *
     * @param keywords List of keywords to search for in resource identified by URL
     * @param url Name of the URL to get resource from
     * @return return number of hits of keywords in given URL resource
     */
    public int getKeywordCount(List<String> keywords, String url) {

        //Convert List<String> to Set<String> for faster searching
        Set<String> keywordSet = new HashSet<String>(keywords);

        String resource = urlCache.getResourceFromURL(url);

        String[] words = resource.split("\\b");

        int numHits = 0;
        for (String word : words) {
            if (keywordSet.contains(word)) {
                numHits += 1;
            }
        }
        return numHits;

    }
}

