/* Team Plebes Not Plebs  -- Sarah Yoon, Nick Ng
   APCS1 pd10
   HW41 -- In America, The Driver Sits on the Left
   2015-12-03 */

public class Rational implements Comparable {
   
    private int numer;
    private int denom;

    // Default Setting
    public Rational() {
	numer = 0;
	denom = 1;
    }

    public Rational(int n, int d) {
	this();
	// denominator cannot be zero
	if (d == 0) {
	    System.out.println("Invalid. Setting to 0/1");
	}
	else {
	    numer=n;
	    denom=d;
	}
    }

    public String toString() {
	return numer + "/" + denom;
    }

    // returns the most precise float version of the rational
    public double floatValue() {
	return ((double)numer)/denom;
    }

    //The next four are kind of self explanatory.
    public void multiply(Rational factor) {
	numer *= factor.numer;
	denom *= factor.denom;
    }

    public void divide(Rational factor) {
	denom *= factor.numer;
	numer *= factor.denom;
    }

    public void add(Rational factor) {
	numer = numer*factor.denom + denom*factor.numer;
	denom *= factor.denom;
    }
	
    public void subtract(Rational factor) {
	numer = numer*factor.denom - denom*factor.numer;
	denom *= factor.denom;
    }

    //We kind of did part of Phase 3 before Phase 2, in that we implemented a static gcd method. But this gcd method doesn't take parameters. Objects can call it to get the gcd between their numerator and denominator. 
    public int gcd() {
	int gcdVal = 0;
	
	if (numer > denom) {
	    gcdVal = gcd(numer, denom);
	}
	else {
	    gcdVal = gcd(denom, numer);
	}      
	
	return gcdVal;
    }

    //It divides the numerator and denominator by the gcd. 
    public void reduce() {
	int gcdVal = gcd();
	numer /= gcdVal;
	denom /= gcdVal;
    }

    //This is the static version of the gcd. It's just Euclid's algo.
    public static int gcd(int p, int q) {
        while (q != 0) {
	    int temp = q;
	    q = p % q;
	    p = temp;
	}
	return Math.abs(p);
    }

    public int compareTo(Object rat) {
	boolean alias = this == rat;
	if (!alias) {
	    if (rat instanceof Rational) {
		Rational temp = new Rational(this.numer, this.denom);
		temp.subtract((Rational)rat);
		if (temp.numer==0) {return 0;}
		else if (temp.numer < 0) {return -1;}
		else {return 1;}
	    }
	    else {throw new ClassCastException("\ncompareTo() input not a Rational");}
	}
	else {return 0;}
    }
    
    public boolean equals(Object rat) {
	if (rat instanceof Rational) {
	    return compareTo((Rational)rat) == 0;
	}
	else {throw new ClassCastException("\nequals() input not a Rational");}
    }


    public static void main(String[] args) {
	Rational r = new Rational (2,3);
	Rational s = new Rational (1,2);
	Rational t = new Rational (4,0);
	Rational u = new Rational (4,6);
	Rational v = new Rational (2,3);
	String w = "fun";

	System.out.println("Testing floatValue()");
	System.out.println("s: " + s.floatValue());
	System.out.println("r: " + r.floatValue());

	System.out.println("Testing toString()");
	System.out.println("s:" + s);
	System.out.println("r:" + r);
	System.out.println("t:" + t);
	System.out.println("u:" + u);
	System.out.println("v:" + v);
	System.out.println("w:" + w);

	System.out.println("Testing multiply()");
	r.multiply(s);
	System.out.println("s:" + s);
	System.out.println("r:" + r);

	System.out.println("Testing divide()");
	r.divide(s);
	System.out.println("s:" + s);
	System.out.println("r:" + r);

	System.out.println("Testing add()");
	r.add(s);
	System.out.println("s:" + s);
	System.out.println("r:" + r);

	System.out.println("Testing subtract()");
	r.subtract(s);
	System.out.println("s:" + s);
	System.out.println("r:" + r);

	System.out.println("Testing reduce()");
	r.reduce();
	s.reduce();
	System.out.println("s:" + s);
	System.out.println("r:" + r);

	System.out.println("Testing compareTo()");
	System.out.println("r.compareTo(s): " + r.compareTo(s));
	System.out.println("s.compareTo(r): " + s.compareTo(r));
	System.out.println("t.compareTo(r): " + t.compareTo(r));
	System.out.println("r.compareTo(r): " + r.compareTo(r));
	System.out.println("r.compareTo(v): " + r.compareTo(v));

	System.out.println("Testing equals()");
	System.out.println("r.equals(s): " + r.equals(s));//This tests for inequality
	System.out.println("s.equals(s): " + s.equals(s));//This tests for reflexive equality (and aliasing)
	System.out.println("t.equals(r): " + t.equals(r));//This tests for inequality
	System.out.println("r.equals(u): " + r.equals(u));//This tests for the value equality (with reduction)
	System.out.println("r.equals(v): " + r.equals(v));//This tests for the value equality (without reduction)
	System.out.println("t.equals(w): " + t.equals(w)); //This tests for the different Object inequality
	
    }


}
	
	
    
