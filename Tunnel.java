/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* CLASS: Tunnel.java 
* Author: Alex Cooke (18406489)
* PURPOSE: This class inherits from NetworkComponent, and contains the
* rest of the state and behaviour attributed to a tunnel.
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class Tunnel extends NetworkComponent
{
    // public class fields
    private double length;
    private LList<Intersection> terminalIntersections;


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * ALTERNATE CONSTRUCTOR
    * IMPORT: nodeData (String array), robotName (String)
    * PURPOSE: This initialises class fields, based on a line from a 
    * robot's file. nodeData[2] is the third entry in the line, which represents
    * Tunnel's length
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public Tunnel ( String[] nodeData, String robotName )
    {
        super( nodeData, robotName );
        setLength( nodeData[2] ); // length requires validation


        terminalIntersections = new LList<Intersection>();
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getLength
    * IMPORT: None
    * EXPORT: length (Real)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public double getLength()
    {
        return length;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getTerminalIntersections 
    * IMPORT: None
    * EXPORT: terminalIntersections (LinkedList of Intersections )
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public LList<Intersection> getTerminalIntersections()
    {
        return terminalIntersections;
    }

   

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: setLength
    * IMPORT: inLength (String)
    * EXPORT: None
    * PURPOSE: Sets length to a valid state. If tunnel's length sensor is
    * broken, then this is indicated by setting length to a value of -1.
    * If length is out of bounds as specified in assignment specification,
    * Or not a valid double, then throw IllegalArgumentException.
    * ASSUMPTION: Even though sensors are inaccurate to 1%, the specification
    * states that a tunnel may be no more than 100.0 kilometers long. Because
    * of this, I'm assuming that a robot can't report a measurement of greater
    * then 100km
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void setLength( String inLength )
    {
        double inLengthDouble;            

        if ( inLength.equals("_") )
        {
            length = -1.0;
        }
        else
        {
            inLengthDouble = Double.parseDouble( inLength );
            if ( super.validateRealNum( inLengthDouble, 0.0, 100.0 ) )
            {
                length = inLengthDouble;
            }
            else
            {
                throw new IllegalArgumentException( "Invalid tunnel length" );
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: Don't need this anymore
    * IMPORT: 
    * EXPORT: 
    * PURPOSE: 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void setLength( double inLength )
    {
        length = inLength;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: updateTunnelID 
    * IMPORT: None
    * EXPORT: None
    * PURPOSE: This method help keep tunnel's serialID is alphbetic order.
    * It concatenates the serialIDs of its terminal intersections, alphabetises
    * them, then sets the ID to the proper value.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void updateTunnelID()
    {
        String id = "";
    
        for ( Intersection intersection : terminalIntersections )
        {
            if ( id.equals("") )
            {
                id += intersection.getSerialID() + "-";
            }
            else
            {
                id += intersection.getSerialID();
            }
        }
        id = alphabetizeSerialID ( id ); // should be in this class
        super.setSerialID( id );
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: alphabetizeSerialID
    * IMPORT: serialID (String)
    * EXPORT: newSerialID (String)
    * PURPOSE: This method is responsible for alphabetizing the tunnel's
    * serialID. It compares the two parts of the ID, and swaps them if
    * they're alphabetically out of order
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
      // Assumption: alice3 is alphabetically before alice30, or alice31 etc.
    public String alphabetizeSerialID( String serialID ) // Overload for intersections
    {
        int result;
        String temp;
        String[] string = serialID.split("-");

        result = string[0].toLowerCase().compareTo( string[1].toLowerCase() );

        if( result > 0 )// they are the wrong way around
        {
            temp = string[0];
            string[0] = string[1];
            string[1] = temp;
        }
        return new String( string[0] + "-" + string[1]);
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: equals 
    * IMPORT: inObj (Object)
    * EXPORT: equal (Boolean)
    * PURPOSE: This method returns true if this Tunnel and inObj are equal.
    * They're equal iff either of their length values are missing, or if
    * their length values are equal, taking into account that each sensor
    * is inaccurate by up to %1.
    *
    * Assumption: inaccurate by up to 1% means plus or minus 1%
    *
    * Given the potential inaccuracy, I've calculated the tolerance value as follows:
    * Find maximum and minimum possible value for each length.
    * For two length to be feasibly equal given inaccuracy, the distance between
    * the values must be within the common range covered by both values.
    *(i.e. must fall between the biggest value on the lower bound, and the smallest
    * value of the upper bound. This is the tolerance value )
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public boolean equals( Tunnel inObj )
    {
        boolean equal = false;

        double length1min;
        double length1max;
        double length2min;
        double length2max;
        double biggestSmall;
        double smallestBig;
        double tolerance;

        length1min = length * 0.99;
        length1max = length * 1.01;
        length2min = inObj.getLength() * 0.99;
        length2max = inObj.getLength() * 1.01;

        biggestSmall = ( length1min > length2min ) ? length1min : length2min;
        smallestBig = ( length1max < length2max ) ? length1max : length2max;

        tolerance = smallestBig - biggestSmall;

        if( super.equals( inObj ) )
        {
            if( ( Math.abs( length - inObj.getLength() ) <= tolerance ) ||
                ( Math.abs( length + 1.0 ) < 0.0001 ) ||
                ( Math.abs( inObj.getLength() + 1.0 ) < 0.0001 )
              )
            {
                equal = true;
            }
        }
        return equal;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: compare
    * IMPORT: t1 (Tunnel), t2 (Tunnel)
    * EXPORT: lessThan (boolean)
    * PURPOSE: This is part of the comparison function used by merge sort
    * for sorting lists. It compares two tunnel's serialID's alphabetically.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public static boolean compare( Tunnel t1, Tunnel t2 )
    {
        int result;
        boolean lessThan = false;

        result = t1.getSerialID().compareTo( t2.getSerialID() );

        if ( result < 0 )
        {
            lessThan = true;
        }

        return lessThan;

    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: updateMissingInfo
    * IMPORT: inTunnel (Tunnel)
    * EXPORT: None
    * PURPOSE: This works kind of like an equals method. It first calls
    * the parent classes updateMissingInfo function, then updates inaccurate
    * length if serialID comes alphabetically after new Tunnel's serialID
    * as per the assignment specification.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void updateMissingInfo( Tunnel inComponent, String robotName )
    {
        super.updateMissingInfo( inComponent );
        Tunnel inTunnel = (Tunnel) inComponent;

        if( comesAlphabeticallyAfter( robotName ) )
        {
            if( ! inComponent.sensorBroken( inTunnel.getLength() ) )
            {
                setLength( inTunnel.getLength() );
            }
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: toString
    * IMPORT: None
    * EXPORT: String representing Tunnel's current state. Replaces INFO
    * with the length of the Tunnel.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public String toString()
    {
        return super.toString().replace("INFO", String.valueOf(length) );
    }

    

}
