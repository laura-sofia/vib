import java.util.Scanner;

/**
 *
 * @author sveta
 */
public class Main {

        static  void swap( int left, int right) 
            
    { 
        int temp = order[left]; 
        order[left] = order[right]; 
        order[right] = temp; 
    } 
    static void reverse( int left, int right) 
    { 
        while (left < right) { 
            int temp = order[left]; 
            order[left++] = order[right]; 
            order[right--] = temp; 
        } 
    } 
    
    static boolean nextPermutation() 
    { 
        
        int last = n - 2; 
  
        while (last >= 0) { 
            if (order[last] < order[last + 1]) { 
                break; 
            } 
            last--; 
        } 
        if (last < 0) 
            return false; 
  
        int nextGreater = n - 1; 
        
        for (int i = n - 1; i > last; i--) { 
            if (order[i] > order[last]) { 
                nextGreater = i; 
                break; 
            } 
        } 
  
        swap( nextGreater, last); 
  
        reverse(last + 1, order.length - 1); 
  
        return true; 
    }
    
    static int i,n,caseNo=1;
    static int[] order;
    static double[] a, b;
    static double timeGap, maxTimeGap;
    
    static double greedyLanding(){
        
       double lastLanding = a[order[0]];
       for (i=1;i<n;i++){
           double targetLandingTime = lastLanding + timeGap;
           if (targetLandingTime <= b[order[i]]){
           lastLanding = Math.max(a[order[i]], targetLandingTime);}
           else return 1;
       }
       return lastLanding - b[order[n-1]];
    }

    public static void main(String[]args){
        Scanner scn=new Scanner(System.in);
        
        while(true){
            n=scn.nextInt();
            if (n==0)break;
            order=new int[n];
            a=new double[n];
            b=new double[n];
            for (int i=0;i<n;i++){
                a[i]=scn.nextInt()*60;
                b[i]=scn.nextInt()*60;
                order[i]=i;
            }
            
            maxTimeGap = -1;
            do{
                double lowVal=0, highVal=86400;
                timeGap=-1;
                while (Math.abs(lowVal-highVal) >= 1e-3){
                    timeGap=(lowVal+highVal)/2.0;
                    double retVal=greedyLanding();
                    if (retVal <= 1e-2)lowVal=timeGap;
                    else highVal = timeGap;
                }
                maxTimeGap = Math.max(maxTimeGap, timeGap);
            }while(nextPermutation());
            
            maxTimeGap = (int) (maxTimeGap + 0.5);
            int aa=(int)(maxTimeGap%60);
            int bb=(int)(maxTimeGap%60)%10;
            System.out.printf("Case %d: %d:%d%d\n",caseNo++, (int)(maxTimeGap / 60), bb,aa-bb );
            
        }
    }
    
}
