import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class IntegerSetTest {

	static Integer[] firstNums;
	static Integer[] secNums;
	static Integer[] uniqueFirstNums;
	static Integer[] uniqueSecondNums;
	static Integer[] intersection;
	static Integer[] union;
	IntegerSet firstSet, secSet;

	// parameters pass via this constructor
	public IntegerSetTest(Integer[][] test) {
		this.firstNums = test[0]; //holds first list of numbers
		this.secNums = test[2]; //holds second list of numbers
		this.uniqueFirstNums = test[1]; //holds unique set of data from first list of numbers
		this.uniqueSecondNums = test[3]; //holds unique set of data from second list of numbers
		this.intersection = test[4]; //holds intersection values from first and second list
		this.union = test[5]; //holds union values from first and second list
	}

	// Declares parameters here
	@Parameters
	public static Collection testSet() throws NumberFormatException, IOException {

		BufferedReader reader = null;
		//open file
		try {
			reader = new BufferedReader(new FileReader("test_input.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String line = null;
		
		//create 10 2d arrays to hold six 1d arrays each 
		Integer[][] paramArr1 = new Integer[6][];
		Integer[][] paramArr2 = new Integer[6][];
		Integer[][] paramArr3 = new Integer[6][];
		Integer[][] paramArr4 = new Integer[6][];
		Integer[][] paramArr5 = new Integer[6][];
		Integer[][] paramArr6 = new Integer[6][];
		Integer[][] paramArr7 = new Integer[6][];
		Integer[][] paramArr8 = new Integer[6][];
		Integer[][] paramArr9 = new Integer[6][];
		Integer[][] paramArr10 = new Integer[6][];

		int arrCounter = 0; //counter to direct which 2d array gets data
		int row = 0; // counter to direct which row within a 2d gets data

		do {
			//while reader has not reached end of file
			while ((line = reader.readLine()) != null) {
				//if line is empty, we have finished reading for that set
				if (line.isEmpty()) {
					row = 0; //reset row back to 0
					arrCounter++; //Jump to next array
					break;
				}

				String[] data = line.split(",");
				Integer[] integers = new Integer[data.length];

				for (int i = 0; i < integers.length; i++) {
					integers[i] = Integer.parseInt(data[i]);
				}

				switch (arrCounter) {
				case 0:
					paramArr1[row] = integers;
					break;
				case 1:
					paramArr2[row] = integers;
					break;
				case 2:
					paramArr3[row] = integers;
					break;
				case 3:
					paramArr4[row] = integers;
					break;
				case 4:
					paramArr5[row] = integers;
					break;
				case 5:
					paramArr6[row] = integers;
					break;
				case 6:
					paramArr7[row] = integers;
					break;
				case 7:
					paramArr8[row] = integers;
					break;
				case 8:
					paramArr9[row] = integers;
					break;
				case 9:
					paramArr10[row] = integers;
					break;
				}
				row++;
			}
		} while (line != null);

		return Arrays.asList(new Object[][] {
				// { firstNums, secNums, uniqueFirstNums, uniqueSecondNums,
				// intersection, union }
				{ paramArr1 }, { paramArr2 }, { paramArr3 }, { paramArr4 }, { paramArr5 }, { paramArr6 }, { paramArr7 },
				{ paramArr8 }, { paramArr9 }, { paramArr10 } });

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsEmpty() {
		firstSet = new IntegerSet();
		secSet = new IntegerSet();

		firstSet.insertAll(firstNums);
		assertFalse(firstSet.isEmpty());
		assertTrue(secSet.isEmpty());
	}

	@Test(expected = NullPointerException.class)
	public void testAllNull() {
		firstSet = new IntegerSet();
		firstSet.insertAll(null);
	}

	@Test
	public void testCreateSetFromArray() {
		Integer[] arr = { 1, 3, 4, 5, 6 };
		firstSet = new IntegerSet(arr);

		assertEquals(firstSet.toArray(), arr);
	}

	@Test(expected = NullPointerException.class)
	public void testCreateSetFromNull() {
		firstSet = new IntegerSet(null);
	}

	@Test
	public void testInsertAll() {
		firstSet = new IntegerSet();
		secSet = new IntegerSet();

		firstSet.insertAll(firstNums);
		secSet.insertAll(uniqueFirstNums);
		// System.out.println("First Set: " + firstSet.toString());
		// System.out.println("Second Set: " + secSet.toString());
		assertEquals(firstSet.toArray(), secSet.toArray());
	}

	@Test
	public void testDeleteElement() {
		firstSet = new IntegerSet(firstNums); // initialize empty list
		Integer[] value = firstSet.toArray();

		// System.out.println(firstSet.toString());
		// System.out.println(value[0] + " & " + value[1]);

		firstSet.deleteElement(0);

		assertFalse(firstSet.exists(value[0]));
		assertTrue(firstSet.exists(value[1]));

	}

	@Test
	public void testDeleteAll() {
		firstSet = new IntegerSet(); // initialize empty list
		firstSet.insertAll(firstNums); // insert elements
		firstSet.deleteAll(); // delete all elements

		secSet = new IntegerSet();
		secSet.insertAll(secNums);

		assertTrue(firstSet.isEmpty()); // check if all elements where deleted
		assertFalse(secSet.isEmpty()); // check if all elements where deleted
	}

	@Test
	public void testExists() {
		firstSet = new IntegerSet(firstNums); // initialize empty list
		Integer[] value = firstSet.toArray();

		assertTrue(firstSet.exists(value[0]));
		assertFalse(firstSet.exists(-10));
	}

	@Test
	public void testUnion() {
		firstSet = new IntegerSet(firstNums); // create IntegerSet
		secSet = new IntegerSet(secNums); // create IntegerSet

		IntegerSet unionSet = new IntegerSet(union); // create Union IntegerSet

		firstSet = firstSet.union(firstSet, secSet); // create a union from
														// first and sec set

		assertEquals(firstSet.toArray(), unionSet.toArray()); // confirm they
																// are both
																// equal
	}

	@Test(expected = NullPointerException.class)
	public void testUnionWithNullInput() {
		firstSet = new IntegerSet(null);
		secSet = new IntegerSet(null);

		firstSet = firstSet.union(firstSet, secSet);

		//assertEquals(firstSet.toArray(), secSet.toArray());
	}

	@Test
	public void testIntersection() {
		firstSet = new IntegerSet(firstNums);
		secSet = new IntegerSet(secNums);

		IntegerSet intersectionSet = new IntegerSet(intersection);

		firstSet = firstSet.intersection(firstSet, secSet);

		assertEquals(intersectionSet.toArray(), firstSet.toArray());
	}

	@Test
	public void testIntersectionWithNullInput() {
		firstSet = new IntegerSet();
		secSet = new IntegerSet();

		firstSet = firstSet.intersection(firstSet, secSet);

		assertEquals(firstSet.toArray(), secSet.toArray());
	}

}
