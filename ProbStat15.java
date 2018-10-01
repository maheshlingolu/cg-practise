public class ProbStat15 {
	public int countOpenBracket(String str, int start, int end){
		int count=0;
		for(int i=start ; i<=end ; i++)
			if(str.charAt(i)=='(')
				count++;
		return count;
	}
	public int countCloseBracket(String str, int start, int end){
		int count=0;
		for(int i=start ; i<=end ; i++)
			if(str.charAt(i)==')')
				count++;
		return count;
	}
	public int solution(String str){
		int k=0;
		for(k=0;k<str.length();k++)
			if(countOpenBracket(str, 0, k)==countCloseBracket(str, k+1, str.length()-1))
				break;
		return k;
	}
	public static void main(String[] args) {
		ProbStat15 ob = new ProbStat15();
		String s="(())))(";
		System.out.println("Position of split : "+(ob.solution(s)+1));
	}
}