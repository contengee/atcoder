import java.io.*;
import java.util.*;
 
public class Main {
 
	void submit() {
	    int N = nextInt();
	    int[] A1 = new int[N];
	    int[] A2 = new int[N];
	    
	    for(int i=0; i<N; i++){
	        A1[i] = nextInt();
	    }

	    for(int i=0; i<N; i++){
	        A2[i] = nextInt();
	    }
	    
	    int max_total=0;
	    int tmp_total=0;
	    
	    for(int i=0; i<N; i++){
	        tmp_total += A1[i];
	    }
	    tmp_total += A2[N-1];
	    max_total = tmp_total;
	    
	    
	    tmp_total = 0;
	     
	    for(int i=0; i<N; i++){
	        int j, k;
            for(j=0; j<=i; j++){
            	tmp_total += A1[j];            
            }
//            out.println("tmp_total A1 : "+tmp_total);   

            for(k=j-1; k<N; k++){
//                out.println("tmp_total: "+tmp_total);                   
                tmp_total += A2[k];
            }
//            out.println("tmp_total A1A2: "+tmp_total);   
            
//            out.println("tmp_total: "+tmp_total);    	        
    	    if(max_total < tmp_total){
    	        max_total = tmp_total;
    	    }
    	    tmp_total = 0;
	    }
	    
        out.println(max_total);
	}

	void preCalc() {
 
	}
 
	void stress() {
 
	}
 
	void test() {
 
	}
 
	Main() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		preCalc();
		submit();
		//stress();
		//test();
		out.close();
	}
 
	static final Random rng = new Random();
 
	static int rand(int l, int r) {
		return l + rng.nextInt(r - l + 1);
	}
 
	public static void main(String[] args) throws IOException {
		new Main();
	}
 
	BufferedReader br;
	PrintWriter out;
	StringTokenizer st;
 
	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return st.nextToken();
	}
 
	String nextString() {
		try {
			return br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
 
	int nextInt() {
		return Integer.parseInt(nextToken());
	}
 
	long nextLong() {
		return Long.parseLong(nextToken());
	}
 
	double nextDouble() {
		return Double.parseDouble(nextToken());
	}
}