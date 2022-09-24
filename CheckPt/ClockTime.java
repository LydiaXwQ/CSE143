import javax.xml.namespace.QName;

public class ClockTime implements Comparable <ClockTime> {
    private int hours;
    private int minutes;
    private String amPm;

    // TODO: Your code here
    public int compareTo(ClockTime other) {
        if(!this.amPm.equals(other.amPm)) {
            return this.amPm.compareTo(other.amPm);
        } else if (this.hours != other.hours) {
            return Integer.compare(this.hours % 12, other.hours % 12);
        } else {
            return Integer.compare(this.minutes, other.minutes);
        }
    }
    

    public static void main(String[] args) {
        ClockTime a = new ClockTime(11, 59, "pm");
        ClockTime b = new ClockTime(12, 0, "pm");
        System.out.println("11:59pm > 12:00am");
        System.out.println(a.compareTo(b));
    }

    public ClockTime(int hours, int minutes, String amPm) {
        this.hours = hours;
        this.minutes = minutes;
        this.amPm = amPm;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getAmPm() {
        return amPm;
    }

    public String toString() {
        String result = hours + ":";
        if (minutes < 10) {
            result += "0" + minutes;
        } else {
            result += minutes;
        }
        result += " " + amPm;
        return result;
    }
}