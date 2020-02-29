/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* CLASS: Sorting 
* Author: Alex Cooke (18406489)
* PURPOSE: This is a collection of static sorting methods. Both are
* modified versions of the sorts I completed as part of my Practical 2.
* I've modified the insertion sort to sort an array of Strings. The 
* mergeSort has been modified to sort an array of NetworkComponents,
* and uses the 'compare' function contained within the 
* NetworkComponent class and its subclasses.
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class Sorting
{
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: Insertion sort 
    * IMPORT: A (String array)
    * EXPORT: None
    * PURPOSE: Performs an insertion sort of an array of strings. Given
    * that the serialIDs which it sorts are only ever one swap away from
    * being back in order, and that the serialID's are also never too long
    * (well, the names are long, but the number of elements compared to the
    * overall number of robots shouldn't be too large), insertion sort
    * seemed like a reasonable option. 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public static void insertionSort( String[] A)
    {
        String temp;

        for( int nn = 1; nn <= ( A.length - 1 ); nn++ ) 
                                               
        {
            int ii = nn; // ii begins at incrementally higher values and
            // runs back down to zero

            // if value to left of ii is bigger than ii then swap them
            // and keep swapping until this isn't true
            while ( (ii > 0 ) && ( A[ii - 1].compareTo(A[ii]) > 0 ) )
            {
                temp = A[ii];
                A[ii] = A[ii - 1];
                A[ii - 1] = temp;

                ii = ii - 1;
            }
        }
    }// end insertionSort()


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: mergeSort
    * IMPORT: A ( array of NetworkComponent)
    * EXPORT: None
    * PURPOSE: Wrapper method for merge sort ensure valid start conditions
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public static void mergeSort(NetworkComponent[] A)
    {
       mergeSortRecurse( A, 0, A.length - 1 );
    }//mergeSort()


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: mergeSortRecurse 
    * IMPORT: array (array of NetworkComponent), int leftIdx, int rightIdx
    * EXPORT: None
    * PURPOSE: Performs a merge sort on input array
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    private static void mergeSortRecurse( NetworkComponent[] array, int leftIdx, int rightIdx)
    {
        if( leftIdx < rightIdx )
        {
            int midIdx = ( leftIdx + rightIdx ) / 2;

            mergeSortRecurse( array, leftIdx, midIdx );
            mergeSortRecurse( array, midIdx + 1, rightIdx );

            merge( array, leftIdx, midIdx, rightIdx );
        }
        else
        {
            return; // array already sorted (base case)
        }

    }//mergeSortRecurse()



    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: merge 
    * IMPORT: array (array of NetworkComponent), int leftIdx, int midIdx,
    *         int rightIdx 
    * EXPORT: None
    * PURPOSE: Creates a temporary array of NetworkComponents, and 
    * uses the compare function written in NetworkComponent and its subclasses
    * to sort the output to assignment specifications
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    private static void merge( NetworkComponent[] array, int leftIdx, int midIdx, int rightIdx)
    {
        NetworkComponent[] tempArr = new NetworkComponent[ rightIdx - leftIdx + 1 ];
        int ii = leftIdx;
        int jj = midIdx + 1;
        int kk = 0;

        while (( ii <= midIdx ) && ( jj <= rightIdx )) 
        {
            if ( NetworkComponent.compare(array[ii], array[jj]) )              
                                                    
            {
                tempArr[kk] = array[ii];              
                ii = ii + 1;                         
            }
            else
            {
                tempArr[kk] = array[jj];            
                jj = jj + 1;                       
            }
            kk = kk + 1;                           
        }

        for ( ii = ii; ii <= midIdx; ii++ )        
        {
            tempArr[kk] = array[ii];
            kk = kk + 1;
        }
        for( jj = jj; jj <= rightIdx; jj++ )   
        {
            tempArr[kk] = array[jj];
            kk = kk + 1;
        }

        for( kk = leftIdx; kk <= rightIdx; kk++ )       
        {
            array[kk] = tempArr[ kk - leftIdx];
        }

    }// end merge()
}
