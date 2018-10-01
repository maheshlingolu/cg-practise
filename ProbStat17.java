
public class ProbStat17 {
	public int solution(int[] A)
	{
		int pairCount=0;
		for(int i=0;i<A.length-1;i++)
			for(int j=i+1;j<A.length;j++)
				if(A[i]==A[j])
					pairCount++;
		return pairCount;
	}
	public static void main(String[] args) {
		ProbStat17 ob = new ProbStat17();
		int[] array ={3,5,6,3,3,5};
		System.out.println("No. of pairs found in : "+ob.solution(array));
		}
}