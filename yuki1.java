import java.io.*;
import java.util.*;
 
public class Main {
 
	void submit() {
	    int N = nextInt();
	    int X = nextInt();
	    int Y = nextInt();
	    int Z = nextInt();	    

	    int[] A = new int[N];

	    for(int i=0; i<N; i++){
	        A[i] = nextInt();
	    }
	    
	    String buy = "Yes";
        
        for(int i=0; i<N; i++){
            if(A[i]<1000){
                if(X>0){
                    X--;
                }else if(Y>0){
                    Y--;
                }else if(Z>0){
                    Z--;
                }else{
        out.println("1 "+ A[i]);
                    buy = "No";
                    break;
                }
                /*
                1 435円
                合計千円札1枚
                これがダメなら
                5千円札1枚
                これがダメなら
                1万円札1枚
                これもダメならNoに書き換えてブレイク
                */
           }
            else if(A[i]<5000){
                int tmp_X=A[i]/1000;
                if(A[i]%1000!=0){
                    tmp_X++;
                }
                
                if(X>=tmp_X){
                    X -= tmp_X;
                }else if(Y>0){
                    Y--;
                }else if(Z>0){
                    Z--;
                }else{
        out.println("2 "+ A[i]);                    
                    buy = "No";
                    break;
                }
                /*              
                2 4236円
                1千円札個数 4236/1000 ※
                あまり 4236 % 1000 ! なら 1千円札消費１つ多い
                合計千円札5枚

                これがダメなら
                5千円札1枚
                これがダメなら
                1万円札1枚
                これもダメならNoに書き換えてブレイク
                */
            }else if(A[i]<10000){
                if(X>0){
                    A[i]-=5000;
                    X--;
                }
                int tmp_X=A[i]/1000;
                if(A[i]%1000!=0){
                    tmp_X++;
                }

                if(X>=tmp_X){
                    X -= tmp_X;
                }else if(Y>1){
                    Y-=2;
                }else if(Z>0){
                    Z--;
                }else{
        out.println("3 "+ A[i]);                    
                    buy = "No";
                    break;
                }
                /*
                3 8932円
                まず5千円札1枚
                出来たら壱に飛ばして、途中で駄目なら戻ってきて
                千円札で全て計算
                これもダメなら5千円札2枚　または　1万円札で計算
                これもだめならNoに書き換えてブレイク
                */
            }else{
                int kingaku = A[i];
                int amari_Z =A[i];
                int tmp_Z = A[i]/10000;
                int tmp_Y = tmp
                if(Z>=tmp_Z){
                    Z -= tmp_Z;
//                    amari_Z = A[i]%10000;
                }else{
                    Z = 0;
                    kingaku = A[i]- tmp_Z*10000;
                }

                kingaku = A[i]- tmp_Z*10000;                

                if(Y>=tmp_Z){

                }






                // 5千円以上の時
                if(Y>0 && amari_Z>=5000){
                    Y--;
                    amari_Z -= 5000;
                }
                // 5千円未満
                int tmp_X=amari_Z/1000;
                if(amari_Z%1000!=0){
                    tmp_X++;
                }
                
                if(X>=tmp_X){
                    X -= tmp_X;
                }else if(Y>0){
                    Y--;
                }else if(Z>0){
                    Z--;
                }else{
        out.println("5 "+ A[i]);
                    buy = "No";
                    break;
                }
                /*
                4 528932円
                1万円札個数   528932/10000　かつ　Zが沢山ある
                　　　　あまり 528932%10000
                5千円札個数   528932%10000 / 5000　かつ Yが沢山ある
               　 　　　あまり 528932%10000 % 5000
                1千円札個数   528932%10000%5000 / 1000 かつ
                　 　　　あまり 528932%10000 % 5000 % 1000 != 0 なら1千円札個数消費1つ多い　し、 Xが沢山ある
                */
            }
        }

        out.println(buy);
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