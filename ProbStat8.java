import java.lang.Math;
public class ProbStat8 {
	public int solution(int[] A){
		int i,j,sum=0,minSum=A[0];
		for(i=0;i<A.length;i++)
			for(j=i;j<A.length;j++){
				sum+=A[j];
				if(Math.abs(sum)<minSum)
					minSum = Math.abs(sum);
			}
		return minSum;
	}
	public static void main(String[] args) {
		ProbStat8 ob = new ProbStat8();
		int array[]={2,-4,6,-3,9};
		System.out.println("The min abs is "+ob.solution(array));
	}
}