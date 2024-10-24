Hi Alex

Here is a solution to the interview question.

We have three transaction windows. Each windows tracks its size in whole seconds, the current boundaries of the window, the number of requests allowed in the window, and the requests it has seen in that window.

Broadly, for each request, we give the request to each window and ask if the request should be allowed. If, in the process of that, our window moves along the timeline, we discard the requests that no longer belong to our window.

The windows are initially elastic. They all start at the same time, 0 (zero), and move through time as the requests are received sequentially by increasing time. For example, our 10 second window isn't initially 10 seconds wide. It's, 0, then 1, then 2 and so on. Only after it reaches 10 seconds wide does it stay that size. 

Thinking of some improvements...

(1) It doesn't seem important to keep the requests in the windows. All we really care about is the count of requests for each second. A refactor would be to simplfy TransactionWindow to just keep a track of requests for each second in the window.

(2) The problem looks like it should lend itself to some functional programming and/or streams. I've had a bit of a play with each, but i think my solution would need a rethink to be functional or stream friendly.

Thanks

Jamie
