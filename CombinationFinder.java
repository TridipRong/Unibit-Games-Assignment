import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationFinder {
	public static int[][] findSumPairs(int[] nums, int target) {
	    List<int[]> pairs = new ArrayList<>();
	    for (int i = 0; i < nums.length; i++) {
	        for (int j = i + 1; j < nums.length; j++) {
	            if (Math.abs(nums[i] + nums[j]) == target) {
	                int[] pair = {nums[i], nums[j]};
	                boolean alreadyPresent = false;
	                for (int[] existingPair : pairs) {
	                    if (Arrays.equals(pair, existingPair)) {
	                        alreadyPresent = true;
	                        break;
	                    }
	                }
	                if (!alreadyPresent) {
	                    pairs.add(pair);
	                }
	            }
	        }
	    }
	    int[][] result = new int[pairs.size()][2];
	    return pairs.toArray(result);
	}


    public static int[] mergeArray(int[][] array) {
        List<Integer> mergedList = new ArrayList<>();
        for (int[] subArray : array) {
            for (int num : subArray) {
                mergedList.add(num);
            }
        }
        int[] mergedArray = new int[mergedList.size()];
        for (int i = 0; i < mergedList.size(); i++) {
            mergedArray[i] = mergedList.get(i);
        }
        Arrays.sort(mergedArray);
        return mergedArray;
    }

    public static int doubleTarget(int target) {
        return target * 2;
    }

    public static int[][] findCombinations(int[] nums, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        Arrays.sort(nums); 
        backtrack(nums, target, 0, new ArrayList<>(), combinations);

        int[][] result = new int[combinations.size()][];
        for (int i = 0; i < combinations.size(); i++) {
            List<Integer> combination = combinations.get(i);
            result[i] = new int[combination.size()];
            for (int j = 0; j < combination.size(); j++) {
                result[i][j] = combination.get(j);
            }
        }
        return result;
    }

    private static void backtrack(int[] nums, int target, int start, List<Integer> currentCombination, List<List<Integer>> combinations) {
        if (target == 0) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue; 
            }

            int num = nums[i];
            if (num > target) {
                break;
            }

            currentCombination.add(num);
            backtrack(nums, target - num, i + 1, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, -4, -6, -2, 8};
        int target = 4;

        int[][] sumPairs = findSumPairs(nums, target);
        System.out.println("First Combination for " + target + ":");
        for (int[] pair : sumPairs) {
            System.out.println(Arrays.toString(pair));
        }

        int[] mergedArray = mergeArray(sumPairs);
        System.out.println("Merged Array: " + Arrays.toString(mergedArray));

        int doubledTarget = doubleTarget(target);
        int[][] combinations = findCombinations(nums, doubledTarget);
        System.out.println("Second Combination for " + doubledTarget + ":");
        for (int[] combination : combinations) {
            System.out.println(Arrays.toString(combination));
        }
    }
}
