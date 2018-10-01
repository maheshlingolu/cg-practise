import java.lang.Character;
public class ProbStat12 {
	public int solution(String s){
		int currLength=0, maxLength=0;
		boolean upperPresent = false;
		for(int i=0 ; i<s.length() ; i++){
			if((upperPresent==true && Character.isUpperCase(s.charAt(i))) || Character.isDigit(s.charAt(i))){
				if(Character.isDigit(s.charAt(i)))
					currLength=0;
				else
					currLength=1;
				upperPresent = false;
			}
			else if(upperPresent==false && Character.isUpperCase(s.charAt(i))){
				upperPresent=true;
				currLength++;
			}
			else
				currLength++;
			maxLength = currLength>maxLength?currLength:maxLength;
		}
		if(maxLength==0)
			return -1;
		else
			return maxLength;
	}
	public static void main(String[] args) {
		ProbStat12 ob = new ProbStat12();
		String s = "a0Ba";
		if(ob.solution(s)==-1)
			System.out.println("No valid sub-string");
		else
			System.out.println("Max length = "+ob.solution(s));
	}
}