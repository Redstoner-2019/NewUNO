import me.redstoner2019.uno.util.Util;


public class Main {
    public static void main(String[] args) {
        int attempts = 0;
        while (true){
            attempts++;
            String code = Util.generateRandomCode(5);
            /*if("BOOBS".equals(code.replace("0","O").replace("8","B"))){
                System.out.println(code);
                System.out.println(attempts);
                break;
            }*/
            if("BOOBS".equals(code)){
                System.out.println(code);
                System.out.println(attempts);
                break;
            }
            if(attempts%1000000 == 0){
                System.out.println("Attempt: " + attempts);
            }
        }
    }
}
