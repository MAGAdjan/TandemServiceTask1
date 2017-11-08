import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testCleverSort {

    private List<String[]> actual;


    @Before
    public void testListInit() {
        actual = new ArrayList();
        actual.add(new String[] {"juve", "101.8a", " ", "Tandem"});
        actual.add(new String[] {"juve2", "101.a8", "null", "tandemService"});
        actual.add(new String[] {"e", "101", null});
        actual.add(new String[] {"juva", "101.8", "abrakadabra", " Tandem"});
    }

    @Test
    public void testNull() {
        List<String[]> nullList = null;
        Task1Impl.INSTANCE.sort(nullList, 0);
        assertEquals(null, nullList);
    }

    @Test
    public void testEmptyList() {
        List<String[]> emptyList = new ArrayList();
        Task1Impl.INSTANCE.sort(emptyList, 0);
        assertTrue(emptyList.size() == 0);
    }

    @Test
    public void testByColumn1() {
        List<String[]> expected = new ArrayList();
        expected.add(actual.get(2));
        expected.add(actual.get(3));
        expected.add(actual.get(0));
        expected.add(actual.get(1));
        Task1Impl.INSTANCE.sort(actual, 0);
        assertEquals(expected, actual);
    }

    @Test
    public void testByColumn2() {
        List<String[]> expected = new ArrayList();
        expected.add(actual.get(2));
        expected.add(actual.get(3));
        expected.add(actual.get(0));
        expected.add(actual.get(1));
        Task1Impl.INSTANCE.sort(actual, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testByColumn3() {
        List<String[]> expected = new ArrayList();
        expected.add(actual.get(2));
        expected.add(actual.get(0));
        expected.add(actual.get(3));
        expected.add(actual.get(1));
        Task1Impl.INSTANCE.sort(actual, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void testByColumn4() {
        List<String[]> expected = new ArrayList();
        expected.add(actual.get(0));
        expected.add(actual.get(1));
        expected.add(actual.get(2));
        expected.add(actual.get(3));
        Task1Impl.INSTANCE.sort(actual, 3);
        assertEquals(expected, actual);
    }
}
