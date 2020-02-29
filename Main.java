import java.util.*;
import java.io.*;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* CLASS: Main.java 
* Author: Alex Cooke (18406489)
* PURPOSE: Reads each file, and creates appropriate Intersection and 
* Tunnel objects using the available data. The creation of these objects
* occurs within the createVertices and createEdges submodules. I added
* these to improve readability. 
*
* The basic design of my program can be summarised as follows:
* (1) For each robot:
*   (a) Create each Intersection object.
*       (i) If it's unlike anything else in the network, then add it.
*       (ii) Otherwise, update the relevant vertex to indicate that it's
*            also been spotted by the current robot.
*   (b) Create each Tunnel object.
*       (i) If it's unlike anything else, then add it.
*       (ii) Otherwise, update the relevant edge.
*
* (2) Sort the vertices and edges lists.
* (3) Write them to file.
*
* Why read each robot twice?
*
* Each Tunnel object contains a linked list of references to its terminal
* intersections, and its serialID is dependant upon the serialID's of these
* intersections. In my design, it's not possible for a tunnel to be 
* correctly named until all possible intersections for the current robot
* have been added to the graph. It's for this reason that each robot's
* intersections must be read first, and then the tunnels can be named
* accordingly. 
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class Main
{
    public static void main( String[] fileNames )
    {
        String robotName;
        String date;
        String[] lines;

        // date of execution, formatted as per assignment specification
        date = FileIO.getDateOfExecution();
        Graph phoebe = new Graph();
        try
        { 
            for ( int fileNumber = 0; fileNumber < fileNames.length; fileNumber++ )
            {
                lines = FileIO.getLines( fileNames[fileNumber] );
                robotName = FileIO.parseFileName( fileNames[fileNumber], "robot", ".csv" );

                for ( int lineNumber = 0; lineNumber < lines.length; lineNumber++ )
                {
                    createVertices( lineNumber, lines, robotName, phoebe  );
                }

                for ( int lineNumber = 0; lineNumber < lines.length; lineNumber++ )
                {
                    createEdges( lineNumber, lines, robotName, phoebe );
                }

                phoebe.update();
            }
            
            phoebe.sortAdjList();
            phoebe.writeToFile( date, "18406489" );
        }
        catch ( NumberFormatException e )
        {
            System.out.println( e.getMessage() );
        }
        catch( IllegalArgumentException e )
        {
            System.out.println( e.getMessage() );
        }
        
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: createVertices
    * IMPORT: lineNumber (Integer), lines (String array), robotName (String),
    *         phoebe (Graph) 
    * EXPORT: NONE
    * PURPOSE: This method creates the valid Intersection object expected
    * by Graph class. The graph itself determines whether this entry should
    * be added to the graph, or used to update an identical entry.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public static void createVertices( int lineNumber, String[] lines, String robotName, Graph phoebe  )
    {
        String[] nodeData = lines[lineNumber].split(",");

        if( nodeData[0].equals("i") )
        {
            Intersection newVertex = new Intersection( nodeData, robotName + nodeData[1] );
            phoebe.readVertex( newVertex );
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: createEdges
    * IMPORT: lineNumber (Integer), lines (String array), robotName (String),
    *         phoebe (Graph) 
    * EXPORT: NONE
    * PURPOSE: This method creates the valid Tunnel object expected by Graph
    * class. Again, the graph determines what should be done with this object. 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public static void createEdges( int lineNumber, String[] lines, String robotName, Graph phoebe )
    {
        String[] nodeData = lines[lineNumber].split(",");

        if( nodeData[0].equals( "t" ))
        {
            Tunnel newEdge = new Tunnel( nodeData, robotName );
            phoebe.readEdge( newEdge, nodeData[1], robotName );
        }
    }

}  

