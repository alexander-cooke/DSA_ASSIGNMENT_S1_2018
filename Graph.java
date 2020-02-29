import java.util.*;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* CLASS: Graph.java 
* Author: Alex Cooke (18406489)
* PURPOSE: The underlying implementation of this graph consists of two
* linked lists - called vertices and edges - each of which stores references
* to Intersection and Tunnel objects, respectively. Additionally,
* each of these objects store references to objects within the list with
* which they share a relationship (i.e. a tunnel stores references to 
* its terminal intersections and an intersection stores references to
* its surrounding tunnels.
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

public class Graph
{
    // private class fields
    private LList<Intersection> vertices; 
    private LList<Tunnel> edges;


   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * DEFAULT CONSTRUCTOR
    * IMPORT: None
    * EXPORT: None
    * PURPOSE: This constructor initialises an empty graph.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public Graph()
    {
        vertices = new LList<Intersection>();
        edges = new LList<Tunnel>();
    }


   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: readVertex
    * IMPORT: newVertex (Intersection)
    * EXPORT: None
    * PURPOSE: This method drives the process of figuring out whether any
    * given vertex should be added to the graph, or used to update a vertex
    * which already exists. 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void readVertex( Intersection newVertex )
    {
        if ( graphHasVertex( newVertex ) )
        {
            updateGraph( newVertex );
        }
        else
        {
            vertices.insertFirst( newVertex );
        }
    }


   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: graphHasVertex
    * IMPORT: newVertex (Intersection)
    * EXPORT: graphHasVertex (Boolean)
    * PURPOSE: This method loops through all currently existing vertices, 
    * checking for potential matches with newVertex
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public boolean graphHasVertex( Intersection newVertex )
    {
        boolean graphHasVertex = false;

        for( Intersection vertex : vertices )
        {
            if( vertex.equals( newVertex ) )
            {
                graphHasVertex = true;
            }
        }

        return graphHasVertex;
    }


   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: updateGraph
    * IMPORT: newVertex (Intersection)
    * EXPORT: None
    * PURPOSE: This method drives the modification of a vertex in the graph.
    * If newVertex has information that foundVertex doesn't (i.e. broken sensors),
    * then this info is added, then the serial identifier is updated.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void updateGraph( Intersection newVertex )
    {
        Intersection foundVertex;
        foundVertex = findIdenticalVertex( newVertex );

        foundVertex.updateMissingInfo( newVertex );
        foundVertex.updateID( newVertex.getSerialID(), ":" );
    } 


   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: findIdenticalVertex
    * IMPORT: newVertex (Intersection)
    * EXPORT: foundVertex (Intersection)
    * PURPOSE: This method works similarly to the graphHasVertex method, but
    * returns a reference to the Intersection with which newVertex matches,
    * instead of a boolean. Ideally, these two methods could be combined,
    * which would eliminate one of the for-each loops.
    * (i.e. if foundVertex = null, then this could be equivalent to false etc.)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public Intersection findIdenticalVertex( Intersection newVertex )
    {
        Intersection foundVertex = null;

        for( Intersection vertex : vertices )
        {
            if ( vertex.equals( newVertex ) )
            {
                foundVertex = vertex;
            }
        }

        return foundVertex;
    }

    
   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: readVertex 
    * IMPORT: newEdge (Tunnel), originalID (String), robotName (String)
    * EXPORT: None
    * PURPOSE: This method works similarly to readVertex, driving the process
    * behind figuring out whether an edge should be added to the graph, or used
    * to augment and existing one. It takes a few additional parameters which
    * are necessary for finding it's terminal intersections.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void readEdge( Tunnel newEdge, String originalID, String robotName )
    {
        if( graphHasEdge( newEdge ) )
        {
            updateGraph( newEdge, robotName );
        }     
        else
        {
            addEdge( newEdge, robotName, originalID );   
        }
    }


   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: graphHasEdge
    * IMPORT: newEdge (Tunnel)
    * EXPORT: hasEdge (Boolean)
    * PURPOSE: This method works similarly to graphHasVertex. I made it 
    * primiarly to improve readability of the readEdge method. It searches 
    * through the list of edges, and returns true if there's a match.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public boolean graphHasEdge( Tunnel newEdge )
    {
        boolean hasEdge = false;

        for ( Tunnel edge : edges )
        {
            if ( edge.equals( newEdge ) )
            {
                hasEdge = true;
            }
        }
        return hasEdge;   
    }    


   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: updateGraph
    * IMPORT: newEdge (Tunnel), robotName (String)
    * EXPORT: None
    * PURPOSE: If newEdge possesses data which the exisiting edge doesn't,
    * then it's added here.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void updateGraph( Tunnel newEdge, String robotName )
    {
        Tunnel foundEdge = null;
        foundEdge = findIdenticalEdge( newEdge );
        foundEdge.updateMissingInfo( newEdge, robotName );
    }

   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: findIdenticalEdge
    * IMPORT: newEdge (Tunnel)
    * EXPORT: foundEdge (Tunnel)
    * PURPOSE: This method returns a Tunnel object with which newEdge matches.
    * Again, this could be combined with the graphHasEdge method.
    * I should do that now...
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public Tunnel findIdenticalEdge( Tunnel newEdge )
    {
        Tunnel foundEdge = null;

        for ( Tunnel edge : edges )
        {
            if ( edge.equals( newEdge ) )
            {
                foundEdge = edge;
            }
        }
        
        return foundEdge;
    }

   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: addEdge 
    * IMPORT: newEdge (Tunnel), robotName (String), originalID (String)
    * EXPORT: None
    * PURPOSE: A few things must be taken care of before the newEdge is 
    * added to the graph. Its terminal intersections should be added
    * to its own list, and it should, in turn, be added to its terminal
    * intersection's list of surrounding tunnels. This can be done at the
    * same time.
    *
    * For example, robotbob reads in two intersections (5 and 87). bob5 and bob87
    * are either added to the list, or the named 'bob5' and 'bob87' are appended
    * to identical intersections already spotted by a previous robot. Either way,
    * when the program reads bob's tunnel 5-87, it can seach for 'bob5' and 'bob87'
    * and be confident that any intersection containing these sequences is a 
    * legitimate terminal intersection, and can add these two the tunnel's list.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void addEdge( Tunnel newEdge, String robotName, String originalID )
    {
        String[] delim = originalID.split("-");

        for ( Intersection vertex : vertices )
        {
            if ( vertex.getSerialID().contains( robotName + delim[0]) // bob87
               ||vertex.getSerialID().contains(robotName + delim[1])) // bob5
            {
                newEdge.getTerminalIntersections().insertFirst( vertex );
                vertex.getSurroundingTunnels().insertFirst( newEdge );
            }
        }

        edges.insertFirst( newEdge );
    }



   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: update 
    * IMPORT: None
    * EXPORT: None
    * PURPOSE: If the names of a tunnel's terminal intersections have changed,
    * then it's own serialID should be changed to reflect this. This only happens
    * after each robot has fully read in both tunnels and intersections. 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void update()
    {
        for ( Tunnel edge : edges )
        {
            edge.updateTunnelID(); // make more efficient so only called when name has changed
        }
    }

   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: sortAdjList 
    * IMPORT: None
    * EXPORT: None
    * PURPOSE: This method sorts the lists of vertices and edges according
    * to the assignment specification. I mean, it sorts the lines correctly.
    * The sorting of each serialID (bob5:alice1 -> alice1:bob5)is handled by 
    * the NetworkComponent class, which is the parent class of Tunnel and Intersection
    *
    * Now, I'm not super happy about this. All along I'd pictured mergeSorting
    * the linkedList itself. This wouldn't have required any auxillarly space - 
    * as opposed to merge sorting an array - and would've been fairly fast for the 
    * task at hand O(n(log(n)). I couldn't get it working, basically. Bummer.
    * I tried.
    * 
    * I know how to merge sort an array, though, and that's what I've chosen to
    * do. I copy the references to an array, sort them, and them add them
    * back to the linked lists. A quicksort would be better, and saves the 
    * auxillary space, I'll implement that if I have the time.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void sortAdjList()
    {
        Intersection[] v = new Intersection[ vertices.getCount() ];
        Tunnel[] e = new Tunnel[ edges.getCount() ];
        int i = 0;

        for( Intersection vertex : vertices )
        {
            v[i] = vertex;
            i++;
        } 

        i = 0;
        for( Tunnel edge : edges )
        {
            e[i] = edge;
            i++;
        }

        Sorting.mergeSort( v );
        Sorting.mergeSort( e );

        vertices.delete( vertices );
        edges.delete( edges );

        for ( int j = 0; j < v.length; j++ )
        {
                vertices.insertLast( v[j] );            
        }
        for ( int k = 0; k < e.length; k++ )
        {
            edges.insertLast( e[k] );
        }
        
    }

   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: writeToFile
    * IMPORT: date (String), studentID (String)
    * EXPORT: void
    * PURPOSE: This concatenates all the info contained within the graph
    * into a string, which is written to a file containing the relevant
    * date and studentID information via a writeToFile method in fileIO class.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void writeToFile( String date, String studentID )
    {
        String outStr = "";

        for ( Intersection vertex : vertices )
        {
            outStr += "i,";
            outStr += vertex.toString();
        }
        for ( Tunnel edge : edges )
        {
            outStr += ("t,");
            outStr += edge.toString();
        }

        FileIO.writeToFile( date, studentID, outStr );

    }
} // end Graph class 





