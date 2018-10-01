public class ProbStat1 {
	public int min(int a, int b, int c){
		return ((a-b)<(c-b)?(a-b):(c-b));
	}
	public int solution(int[] a){
		int p=0,r=0,q=0,depth=-1;
		while(p<a.length-3 && q<a.length-2 && r<a.length-1){
			if(a[p]>a[p+1]){
				//System.out.println("P stops at "+p);
				while(q<a.length-2 && r<a.length-1){
					if(a[q]<a[q+1]){
						//System.out.println("Q stops at "+q);
						
						while(r<a.length-1 && a[r]<a[r+1])
							r++;
						//System.out.println("R stops at "+r);
						depth = depth>min(a[p],a[q],a[r])?depth:min(a[p],a[q],a[r]);
						p=q=r;
						break;
					}
					else{
						q++;
						r++;
					}
				}
			}
			else{
				p++;
				q++;
				r++;
			}
		}
		return depth;
	}
	public static void main(String[] args) {
		int array[] = {0,1,3,-2,0,1,0,-3,2,3};
		ProbStat1 ob = new ProbStat1();
		System.out.println("Deepest pit is "+ob.solution(array)+ " deep");
	}
}