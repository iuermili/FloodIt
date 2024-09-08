## Group members: Sarvesh Satish, Christine Chen, Lincoln Firks, Eric Li, Dhruv Chavan

## [Question 1] What function roughly fits that graph?

The function f(n) = n² fits the graph.

## [Question 2] What is the time complexity of your flood() function based on analyzing its code? Answer the question in your write-up and write the complexity as a line of comment in your code above the function.

In the flood() function, we start by initializing a new linked list, named coordsToCheck, with the elements of the flooded_list. In order to copy over the elements to coordsToCheck, flooded_list was  iterated through, which means this operation’s time complexity is O(n). Then, for each element (coord) in coordsToCheck, we get the neighbors from the provided neighbors function which is contant time as there will be at most 4 neighbors for each. Then we iterate through each neighbor and check if it exists in the flooded_list. Since flooded_list is a linked list, this lookup takes O(n) as it has to go through the entiere list in the worst-case scenario.  Because we iterated through the entire flooded_list for each element in coordsToCheck, this operation’s time complexity is O(n²). By combining the time complexities of both operations, we end with n + n², which reduces down to O(n²). Therefore, the time complexity of the flood() function is O(n²).

## [Question 3] Does your analysis match up with what you see in the graph?

The graph that represents the flood() function’s time complexity roughly resembles a quadratic function (f(n) = n²). This indicates that the flood() function does have a time complexity of O(n²), the conclusion we arrived at in our code analysis.

## [Question 4] What data structures did you use (linked lists, arrays…) ? Is the time complexity of your flood function the best it can be or can you do better? Why?

Our original implementation for the flood() function utilized one additional linked list.

The time complexity of our implementation is definitely not the best we can do. We know that because whenever we perform a search through a linked list in our code (which we do n times) we have to iterate through n elements. However, through employing a data structure that is more appropriate for search operations, we can resolve this issue.

## [Question 5] What is the time complexity of your alternative implementation? Comment the function with its time complexity in your code and write down the time complexity in your write-up.

Our alternative implementation for the flood1() function utilized an extra linked list and a hash set. The hash set enabled us to make the previous O(n) search operations on the flooded_list into O(1), constant time, operations. The cost of initializing the HashSet is O(n), outside of the loop, which is reasonable.

We believe that the time complexity of our alternative implementation, which is O(n), is the best that we can do because we have to iterate through all the elements in the flooded_list (O(n) operation) no matter what to correctly implement the flood() function.
