import java.util.*;



public class reverseSentence {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the sentence: ");
        String a = sc.nextLine();
        String revString = "";
        for(int i = a.length()-1; i >= 0; i--){    
            revString = revString + a.charAt(i);    
        }    
            
        System.out.println("Original string: " + a);    
        //Displays the reverse of given string    
        System.out.println("Reverse of given string: " + revString);    
    }    


}
    
