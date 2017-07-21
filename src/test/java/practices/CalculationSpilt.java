package practices;

public class CalculationSpilt {
	
	public static void main(String[] args) {
		String text1 = "Rs 1,640";
		String text2="Rs 2,564";
		String text3 = "Rs 4,204";
		String mark1 = text1.replace(",", "").split("Rs ")[1];
		String mark2 = text2.replace(",", "").split("Rs ")[1];
		System.out.println(mark1);
		System.out.println(mark2);
		System.out.println(Integer.parseInt(mark1)+Integer.parseInt(mark2));
		//int var = Integer.parseInt(mark1);
		//int var1 = Integer.parseInt(mark2);
		//System.out.println(var+var1);
		
	}

}
