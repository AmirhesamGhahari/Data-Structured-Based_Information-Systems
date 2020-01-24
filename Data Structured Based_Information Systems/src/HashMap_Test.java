
public class HashMap_Test {

	
	
	public static void main(String[] args) {
		
		HashMap<String, Double> myMap = new HashMap<String, Double>(10, 17);
		System.out.println(myMap.getSize());
		System.out.println(myMap.getPrime());
		System.out.println(myMap.getCapacity());
		System.out.println("--------");
		
		
		System.out.println(myMap.put("Hesam", 28.00));
		System.out.println(myMap.put("golzar", 25.00));
		System.out.println(myMap.put("bro", 26.00));
		System.out.println(myMap.put("say", 21.00));
		System.out.println(myMap.getSize());
		
		System.out.println("--------");

		System.out.println(myMap.remove("say"));
		System.out.println(myMap.getSize());
		System.out.println("--------");
		
		System.out.println(myMap.put("far", 58.00));
		System.out.println(myMap.put("mana", 54.00));
		System.out.println(myMap.getSize());
		System.out.println("--------");
		
		System.out.println(myMap.get("far"));
		System.out.println(myMap.get("par"));
		System.out.println("--------");

		System.out.println(myMap.put("mrez", 27.00));
		System.out.println(myMap.put("tim", 25.00));
		System.out.println(myMap.put("sia", 27.00));
		System.out.println(myMap.getSize());
		System.out.println(myMap.getCapacity());
		System.out.println("--------");
		
		System.out.println(myMap.put("gia", 27.00));
		System.out.println(myMap.put("kirk", 25.00));
		System.out.println(myMap.put("sima", 27.00));
		System.out.println(myMap.put("aria", 25.00));
		System.out.println(myMap.put("pete", 27.00));
		System.out.println(myMap.getSize());
		System.out.println(myMap.getCapacity());
		System.out.println("--------");




		
		
		
	}

}
