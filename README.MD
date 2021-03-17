## Side notes for Reviewers:
1. Task specification does not define what should be the behaviour if the number of followers is equal to zero. From mathematical perspective we can't divide by zero, so I decided to just return zero for that case.

2. I decided to use native query in a repository layer to provide thread safety for incrementing request count. Probably there are some better ways of doing that, but I had no time to look into this deeper :(