public class MovieRating implements Comparable <MovieRating> {
    private String title;
    private double cumulativeRating;
    private int numRatings;

    // TODO: Your code here
    public int compareTo(MovieRating other) {
        int ratingCompare = Double.compare(this.averageRating(), other.averageRating());
        if(ratingCompare != 0 && this.numRatings > 0 && other.numRatings > 0 ) {
            return ratingCompare;
        } else if(this.numRatings != other.numRatings) {
            return Integer.compare(this.numRatings, other.numRatings);
        } else {
            return this.title.compareTo(other.title);
        }
    }

    public static void main(String[] args) {
        MovieRating bruno = new MovieRating("Encanto");
        MovieRating fourTownie = new MovieRating("Turning Red");
        bruno.addRating(4.7);
        fourTownie.addRating(4.6);
        System.out.println("4.7 Encanto > 4.6 Turning Red");
        System.out.println(bruno.compareTo(fourTownie));
    }

    public MovieRating(String title) {
        this.title = title;
    }

    public String movieTitle() {
        return title;
    }

    public double averageRating() {
        if (numRatings == 0) {
            return Double.NaN;
        }
        return cumulativeRating / numRatings;
    }

    public void addRating(double rating) {
        cumulativeRating += rating;
        numRatings++;
    }

    public String toString() {
        return movieTitle() + " -- " + averageRating();
    }

}