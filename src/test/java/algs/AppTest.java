package algs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import algs.collection.BST;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * BST Test
     */
    @Test
    public void BST_TEST()
    {
        BST<Integer, Integer> st = new BST<Integer, Integer>();
        st.put(0, 0);
        st.put(1, 1);
        st.put(2, 2);
        st.put(3, 3);
        st.put(4, 4);     
        assertTrue( st.size() == 5 );
        assertTrue( st.ceiling(3) == 3);
        assertTrue( st.floor(2) == 2);
        assertTrue( st.max() == 4);
        assertTrue( st.min() == 0);
        assertTrue( st.contains(0));
        assertTrue( st.contains(1));
        assertTrue( st.contains(2));
        assertTrue( st.contains(3));
        assertTrue( st.contains(4));
        assertFalse( st.contains(5));
        assertFalse( st.contains(-1));
        st.deleteMax();
        assertTrue( st.max() == 3);
        st.deleteMin();
        assertTrue( st.min() == 1);
        st.put(3, 10);
        assertTrue( st.get(3) == 10);
        st.put(5, 5);
        st.put(6, 6);
        int[] temp = new int[] {10, 5};
        int i = 0;
        for (int k : st.keys(3, 5) ){
            assertTrue( st.get(k) == temp[i++]);
        }
    }
}
