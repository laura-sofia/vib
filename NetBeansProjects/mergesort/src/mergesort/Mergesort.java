/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mergesort;
import java.util.*;
/**
 *
 * @author sveta
 */
public class Mergesort {
    
    
static String[] arr=new String[10];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        // TODO code application logic here
    }
    public static String[][] mergesort(String[][] map ){
        
        int n=map[0].length;
        if (n==1)return map;
        
        if (n==2){
            
            int a=Integer.parseInt(map[0][0]);
            int b=Integer.parseInt(map[0][1]);
            if (a>b){
                map[0][0]=Integer.toString(b);
                map[0][1]=Integer.toString(a);
                String temp=map[1][0];
                map[1][0]=map[1][1];
                map[1][1]=temp;
            }
            return map;
            
        }
        int len=n/2;
        String[][]a=new String[2][len];
        String[][]b=new String[2][n-len];
        
        for (int i=0;i<len;i++){
            a[0][i]=map[0][i];
            a[1][i]=map[1][i];
        }
        for (int i=0;i<n-len;i++){
            b[0][i]=map[0][len+i];
            b[1][i]=map[1][len+i];
        }
        
        a=mergesort(a);
        b=mergesort(b);
        String[][]res=new String[2][n];
        
        int ia=0;
        int ib=0;
        
        for (int i=0;i<n;i++){
            int na=Integer.parseInt(a[0][ia]);
            int nb=Integer.parseInt(b[0][ib]);
            
            if (na>nb){
                
                res[0][i]=b[0][ib];
                res[1][i]=b[1][ib];
                
                ib++;
            }
            else{
                
                res[0][i]=a[0][ia];
                res[1][i]=a[1][ia];
                
                ia++;
            }
            
            if (ia==len){
                for (int y=ib;y<n-len;y++){
                    i++;
                    res[0][i]=b[0][ib];
                    res[1][i]=b[1][ib];
                    
               }
                break;
            }
            else if (ib==n-len){
                for (int y=ia;y<len;y++){
                    i++;
                    res[0][i]=a[0][ia];
                    res[1][i]=a[1][ia];
                    
                }
                break;
            }
        }
        return res;
    }
    
}
