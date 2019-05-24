package lab05;

/**
 * This class represents a simple row or column vector of numbers. In a row
 * vector, the numbers are written horizontally (i.e., along the columns). In a
 * column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author Erin Parker & Jason Crandall
 * @version January 9, 2019
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;

	/**
	 * Creates a new row or column vector. For a row vector, the input array is
	 * expected to have 1 row and a positive number of columns, and this number of
	 * columns represents the vector's length. For a column vector, the input array
	 * is expected to have 1 column and a positive number of rows, and this number
	 * of rows represents the vector's length.
	 * 
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the
	 *                                  input 2D array is not compatible with a row
	 *                                  or column vector
	 */
	public MathVector(double[][] data) {
		if (data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if (data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");

		if (data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		} else if (data[0].length == 1) {
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		} else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");

		// Create the array and copy data over.
		if (this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	/**
	 * Determines whether this vector is "equal to" another vector, where equality
	 * is defined as both vectors being row (or both being column), having the same
	 * vector length, and containing the same numbers in the same positions.
	 * 
	 * @param other - another vector to compare
	 */
	public boolean equals(Object other) {

		if (!(other instanceof MathVector))
			return false;

		MathVector otherVec = (MathVector) other;

		// Compare the lengths of the two vectors
		if (this.vectorSize != otherVec.vectorSize)
			return false;
		// Compare the types of the two vectors
		if (this.isRowVector != otherVec.isRowVector)
			return false;

		// Compare the individual elements of each vector
		for (int i = 0; i < this.data.length; i++)
			for (int j = 0; j < this.data[i].length; j++)
				if (this.data[i][j] != otherVec.data[i][j])
					return false;

		return true;
	}

	/**
	 * Generates a returns a new vector that is the transposed version of this
	 * vector.
	 */
	public MathVector transpose() {

		// Create a new vector of opposite dimensions
		int rows = this.data[0].length;
		int cols = this.data.length;

		double[][] transposeData = new double[rows][cols];

		// Place each element of the old vector into its corresponding position in the
		// new vector
		for (int i = 0; i < this.data.length; i++)
			for (int j = 0; j < this.data[i].length; j++)
				transposeData[j][i] = this.data[i][j];

		return new MathVector(transposeData);
	}

	/**
	 * Generates and returns a new vector representing the sum of this vector and
	 * another vector.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 */
	public MathVector add(MathVector other) {

		if (this.isRowVector != other.isRowVector || this.vectorSize != other.vectorSize)
			throw new IllegalArgumentException("Vectors are of a different type or size.");

		// Create a vector of the same size as the imported vector
		double[][] sum = new double[this.data.length][this.data[0].length];

		// Populate the new vector with the sum of the two vectors
		for (int i = 0; i < this.data.length; i++)
			for (int j = 0; j < this.data[i].length; j++)
				sum[i][j] = this.data[i][j] + other.data[i][j];

		return new MathVector(sum);
	}

	/**
	 * Computes and returns the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector to produce the
	 *              dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 */
	public double dotProduct(MathVector other) {

		if (this.isRowVector != other.isRowVector || this.vectorSize != other.vectorSize)
			throw new IllegalArgumentException("Vectors are of a different type or size.");

		double dotProdResult = 0.0;

		// Multiply each corresponding element in the two vectors and add them to the
		// dotProdResult
		for (int i = 0; i < this.data.length; i++)
			for (int j = 0; j < this.data[i].length; j++)
				dotProdResult += this.data[i][j] * other.data[i][j];

		return dotProdResult;
	}

	/**
	 * Computes and returns this vector's magnitude (also known as a vector's
	 * length) .
	 */
	public double magnitude() {

		double vecMagnitude = 0.0;

		// Square and add each element in the vector
		for (int i = 0; i < this.data.length; i++)
			for (int j = 0; j < this.data[i].length; j++)
				vecMagnitude += this.data[i][j] * this.data[i][j];

		// Take the square root of the total
		vecMagnitude = Math.sqrt(vecMagnitude);

		return vecMagnitude;
	}

	/**
	 * Generates and returns a normalized version of this vector.
	 */
	public MathVector normalize() {

		// Create a new vector of the same size as the original
		double[][] normalizedVec = new double[this.data.length][this.data[0].length];

		// Divide each element of the original vector by its magnitude and add to the
		// new vector
		for (int i = 0; i < this.data.length; i++)
			for (int j = 0; j < this.data[i].length; j++)
				normalizedVec[i][j] = this.data[i][j] / this.magnitude();

		return new MathVector(normalizedVec);
	}

	/**
	 * Generates and returns a textual representation of this vector. For example,
	 * "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and "1.0 2.0 3.0
	 * 4.0 5.0" for a sample column vector of length 5. In both cases, notice the
	 * lack of a newline or space after the last number.
	 */
	public String toString() {

		// Create a string buffer to add vector elements to
		StringBuffer vecString = new StringBuffer();

		char delimeter = ' ';
		if (!isRowVector)
			delimeter = '\n';

		// Add each element in the vector to the string buffer
		for (int i = 0; i < this.data.length; i++)
			for (int j = 0; j < this.data[i].length; j++)
				vecString.append(this.data[i][j]).append(delimeter);

		vecString.deleteCharAt(vecString.length() - 1);

		return vecString.toString();
	}
}