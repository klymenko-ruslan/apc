import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChipsetProductionFactory {

    /**
     * Calculates all of the possible production combimations of given chipset amount from given items. Takes 2 in N time.
     * @param items
     * @param chipsetsToBeProduced
     * @return
     */
    public static FactoryProductionResult getFactoryProductionStatistics(List<Integer> items, int chipsetsToBeProduced) {

        FactoryProductionResult result = new FactoryProductionResult();

        if(items == null || items.isEmpty() || chipsetsToBeProduced < 0) return result;

        int combinationTimes = 1 << items.size();

        List<Integer> itemsBuffer = new ArrayList<>(items.size());

        Set<Integer> usedItems = new HashSet<>();

        for(int i = 0; i < combinationTimes; i++) {
            int cnt = 1;
            int sum = 0;
            itemsBuffer.clear();
            for(int j = 0; j < items.size(); j++) {
                if((i & cnt) > 0) {
                    itemsBuffer.add(items.get(j));
                    sum += items.get(j);
                }
                cnt <<= 1;
            }
            if(sum == chipsetsToBeProduced) {
                usedItems.addAll(itemsBuffer);
                result.addSolution(new ArrayList<>(itemsBuffer));
            }
        }
        result.setWaste(items.size() - usedItems.size());
        return result;
    }
}
class FactoryProductionResult {
    private List<List<Integer>> solutions = new ArrayList<>();
    private int waste;

    public List<List<Integer>> getSolutions() {
        return solutions;
    }

    public void addSolution(List<Integer> solution) {
        this.solutions.add(solution);
    }

    public int getWaste() {
        return waste;
    }

    public void setWaste(int waste) {
        this.waste = waste;
    }
}