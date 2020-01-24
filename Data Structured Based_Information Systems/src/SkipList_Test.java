
public class SkipList_Test {

	public static void main(String[] args) {
		
		SkipList<Integer> mySkipList = new SkipList<Integer>();
		System.out.println(mySkipList.getSize());
		System.out.println(mySkipList.getNumOfLevels());
		
		System.out.println(mySkipList.get("hesam"));
		System.out.println(mySkipList.delete("hesam"));
		System.out.println(mySkipList.getSize());
		System.out.println(mySkipList.getNumOfLevels());
		System.out.println("--------");
		
		
		System.out.println(mySkipList.put("hesam", 10));
		System.out.println(mySkipList.put("Tina", 20));
		System.out.println(mySkipList.put("Aria", 120));
		System.out.println(mySkipList.put("Mike", 14));
		System.out.println(mySkipList.put("Tim", 10));
		System.out.println(mySkipList.getSize());
		System.out.println(mySkipList.getNumOfLevels());
		System.out.println("--------");

		
		System.out.println(mySkipList.get("hesam"));
		System.out.println(mySkipList.get("Mike"));
		System.out.println(mySkipList.getSize());
		System.out.println(mySkipList.getNumOfLevels());
		System.out.println("--------");
		
		
		

	}

}
