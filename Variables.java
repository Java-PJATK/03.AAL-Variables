// AAL-Variables/Variables.java
 
public class Variables {
    public static void main(String[] args) {
        int    ifour = 4;
        double xhalf = 0.5;
        double four  = ifour;    // automatic conversion
        // int badFour = 4.0;    // WRONG
        int k = 1, m, n = k + 3;
        m = 2;
        final double two = xhalf*ifour;
        // two = two + 2;        // WRONG
        boolean b = true;
        if (b) System.out.println(
                "k=" + k + ", m = " + m + ", n = " + n +
                "\nSum by 4 is equal to " + (k+m+n)/ifour);
        String john,             // does string john exist?
               mary = "Mary";
        john = "John";
        System.out.println(john + " and " + mary);
        john = mary;
          // reference copied, string "John" lost!
        System.out.println(john + " and " + mary);
    }
}
