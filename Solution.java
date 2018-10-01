import java.util.Arrays;
public class Solution {
	public void printArray(int num[])
	{
		for(int i=0;i<num.length;i++)
			System.out.print(num[i]+" ");
		System.out.println();
	}
	public boolean solution(int A[]){
		int[] B = new int[A.length];
		for(int i=0;i<A.length;i++)
			B[i] = A[i];
		Arrays.sort(B);//to track swaps
		int[] checked = new int[A.length];//keeps track of swapped positions
		for(int i=0;i<A.length;i++){
			if(checked[i]!=0)
				continue;
			else{
				int j=i;
				if(A[i]==B[j])
					checked[i] = 1;
				else{
					for(j=0;j<A.length;j++){
						if(A[i]==B[j]){
							if(checked[j]!=0)
								return false;
							else{
								checked[j]=1;
								checked[i]=1;
								break;
							}
						}
					}
				}
			}
		}
		return true;
	}
	public static void main(String args[])
	{
		Solution s = new Solution();
		int array[] = {3,8,6,9,3};
		System.out.println(s.solution(array)?"Can be done":"No can do");
	}
}