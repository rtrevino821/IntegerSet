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

				//stores values from data array into integers array as Integers
				for (int i = 0; i < integers.length; i++) {
					integers[i] = Integer.parseInt(data[i]);
				}

				//switch statements checks counter to see where to store integer array from above
				switch (arrCounter) {
				case 0: //if arrCounter is 0, store integer[] into paramArr1
					paramArr1[row] = integers;
					break;
				case 1: //if arrCounter is 1, store integer[] into paramArr2
					paramArr2[row] = integers;
					break;
				case 2: //if arrCounter is 2, store integer[] into paramArr3
					paramArr3[row] = integers;
					break;
				case 3: //if arrCounter is 3, store integer[] into paramArr4
					paramArr4[row] = integers;
					break;
				case 4: //if arrCounter is 4, store integer[] into paramArr5
					paramArr5[row] = integers;
					break;
				case 5: //if arrCounter is 5, store integer[] into paramArr6
					paramArr6[row] = integers;
					break;
				case 6: //if arrCounter is 6, store integer[] into paramArr7
					paramArr7[row] = integers;
					break;
				case 7: //if arrCounter is 7, store integer[] into paramArr8
					paramArr8[row] = integers;
					break;
				case 8: //if arrCounter is 8, store integer[] into paramArr9
					paramArr9[row] = integers;
					break;
				case 9: //if arrCounter is 9, store integer[] into paramArr10
					paramArr10[row] = integers;
					break;
				}
				row++; //keeps track of what row to store the array in
			}
		} while (line != null);

		return Arrays.asList(new Object[][] {
				{ paramArr1 }, { paramArr2 }, { paramArr3 }, { paramArr4 }, { paramArr5 }, { paramArr6 }, { paramArr7 },
				{ paramArr8 }, { paramArr9 }, { paramArr10 } });
	}

	@After
	public void tearDown() throws Exception {
	}

	//test whether an IntegerSet is empty
	//firstSet is given data
	//secSet is not given data
	@Test
	public void testIsEmpty() {
		firstSet = new IntegerSet();
		secSet = new IntegerSet();

		firstSet.insertAll(firstNums);
		assertFalse(firstSet.isEmpty());
		assertTrue(secSet.isEmpty());
	}

	//test insertAll method with null input
	@Test(expected = NullPointerException.class)
	public void testAllNull() {
		firstSet = new IntegerSet();
		firstSet.insertAll(null);
	}

	//test creating an IntegerSet from array
	@Test
	public void testCreateSetFromArray() {
		Integer[] arr = { 1, 3, 4, 5, 6 };
		firstSet = new IntegerSet(arr);

		assertEquals(firstSet.toArray(), arr);
	}

	//test creating a set with null parameter
	@Test(expected = NullPointerException.class)
	public void testCreateSetFromNull() {
		firstSet = new IntegerSet(null);
	}

	//test to check if all integers were inserted into integerSet
	@Test
	public void testInsertAll() {
		firstSet = new IntegerSet();
		secSet = new IntegerSet();

		firstSet.insertAll(firstNums); //insert data
		secSet.insertAll(uniqueFirstNums); //insert all unique data from firstNums
		
		//if all data has been inserted successfully, they should equal
		assertEquals(firstSet.toString(), secSet.toString());
	}

	//test if an element was deleted
	@Test
	public void testDeleteElement() {
		firstSet = new IntegerSet(firstNums); // initialize integerSet
		Integer[] value = firstSet.toArray(); //store copy of firstSet array data

		firstSet.deleteElement(0); //delete element

		assertFalse(firstSet.exists(value[0])); //check element was deleted
		assertTrue(firstSet.exists(value[1])); //check for element that still exist
	}

	//test to check all data gets deleted from integer set
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

	//Test to check the exist method that finds an element within an IntegerSet
	@Test
	public void testExists() {
		firstSet = new IntegerSet(firstNums); // initialize IntegerSet
		Integer[] value = firstSet.toArray(); //store copy of IntegerSet data

		assertTrue(firstSet.exists(value[0])); //check for value that should exist
		assertFalse(firstSet.exists(-10)); //check for value that should not exist
	}

	//test the union of two integerSets
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

	//test the union of two IntegerSets with null input
	@Test(expected = NullPointerException.class)
	public void testUnionWithNullInput() throws NullPointerException {
		firstSet = new IntegerSet(); //create empty sets
		secSet = new IntegerSet(); //create empty sets
		
		firstSet = null; //set to null
		secSet = null; //set to null

		firstSet = firstSet.union(firstSet, secSet); //should throw expected exception
	}

	//test the intersection of two sets
	@Test
	public void testIntersection() {
		firstSet = new IntegerSet(firstNums); //create set from first line of integers
		secSet = new IntegerSet(secNums); //create set from second line of integers

		IntegerSet intersectionSet = new IntegerSet(intersection); //create an IntegerSet from intersection data

		firstSet = firstSet.intersection(firstSet, secSet); //intersect data from both sets

		assertEquals(intersectionSet.toArray(), firstSet.toArray()); //true, if intersection was successful
	}

	//test the intersection of two empty sets
	@Test
	public void testIntersectionWithNullInput() {
		firstSet = new IntegerSet(); //create an empty set
		secSet = new IntegerSet(); //create an empty set

		//intesect empty sets, should return empty set
		firstSet = firstSet.intersection(firstSet, secSet);
		
		assertTrue(firstSet.isEmpty()); //true if set is empty
	}

}
