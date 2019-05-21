package PT2019.Assignment1.Assignment1;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Assert;

import org.junit.Test;

import PT2019.Assignment1.Assignment1.Monom;
import PT2019.Assignment1.Assignment1.Polinom;

public class PolinomTest {

	private Polinom p1;
	private Polinom p2;

	@Test
	public void testAddPoly() {
		p1 = new Polinom();
		p2 = new Polinom();

		// p1=x^2 + 1 + 1
		p1.getPolinom().add(new Monom(1, 2));
		p1.getPolinom().add(new Monom(1, 0));
		p1.getPolinom().add(new Monom(1, 0));
		
		// p2=x^3 + x+ 3x^2 + 1 + x^3 + x^3 + 2x^2 + 3 + 7x
		p2.getPolinom().add(new Monom(1, 3));
		p2.getPolinom().add(new Monom(1, 1));
		p2.getPolinom().add(new Monom(3, 2));
		p2.getPolinom().add(new Monom(1, 0));
		p2.getPolinom().add(new Monom(1, 3));
		p2.getPolinom().add(new Monom(1, 3));
		p2.getPolinom().add(new Monom(2, 2));
		p2.getPolinom().add(new Monom(3, 0));
		p2.getPolinom().add(new Monom(7, 1));
		
		Polinom result = new Polinom();
		result = p1.addPoly(p2);
		// result=3x^3 + 6x^2 + 8x + 6
		Polinom resTrue = new Polinom();
		resTrue.getPolinom().add(new Monom(3,3));
		resTrue.getPolinom().add(new Monom(6,2));
		resTrue.getPolinom().add(new Monom(8,1));
		resTrue.getPolinom().add(new Monom(6,0));
		
		assertEquals(result.toString(),resTrue.toString());
	}

	@Test
	public void testSubPoly() {
		p1 = new Polinom();
		p2 = new Polinom();

		// p1=x^2 + 5
		p1.getPolinom().add(new Monom(1, 2));
		p1.getPolinom().add(new Monom(5, 0));
		
		// p2=x^3 + x^5 + 2
		p2.getPolinom().add(new Monom(1, 3));
		p2.getPolinom().add(new Monom(1, 5));
		p2.getPolinom().add(new Monom(2, 0));
		
		Polinom result = new Polinom();
		result = p1.subPoly(p2);
		// result=-x^5 - x^3 + x^2 + 3
		Polinom resTrue = new Polinom();
		resTrue.getPolinom().add(new Monom(-1,5));
		resTrue.getPolinom().add(new Monom(-1,3));
		resTrue.getPolinom().add(new Monom(1,2));
		resTrue.getPolinom().add(new Monom(3,0));
		
		assertEquals(result.toString(),resTrue.toString());
	}

	@Test
	public void testMulPoly() {
		p1 = new Polinom();
		p2 = new Polinom();

		// p1=x + 1
		p1.getPolinom().add(new Monom(1, 1));
		p1.getPolinom().add(new Monom(1, 0));
		
		// p2=x - 1
		p2.getPolinom().add(new Monom(1, 1));
		p2.getPolinom().add(new Monom(-1, 0));

		Polinom result = new Polinom();
		result = p1.mulPoly(p2);
		// result=-x^2 - 1
		Polinom resTrue = new Polinom();
		resTrue.getPolinom().add(new Monom(1,2));
		resTrue.getPolinom().add(new Monom(-1,0));
		
		assertEquals(result.toString(),resTrue.toString());
	}

	@Test
	public void testDivPoly() {
		p1 = new Polinom();
		p2 = new Polinom();

		// p1=x^2 - 1 
		p1.getPolinom().add(new Monom(1, 2));
		p1.getPolinom().add(new Monom(-1, 0));
		
		// p2=x - 1
		p2.getPolinom().add(new Monom(1, 1));
		p2.getPolinom().add(new Monom(-1, 0));

		Polinom[] result = new Polinom[2];
		result = p1.divPoly(p2);
		// result=x + 1
		Polinom[] resTrue = new Polinom[2];
		resTrue[0] = new Polinom();
		resTrue[1] = new Polinom();
		
		resTrue[0].getPolinom().add(new Monom(1,1));
		resTrue[0].getPolinom().add(new Monom(1,0));
		
		resTrue[1].getPolinom().add(new Monom(0,0));
		
		assertEquals(result[0].toString(),resTrue[0].toString());
		assertEquals(result[1].toString(),resTrue[1].toString()); 
	}

	@Test
	public void testDerivPoly() {
		p1 = new Polinom();
		p2 = new Polinom();

		// p1=x^5 + 3x^2 + 2x +1
		p1.getPolinom().add(new Monom(1, 5));
		p1.getPolinom().add(new Monom(3, 2));
		p1.getPolinom().add(new Monom(2,1));
		p1.getPolinom().add(new Monom(1,0));
		
		Polinom result = new Polinom();
		result = p1.derivPoly();
		// result=5x^4 + 6x + 2
		Polinom resTrue = new Polinom();
		resTrue.getPolinom().add(new Monom(5,4));
		resTrue.getPolinom().add(new Monom(6,1));
		resTrue.getPolinom().add(new Monom(2,0));
		
		assertEquals(result.toString(),resTrue.toString());
	}

	@Test
	public void testIntegrPoly() {
		p1 = new Polinom();
		p2 = new Polinom();

		// p1=3x^2 + 2x + 4
		p1.getPolinom().add(new Monom(3, 2));
		p1.getPolinom().add(new Monom(2, 1));
		p1.getPolinom().add(new Monom(4,0));
		
		Polinom result = new Polinom();
		result = p1.integrPoly();
		// resTrue=x^3 + x^2 + 4x
		Polinom resTrue = new Polinom();
		resTrue.getPolinom().add(new Monom(1,3));
		resTrue.getPolinom().add(new Monom(1,2));
		resTrue.getPolinom().add(new Monom(4,1));
		
		assertEquals(result.toString(),resTrue.toString());
	}

}
