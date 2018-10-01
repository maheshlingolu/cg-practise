public class ProbStat4 {
	public int solution(int[] A){
		int leader = A[0];
		int maxCount=1, currCount=1,i;
		for(i=0;i<A.length-1;i++){
			if(A[i+1]==A[i])
				currCount++;
			else{
				if(maxCount<currCount){
					leader = A[i];
					maxCount = currCount;
				}
				currCount = 0;
			}
		}
		if(maxCount>A.length/2)
			return leader;
		else
			return -1;
	}
	public static void main(String[] args) {
		int array[] = {2,2,2,2,2,4,5,5,5,5};
		ProbStat4 ob = new ProbStat4();
		if(ob.solution(array) == -1)
				System.out.println("The supreme leader is dead!");
		else
			System.out.println("The leader is "+ob.solution(array));
	}
}