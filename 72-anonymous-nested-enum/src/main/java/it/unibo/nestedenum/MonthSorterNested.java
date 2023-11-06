package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.lang.String;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month{
        GENNAIO("january",31),
        FEBBRAIO("february",28),
        MARZO("march",31),
        APRILE("april",30),
        MAGGIO("may",31),
        GIUGNO("june",30),
        LUGLIO("july",31),
        AGOSTO("august",31),
        SETTEMBRE("september",30),
        OTTOBRE("october",31),
        NOVEMBRE("november",30),
        DICEMBRE("december",31);



        private String actualName;
        private final int nDays;

        private Month(final String actualName, final int nDays){
            this.actualName = actualName;
            this.nDays = nDays;
        }

        public static Month fromString( String month){
            List<Month> monthsList = List.of(Month.values());
            boolean isValid;
            List<Month> validMonths = new ArrayList<>();
            month = month.toLowerCase();

            for (Month m : monthsList) {
                if(month.equals(m.actualName)){
                    return m;
                }
                isValid = true;
                for(int i=0; i<month.length(); i++){
                    if(m.actualName.length() > i){
                        if(!(month.charAt(i) == m.actualName.charAt(i))){
                            isValid = false;
                        }
                    }
                }
                if(isValid){
                    validMonths.add(m);
                }
            }

            if(validMonths.size() == 1){
                return validMonths.get(0);
            }
            throw new IllegalArgumentException("Nessun mese trovato per"+month);
        }
    }


    private static class SortByMonthOrder implements Comparator<String>{

        public int compare(String o1, String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }
    }

    private static class SortByDate implements Comparator<String>{

        public int compare(String o1, String o2) {
            return Month.fromString(o1).nDays - Month.fromString(o2).nDays;
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }
}
