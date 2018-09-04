/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package substituteteacher;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class SubstituteTeacher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner reader=new Scanner(System.in);
        for(int i=0; i< 10;i++){
            String real = reader.nextLine();
            real = real.substring(1,real.length()-2);
            String encrypt = reader.nextLine();
            encrypt = encrypt.substring(1,encrypt.length()-2);
            String message=reader.nextLine();
            message = message.substring(1,message.length()-1);
            String Rm = findRealmessage(real,encrypt,message);
            System.out.println("*"+Rm+"*");
        }
    }

    private static String findRealmessage(String real, String encrypt, String message) {
        int ke = 0;
        int kd = 0;
        boolean found = false;
        String RE = "";
        for (ke=2;ke<=66;ke++) {
            for (kd=2;kd<=66;kd++) {
                for (int i=0;i<real.length();i++) {
                    int m= Numchange(real.charAt(i));
                    int c= Numchange(encrypt.charAt(i));
                    if (ke*m%67 ==c && kd*c%67==m) {
                        found=true;
                        break;
                    }
                }
                if (found) break;
            }
            if (found) break;
        }
        if(ke==66 && kd == 66 && found == false){
            RE = "not resolved.";
            return RE;
        }
        
        for(int i =0; i<message.length();i++){
            int m=kd*Numchange(message.charAt(i))%67;
            char Re = Rechange(m);
            RE += String.valueOf(Re); 
        }
        return RE;
    }
    

    private static int Numchange(char c) {
        if (c>='A' && c<='Z') return (c-'A'+1);
        if (c>='a' && c<='z') return (c-'a'+27);
        if (c>='0' && c<='9') return (c-'0'+53);
        switch (c) {
            case ' ':
                return 63;
            case '.':
                return 64;
            case ',' :
                return 65;
            case '?':
                return 66;
        }
        return 0;
    }

    private static char Rechange(int m) {
        if (m>=1 && m<=26) return (char)('A'+m-1);
        if (m>=27 && m<=52) return (char)('a'+m-27);
        if (m>=53 && m<=62) return (char)('0'+m-53);
        switch (m) {
            case 63:
                return ' ';
            case 64:
                return '.';
            case 65:
                return ',';
            case 66:
                return '?';
        }
        return '*';
    }
    
}
