/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* File: unitTestTunnel.java
* Author: Alex Cooke (18406489)
* PURPOSE: Test Tunnel class
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.io.*;

public class UnitTestTunnel
{
	public static void main(String args[])
	{
        Intersection inter1 = null;
        Tunnel tunnel1 = null;
        Tunnel tunnel2 = null;
        int numTests = 0;
        int numPassed = 0;
       
        // test 1
        numTests++;
        System.out.println("Testing Tunnel constructor: ");
        String[] nodeData1 = {"t","325-4623","3.8","2.2","blue","black","silver"};
        tunnel1 = new Tunnel( nodeData1, "alice" );
        
        if ( Math.abs( tunnel1.getLength() - 3.8 ) < 0.001 )
        {
            numPassed++;
            System.out.println( "Tunnel created\n" );
        }


        // test 2
        numTests++;
        System.out.println("Testing terminalIntersections: ");
        String[] nodeDataInter = {"i","325","3","2.2","blue","black","silver"};
        inter1 = new Intersection( nodeDataInter, "alice" );
        tunnel1.getTerminalIntersections().insertFirst( inter1 );
        if ( tunnel1.getTerminalIntersections().getCount() == 1 )
        {
            numPassed++;
            System.out.println( "Intersection added to terminalIntersections\n" );
        }


        // test 3
        numTests++;
        System.out.println("Testing equals(): ");
        tunnel2 = new Tunnel( nodeData1, "alice" );
        if ( tunnel1.equals( tunnel2) )
        {
            numPassed++;
            System.out.println( "Equals() succesfull\n" );
        }


        // test 4
        numTests++;
        System.out.println("Testing toString(): ");
        System.out.println( tunnel1.toString() );
        
        if( tunnel1.toString().equals("alice,3.8,2.2,blue,black,silver\n"))
        {
            System.out.println( "toString() succesfull" );
            numPassed++;
        }

        System.out.println("numTests: " + numTests );
        System.out.println("numPassed: " + numPassed );
     }
}
