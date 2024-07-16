# Redundant Edge Removal in Graphs

This project provides a Java solution to find and remove a redundant edge in a graph that started as a tree with one additional edge added. By removing the extra edge, the resulting graph will be a tree (i.e., a connected graph with no cycles).

## Data Structures Used

- **Union-Find**: Used for merging sets and finding roots. Includes path compression and union by rank optimizations.

## Code Explanation

- **`UnionFind` Class**: Implements the Union-Find data structure, performing root finding and set merging operations using `find` and `union` methods.
- **`findRedundantConnection` Method**: Processes edges to find and return the redundant edge.

## Tests

The project includes two test data sets:

- `edges1` = `[[1, 2], [1, 3], [2, 3]]`
  - Expected Output: `[2, 3]`

- `edges2` = `[[1, 2], [2, 3], [3, 4], [1, 4], [1, 5]]`
  - Expected Output: `[1, 4]`
