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
    public static void main(String[] args){
        
        Scanner scn=new Scanner(System.in);
        
        int test=scn.nextInt();
        
        for (int i=0;i<test;i++){
            String fin="";
            int m=scn.nextInt();
            for(int j=0;j<m;j++){
                int t=scn.nextInt();
                
                scn.nextLine();
                String s=scn.nextLine();
                
                for (int y=0;y<t;y++){
                    fin+=s;
                }
            }
            arr=new int[fin.length()];
            
            for (int k=0;k<fin.length();k++){
                arr[k]=(int)fin.charAt(k)-48;
            }
            tree=new int[(int)(2*Math.pow(2, Math.floor((Math.log((double)arr.length)/Math.log(2))+1)))];
            create(0,arr.length-1,1);
            int len=arr.length;
            int q=scn.nextInt();
            scn.nextLine();
            for (int j=0;j<q;j++){
                String[] h=scn.nextLine().split(" ");
                int a=Integer.parseInt(h[1]);
                int b=Integer.parseInt(h[2]);
                
                if ("F".equals(h[0])){
                    make(0,len-1,a,b,1,1);
                }
                else if("E".equals(h[0])){
                    make(0,len-1,a,b,1,0);
                }
                else if("I".equals(h[0])){
                    reverse(0,len-1,a,b,1);
                }
                else{
                    int res=query(0,len-1,a,b,1);
                    System.out.println(res);
                }
            }
            
        }
        
    }
    // tree[i][0]=n of 0(bulgrian);
    // tree[i][1]= no
    static int[]tree;
    static int[]arr;
    static void create(int start, int end, int index){
        
        if (start==end){
            tree[index]=arr[start];
        }
        else{
            int leftIdx=index*2;
            int rightIdx=leftIdx+1;
            
            create(start,(start+end)/2,leftIdx);
            create((start+end)/2+1,end,rightIdx);
            
            tree[index]=tree[leftIdx]+tree[rightIdx];
        }
    }
    static int query(int left, int right,int ql, int qr,int index){
        
        if(right<ql || left>qr)return -1;
        if (left>=ql && right<=qr)return tree[index];
        
        int p1=query(left, (left+right)/2, ql,qr,index*2);
        int p2=query((left+right)/2+1,right,ql,qr,index*2+1);
        
        if (p1==-1)return p2;
        if (p2==-1)return p1;
        
        return p1+p2;
    }
    static int reverse(int left,int right,int ql,int qr,int index){
        if (left>qr || right<ql)return -1;
        if (left==right&& left>=ql && left<=qr){
            if(tree[index]==1)tree[index]=0;
            else tree[index]=1;
            return tree[index];
        }
        
        int p1=reverse(left,(left+right)/2,ql,qr,index*2);
        int p2=reverse((left+right)/2+1,right,ql,qr,index*2+1);
        
        if(p1==-1){
            tree[index]=p2;
            return p2;
        }
        if (p2==-1){
            tree[index]=p1;
            return p1;
        }
        tree[index]=p1+p2;
        return p1+p2;
    }
    static int make(int left, int right, int ql, int qr, int index, int no){
        if (left>qr || right<ql)return -1;
        if (left==right&& left>=ql && left<=qr){
            
            return tree[index]=no;
        }
        int p1=make(left,(left+right)/2,ql,qr,index*2,no);
        int p2=make((left+right)/2+1,right,ql,qr,index*2+1,no);
        
        if(p1==-1){
            tree[index]=p2;
            return p2;
        }
        if (p2==-1){
            tree[index]=p1;
            return p1;
        }
        tree[index]=p1+p2;
        return p1+p2;
    }
}

