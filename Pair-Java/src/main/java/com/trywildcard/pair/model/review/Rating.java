package com.trywildcard.pair.model.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.validation.ValidationTool;

public class Rating {

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    //required fields
    private Float rating;
    private Float minimumRating;
    private Float maximumRating;

    //optional fields
    private Integer numberOfRatings;

    /** Constructor for Deserialization **/
    private Rating() {}

    public Rating(Float rating, Float minimumRating, Float maximumRating) throws CardBuilderException {
        setRating(rating);
        setMinimumRating(minimumRating);
        setMaximumRating(maximumRating);
    }

    public Rating(Float rating, Float minimumRating, Float maximumRating,
                   Integer numberOfRatings) throws CardBuilderException {
        setRating(rating);
        setMinimumRating(minimumRating);
        setMaximumRating(maximumRating);

        setNumberOfRatings(numberOfRatings);
    }

    public Float getRating() {
        return rating;
    }

    public Float getMinimumRating() {
        return minimumRating;
    }

    public Float getMaximumRating() {
        return maximumRating;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    private void setRating(Float rating) throws CardBuilderException {
        v.required(v.notNull(rating), "Rating must not be null");
        v.required(v.notNegative(rating), "Rating must be a non-negative Float.");

        this.rating = rating;
    }

    private void setMinimumRating(Float minimumRating) throws CardBuilderException {
        v.required(v.notNull(minimumRating), "Minimum Rating must not be null");
        v.required(v.notNegative(minimumRating), "Minimum Rating must be a non-negative Float.");

        this.minimumRating = minimumRating;
    }

    private void setMaximumRating(Float maximumRating) throws CardBuilderException {
        v.required(v.notNull(maximumRating), "Maximum Rating must not be null");
        v.required(v.notNegative(maximumRating), "Maximum Rating must be a non-negative Float.");

        this.maximumRating = maximumRating;
    }

    public void setNumberOfRatings(Integer numberOfRatings) throws CardBuilderException {
        boolean isNotNull = v.optional(v.notNull(numberOfRatings), "Number of Ratings must not be null");
        boolean isNotNegative = v.optional(v.notNegative(numberOfRatings), "Number of Ratings must be a non-negative Integer.");

        if (isNotNull && isNotNegative) {
            this.numberOfRatings = numberOfRatings;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating1 = (Rating) o;

        if (maximumRating != null ? !maximumRating.equals(rating1.maximumRating) : rating1.maximumRating != null)
            return false;
        if (minimumRating != null ? !minimumRating.equals(rating1.minimumRating) : rating1.minimumRating != null)
            return false;
        if (numberOfRatings != null ? !numberOfRatings.equals(rating1.numberOfRatings) : rating1.numberOfRatings != null)
            return false;
        if (rating != null ? !rating.equals(rating1.rating) : rating1.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rating != null ? rating.hashCode() : 0;
        result = 31 * result + (minimumRating != null ? minimumRating.hashCode() : 0);
        result = 31 * result + (maximumRating != null ? maximumRating.hashCode() : 0);
        result = 31 * result + (numberOfRatings != null ? numberOfRatings.hashCode() : 0);
        return result;
    }
}
