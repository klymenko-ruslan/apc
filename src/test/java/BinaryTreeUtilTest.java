import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BinaryTreeUtilTest {

    private BinaryTreeUtil<String> binaryTreeUtil = new BinaryTreeUtil();

    @Test
    public void testNullTree() {
        boolean result = binaryTreeUtil.isBinaryTree(null);
        Assert.assertFalse(result);
    }

    @Test
    public void testEmptyTree() {
        boolean result = binaryTreeUtil.isBinaryTree(new ArrayList<Pair<String, String>>());
        Assert.assertFalse(result);
    }

    @Test
    public void testNonExistingVertexTree() {
        boolean result = binaryTreeUtil.isBinaryTree(new ArrayList<Pair<String, String>>() {
            {
                add(new Pair("a", "b"));
                add(new Pair("b", "f"));
                add(new Pair("a", "c"));
                add(new Pair("b", "e"));
                add(new Pair("non-existing", "n"));
            }
        });
        Assert.assertFalse(result);
    }

    @Test
    public void testMoreThanTwoEdgesTree() {
        boolean result = binaryTreeUtil.isBinaryTree(new ArrayList<Pair<String, String>>() {
            {
                add(new Pair("a", "b"));
                add(new Pair("a", "c"));
                add(new Pair("a", "d"));
            }
        });
        Assert.assertFalse(result);
    }

    @Test
    public void testCorrectTree() {
        boolean result = binaryTreeUtil.isBinaryTree(new ArrayList<Pair<String, String>>() {
            {
                add(new Pair("a", "b"));
                add(new Pair("b", "f"));
                add(new Pair("a", "c"));
                add(new Pair("b", "e"));
                add(new Pair("c", "n"));
            }
        });
        Assert.assertTrue(result);
    }

    @Test
    public void testWrongOrderTree() {
        boolean result = binaryTreeUtil.isBinaryTree(new ArrayList<Pair<String, String>>() {
            {
                add(new Pair("b", "c"));
                add(new Pair("a", "b"));
            }
        });
        Assert.assertFalse(result);
    }

    @Test
    public void testRecursiveTree() {
        boolean result = binaryTreeUtil.isBinaryTree(new ArrayList<Pair<String, String>>() {
            {
                add(new Pair("a", "a"));
            }
        });
        Assert.assertFalse(result);
    }

    @Test
    public void testCycleTree() {
        boolean result = binaryTreeUtil.isBinaryTree(new ArrayList<Pair<String, String>>() {
            {
                add(new Pair("a", "b"));
                add(new Pair("b", "c"));
                add(new Pair("c", "a"));
            }
        });
        Assert.assertFalse(result);
    }
}
