package aaa7;

import java.util.ArrayList;
import java.util.Scanner;


public class Aaa7 {
    
    static int M, C, K;
    static int[][]price=new int[25][25];
    static int[][]memo= new int[210][25];
    
    static int shop(int money_left, int garment_id){
        
        if (money_left <0)return -1;
        if (garment_id == C) return M- money_left;
        if (memo[money_left][garment_id] != -1)return memo[money_left][garment_id];
        int max_value=-10;
        for (int model=1;model<=price[garment_id][0];model++){
            max_value=Math.max(max_value, shop(money_left-price[garment_id][model], garment_id +1));
            
        }return memo[money_left][garment_id]=max_value;
    }
    

    public static void main(String[]args){
        
        Scanner scn=new Scanner(System.in);
        
        int score, TC,i,j;
        
        TC=scn.nextInt();
        while(TC--!=0){
            M=scn.nextInt();
            C=scn.nextInt();
            for (i=0;i<C;i++){
                price[i][0]=scn.nextInt();
                for (j=1;j<=price[i][0];j++){
                    price[i][j]=scn.nextInt();
                }
            }
        
        initialize();
        score = shop(M,0);
        if(score <0)System.out.println("no solution");
        else System.out.println(score);
        }
    }
    static void initialize(){
        for (int i=0;i<210;i++){
            for (int j=0;j<25;j++){
                memo[i][j]=-1;
            }
        }
    }
            
}