import java.util.Arrays;
public class ProbStat10 {

	//getting the weight of any slice of the queue
	int weightTill(int[] weightArray, int from, int to){
		int totalWeight=0;
		for(int i=from;i<=to;i++)
			totalWeight+=weightArray[i];
		return totalWeight;
	}

	//no. of stops a boarded group will make
	int stopsPerGroup(int[] stopsArray, int from, int to){
		Arrays.sort(stopsArray,from,to);
		int stops=1;
		for(int i=from;i<=to && i<stopsArray.length-1;i++)
			if(stopsArray[i+1]!=stopsArray[i]) // to ignore if more than one person gets off on the same floor
				stops++;
		return stops;
	}

	public int solution(int[] A, int[] B, int M, int X, int Y){
		int stopCount=0,from=0, noOfPpl=1;
		for(int i=0;i<A.length;i++){
			if(weightTill(A,from,i)>Y || noOfPpl>X || i==A.length-1){ //consider as a group if any limit is exceeded or reached the end of queue
				stopCount+=stopsPerGroup(B, from, i-1)+1;//+1 for returning to ground after a trip
				noOfPpl=1;//resetting the variables for counting group's strength
				from=i;		// and the starting index of the group ('i' because that person isn't included in current calculation)
			}
			else
				noOfPpl++;
		}
		return stopCount;
	}
	public static void main(String[] args) {
		ProbStat10 ob = new ProbStat10();
		int[] weightInfo = {40,100,80,20};
		int[] stopInfo = {3,2,2,3};
		System.out.println("Total number of stops: "+ob.solution(weightInfo, stopInfo, 3, 5, 200));
	}
}