import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ChipsetProductionFactoryTest {

    @Test
    public void testNullData() {
        FactoryProductionResult result = ChipsetProductionFactory.getFactoryProductionStatistics(null, 11);
        Assert.assertEquals(0, result.getWaste());
        Assert.assertEquals(0, result.getSolutions().size());
    }

    @Test
    public void testNegativeChipsetAmount() {
        FactoryProductionResult result = ChipsetProductionFactory.getFactoryProductionStatistics(new ArrayList<Integer>() {
            {
                add(1);
            }
        }, -1);
        Assert.assertEquals(0, result.getWaste());
        Assert.assertEquals(0, result.getSolutions().size());
    }

    @Test
    public void testNoData() {
        FactoryProductionResult result = ChipsetProductionFactory.getFactoryProductionStatistics(new ArrayList<Integer>(), 11);
        Assert.assertEquals(0, result.getWaste());
        Assert.assertEquals(0, result.getSolutions().size());
    }

    @Test
    public void testWrongData() {
        FactoryProductionResult result = ChipsetProductionFactory.getFactoryProductionStatistics(new ArrayList<Integer>() {
            {
                add(1);
                add(2);
                add(3);
            }
        }, 11);
        Assert.assertEquals(3, result.getWaste());
        Assert.assertEquals(0, result.getSolutions().size());
    }

    @Test
    public void testNoWaste() {
        FactoryProductionResult result = ChipsetProductionFactory.getFactoryProductionStatistics(new ArrayList<Integer>() {
            {
                add(1);
                add(2);
                add(4);
                add(10);
                add(5);
                add(6);
            }
        }, 11);

        Assert.assertEquals(0, result.getWaste());
        Assert.assertEquals(4, result.getSolutions().size());
        Assert.assertEquals(new ArrayList<Integer>(){
            {
                add(1);
                add(10);
            }
        }, result.getSolutions().get(0));
        Assert.assertEquals(new ArrayList<Integer>(){
            {
                add(2);
                add(4);
                add(5);
            }
        }, result.getSolutions().get(1));
        Assert.assertEquals(new ArrayList<Integer>(){
            {
                add(1);
                add(4);
                add(6);
            }
        }, result.getSolutions().get(2));
        Assert.assertEquals(new ArrayList<Integer>(){
            {
                add(5);
                add(6);
            }
        }, result.getSolutions().get(3));
    }

    @Test
    public void testWaste() {
        FactoryProductionResult result = ChipsetProductionFactory.getFactoryProductionStatistics(new ArrayList<Integer>() {
            {
                add(1);
                add(2);
                add(4);
                add(10);
                add(5);
                add(6);
                add(-100);
                add(-200);
            }
        }, 11);

        Assert.assertEquals(2, result.getWaste());
        Assert.assertEquals(4, result.getSolutions().size());
        Assert.assertEquals(new ArrayList<Integer>(){
            {
                add(1);
                add(10);
            }
        }, result.getSolutions().get(0));
        Assert.assertEquals(new ArrayList<Integer>(){
            {
                add(2);
                add(4);
                add(5);
            }
        }, result.getSolutions().get(1));
        Assert.assertEquals(new ArrayList<Integer>(){
            {
                add(1);
                add(4);
                add(6);
            }
        }, result.getSolutions().get(2));
        Assert.assertEquals(new ArrayList<Integer>(){
            {
                add(5);
                add(6);
            }
        }, result.getSolutions().get(3));
    }
}
