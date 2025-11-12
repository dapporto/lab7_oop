package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public static final int MONTHNUMBER = 12;

    public enum Month{
        JANUARY(31), 
        FEBRUARY(28), 
        MARCH(31), 
        APRIL(30), 
        MAY(31), 
        JUNE(30), 
        JULY(31), 
        AUGUST(31), 
        SEPTEMBER(30), 
        OCTOBER(31), 
        NOVEMBER(30), 
        DECEMBER(31);

        private final int monthDays;

        private Month(int monthDays){
            this.monthDays = monthDays;
        }
        
        public int getMonthDays() {
            return monthDays;
        }

        public int getIndex() {
            return this.ordinal();
        }

        public static Month fromString(String wordToCheck) {
            if (wordToCheck == null || wordToCheck.trim().isEmpty()) {
                throw new IllegalArgumentException("The string passed is null");
            }

            wordToCheck = wordToCheck.trim().toLowerCase();
            final var matches = new ArrayList<Month>();
            for (Month m : Month.values()) {
                String monthName = m.name().toLowerCase();
                if (monthName.startsWith(wordToCheck)) {
                    matches.add(m);
                }
            }
            if (matches.size() == 1) {
                return matches.get(0);
            }
            for (Month m : Month.values()) {
                if (wordToCheck.equals(m.name().toLowerCase())) {
                    return m;
                }
            }
            throw new IllegalArgumentException("No month matched");
        }
    }

    private class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(Month.fromString(o1).getIndex(), Month.fromString(o2).getIndex());
        }
    }

    private class SortByDate implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(Month.fromString(o1).getMonthDays(), Month.fromString(o2).getMonthDays());
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
