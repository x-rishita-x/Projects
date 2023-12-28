import java.util.*;

class MyException extends Exception{
    MyException(String msg){
        super(msg);
    }
}
public class Solutions {

    public static void main(String[] args)  {
        Scanner sc=new Scanner(System.in);
        try{
            int n=sc.nextInt();
            if(n<0 || n>9){
                throw new MyException ("myyy");
            }
            int ans=n*n;
            System.out.println(ans);

        }
        catch(MyException e){
            System.out.println("e");
        }
    }
}
