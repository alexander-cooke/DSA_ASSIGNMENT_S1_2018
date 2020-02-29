import java.util.*;
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* CLASS: LList 
* Author: Alex Cooke (18406489)
* DISCLAIMER: This class is based upon work that I completed as part of
* Practical 4. I've modified it slightly, such as adding a count class field,
* which seems to be the easiest way to keep track of the length of the list, a
* getCount() method, and a remove() method, which searches for a certain value
* and remove it from the list, but for the most part this is just a generic
* linked list.
*
* I'd tried to add a lot more here, like a sortList function which 
* used a mergeSort, but I wasn't able to get it working properly, so I
* removed it. I still use the tail pointer to remove things from the end of
* the list.
* 
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class LList<E> implements Iterable<E> 
{
    private ListNode<E> head;
    private ListNode<E> tail;
    private int count; //added to keep track of length

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * DEFAULT CONSTRUCTOR
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public LList()
    {
        // Initial: empty list
        // head points to null
        head = null;
        tail = null;
        count = 0;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * METHOD TO RETURN NEW ITERATOR
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public Iterator<E> iterator()
    {
        return new LListIterator<E>( this );
    }

    // setters
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: insertFirst 
    * IMPORT: newValue E
    * EXPORT: None
    * PURPOSE: This method creates a new ListNode, by passing newValue
    * as a parameter to its constructor. If the list is empty, then make
    * head point to newNd. If there are other elements in the list then
    * we make newNd point to whatever head pointed to, then make head point
    * to newNd
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void insertFirst( E newValue )
    {
        // newValue becomes newNd's value class field
        ListNode<E> newNd = new ListNode<E>( newValue );
        ListNode<E> nextNode;
         
        if ( isEmpty() )
        {
            // overwrite 'null' with reference to newNd
            // i.e head and tail now point to newNd
            head = newNd;
            tail = newNd;
        }
        else // (List isn't empty, but we're still inserting
             //  before first element )
        {
            //newNd now points to whatever head pointed to
            head.setPrevious( newNd ); // first element points back to newNd
            newNd.setNext( head ); // newNd points to original first element 
            newNd.setPrevious( null ); // newNd has no previous element
            // head now points to newNd. Calling insertFirst() on  a one- or multi-
            // item list doesn't effect tail member field. 
            head = newNd;
        }
        count++;
    } // end insertFirst()


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: getCount 
    * IMPORT: None
    * EXPORT: count (Integer)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public int getCount()
    {
        return count;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: insertLast 
    * IMPORT: newValue (E)
    * EXPORT: None
    * PURPOSE: Insert an item to the end of the list in O(1), with use
    * of tail pointer
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void insertLast( E newValue )
    {
        ListNode<E> newNd = new ListNode<E>( newValue );
        ListNode<E> currentNode;

        if( isEmpty() )
        {
            head = newNd;
            tail = newNd;
        }
        else
        {
            tail.setNext( newNd );
            newNd.setPrevious( tail );
            tail = newNd;
        }
    } 

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: delete
    * IMPORT: list ( LinkedList of E )
    * EXPORT: None
    * PURPOSE: Delete the list
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void delete( LList<E> list )
    {
        head = null;
        tail = null;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: isEmpty
    * IMPORT: None
    * EXPORT: empty (Boolean)
    * PURPOSE: Return true if list is empty
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public boolean isEmpty()
    {
        boolean empty = false;

        if( head == null )
        {
            empty = true;
        }

        return empty;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: peekFirst
    * IMPORT: None
    * EXPORT: nodeValue (E)
    * PURPOSE: Return the first item in list, but don't remove it
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public E peekFirst()
    {
        E nodeValue;

        if ( isEmpty() )
        {
            throw new IllegalStateException("The list is empty");
        }
        else
        {
            nodeValue = head.getValue();
        }

        return nodeValue;
    }


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: peekLast
    * IMPORT: None
    * EXPORT: nodeValue (E)
    * PURPOSE: Return the last item in list, but don't remove it.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public E peekLast()
    {
        E nodeValue;
        ListNode<E> currNd;
    
        if( isEmpty() )
        {
            throw new IllegalStateException("The list is empty");
        }
        else
        {
            nodeValue = tail.getValue();
        }

        return nodeValue;
    }


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: removeFirst
    * IMPORT: None
    * EXPORT: nodeValue E
    * PURPOSE: Return the first value in the list, and remove it from list
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public E removeFirst()
    {
        E nodeValue;

        if ( isEmpty() )
        {
            throw new IllegalStateException("The list is empty");
        }
        else if ( head.getNext() == null ) //one-item list
        {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }
        else
        {
            nodeValue = head.getValue();
            head = head.getNext();
            head.setPrevious(null);
        }
        
        return nodeValue;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: remove
    * IMPORT: value (E)
    * EXPORT: None
    * PURPOSE: I wrote this one from scratch for the assignment. It looks
    * through the list for the value input as a paramter as removes it from the
    * list
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public void remove( E value )
    {
        ListNode<E> currNd = head;
        ListNode<E> prevNd = null;

        if( isEmpty() )
        {   
            throw new IllegalStateException( "The list is empty" );
        }
        
        while( currNd != null && currNd.getValue() != value )
        {
            prevNd = currNd;
            currNd = currNd.getNext();
        }

        prevNd.setNext( currNd.getNext() );
        currNd.setNext( null );
        count--;
    }


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * SUBMODULE: removeLast 
    * IMPORT: None
    * EXPORT: nodeValue (E)
    * PURPOSE: Returns the last value in the list, and removes it from list
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public E removeLast()
    {
        E nodeValue;   
        ListNode<E> currNd;
        ListNode<E> prevNd;

        if ( isEmpty() )
        {
            throw new IllegalStateException("The list is empty");
        }
        else if ( head.getNext() == null ) // only one node in list
        {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }
        else
        {
            nodeValue = tail.getValue();
            tail = tail.getPrevious();
            tail.setNext(null);
        }

        return nodeValue;
    }



    // Private inner classes. No other class should be able to access
    // the underlying implementation of the linked list. For this reason
    // it makes sense for both LLIterator and ListNode to be private inner classes
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * CLASS: LLIterator
    * PURPOSE: Allows iteration through the list 
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    private class LListIterator<E> implements Iterator<E>
    {
        private LList<E>.ListNode<E> iterNext;
        public LListIterator( LList<E> theList )
        {
            iterNext = theList.head;
        }

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        * SUBMODULE: hasNext 
        * IMPORT: None
        * EXPORT: hasNext (Boolean)
        * PURPOSE: Determines whether next element exists in list
        * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        public boolean hasNext()
        {
            return iterNext != null;
        }

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        * SUBMODULE: next
        * IMPORT: None
        * EXPORT: value (E)
        * PURPOSE: Returns next element from list
        * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        public E next()
        {
            E value;
            if ( iterNext == null )
            {
                value = null;
            }
            else
            {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }

            return value;
        }

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        * SUBMODULE: remove
        * IMPORT: None
        * EXPORT: None
        * PURPOSE: I haven't chosen to implement this.
        * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        public void remove()
        {
                throw new UnsupportedOperationException();
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * CLASS: ListNode
    * PURPOSE: The fundamental building block of the LList
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    private class ListNode<E>
    {

        // Private class fields
        private E value;
        private ListNode<E> next;
        private ListNode<E> previous;

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        * ALTERNATE CONSTRUCTOR
        * IMPORT: inValue (E)
        * PURPOSE: sets value to inValue 
        * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        // Alternate constructor
        public ListNode( E inValue )
        {
            value = inValue;
            next = null;
            previous = null;
        }
        
        // getters
        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        * SUBMODULE: getValue
        * IMPORT: None
        * EXPORT: value (E)
        * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        public E getValue()
        {
            return value;
        }

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        * SUBMODULE: getNext 
        * IMPORT: None
        * EXPORT: next (ListNode<E>)
        * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        
        public ListNode<E> getNext()
        {
            return next;    
        }

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        * SUBMODULE: getPrevious 
        * IMPORT: None
        * EXPORT: ListNode<E>
        * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        public ListNode<E> getPrevious()
        {
            return previous;
        }

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        * SUBMODULE: setValue 
        * IMPORT: inValue (E)
        * PURPOSE: Set value to inValue
        * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        // setters
        public void setValue( E inValue )
        {
            value = inValue;
        }

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        * SUBMODULE: setNext
        * IMPORT: inNext (ListNode<E>)
        * EXPORT: None
        * PURPOSE: Set next to inNext
        * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        public void setNext( ListNode<E> inNext )
        {
            next = inNext;
        }

        /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        * SUBMODULE: setPrevious
        * IMPORT: inPrevious (ListNode<E>)
        * EXPORT: None
        * PURPOSE: Sets previous to inPrevious
        * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
        public void setPrevious( ListNode<E> inPrevious )
        {
            previous = inPrevious;
        }
    }
}
