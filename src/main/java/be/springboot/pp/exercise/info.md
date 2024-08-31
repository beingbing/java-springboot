# Practice Exercise

## 1. Web Crawler
- build it
- its a component which take seed_url as an input
- and outputs all the Urls which are linked to it (reachable from seed_url)

### design
- `/crawl` api will accept request that contain seed_url and response will contain a list of Urls.
- it will be too slow as crawling take minutes, even hours to finish.
- so, instead of directly giving response in `/crawl`, we will return a `unique_token` assuring that the request is completed.
- `unique_token` is for client to poll on `/results`
- on every hit, `/results` will return current list of urls which are extracted till now.
- along with a flag `status` having values `FINISHED/IN_PROGRESS` indicating completion of crawling activity.

#### design v2
- we can add caching
- we can add flag `newResults` in `/crawl`, which indicate to not hit cache and get results by initiating fresh crawling.
- we can add integer `depth` indicating the depth till which crawler needs to go.

### implementation
- an existing library for parsing HTML Document.
- parsing: extracting all the link(hrefs) from that document.
- library should be able to connect via HTTP to a website and download HTML/CSS.
- we need to perform many tasks asynchronously
