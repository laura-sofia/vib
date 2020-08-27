import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    
   

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
        ArrayList<Integer> res=new ArrayList<>();
        for (int i=0;i<ar.size();i++){
            ArrayList<Integer> n=lis(i);
            if (n.size()>res.size())res=n;
        }
        System.out.println(res.size());
        System.out.println("-");
        for (int i:res){
            System.out.println(i);
        }
        
    }
    static ArrayList<Integer> ar=new ArrayList<>();
    static ArrayList<Integer> lis( int i){
        ArrayList<Integer> arr=new ArrayList<>();
        if(i==0){
            arr.add(ar.get(0));
            return arr;
        }
        int res=0;
        ArrayList<Integer> qwe=new ArrayList<>();
        for (int j=0;j<i;j++){
            if (ar.get(j)<ar.get(i)){
                ArrayList<Integer> r=lis(j);
                if (r.size()+1>res){
                    qwe=r;
                    res=r.size();
                }
            }
        }
        qwe.add(ar.get(i));
        return qwe;
    }
            
}