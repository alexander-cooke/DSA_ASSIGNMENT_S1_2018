/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * NAME: Alex Cooke (18406489)
 * CLASS: FileIO.java
 * DISCLAIMER: This class contains FileIO methods that I wrote as part of
 * Practical 1. Those methods however ( getNumLines(), readFile(), and writeFile() )
 * were written basically according to how we'd been taught in OOPD.
 *
 * I've modified this class to include other FileIO methods that I've used
 * in my design. These include getLines(), parseFileName() and
 * getDateOfExecution()
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.util.*;
import java.io.*;
import java.text.*;

public class FileIO
{
   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getNumLines
    * IMPORT: fileName (String)
    * EXPORT: numLines (Integer)
    * PURPOSE: Generic method that returns the number of lines 
    * in a given file. 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
   public static int getNumLines( String fileName ) 
   {
      FileInputStream fileStrm = null;
      InputStreamReader rdr;
      BufferedReader bufRdr;
      int numLines = 0;
      String line;

      try
      {
         fileStrm = new FileInputStream( fileName ); 
         rdr = new InputStreamReader( fileStrm ); 
         bufRdr = new BufferedReader( rdr ); 

         line = bufRdr.readLine();  

         while ( line != null ) // while not EOF
         {
            numLines += 1;
            line = bufRdr.readLine();
         }
      }
      catch( IOException e)
      {
          System.out.println( e.getMessage() );
      }
      finally
      {
         try
         {
            fileStrm.close();
         }
         catch(IOException e)
         {
            System.out.println( e.getMessage() );
         }

      }

      return numLines;

   } // end getNumLines


   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: readFile
    * IMPORT: fileName (String), numLines (Integer)
    * EXPORT: eachLine (ARRAY OF String);
    * PURPOSE: A generic method which reads in a file and returns an array
    * containing a single line of the .csv file at each index.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
   public static String[] readFile( String fileName, int numLines ) 
   {
       FileInputStream fileStrm = null;
       InputStreamReader rdr;
       BufferedReader bufRdr;
       String line;
       String[] eachLine = new String[ numLines ];
       

       try
       {
          fileStrm = new FileInputStream( fileName);
          rdr = new InputStreamReader( fileStrm );
          bufRdr = new BufferedReader( rdr );

          line = bufRdr.readLine();

          for ( int i = 0; i < numLines; i++ )
          {
              eachLine[i] = line;
              line = bufRdr.readLine();
          }

       }
       catch( IOException e )
       {
           System.out.println( e.getMessage() );
       }

       finally
       {
           try
           {
               fileStrm.close();
           }
           catch (IOException e )
           {
               System.out.println( e.getMessage() );
           }
       }

      return eachLine;

   } // end readFile


   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getLines
    * IMPORT: file (String)
    * EXPORT: lines (array of String)
    * PURPOSE: This method facilitates reading each file, and returns
    * all the lines within each file as an array, where each successive line
    * is stored at each successive index.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
   public static String[] getLines( String file )
   {
       int numLines;
       String[] lines;

       numLines = FileIO.getNumLines( file );
       lines = FileIO.readFile( file, numLines );

       return lines;
   }

   
   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: parseFileNames
    * IMPORT: fileName (String), start (String), end (String)
    * EXPORT: section (String)
    * PURPOSE: Returns a section of the file's name between start and end.
    * Any associated local or global paths are removed from the string.
    * (i.e. input/robotalice.csv -> alice 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
   public static String parseFileName( String fileName, String start, String end ) 
   {
       String[] delimitedFileName  ;
       String section;
       delimitedFileName = fileName.split("/");
       section = delimitedFileName[ delimitedFileName.length - 1 ]; // remove any global/local path
       section = section.replace(start, "").replace(end, "" );
        
       return section;
    }
    

   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getDateOfExecution 
    * IMPORT: None
    * EXPORT: date (String)
    * PURPOSE: This method returns the time, formatted as per the assignment
    * specification, of when the first file is read. 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public static String getDateOfExecution()
    {
        String date;

        Date dateObject = new Date();
        date = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format( dateObject );

        return date;        
    }



   /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: writeToFile
    * IMPORT: stringToWrite (String)
    * EXPORT: None
    * PURPOSE: Writes contents of string to "output.txt", delimited by "/"
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  */
   public static void writeToFile( String date, String studentID, String outStr )
   {
       
       FileOutputStream fileStrm = null;
       PrintWriter pw;
       String fileName = "combined-" + studentID + "-" + date + ".csv";
       String[] toOutput;

       toOutput = outStr.split("\n");

       try
       {
           fileStrm = new FileOutputStream( fileName );
           pw = new PrintWriter( fileStrm );

           for ( int i = 0; i < toOutput.length; i++ )
           {
               pw.println(toOutput[i]);
           }

           pw.close();
       }
       catch( IOException e )
       {
           System.out.println("There was an IO error while writing");
       }
       finally
       {
           try
           {
               fileStrm.close();
           }
           catch (IOException e )
           {
               System.out.println("There was an IO while closing file");
           }
       }
   } // writeToFile




} // end FileIO class
