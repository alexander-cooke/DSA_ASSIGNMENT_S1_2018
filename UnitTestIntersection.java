/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* File: unitTestIntersection.java
* Author: Alex Cooke (18406489)
* PURPOSE: Test Intersection class
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.io.*;

public class UnitTestIntersection
{
	public static void main(String args[])
	{
        Intersection inter1 = null;
        Intersection inter2 = null;
        Tunnel tunnel = null;
        int numTests = 0;
        int numPassed = 0;
       
        // test 1
        numTests++;
        System.out.println("Testing Intersection constructor: ");
        String[] nodeData1 = {"i","325","3","2.6","blue","green","yellow"};
        inter1 = new Intersection( nodeData1, "alice" );
        
        if ( inter1.getDegree() == 3 )
        {
            numPassed++;
            System.out.println( "Intersection created\n" );
        }


        // test 2
        numTests++;
        System.out.println("Testing surroundingTunnels: ");
        String[] nodeData2 = {"t","325-4623","3.8","2.2","blue","black","silver"};
        tunnel = new Tunnel( nodeData2, "alice" );
        inter1.getSurroundingTunnels().insertFirst( tunnel );
        if ( inter1.getSurroundingTunnels().getCount() == 1 )
        {
            numPassed++;
            System.out.println( "Tunnel added to surroundingTunnels\n" );
        }


        // test 3
        numTests++;
        System.out.println("Testing equals(): ");
        inter2 = new Intersection( nodeData1, "alice" );
        if ( inter1.equals( inter2) )
        {
            numPassed++;
            System.out.println( "Equals() succesfull\n" );
        }


        // test 4
        numTests++;
        System.out.println("Testing toString(): ");
        System.out.println( inter1.toString() );
        
        if( inter1.toString().equals("alice,3,2.6,blue,green,yellow\n"))
        {
            System.out.println( "toString() succesfull" );
            numPassed++;
        }

        System.out.println("numTests: " + numTests );
        System.out.println("numPassed: " + numPassed );
     }
}
