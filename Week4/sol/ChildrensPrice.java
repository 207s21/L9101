package sol;

/**
 * The price for a Children's Movie.
 */
public class ChildrensPrice extends Price {
    /**
     * Set's this class' base price and the amount of days until the charge gets
     * incremented.
     */
    public ChildrensPrice() {
        this.basePrice = 1.5;
        this.dayLimit = 3;
    }

    /**
     * Return's the amount charged for a child's price depending on the amount of
     * days they rented.
     * 
     * @param daysRented the number of days the movie was rented.
     */
    double getCharge(int daysRented) {
        double result = basePrice;
        if (daysRented > this.dayLimit)
            result += (daysRented - this.dayLimit) * 1.5;
        return result;
    }

    /**
     * Get the price code for a children's movie.
     * 
     * @return the price code for a children's movie
     */
    int getPriceCode() {
        return Movie.CHILDRENS;
    }
}
