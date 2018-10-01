
public class Prob2 {
	public int solution(int[] A){
		int i=0, count=0;
		while(A[i]!=-1){
			count++;
			i=A[i];
		}
		return count+1;
	}
	public static void main(String[] args) {
		int array[] = {1,4,-1,3,2};
		Prob2 ob = new Prob2();
		System.out.println("Count = "+ob.solution(array));
	}

}
