package aaa7;

import java.util.ArrayList;
import java.util.Scanner;


public class Aaa7 {
    
   

    public static void main(String[]args){
        
        Scanner scn=new Scanner(System.in);
        
        while(true){
            
            String s;
            try{
                s=scn.nextLine();
                if(s.isEmpty()){
                    break;
                }
            }catch(Exception e){
                break;
            }
            ar.add(Integer.parseInt(s));
        }
        int[]res=new int[0];
        for (int i=1;i<ar.size();i++){
            p=new int[i];
            m=new int[i+1];
            int[] n=lis(i);
            if (n.length>res.length)res=n;
        }
        System.out.println(res.length);
        System.out.println("-");
        for (int i:res){
            System.out.println(i);
        }
        
    }
    static ArrayList<Integer> ar=new ArrayList<>();
    static int[] p, m;
    static int l;
    static int[] lis( int n){
        l=0;
        for (int i=0;i<n;i++){
            int low=1;
            int high=l;
            
            while(low<=high){
                int mid=(low+high)/2;
                if (ar.get(m[mid])< ar.get(i))low=mid+1;
                else high=mid-1;
            }
            int newL=low;
            p[i]=m[newL-1];
            m[newL]=i;
            
            if (newL>l)l=newL;
            
        }
        int[]s=new int[l];
            int k=m[l];
            for (int y=l-1;y>-1;y--){
                s[y] =ar.get(k);
                k=p[k];
            }
        return s;
    }
}
    

    
