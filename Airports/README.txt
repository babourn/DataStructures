README.txt

programming 3

mainf2012.java
This file just contains a basic front end which processes the input whether it be from a live user
or an input file.

FileReader.java
This file reads a given text file of cities and their longitude and latitude and turns them into 
nodes and then calculates random edges. it makes a list of nodes and a list of edges.
The Load method- simply loads the given file since it uses the file reader it's got a big O(V) since it has to go through
all the cities twice and Create all the vertexes once. 
if there is already a graph loaded and you choose to add to it, all the edges will be recalculated, not added to.
edgeAdder method - this methods runtime is big O(V +V*E) because it runs through cities then runs through the edges for each
city.


MyGraphMap.java
this is the main graph class. the vertexes/nodes are City objects and the Edges are edge objects.
it also implements Dijkstra's algorithm with time complexity O = E log V where E is the number of edges
and V is the number of vertices. I got most of this algorithm from the Text but I tweaked it a little
so that it would run smoother.

MyCityComparable.java/EdgeCostComparable.java
these two comparators were made so that I could use collections.sort on the list of cities to get them
in order based off of geographic distance/ edgeCost. this is used heavily in the processor class so that
we could print off a certain number.

LatLongConverter.java
this Class simply calculated a distance when given two sets of Latitude and Longitude.

City.java
this was the vertex object. it contained information like distance, latitude/longitude, Edges, EdgeCosts,
and other relevant information about the vertex

Edge.java
this was the edge object. it contained a fromCity, a toCity, and a Cost.

Processor.java
This was where most of the work happened for simplicities sake it imported the vertexes and edges from the graph class
to use in the actual data processing.

the get cities from state method- bigO(V) because it simply goes through all the cities once

to get city by name- Big O (V) once again it simply goes through all the cities

set current city- big O (V) simply runs through the cities until it finds the one with the correct ID

show current city - big O(7) since it simply prints the relevant info

closest by GPS - Big O(V+VlogV) because first it goes through the cities once and then it sorts them

Closest by Edges - Big O(ElogV + VlogV) because it first runs dijkstra's and then sorts the list

Shortest Path - Big O(ElogV) because it implements dijkstra's and then simply prints the list.



