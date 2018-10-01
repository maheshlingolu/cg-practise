public class ProbStat14 {
	public int solution(int a, int b){
		int c=a*b, count=0;
		while(c!=0){
			if(c%2==1)
				count++;
			c/=2;
		}
		return count;
	}
	public static void main(String[] args) {
		ProbStat14 ob =new ProbStat14();
		System.out.println("No. of bits set to '1' : "+ob.solution(1,15));
	}
}