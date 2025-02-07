## Discussion
`ProjectedScoreBoard` class has a dependency on `CricketScoreBoard`. Similar case will be with `RunRateBoard`. All these classes are acting as subscribers, and `CricketScoreBoard` is the publisher. But the subscribers are continuously running on a separate thread and checking the service for new updates. They all are busy waiting.

This system can be easily understood as a pull notification system. Which is wasting a lot of CPU cycles to run infinite while-loops when clearly nothing is happening. 

### Another approach
Whenever `CricketScoreBoard` gets the updated information. It needs to call other objects to provide them update on new information. This will be a depiction of push system.

But the problem here is violation of O and D principles out of 5 SOLID principles we have.
- **Dependency inversion principle violation:** `CricketScoreBoard` concrete class depends on other concrete classes like `ProjectedScoreBoard`.
- **Open/Close principle violations:** As new concrete classes come up, `CricketScoreBoard` class needs to repeatedly modified.

## Better Approach
We can create a `Subscriber` interface which will have an `update()`. Instead of depending on concretions, we can use it to push updates. This will remedy Open/Close principle violations.

We can also create a `Publisher` interface which will have a `notifyAll()`. All the subscribed objects will get current updates pushed via this.

This interaction is called PubSub interface and is also known as Observer design pattern.

### Improvements
If there are multiple publishers, then `update()` will notify all subscribers about a new update, but won't tell which publisher has it. So we need to provide the publisher as well, who is pushing the latest update.