package deliveroo.cron.utilities;

import java.util.Map;

public class PrintUtils {

    public static void printTable(final Map<String,String> input){
        for(Map.Entry<String,String> entry : input.entrySet()){
            System.out.print(entry.getKey());
            for(int i=0;i<(14-entry.getKey().length());i++){
                System.out.print(" ");
            }
            System.out.println(entry.getValue());
        }
    }
}
