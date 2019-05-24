package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the MathVector class.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a 
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true). 
 * 
 * @author Erin Parker & Jason Crandall
 * @version January 9, 2019
 */
class MathVectorJUnitTester {
	
	private MathVector rowVec, rowVecTranspose, unitVec, sumVec, colVec;

	@BeforeEach
	void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][]{{3, 1, 2}});
		
		// Creates a column vector with three elements: 3.0, 1.0, 2.0
		rowVecTranspose = new MathVector(new double[][]{{3}, {1}, {2}});
		
		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][]{{1, 1, 1}});
		
		// Creates a row vector with three elements: 4.0, 2.0, 3.0
		sumVec = new MathVector(new double[][]{{4, 2, 3}});
		
		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}});		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	public void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
	}
	
	@Test
	public void smallColumnVectorEquality() {
		assertTrue(colVec.equals(new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}})));
	}
	
	@Test
	public void otherObjectTypeVectorInequality() {
		assertFalse(rowVec.equals(new String("3.0 2.0 1.0")));
	}
	
	@Test
	public void smallRowVectorInequality() {
		assertFalse(rowVec.equals(unitVec));
	}
	
	@Test
	public void smallRowVectorSizeInequality() {
		assertFalse(rowVec.equals(new MathVector(new double[][] {{3, 1, 2, 4}})));
	}

	@Test
	public void rowVectorColumnVectorInequality() {
		assertFalse(rowVec.equals(rowVecTranspose));
	}
	
	@Test
	public void createVectorFromBadArray() {
	  double arr[][] = {{1, 2}, {3, 4}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	public void transposeSmallRowVector() {
		MathVector transposeResult = rowVec.transpose();
		assertTrue(transposeResult.equals(rowVecTranspose));
	}
	
	@Test
	public void transposeSmallColumnVector() {
		MathVector transposeResult = rowVecTranspose.transpose();
		assertTrue(transposeResult.equals(rowVec));
	}
	
	@Test
	public void transposeOneDimensionVector() {
		MathVector vector = new MathVector(new double[][] {{1}});
		MathVector transposeResult = vector.transpose();
		assertTrue(transposeResult.equals(vector));
	}
	
	@Test
	public void addRowAndColVectors() {
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	public void addDifferentSizeRowVectors() {
		assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec.transpose()); });
	}
	
	@Test
	public void addDifferentSizeColumnVectors() {
		assertThrows(IllegalArgumentException.class, () -> { rowVec.transpose().add(colVec); });
	}
	
	@Test
	public void addSmallRowVectors() {
		MathVector addResult = rowVec.add(unitVec);
		assertTrue(addResult.equals(sumVec));		
	}
	
	@Test
	public void addSmallColumnVectors() {
		MathVector addResult = rowVec.transpose().add(unitVec.transpose());
		assertTrue(addResult.equals(sumVec.transpose()));
	}

	@Test
	public void dotProductRowAndColumnVectors() {
		assertThrows(IllegalArgumentException.class, () -> { rowVec.dotProduct(unitVec.transpose()); });
	}
	
	@Test
	public void dotProductRowVectorsDiffSize() {
		assertThrows(IllegalArgumentException.class, () -> { rowVec.dotProduct(colVec.transpose()); });
	}
	
	@Test
	public void dotProductColVectorsDiffSize() {
		assertThrows(IllegalArgumentException.class, () -> { rowVec.transpose().dotProduct(colVec); });
	}
	
	@Test
	public void dotProductSmallRowVectors() {
		double dotProdResult = rowVec.dotProduct(unitVec);
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);		
	}
	
	@Test
	public void dotProductSmallColumnVectors() {
		double dotProdResult = rowVec.transpose().dotProduct(unitVec.transpose());
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);	
	}
	
	@Test
	public void dotProductPerpendicularVectors() {
		MathVector vector1 = new MathVector(new double[][] {{1, 0, 0}});
		MathVector vector2 = new MathVector(new double[][] {{0, 1, 0}});
		double dotProductResult = vector1.dotProduct(vector2);
		assertEquals(0, dotProductResult);
	}
	
	@Test
	public void smallRowVectorMagnitude() {
		double vecMagnitude = rowVec.magnitude();
		assertEquals(vecMagnitude, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));		
	}
	
	@Test
	public void smallColVectorMagnitude() {
		double vecMagnitude = rowVec.transpose().magnitude();
		assertEquals(vecMagnitude, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));		
	}
	
	@Test
	public void smallRowVectorMagnitudeAllNeg() {
		MathVector vector = new MathVector(new double[][] {{-3, -1, -2}});
		double vecMagnitude = vector.magnitude();
		assertEquals(vecMagnitude, rowVec.magnitude());
	}
	
	@Test
	public void smallColVectorMagnitudeAllNeg() {
		MathVector vector = new MathVector(new double[][] {{-3}, {-1}, {-2}});
		double vecMagnitude = vector.magnitude();
		assertEquals(vecMagnitude, rowVec.magnitude());
	}
	
	@Test
	public void smallRowVectorMagnitudeNegAndPos() {
		MathVector vector = new MathVector(new double[][] {{-3, 1, -2}});
		double vecMagnitude = vector.magnitude();
		assertEquals(vecMagnitude, rowVec.magnitude());
	}
	
	@Test
	public void smallColVectorMagnitudeNegAndPos() {
		MathVector vector = new MathVector(new double[][] {{-3}, {1}, {-2}});
		double vecMagnitude = vector.magnitude();
		assertEquals(vecMagnitude, rowVec.magnitude());
	}
	
	@Test
	public void magnitudeOfNormalizedVec() {
		MathVector normalVec = rowVec.normalize();
		assertEquals(1, normalVec.magnitude());
	}
	
	@Test
	public void smallRowVectorNormalize() {
		MathVector normalVec = rowVec.normalize();
		double magnitude = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0 / magnitude, 1.0 / magnitude, 2.0 / magnitude}})));		
	}
	
	@Test
	public void smallColVectorNormalize() {
		MathVector normalVec = rowVec.transpose().normalize();
		double magnitude = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0 / magnitude}, {1.0 / magnitude}, {2.0 / magnitude}})));		
	}
	
	@Test
	public void smallColVectorToString() {
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
		assertEquals(colVec.toString(), resultStr);		
	}
	
	@Test
	public void smallRowVectorToString() {
		String resultStr = "-11.0 2.5 36.0 -3.14 7.1";
		assertEquals(colVec.transpose().toString(), resultStr);		
	}
	
	@Test
	public void vectorOfOneElementToString() {
		String result = "1.0";
		MathVector vector = new MathVector(new double[][] {{1}});
		assertEquals(result, vector.toString());
	}
	
	@Test
	public void addedVectorsToString() {
		MathVector result = rowVec.add(unitVec);
		assertEquals(sumVec.toString(), result.toString());
	}
	

}