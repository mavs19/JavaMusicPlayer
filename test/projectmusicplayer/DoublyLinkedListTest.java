/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmusicplayer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static junit.framework.TestCase.assertEquals;

/**
 *
 * @author David Perry
 */
public class DoublyLinkedListTest {
    
    public DoublyLinkedListTest() {
    }
    
    DoublyLinkedList<String> testList = new DoublyLinkedList<>();


    @org.junit.Test
    public void testAddFirst() {
        testList.addFirst("songOne", "pathOne");
        testList.addFirst("songTwo", "pathTwo");
        testList.addFirst("songThree", "pathThree");
        assertEquals(3, testList.length());
        assertEquals("songThree", testList.head.title);
    }

    @org.junit.Test
    public void testAddAfter() {
        testList.addFirst("songOne", "pathOne");
        testList.addAfter(testList.head, "songTwo", "pathTwo");
        testList.addAfter(testList.head,"songThree", "pathThree");
        assertEquals(3, testList.length());
        assertEquals("songTwo", testList.head.next.next.title);
    }

    @org.junit.Test
    public void testAddLast() {
        testList.addLast("songOne", "pathOne");
        testList.addLast("songTwo", "pathTwo");
        testList.addLast("songThree", "pathThree");
        assertEquals(3, testList.length());
        assertEquals("songTwo", testList.head.next.title);
    }

    @org.junit.Test
    public void testPlayNext() {
        testList.addFirst("songOne", "pathOne");
        testList.addFirst("songTwo", "pathTwo");
        testList.addFirst("songThree", "pathThree");
        Node test = testList.playNext(testList.head);
        assertEquals(test.title, "songTwo");
    }

    @org.junit.Test
    public void testPlayPrev() {
        testList.addFirst("songOne", "pathOne");
        testList.addFirst("songTwo", "pathTwo");
        testList.addFirst("songThree", "pathThree");
        Node test = testList.playPrev(testList.head.next);
        assertEquals(test.title, "songThree");
    }

    @org.junit.Test
    public void testSplit() {
        testList.addFirst("A", "pathOne");
        testList.addAfter(testList.head, "B", "pathTwo");
        testList.addLast("C", "pathThree");
        testList.addLast("D", "pathFour");
        testList.addLast("E", "pathFive");
        testList.addLast("F", "pathSix");
        Node test = testList.split(testList.head);
        assertEquals(test.title, "D");
    }

    @org.junit.Test
    public void testMergeSort() {
        testList.addFirst("A", "pathOne");
        testList.addFirst("B", "pathTwo");
        testList.addFirst("C", "pathThree");
        assertEquals(testList.head.title , "C");
        Node test = testList.mergeSort(testList.head);
        assertEquals(test.title , "A");
        assertEquals(test.next.title , "B");
        assertEquals(test.next.next.title , "C");
    }


    @org.junit.Test
    public void testBinarySearch() {
        testList.addFirst("A", "pathOne");
        testList.addFirst("B", "pathTwo");
        testList.addFirst("C", "pathThree");
        Node test = testList.binarySearch(testList.head, "B");
        assertEquals(test.title , "B");
    }


    @org.junit.Test
    public void testDelete() {
        testList.addFirst("A", "pathOne");
        testList.addFirst("B", "pathTwo");
        testList.addFirst("C", "pathThree");
        assertEquals(3, testList.length());
        Node test = testList.delete(testList.head, "B");
        assertEquals(test.title , "C");
        assertEquals(2, testList.length());
    }

    @org.junit.Test
    public void testGetSong() {
        testList.addFirst("A", "pathOne");
        testList.addFirst("B", "pathTwo");
        testList.addFirst("C", "pathThree");
        Node test = testList.getSong(testList.head, "B");
        assertEquals(test.path , "pathTwo");
    }

    
    
}
