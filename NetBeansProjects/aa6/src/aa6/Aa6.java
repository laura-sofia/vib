/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aa6;

import java.util.*;

/**
 *
 * @author sveta
 */
public class Aa6 {
    static long sum=0;
            
    static Queue<int[]>pos=new LinkedList<>();
           
    static Queue<int[]>neg=new LinkedList<>();
            

    public static void main(String[]args){
        
        Scanner scn=new Scanner(System.in);
        
        while(true){
            int n=scn.nextInt();
            if(n==0)break;
            
            sum=0;
            pos.clear();
            neg.clear();
            
            for (int i=0;i<n;i++){
                int a=scn.nextInt();
                
                if (a<0){
                    negg(a,i);
                }
                else if(a>0){
                    poss(a,i);
                    
                }
            }
            System.out.println(sum);
            
            
        }
    }
    public static void negg(int a, int i){
        if (pos.isEmpty())neg.add(new int[]{Math.abs(a),i});
        else{
            int q=Math.abs(a);
            int[]now=pos.peek();
            if (now[0]>q){
                sum+=(q)*(i-now[1]);
                pos.peek()[0]=now[0]-q;
            }
            else if(now[0]<q){
                sum+=(now[0])*(i-now[1]);
                a+=now[0];
                pos.remove();
                negg(a,i);
            }
            else{
                pos.remove();
                sum+=q*(i-now[1]);
            }
                       
        }
    }
    public static void poss(int a,int i){
        if (neg.isEmpty())pos.add(new int[]{a,i});
        else{
            
            int now[]=neg.peek();
            if (now[0]>a){
                sum+=(a)*(i-now[1]);
                neg.peek()[0]=now[0]-a;
            }
            else if(now[0]<a){
                sum+=(now[0])*(i-now[1]);
                a-=now[0];
                neg.remove();
                poss(a,i);
            }
            else{
                neg.remove();
                sum+=a*(i-now[1]);
            }
                        
        }
    }
}
