package actor;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Test {
	
	public static void main(String args[]) {
		ScheduledThreadPoolExecutor executor;
		String s = "abccba";
		int n = s.length() * 2 + 1;
		int mr = 0;
		int c = 0;
		int ml = 0;
		int begin = 0;
		int end = 0;
		int[] p = new int[n];
		StringBuilder sb = new StringBuilder();
		for (char ch : s.toCharArray()) {
			sb.append("#").append(ch);
		}
		sb.append("#");
		for (int i = 0; i <= n - 1; i++) {
			if (2 * c - i >= 0) {
				p[i] = Math.min(p[2 * c - i], mr - i);
			} else {
				p[i] = mr - i;
			}
			if (p[i] < 0) {
				p[i] = 0;
			}
			int l = p[i];
			while (true) {
				l++;
				if (i + l > n - 1 || i - l < 0) {
					break;
				}
				if (sb.charAt(i + l) != sb.charAt(i - l)) {
					break;
				}
				p[i] = l;
			}
			if (p[i] > ml) {
				ml = p[i];
				begin = i - p[i];
				end = i + p[i];
			}
			if (mr < i + p[i]) {
				mr = i + p[i];
				c = i;
			}
		}
		StringBuilder sb2 = new StringBuilder();
		for (int i = begin; i <= end; i++) {
			if (sb.charAt(i) != '#') {
				sb2.append(sb.charAt(i));
			}
		}
		
		System.out.println(ml);
	}

}
