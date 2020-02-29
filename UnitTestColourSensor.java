/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* File: unitTestColourSensor.java
* Author: Alex Cooke (18406489)
* PURPOSE: Test ColouSensor
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.io.*;

public class UnitTestColourSensor
{
	public static void main(String args[])
	{
        ColourSensor sensor1 = null;
        ColourSensor sensor2 = null;
        int numTests = 0;
        int numPassed = 0;
       
        // test 1
        numTests++;
        System.out.println("Testing ColourSensor constructor: ");
        sensor1 = new ColourSensor( "white" );
        if ( sensor1.getColour().equals( "white") )
        {
            numPassed++;
            System.out.println( "ColourSensor succesfully created\n" );
        }


        // test 2
        numTests++;
        System.out.println("Testing getRank(): ");
        if ( sensor1.getRank() == 2 )
        {
            numPassed++;
            System.out.println( "getRank() succesfull\n" );
        }


        // test 3
        numTests++;
        System.out.println("Testing setColour(): ");
        sensor1.setColour( "black" );
        if ( sensor1.getColour().equals("black") )
        {
            numPassed++;
            System.out.println( "setColour() successful\n" );
        }


        // test 4
        numTests++;
        System.out.println("Testing getRank() after colour change: ");
        if ( sensor1.getRank() == 1 )
        {
            numPassed++;
            System.out.println( "getRank() after colour change succesfull\n" );
        }

        // test 5
        numTests++;
        System.out.println("Testing sensorBroken(): ");
        sensor1.setColour("_");
        if ( sensor1.sensorBroken() )
        {
            numPassed++;
            System.out.println( "sensorBroken() succesfull\n" );
        }

        // test 6
        numTests++;
        System.out.println("Testing equals(): ");
        sensor2 = new ColourSensor("_");
        if ( sensor1.equals(sensor2) )
        {
            numPassed++;
            System.out.println( "equals() succesfull\n" );
        }

        System.out.println("numTests: " + numTests );
        System.out.println("numPassed: " + numPassed );
     }
}
