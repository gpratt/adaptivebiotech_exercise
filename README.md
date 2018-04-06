Library object that, given a url and set of keywords, returns the number of times any of these keywords appear in the resource identified by the URL. Only full-word match "hit". Words are delimited by regex word-boundary characters (i.e. '\b')

The library supports multi-threaded access

Once URL resources are loaded, the URL data is cached for subsequent requests. A request will refresh any cached URL data if the data is at least an hour old.

To install package run

    git clone git@github.com:gpratt/adaptivebiotech_exercise.git
    cd adaptivebiotech_exercise
    make

To test run

    java WordCounterExample

Its not a perfect test, but output should be

    5
    Waiting
    5
    6
    0
    0






