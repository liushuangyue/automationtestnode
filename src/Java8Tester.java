import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Map;
public class Java8Tester {
    public static void main(String args[]) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        long count = getCountEmptyStringUsingJava7(strings);
        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        String mergedString = getMergedStringUsingJava7(strings,", ");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = getSquares(numbers);
        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
        Random random = new Random();

        for(int i=0; i < 10; i++){
            System.out.println(random.nextInt());
        }
        System.out.println("using java 8:");
        System.out.println("List Table: " + strings);

        count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("empty string:" + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("string length 3,the number is :" + count);

        filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
        System.out.println("filtered list table "+ filtered);

        mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println("merged string: "+ mergedString);

        squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.println("Squares List:" + squaresList);
        System.out.println("List table: " + integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x)->x).summaryStatistics();

        System.out.println("the max number : " + stats.getMax());
        System.out.println("the min number : " + stats.getMin());
        System.out.println("the sum : " + stats.getSum());
        System.out.println("the average : " + stats.getAverage());
        System.out.println("the random: ");

        random.ints().limit(10).sorted().forEach(System.out::println);
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("empty string: " + count);
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.isEmpty()){
                count++;
            }
        }
        return count;
    }
    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings){
        List<String> filteredList = new ArrayList<String>();

        for(String string: strings){

            if(!string.isEmpty()){
                filteredList.add(string);
            }
        }
        return filteredList;
    }
    private static String getMergedStringUsingJava7(List<String> strings, String separator){
        StringBuilder stringBuilder = new StringBuilder();

        for(String string: strings){

            if(!string.isEmpty()){
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length()-2);
    }
    private static List<Integer> getSquares(List<Integer> numbers){
        List<Integer> squaresList = new ArrayList<Integer>();

        for(Integer number: numbers){
            Integer square = new Integer(number.intValue() * number.intValue());

            if(!squaresList.contains(square)){
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers){
        int max = numbers.get(0);

        for(int i=1;i < numbers.size();i++){

            Integer number = numbers.get(i);

            if(number.intValue() > max){
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers){
        int min = numbers.get(0);

        for(int i=1;i < numbers.size();i++){
            Integer number = numbers.get(i);

            if(number.intValue() < min){
                min = number.intValue();
            }
        }
        return min;
    }
}
