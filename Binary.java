/* Sarah Yoon
   APCS1 pd10
   HW43 -- This or That
   2015-12-07 */

public class Binary implements Comparable{

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
	_decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
	_decNum = n;
	_binNum = decToBin(n);
    }

    public int getDecNum() {return _decNum;};
    
    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
	_binNum = s;
	_decNum = binToDec(s);
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	return _binNum;
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
	String retStr = "";
	int q = n;
	while (q!=0) {
	    retStr = q%2 + retStr;
	    q /= 2;
	}
	if (retStr.substring(0,1).equals("0")){
	    return retStr.substring(1);
	}
	else {
	    return retStr;
	}
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) { 
	if (n==0) {return "";}
	else {
	    return n%2 + decToBinR(n/2);
	}
    }


    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	int sum = 0;
	int f = Integer.parseInt(s);
	for(int i=0; i<s.length(); i++) {
	    sum += f%10 * Math.pow(2,i);
	    f/=10;
	}
	return sum;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) { 
	if (s.length() == 0) {return 0;}
	else {
	    return (int)(Integer.parseInt(s.substring(0,1)) * Math.pow(2, s.length()-1)) + binToDecR(s.substring(1));
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) { 
	return this == other || this.compareTo(other) == 0;
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	if (other instanceof Comparable) {
	    if (other instanceof Binary) {
		if (this._decNum == ((Binary)other).getDecNum()) {return 0;}
		else if (this._decNum > ((Binary)other).getDecNum()) {return 1;}
		return -1;
	    }
	    else if (other instanceof Hexadecimal) {
		if (this._decNum == ((Hexadecimal)other).getDecNum()) {return 0;}
		else if (this._decNum > ((Hexadecimal)other).getDecNum()) {return 1;}
		return -1;
	    }
	    else {
		if (other instanceof Rational) {
		    if (this._decNum == ((Rational)other).floatValue()) {return 0;}
		    else if (this._decNum > ((Rational)other).floatValue()) {return 1;}
		    return -1;
		}
	    }
	}
	throw new ClassCastException("\ncompareTo() input not comparable\n");
	//throw new NullPointerException("\ncompareTo() input is null\n");
    }
    //main method for testing
    public static void main( String[] args ) {
	
	System.out.println();
	System.out.println( "Testing ..." );

	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(7);

	System.out.println( b1 ); //should be 101
	System.out.println( b2 ); //should be 101
	System.out.println( b3 ); //should be 101
	System.out.println( b4 ); //should be 111     

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos

    }//end main()

} //end class
