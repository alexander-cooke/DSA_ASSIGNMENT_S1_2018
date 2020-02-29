/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* CLASS: Intersection 
* Author: 18406489
* PURPOSE: This class inherits from NetworkComponent, and contains the
* rest of the information and behaviour required by an Intersection object
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class Intersection extends NetworkComponent
{
    // public class fields
    private int degree;
    private LList<Tunnel> surroundingTunnels;

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * ALTERNATE CONSTRUCTOR
    * IMPORT: nodeData (String array), robotName (String)
    * PURPOSE: 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public Intersection( String[] nodeData, String robotName )
    {
        super( nodeData, robotName );
        try
        {
            this.degree = Integer.parseInt( nodeData[2] ); 
        }
        catch( NumberFormatException e )
        {
            System.out.println( "Invalid degree" );
            throw e;
        }

        surroundingTunnels = new LList<Tunnel>();
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getDegree 
    * EXPORT: degree (Integer)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public int getDegree()
    {
        return degree;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getSurroundingTunnels 
    * EXPORT: surroundingTunnels (Linked list of Tunnel objects)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public LList<Tunnel> getSurroundingTunnels()
    {
        return surroundingTunnels;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: equals 
    * IMPORT: inObj (Object)
    * EXPORT: equal (Boolean)
    * PURPOSE: Returns true if inObj and this Intersection are equal
    * They're equal is their degree is equal. I'm assuming degree is always
    * reported perfectly, as per assignment specification
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public boolean equals( Intersection inObj )
    {
        boolean equal = false;

        if( super.equals( inObj ))
        {
            if ( degree == inObj.getDegree() )
            {
                    equal = true;
            }
        }
        return equal;
    }

    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: toString
    * EXPORT: String describing state of current object
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public String toString()
    {
        return super.toString().replace("INFO", String.valueOf( degree) );
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: compare 
    * IMPORT: i1( Intersection), i2(Intersection)
    * EXPORT: lessThan (Boolean)
    * PURPOSE: This comparison function is used by mergeSort to correctly
    * sort final output. It compares the serialID of two Intersections
    * and returns true if the first comes alphabetically after the second.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public static boolean compare( Intersection i1, Intersection i2 )
    {
        int result;
        boolean lessThan = false;

        result = i1.getSerialID().compareTo( i2.getSerialID() );

        if ( result < 0 )
        {
            lessThan = true;
        }

        return lessThan;

    }

} // end Intersection class
