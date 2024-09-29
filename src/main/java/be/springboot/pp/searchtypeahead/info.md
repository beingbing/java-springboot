# Exercise: minor project

## advice
- clear all the requirement
- start with HLD
- after gathering requirements start from brute force without worrying about scale.
- it will always help you to first think in terms of DB tables, then worry about APIs.
- do not try to write every function from top to bottom
- define method signature and layout the process it would undertake, then write the implementation.
- always return a user-defined response, never give a primitive response
- 

## requirement
- design a search-type-ahead system
- an api `/getSuggestions(String)` which takes a query string and returns a list of strings
- deciding factor behind the list of strings should be the popularity of that string as a suggestion which starts with the query string
- so, suggestions have to be returned on the basis of search popularity.
- example, query string is "best", and before me, 100 people also queries something starting with that keyword and out of them 70 searched for "best chocolates in the world", so my first suggestion should be that, and it should be followed by suggestions in decreasing order of popularity.
- max length of list can be 5.
- no personalization is required.
- result of api should change with time based on what's popular.
- and change in suggestion depends upon queries made by people.
- another api `query(actual_query_string)` which takes the string on which user pressed enter to load the search result.
- will keep in record that this query has got one hit.
- `/query` will keep updating our inventory of searched words.
- it is not necessary that suggestion list changes happen in real-time based on what others are searching. Delay in that is also ok.

### missed questions 
- what kind of characters are allowed.

## implementation 1: brute force (HLD)
- i will just have one table - `query_frequence` with columns - word, frequency
- our action plan will be, update frequency if query found, otherwise make a new entry and mark its frequency as 1.
- so our `/getSuggestoins` api will simply do - `select * from query_frequency where query like 'ma%'`

### bottlenecks -
- query will get slower as soon as our DB records increases, hence query has O(n) complexity
- SQL `like` query in itself is a heavy process of o(n) complexity in worst case with '%abc%' and O(log n) in best case with 'abc%' or '%abc';
- so, in our case, the simple search is of O(n * log n), which is not good keeping in mind the frequency of the api hits we will be getting.

## implementation 2: better than previous (HLD)
- the bottleneck was with the execution time with the query.
- think of execution issue as a DS/Algo problem, where, we have prefix and words, and we want to fetch words starting with a given prefix.
- so, `/getSuggestions(query)` will be returning a list of max 5 strings whose prefix is `query`
- in terms of a function `public List<String> getSuggestions(List<String> wordsCollection, String query);`
- whether we use Z algorithm or KMP algorithm, that will not be effective because that will work for pattern matching and we are doing prefix matching
- and prefix matching can be done in O(n) even in brute force and O(log n) when optimized.
- so, any algorithms won't be able to optimize matching more than O(log n).
- hence, we need to look for a DS which can reduce the other O(n) we have in terms of the collection we need to queried.
- We can go with Trie (prefix tree), which is best for retrieval of some values from a collection of large sample set based on prefixes.
- which will introduce a new variable in our calculation, which is m - length of query string.
- so, we have n - size of wordsCollection list and m - length of query string.
- with using trie complexity changed from O(n * log n) -> O(m) in best case scenarios
- and m is insignificant in front of n, and if we freeze the max query length then it becomes a constant c in worst case considerations. 
- m will be in between (1 - 20 chars) and n will be order of 10^5 or something
- so the searching becomes an O(c) process.
- but we reach a designated node on trie from which a result needs to be compiled. We need to gather all the candidates and sort them in order of frequency.
- but sorting is an O(k * log k) process, where k is the total number of results gathered, which can be a large number in generic considerations.
- so, we now need to optimize for sorting based on frequency.
- But as our result can have atmost 5 suggestion, this gives us a scope of precomputation.
- at each node, we have possible 26 subtrees.
- if each subtree node keeps 2 most frequent searches in that subtree, then our sorting becomes an O(52 Log 52) solution, which is again an O(c) implementation.
- or at each node, i can store top 5 most frequent searches in that subtree.
- but we can not keep our trie all the time in memory.
- We can continue to keep using `query_frequency` table as it is, if we loose our trie, we can rebuild it using the table.
- so, whenever our application starts, it reads the DB and builds the trie out of it.
- and then trie can keep getting updated with new searched queries.
- and our `/query` updates frequency in DB only.
- and we can create a separate service/application whose purpose is solemnly to update trie with updates recorded in DB.
- We can call it as `trieUpdator/aggregator`.
- It will run every 1 hour or so and will update the trie with the snapshot of DB which is the process picked up while initializing the updation.
- The `trieUpdator` will construct a new trie, and will replace the old one with it.

### v2
- we can build another server for cache and work on cache eviction and storage

## LLD implementation of solution 2
- it is the part where we write code skeleton.
- 