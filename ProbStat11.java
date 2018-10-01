
public class ProbStat11 {
	public int solution(int n) {
		int[] d = new int[30];
		int l = 0;
		int p;
		while (n > 0) {
			d[l] = n % 2;
			n /= 2;
			l++;
		}
		for (p = 1; p <= l/2; ++p) {//periodicity cannot be greater than half of the array size
			int i;
			boolean ok = true;
			for (i = 0; i < l - p; ++i) {
				if (d[i] != d[i + p]) {
					ok = false;
					break;
				}
			}
			if (ok) {
				return p;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		ProbStat11 ob = new ProbStat11();
		System.out.println(ob.solution(955));
	}

}
