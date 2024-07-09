# Assignment_5

## Strassen's Matrix Multiplication
This algorithm is used to multiply to matrices of the same size(i.e n x n). It is faster than the traditional matrix multiplication algorithm. It uses the divide and conquer method to achieve this. 

#### Pseudocode
![Matrix-Divided](./images/matrix-divided.png)

1. Divide the two matrices into 4 sub-matrices of size n/2 x n/2 as shown above

2. Calculate the 7 matrix multiplications recursively

3. Compute the submatrices of C as shown below
!['Calculation_of_sub-matrices'](./images/matrix-calulation.png)

4. Combine the submatrices into a new matrix 

#### Time Complexity
T(N)  = 7T(N/2) + O(N^(log7))
T(N) = 7T(N/2) +  O(N^2.8074)
It has worst case of O(N^2.8074)

It's time complexity is closer to the traditonal method but faster it. From the graph below the green line is the Strasen's algorithm  and the blue line is the traditional algorithm
!['Traditional_vs_Strassen'](./images/traditional%20vs%20strassen.png)

#### Flowchart
!['Flowchart'](./images/Flow-diagram-of-Strassens-algorithm.png)