public class ProbStat3 {
	public int solution(int[] A, int[] B){
		float [] C = new float[A.length];
		for(int i=0;i<A.length;i++)
			C[i] = (float)A[i]+(float)(B[i])/1000000;
		int p,q,count=0;
		for(p=0;p<A.length-1;p++)
			for(q=p+1;q<A.length;q++)
				if(C[p]*C[q]>=(C[p]+C[q]))
					count++;
		return count;
	}
	public static void main(String[] args) {
		int []A = {0,1,2,2,3,5};
		int[] B = {500000,500000,0,0,0,20000};
		ProbStat3 ob = new ProbStat3();
		System.out.println("Number of multiplicative pairs: "+ob.solution(A,B));
	}
}