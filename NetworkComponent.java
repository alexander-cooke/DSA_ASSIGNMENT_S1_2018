
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* CLASS: NetworkComponent 
* Author: Alex Cooke (18406489)
* PURPOSE: This is the super class from which Tunnel and Intersection
* inherit their commonalities. This class is abstract, because you can't
* have a NetworkComponent without knowing whether it's a Tunnel or an
* Intersection
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public abstract class NetworkComponent
{
    // private class fields
    private String serialID;
    private double ceilingHeight;
    private ColourSensor floorColour;
    private ColourSensor wallColour;
    private ColourSensor ceilingColour;

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * ALTERNATE CONSTRUCTOR
    * PURPOSE: This constructs an NetworkComponent object according to a single
    * line of a robot's file ( This is the nodeData bit)
    *
    * From robotalice.csv:
    * nodeData[] = {i,325,3,2.6,blue,green,yellow}
    *
    * nodeData[3] = 2.6 (ceilingHeight)
    * nodeData[4] = blue (floorColour)
    * nodeData[5] = green (wallColour)
    * nodeData[6] = yellow (floorColour)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public NetworkComponent( String[] nodeData, String inSerialID  )
    {
        serialID =  inSerialID;

        
        setCeilingHeight( nodeData[3] );
        floorColour = new ColourSensor( nodeData[4] );
        wallColour = new ColourSensor( nodeData[5] );
        ceilingColour = new ColourSensor( nodeData[6] );
    }
    

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getSerialID 
    * IMPORT: None
    * EXPORT: serialID (String)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public String getSerialID()
    {
        return serialID;
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getCeilingHeight 
    * IMPORT: None
    * EXPORT: ceilingHeight (Real)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public double getCeilingHeight()
    {
        return ceilingHeight;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getFloorColour
    * IMPORT: None
    * EXPORT: floorColour (ColourSensor)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public ColourSensor getFloorColour()
    {
        return floorColour;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: wallColour 
    * IMPORT: None
    * EXPORT: wallColour (ColourSensor)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public ColourSensor getWallColour()
    {
        return wallColour;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getCeilingColour 
    * IMPORT: None
    * EXPORT: ceilingColour (ColourSensor)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public ColourSensor getCeilingColour()
    {
        return ceilingColour;
    }

    // mutators
   
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: setSerialID 
    * IMPORT: inSerialID (String)
    * EXPORT: None
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void setSerialID( String inSerialID )
    {
        serialID = inSerialID;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: setCeilingHeight 
    * IMPORT: inHeight (String)
    * EXPORT: None
    * PURPOSE: If a robot's ceilingHeight sensor is broken, then ceilingHeight
    * is set to a value of -1. If the value isn't a double (i.e. a character),
    * then an IllegalArgumentException is thrown. This exception is also
    * thrown if the ceilingHeight as out of the bounds specified in the
    * assignment outline. 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void setCeilingHeight( String inHeight )
    {
        double inHeightDouble;

        if ( inHeight.equals("_") )
        {
            ceilingHeight = -1;
        }
        else
        {
            inHeightDouble = Double.parseDouble( inHeight );
     
            if( validateRealNum( inHeightDouble, 0.0, 50.0 ) )
            {
                
                ceilingHeight = inHeightDouble;
            }
            else
            {
                throw new IllegalArgumentException( "Invalid ceiling height" );
            }
        }
    }


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: setCeilingHeight 
    * IMPORT: inHeight (Real)
    * EXPORT: None
    * PURPOSE: There are two setCeilingHeight methods. This one is used for coping
    * values between NetworkComponent's whose values have already been proven valid
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void setCeilingHeight( Double inHeight )
    {
        ceilingHeight = inHeight;



    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: setFloorColour
    * IMPORT: inSensor (ColourSensor)
    * EXPORT: void
    * PURPOSE: This creates a copy of inSensor, and sets that to floorColour
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void setFloorColour( ColourSensor inSensor )
    {
        floorColour = new ColourSensor( inSensor.getColour() );
    }



    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: setWallColour
    * IMPORT: inSensor (ColourSensor)
    * EXPORT: void
    * PURPOSE: This creates a copy of inSensor, and sets that to wallColour
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void setWallColour( ColourSensor inSensor )
    {
        wallColour = new ColourSensor( inSensor.getColour() );
    }



    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: setCeilingColour
    * IMPORT: inSensor (ColourSensor)
    * EXPORT: void
    * PURPOSE: This creates a copy of inSensor, and sets that to ceilingColour
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void setCeilingColour( ColourSensor inSensor )
    {
        ceilingColour = new ColourSensor( inSensor.getColour() );
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: Check whether you still need this. 
    * IMPORT: 
    * EXPORT: 
    * PURPOSE: 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void addToID( String inSerialID )
    {
        if ( this.serialID.equals("") )
        {
            this.serialID += inSerialID + "-";
        }
        else
        {
            this.serialID += inSerialID;
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: equals
    * IMPORT: inObj (Object)
    * EXPORT: equals (Boolean)
    * PURPOSE: Determines whether inObj is equal to this object. 
    * Two NetworkComponent objects are equal if their ceilingHeights are equal -
    * keeping in mind sensors are accurate to 1mm, which results in a tolerance
    * of 0.002 - or if either of the ceilingHeights are missing, and all of their 
    * ColourSensor objects are equal.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
     public boolean equals( NetworkComponent inObj )
    {
        boolean equal = false;


        if ( ( Math.abs( ceilingHeight + 1.0 ) < 0.0001 ) ||
             ( Math.abs( ceilingHeight - inObj.getCeilingHeight() ) < 0.002 ) ||
             ( Math.abs( inObj.getCeilingHeight() + 1.0 ) < 0.0001  ) 
           )
        {
            if ( (floorColour.equals( inObj.getFloorColour() ) ) )
            {
                if ( wallColour.equals( inObj.getWallColour() ) )
                {
                    if( ceilingColour.equals( inObj.getCeilingColour() ) )
                    {
                            equal = true;
                    }
                }
            }
            
        }

        return equal;

    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: compare
    * IMPORT: i1 (NetworkComponent), i2 (NetworkComponent)
    * EXPORT: lessThan (Boolean)
    * PURPOSE: Please clean this mess up. This is a static method used by
    * my mergeSort function to compare two NetworkComponent objects. It
    * returns true if the first NetworkComponent is "less than" the second
    * NetworkComponent according to the assignment specification.
    *
    * Two intersections will never share the same serialID. If they did,
    * they'd be the same intersection. Two tunnels, however, can share
    * the same serialID, and be different tunnels, just with the same start
    * and end points. If this is the case, it's necessary to see which length
    * is smaller, then which height (if lengths are equals), then which
    * floorColour( if heights are equals), then which ceilingColour
    * (if floor colour is equals) and so on. 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static boolean compare( NetworkComponent i1, NetworkComponent i2 )
    {
            int result;
            boolean lessThan = false;

            result = i1.getSerialID().compareTo( i2.getSerialID() );

            if ( result == 0 ) // names are equal ... must be tunnels with same endpoints
            {
                Tunnel i1T = (Tunnel) i1;
                Tunnel i2T = (Tunnel) i2;
                if ( ( i1T.getLength() < i2T.getLength() )) // check length
                {
                    lessThan = true;
                }
                else if ( i1T.getLength() == i2T.getLength() ) // if same length
                {
                    if( i1T.getCeilingHeight() < i2T.getCeilingHeight() ) // check height
                    {
                        lessThan = true;
                    }
                    else if ( i1T.getCeilingHeight() == i2T.getCeilingHeight() ) // if same height
                    {
                        if( i1T.getFloorColour().getRank() < i2T.getFloorColour().getRank() )
                        {
                            lessThan = true;
                        }
                        else if ( i1T.getFloorColour().getRank() == i2T.getFloorColour().getRank() )
                        {
                            if( i1T.getWallColour().getRank() < i2T.getWallColour().getRank() )
                            {
                                lessThan = true;
                            }
                            else if ( i1T.getWallColour().getRank() == i2T.getWallColour().getRank() )
                            {
                                if( i1T.getFloorColour().getRank() < i2T.getFloorColour().getRank() )
                                {
                                    lessThan = true;
                                }
                            }
                        }
                    }
                }
            }
            else if ( result < 0 )
            {
                lessThan = true;
            }

            return lessThan;
    }


    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: updateID 
    * IMPORT: newID (String), delimiter (String)
    * EXPORT: none
    * PURPOSE: If a robot reports a NetworkComponent which has already been
    * seen previously, then the serialID must be updated to indicate it's 
    * been seen by the latest robot. This robot's name might be alphabetically
    * before or after or somewhere in the middle, and so it's necessary to re-sort
    * the serialID so that it remains in alphabetical order.
    *
    * I've chosen to do an insertion sort here, because the serialID is only ever going
    * to be one swap away from being in perfect alphabetical order, and I'm assuming
    * that the number of robots that would report the same intersection is on
    * average going to be a pretty small number, if they're dispersed
    * across the whole moon...
    *
    * newID might be something like 'alice3', or 'bob87'
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void updateID( String newID, String delimiter ) 
    {
        String string[] = serialID.split(delimiter);
        // create an array that's one larger than the delimited serialID,
        // so that we can fit a new serialID into the mix for sorting
        String newArray[] = new String[ string.length + 1 ];
        String id = "";

        // fill up the array
        for ( int i = 0; i < newArray.length - 1; i++ )
        {
            newArray[i] = string[i];
        }

        // put the newID in the remaining position
        newArray[ newArray.length - 1 ] = newID;
        Sorting.insertionSort( newArray );
        

        // add the delimiter back in
        // i.e. ':' for intersection, and '-' for tunnels
        for ( int j = 0; j < newArray.length; j ++ )
        {
            id += newArray[j] + delimiter;
        }

        // remove the last delimiter from the end of the string
        id = id.substring( 0, id.length() - 1 );

        
        setSerialID( id );
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: updateMissingInfo
    * IMPORT: inComponent (NetworkComponent)
    * EXPORT: None
    * PURPOSE: This method replaces missing/inaccurate data. If two 
    * NetworkComponents are equal, but their ceilingHeights differ slightly
    * on account of the inaccuracy of the sensors, then the assignment
    * specifies that we're to take the value from the robot that sorts first
    * in alphabetic order. This is what happens here.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void updateMissingInfo( NetworkComponent inComponent )
    {
        if ( comesAlphabeticallyAfter( inComponent ) )
        {
            if( ! inComponent.sensorBroken( inComponent.getCeilingHeight() ) )
            {
                setCeilingHeight( inComponent.getCeilingHeight() );
            }
        }

        updateBrokenColourSensors( inComponent );
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: updateMissingInfo
    * IMPORT: inComponent (NetworkComponent), robotName (String) 
    * EXPORT: None
    * PURPOSE: This does exactly the same as the above method, but substitutes
    * the robot's name for alphabetic comparison, when a serialID is not yet
    * available, such as when reading in Tunnels.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void updateMissingInfo( NetworkComponent inComponent, String robotName )
    {
        if ( comesAlphabeticallyAfter( robotName ) )
        {
            if( ! inComponent.sensorBroken( inComponent.getCeilingHeight() ) )
            {
                setCeilingHeight( inComponent.getCeilingHeight() );
            }
        }

        updateBrokenColourSensors( inComponent );
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: comesAlphabeticallyAfter
    * IMPORT: inComponent (NetworkComponent)
    * EXPORT: comesAfter (Boolean)
    * PURPOSE: Returns true if the current objects comes
    * alphabetically after inComponent
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public boolean comesAlphabeticallyAfter( NetworkComponent inComponent )
    {
        boolean comesAfter = false;

        if ( serialID.compareTo( inComponent.getSerialID() ) > 0 )
        {
            comesAfter = true;
        }

        return comesAfter;
    } 


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: comesAlphabeticalltAfter
    * IMPORT: robotName (String)
    * EXPORT: comesAfter (Boolean)
    * PURPOSE: Returns true if the current object comes alphabetically
    * after robotName. This is necessary for checking Tunnels ID's,
    * because a Tunnel doesn't have an ID until it's determined its
    * terminal intersections. In this case we can just compare to the robot's
    * name
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public boolean comesAlphabeticallyAfter( String robotName )
    {
        boolean comesAfter = false;

        if ( serialID.compareTo( robotName ) > 0 )
        {
            comesAfter = true;
        }

        return comesAfter;
    } 

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: sensorBroken
    * IMPORT: value (Real)
    * EXPORT: doubleValueMissing (Boolean)
    * PURPOSE: This method determines whether the ceilingHeight has been
    * replaced with the dummy value of -1 (i.e. robot's ceilingHeight
    * sensor has malfunctioned)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public static boolean sensorBroken( double value )
    {
        boolean doubleValueMissing = false;

        if( Math.abs( value + 1.0 ) < 0.0001 )
        {
            doubleValueMissing = true;
        }

        return doubleValueMissing;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: updateBrokenColourSensors
    * IMPORT: inComponent (NetworkComponent)
    * EXPORT: None
    * PURPOSE: Calls each colourSensor's sensorBroken method, which achieve
    * similar thing to above method.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void updateBrokenColourSensors( NetworkComponent inComponent )
    {
        if( floorColour.sensorBroken() )
        {
            setFloorColour( inComponent.getFloorColour() );
        }

        if( wallColour.sensorBroken() )
        {
            setWallColour( inComponent.getWallColour() );
        }

        if( ceilingColour.sensorBroken() )
        {
            setCeilingColour( inComponent.getCeilingColour() );
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: validateRealNum
    * IMPORT: inValue (Real), lower (Real), upper (Real)
    * EXPORT: value (Boolean)
    * PURPOSE: Checks whether double value is within bounds. This method
    * is also used by Tunnel for validating length, hence it's protected,
    * rather than private
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    protected boolean validateRealNum( double inValue, double lower, double upper )
    {
        boolean valid = false;

        if( ( lower < inValue ) && ( inValue <= upper) )
        {
            valid = true;
        }

        return valid;
        
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: toString
    * IMPORT: None
    * EXPORT: State of object as String
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public String toString()
    {
        String height;

         // convert dummy value back to '_'
        if ( Math.abs( ceilingHeight + 1.0 ) < 0.0001 )
        {
            height = "_";
             
        }
        else
        {
            height = Double.toString( ceilingHeight );
        }
    
        return serialID + "," + "INFO," +
               height + "," +
               floorColour.getColour() + "," +
               wallColour.getColour() + "," +
               ceilingColour.getColour() + "\n";
    }

} // end NetworkComponent
