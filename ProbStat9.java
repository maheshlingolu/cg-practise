
public class ProbStat9 {
	boolean max(int a, int b, int c){
		if(b>a && b>c)
			return true;
		else
			return false;
	}
	boolean min(int a, int b, int c){
		if(b<a && b<c)
			return true;
		else
			return false;
	}
	public int solution(int[][] A){
		int i,j,count=0;
		for(i=1;i<A.length-1;i++)
			for(j=1;j<A[0].length-1;j++){
				if(max(A[i-1][j],A[i][j],A[i+1][j]))
					if(min(A[i][j-1],A[i][j],A[i][j+1]))
							count++;
					else
						continue;
				else
					if(min(A[i-1][j],A[i][j],A[i+1][j]))
						if(max(A[i][j-1],A[i][j],A[i][j+1]))
								count++;
						else
							continue;
			}
		return count;
	}
	public static void main(String[] args) {
		int [][]array = {
				{0,1,9,3},
				{7,5,8,3},
				{9,2,9,4},
				{4,6,7,1}
		};
		ProbStat9 ob = new ProbStat9();
		System.out.println("Saddles: "+ob.solution(array));
	}
}