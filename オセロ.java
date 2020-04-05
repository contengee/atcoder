import java.io.*;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main {

    public static final int BAN = 0;
    public static final int WHITE = 1;
    public static final int BLACK = 2;
    public static final int CAN_PLACE = 3;

    public static final int MAX_LEN = 8;

    public static int board[][]  = new int[MAX_LEN][MAX_LEN];
    public static int kouho[][] = new int[MAX_LEN][MAX_LEN];

    public static int tate= 2;
    public static int yoko= 4;

	void okeru() {
	    board[3][3] = WHITE;
	    board[4][4] = WHITE;
	    board[4][3] = BLACK;
	    board[3][4] = BLACK;
	    board[3][5] = BLACK;	    
	    board[3][6] = BLACK;	    
	    board[4][6] = WHITE;	    	    
	    board[5][5] = BLACK;	    	    
	    board[2][2] = WHITE;	    	    
	    board[2][3] = BLACK;	    	    
	    board[2][2] = BLACK;	    	    
	    board[2][1] = WHITE;	    	    
	    board[2][5] = BLACK;	    	    
	    board[2][6] = WHITE;	    	    


    	out.println("白の手番です");
    	
    	// 横（j列）
	    for(int i= 0; i<MAX_LEN; i++){
	        for(int j=0; j<MAX_LEN; j++){
                CheckLeft(i,j);
                CheckRight(i,j);
                CheckUp(i,j);
                CheckDown(i,j);
                CheckLeftUp(i,j);
                CheckLeftDown(i,j);
                CheckRightUp(i,j);
                CheckRightDown(i,j);
            }

        }
    }

    void CheckLeft(final int i, final int j){
        // 白の左側が黒か
        if(board[i][j] != WHITE || board[i][j-1] != BLACK) return;
        // どこまで黒か
        for(int k = j-2; k>0; k--){
            if(board[i][k]==BLACK) continue;
            // 空白を発見したらマーク
            if(board[i][k]==BAN){
                kouho[i][k]=CAN_PLACE;
                break;
            }
        }
        return;
    }

    void CheckRight(final int i, final int j){
        if(board[i][j] != WHITE || board[i][j+1] != BLACK) return;
        for(int k = j+2; k<=MAX_LEN; k++){
            if(board[i][k]==BLACK) continue;
            if(board[i][k]==BAN){
                kouho[i][k]=CAN_PLACE;
                break;
            }
        }
        return;
    }

    void CheckUp(final int i, final int j) {
        if (board[i][j] != WHITE || board[i - 1][j] != BLACK)
            return;
        for (int k = i - 2; k > 0; k--) {
            if (board[k][j] == BLACK)
                continue;
            if (board[k][j] == BAN) {
                kouho[k][j] = CAN_PLACE;
                break;
            }
        }
        return;
    }

    void CheckDown(final int i, final int j) {
        if (board[i][j] != WHITE || board[i + 1][j] != BLACK)
            return;
        for (int k = i + 2; k <= MAX_LEN; k++) {
            if (board[k][j] == BLACK)
                continue;
            if (board[k][j] == BAN) {
                kouho[k][j] = CAN_PLACE;
                break;
            }
        }
        return;
    }

    void CheckLeftUp(final int i, final int j) {
        if (board[i][j] != WHITE || board[i - 1][j - 1] != BLACK)
            return;
        // どこまで黒かチェック
        for (int k = 2; i - k >= 0 && j - k >= 0; k--) {
            if (board[i - k][j - k] == BLACK)
                continue;
            // 空白を発見した時点でaマーク
            if (board[i - k][j - k] == BAN) {
                kouho[i - k][j - k] = CAN_PLACE;
                break;
            }
        }
        return;
    }

    void CheckRightUp(final int i, final int j){
         // 白の右上が黒かチェック
        if(board[i][j] != WHITE || board[i - 1][j + 1] != BLACK)
            return;
        for(int k = 2; i-k < 0 || j+k >= MAX_LEN; k++){
            // どこまで黒かチェック
            if(board[i-k][j+k]==BLACK) continue;
            // 空白を発見した時点で☆マーク                	        
            if(board[i-k][j+k]==BAN){
                kouho[i-k][j+k]=CAN_PLACE;
                break;
            }
        }
        return;
    }

    void CheckLeftDown(final int i, final int j) {
        // 白の左下が黒かチェック
        if (board[i][j] != WHITE || board[i + 1][j - 1] != BLACK)
            return;
        // どこまで黒かチェック
        for (int k = 2; i + k >= 0 && j - k < MAX_LEN; k++) {
            if (board[i + k][j - k] == BLACK)
                continue;
            // 空白を発見した時点で☆マーク
            if (board[i + k][j - k] == BAN) {
                kouho[i + k][j - k] = CAN_PLACE;
                break;
            }
        }
        return;
    }

    void CheckRightDown(final int i, final int j) {
        // 白の右下が黒かチェック
        if(board[i][j] != WHITE || board[i+1][j+1] != BLACK) return;
        for(int k = 2; i+k<MAX_LEN && j+k<MAX_LEN; k++){
            // どこまで黒かチェック
            if(board[i+k][j+k]==BLACK) continue;
            // 空白を発見した時点で☆マーク                	        
            if(board[i+k][j+k]==BAN){
                kouho[i+k][j+k]=CAN_PLACE;
                break;
            }
        }
    }



    void hanten(){
//        aの位置に置いたWHITEから、連続したBLACKの次にあるWHITEまでをWHITEに変換
//        それをタテヨコナナメで繰り返す
//        よこ　スタート位置 board（tate, yoko）
//        よこ（tate,j）
        
        // 横（j列） 左側
        ReverseLeft();
        ReverseRight();
        ReverseUp();
        ReverseDown();
        ReverseLeftUp();
        ReverseLeftDown();
        ReverseRightUp();
        ReverseRightDown();        

/*
        	        // 白の右側が黒かチェック
        	        if(board[i][j+1] == BLACK){
            	        for(int k = j+2; k<=MAX_LEN; k++){
            	            // どこまで黒かチェック
            	        if(board[i][k]==BLACK){
                	            continue;
                	        }
        	                // 空白を発見した時点で☆マーク                	        
                	        if(board[i][k]==BAN){
                                kouho[i][k]=CAN_PLACE;
                                break;
            	           }
            	        }
    	            }
    	        }
    	        */
    }

    void ReverseLeft(){
        // どこまで黒かチェック
        int j;
        for(j=yoko-1; j>0; j--){
            if(board[tate][j]==BLACK){
                continue;
            }else if(board[tate][j]==WHITE){
                break;
            }else{
                return; 
            }
        }
        for(int rev = j; rev<yoko; rev++){
            board[tate][rev] = WHITE;
        }        
        return;
    }

    void ReverseRight(){
        // どこまで黒かチェック
        int j;
        for(j=yoko+1; j<MAX_LEN; j++){
            if(board[tate][j]==BLACK){
                continue;
            }else if(board[tate][j]==WHITE){
                break;
            }else{
                return;
            }
        }
        for(int rev = j; rev>yoko; rev--){
            board[tate][rev] = WHITE;
        }           	           
        return;
    }

    void ReverseUp(){
        // どこまで黒かチェック
        int i;
        for(i=tate-1; i>0; i--){
            if(board[i][yoko]==BLACK){
                continue;
            }else if(board[i][yoko]==WHITE){
                break;
            }else{
                return;
            }
        }
        for(int rev = i; rev<tate; rev++){
            board[rev][yoko] = WHITE;
        }           	           
        return;
    }

    void  ReverseDown(){
        // どこまで黒かチェック
        int i;
        for(i=tate+1; i<MAX_LEN; i++){
            if(board[i][yoko]==BLACK){
                continue;
            }else if(board[i][yoko]==WHITE){
                break;
            }else{
                return;
            }
        }
        for(int rev = i; rev>tate; rev--){
            board[rev][yoko] = WHITE;
        }           	           
        return;
    }

    void ReverseLeftUp(){
        // どこまで黒かチェック
        int j;
        for(j=1; j<MAX_LEN; j++){
            if(board[tate-j][yoko-j]==BLACK){
                continue;
            }else if(board[tate-j][yoko-j]==WHITE){
                break;
            }else{
                return; 
            }
        }
        // 斜めの処理どうする？　minで処理するか、配列自体を[10][10]にして壁を用意する。
        // 現在だと配列からはみ出してしまう
        Min = Math.min(tate, yoko);
        for(int rev = j; rev<yoko; rev++){
            board[tate][rev] = WHITE;

        }        
        return;
    }


    void  ReverseLeftDown(){};
    void  ReverseRightUp(){};
    void ReverseRightDown(){};   
    

	void oku() {
        out.println("横、縦の数値を２つ入力して置く位置を選択して下さい ");
//        yoko= nextInt();
//        tate= nextInt();

        while(true){
            if(kouho[tate][yoko] == CAN_PLACE){
                board[tate][yoko] = WHITE;
                break;
            }
        }
	}

	void hyoji() {
	    // 表示 
        out.print(" ");	    
    	for(int i= 0; i<MAX_LEN; i++){
            out.print(" "+(i+1));
    	}
        out.println();
    	for(int i= 0; i<MAX_LEN; i++){
            out.print((i+1)+"|");                
            for(int j=0; j<MAX_LEN; j++){
        	    if(board[i][j]==WHITE ){
        	        out.print("○|");
        	    }else if(board[i][j]==BLACK){
        	        out.print("●|");
        	    }else if(kouho[i][j]==CAN_PLACE){
        	        out.print("a|");
        	    }else{
        	        out.print(" |");
        	    }
            }
        	out.println(" ");
    	}
	}
 
	Main() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		okeru();
		hyoji();
//０．aから番号選択
		oku();
//１．置いた時にタテ・ヨコ・ナナメの挟まれた色をひっくり返す関数
        hanten();

		hyoji();

//２．白の手番に交代

		out.close();
	}
 
	static final Random rng = new Random();
 
	static int rand(final int l, final int r) {
		return l + rng.nextInt(r - l + 1);
	}
 
	public static void main(final String[] args) throws IOException {
		new Main();
	}
 
	BufferedReader br;
	PrintWriter out;
	StringTokenizer st;
 
	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (final IOException e) {
				throw new RuntimeException(e);
			}
		}
		return st.nextToken();
	}
 
	String nextString() {
		try {
			return br.readLine();
		} catch (final IOException e) {
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