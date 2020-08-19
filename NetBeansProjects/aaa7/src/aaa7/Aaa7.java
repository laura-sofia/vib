package aaa7;

import java.util.ArrayList;
import java.util.Scanner;


public class Aaa7 {

    
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        
        while(true){
            int a=scn.nextInt();
            int b=scn.nextInt();
            
            if (a==0 && b==0)break;
            
            int[]head=new int[a];
            int[]knight=new int[b];
            long pay=0;
            
            for (int i=0;i<a;i++){
                head[i]=scn.nextInt();
            }
            
            for (int i=0;i<b;i++){
                knight[i]=scn.nextInt();
            }
            if (a>b){
                doomed();
                continue;
            }
            boolean cont=true;
            int knightInd=0;
            head=mergesort(head);
            knight=mergesort(knight);
            
            for (int h=0;h<a;h++){
                int hDiam=head[h];
                
                while (knight[knightInd]<hDiam){
                    knightInd++;
                    if (knightInd==b){
                        cont=false;
                        break;
                    }
                }
                pay+=knight[knightInd];
                knightInd++;
                if (!cont)break;
            }
            if(!cont){
                doomed();
                
            }
            else{
                System.out.println(pay);
            }
            
        }
        
        
    }
    static void doomed(){
        String lose="Loowater is doomed!";
        System.out.println(lose);
    }
    static int[] mergesort(int[] arr ){
        
        int n=arr.length;
        if (n==1)return arr;
        
        if (n==2){
            
            int a=arr[0];
            int b=arr[1];
            if (a>b){
                
                arr[0]=b;
                arr[1]=a;
                
            }
            return arr;
            
        }
        int len=n/2;
        int[] a=new int[len];
        int[] b=new int[n-len];
        
        for (int i=0;i<len;i++){
            a[i]=arr[i];
        }
        for (int i=0;i<n-len;i++){
            b[i]=arr[i+len];
        }
        
        a=mergesort(a);
        b=mergesort(b);
        int[]res=new int[n];
        
        int ia=0;
        int ib=0;
        
        for (int i=0;i<n;i++){
            int na=a[ia];
            int nb=b[ib];
            
            if (na>nb){
                
                res[i]=b[ib];
                
                ib++;
                
            }
            else{
                
                res[i]=a[ia];
                
                ia++;
            }
            
            if (ia==len){
                for (int y=ib;y<n-len;y++){
                    i++;
                    res[i]=b[y];
                    
               }
                break;
            }
            else if (ib==n-len){
                for (int y=ia;y<len;y++){
                    i++;
                    res[i]=a[y];
                    
                }
                break;
            }
        }
        return res;
    }
}









