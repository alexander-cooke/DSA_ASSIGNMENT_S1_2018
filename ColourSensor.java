/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* CLASS: ColourSensor.java 
* Author: Alex Cooke (18406489)
* PURPOSE: Defines the functionality and data associated with
* any of the robot's data relating to colour (i.e floor, wall, and
* ceiling colour). Considering that each colour possesses an associated
* rank, as does the "_" field, and its own method for determing equality,
* it seemed reasonable to create a colourSensor class 
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class ColourSensor
{
    private String colour;
    private int rank;

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * ALTERNATE CONSTRUCTORR
    * IMPORT: inColour (String)
    * PURPOSE: Set colour to inColour, and set the rank associated with this
    * colour. The assignment states that the colours are always reported with
    * perfect accuracy (unless the sensor is broken), and so I've assumed this
    * for the rest of the implmentation of this class
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public ColourSensor( String inColour )
    {
        colour = inColour;
        rank = setRank();
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getColour
    * EXPORT: colour (String)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public String getColour()
    {
        return colour;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getRank
    * IMPORT: None
    * EXPORT: rank (Integer)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public int getRank()
    {
        return rank;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: setColour 
    * IMPORT: inColour (String)
    * EXPORT: None
    * PURPOSE: Sets colour to inColour
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void setColour( String inColour )
    {
        colour = inColour;
        rank = setRank();
    }



    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: setRank 
    * IMPORT: None
    * EXPORT: rank (Integer)
    * PURPOSE: Determines rank for each particular colour. This is important
    * when sorting the final entries, as each colour as a different precedence
    * as specified in section 1.3.4 of the assignment. "_" has the lowest rank,
    * which is also stated in the assignment outline. 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public int setRank()
    {
        if ( colour.equals("black") )
        {
            rank = 1;
        }
        else if ( colour.equals("white") )
        {
            rank = 2;
        }
        else if ( colour.equals("red") )
        {
            rank = 3;
        }
        else if ( colour.equals("green") )
        {
            rank = 4;
        }
        else if ( colour.equals("blue") )
        {
            rank = 5;
        }
        else if ( colour.equals("yellow") )
        {
            rank = 6;
        }
        else if ( colour.equals("silver") )
        {
            rank = 7;
        }
        else if ( colour.equals("gold" ) )
        {
            rank = 8 ;
        }
        else if ( colour.equals("_") )
        {
            rank = 0;
        }
        return rank;
        
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: equals
    * IMPORT: inObj (Object)
    * EXPORT: equals (Boolean)
    * PURPOSE: Returns true if this ColourSensor is equal to inObj.
    * Two colourSensors are equal if they posses an identical colour, or 
    * either of their sensors are broken.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public boolean equals( ColourSensor inObj )
    {
        boolean equals = false;

        if ( colour.equals("_") ||
             inObj.getColour().equals("_") ||
             colour.equals( inObj.getColour() )
           )
        {
            equals = true;
        }

        return equals;

    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: equals
    * IMPORT: inColour (String)
    * EXPORT: equal (Boolean)
    * PURPOSE: Returns true if two ColourSensors return the same colour
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public boolean equals( String inColour )
    {
        boolean equal = false;

        if ( colour.equals( inColour ) )
        {
            equal = true;
        } 
        
        return equal;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: sensorBroken 
    * IMPORT: None
    * EXPORT: sensorBroken (Boolean)
    * PURPOSE: Returns true if colourSensor is broken (i.e. robot has reported
    * "_" instead of a valid colour
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public boolean sensorBroken()
    {
        boolean sensorBroken = false;

        if( colour.equals( "_" ) )
        {
            sensorBroken = true;
        }

        return sensorBroken;
    }

    
}
