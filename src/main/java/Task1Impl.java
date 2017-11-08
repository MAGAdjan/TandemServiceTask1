import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */
public class Task1Impl implements IStringRowsListSorter {

    // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
    public static final IStringRowsListSorter INSTANCE = new Task1Impl();


    public void sort(final List<String[]> rows, final int columnIndex) {

        // first check list for not being null or empty
        if(rows == null || rows.size() == 0) {
            return;
        }
        // напишите здесь свою реализацию. Мы ждем от вас хорошо структурированного, документированного и понятного кода.
        Collections.sort(rows, new Comparator<String[]>() {
            public int compare(String[] o1, String[] o2) {
                String s1, s2;
                try {
                    s1 = o1[columnIndex];
                    s2 = o2[columnIndex];
                }catch(ArrayIndexOutOfBoundsException e) {
                    return 0;                                // if arrays are of different size, then don't sort
                }

                // first check for null values (if both strings are null or only first string is null
                // then don't do anything, otherwise swap them)
                if(s1 == null) {
                    return s2 == null ? 0 : -1;
                }

                if(s2 == null) {
                    return 1;
                }

                // then check for an empty strings (if both strings are empty or only first string is empty
                // then don't do anything, otherwise swap them)
                if(s1.trim().equals("")) {
                    return s2.trim().equals("") ? 0 : -1;
                }

                if(s2.trim().equals("")) {
                    return 1;
                }

                // splitToSubstrings each string to a list of substrings
                String[] firstSplitted = splitToSubstrings(s1);
                String[] secondSplitted = splitToSubstrings(s2);

                // loop through substrings, until we find difference
                // first: try to check for integer comparison
                // if NumberFormatException (integer comparison is unavailable), then do string comparison
                for(int i = 0; i < Math.min(firstSplitted.length, secondSplitted.length); i++) {
                    String nextWord1 = firstSplitted[i];
                    String nextWord2 = secondSplitted[i];
                    int result;
                    try{
                        int firstParsed = Integer.valueOf(nextWord1);
                        int secondParsed = Integer.valueOf(nextWord2);
                        result = firstParsed - secondParsed;
                    }catch(NumberFormatException e) {
                        result = nextWord1.compareToIgnoreCase(nextWord2);
                    }
                    if(result != 0) {
                        return result;
                    }
                }
                // in case there's no difference in strings, compare their lengths
                // (smallest goes ahead)
                return firstSplitted.length - secondSplitted.length;
            }
        });
    }

    /**
     * splits the given string into substrings by numbers and other characters
     * @param str string to splitToSubstrings
     * @return list of splitted substrings
     */
    public String[] splitToSubstrings(String str) {
        List<String> list = new ArrayList();
        Matcher match = Pattern.compile("\\d+|\\D+").matcher(str);
        while (match.find()) {
            list.add(match.group());
        }
        return list.toArray(new String[list.size()]);
    }
}
