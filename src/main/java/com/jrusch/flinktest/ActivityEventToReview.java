package com.jrusch.flinktest;

import com.jrusch.flinktest.ActivityEvent;
import com.jrusch.flinktest.Review;
import org.apache.flink.api.common.functions.MapFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityEventToReview implements MapFunction<ActivityEvent, Review> {

    private static final Pattern docProfileIdPattern = Pattern.compile("docprofileid = (\\d+)");
    private static final Pattern reviewSlugPattern = Pattern.compile("reviewslug = '(.*?)'");
    private static final Pattern ratingPattern = Pattern.compile("rating = (\\d+)");
    private static final Pattern rawRatingPattern = Pattern.compile("rawrating = ([\\d.]+)");
    private static final Pattern bodyPattern = Pattern.compile("body = '(.*?)'");
    private static final Pattern sentimentPattern = Pattern.compile("sentiment = '(.*?)'");
    private static final Pattern emailPattern = Pattern.compile("email = '(.*?)'");
    private static final Pattern displayNamePattern = Pattern.compile("displayname = '(.*?)'");
    private static final Pattern hasBodyPattern = Pattern.compile("hasbody = (\\d)");
    private static final Pattern statusPattern = Pattern.compile("status = '(.*?)'");
    private static final Pattern reviewSourcePattern = Pattern.compile("reviewsource = '(.*?)'");
    private static final Pattern reviewSourceIdPattern = Pattern.compile("reviewsourceid = '(.*?)'");
    private static final Pattern reviewDatePattern = Pattern.compile("reviewdate = '(.*?)'");
    private static final Pattern destinationPattern = Pattern.compile("destination = '(.*?)'");
    private static final Pattern maturityPattern = Pattern.compile("maturity = '(.*?)'");
    private static final Pattern publishDatePattern = Pattern.compile("publishdate = '(.*?)'");
    private static final Pattern distributionDatePattern = Pattern.compile("distributiondate = '(.*?)'");
    private static final Pattern lastModifiedPattern = Pattern.compile("lastmodified = '(.*?)'");
    private static final Pattern createdPattern = Pattern.compile("created = '(.*?)'");


    @Override
    public Review map(ActivityEvent activityEvent) throws Exception {
        // Initialize a new Review object
        Review review = new Review();

        // Assuming activityEvent.getCommandText() returns the SQL command text
        String commandText = activityEvent.getCommandText();


        // Extract docProfileId
        Matcher matcher = docProfileIdPattern.matcher(commandText);
        if (matcher.find()) {
            review.setDocProfileId(Integer.parseInt(matcher.group(1)));
        }

        // Extract reviewSlug
        matcher = reviewSlugPattern.matcher(commandText);
        if (matcher.find()) {
            review.setReviewSlug(matcher.group(1));
        }

        // Extract rating
        matcher = ratingPattern.matcher(commandText);
        if (matcher.find()) {
            review.setRating(Integer.parseInt(matcher.group(1)));
        }

        // Extract rawRating
        matcher = rawRatingPattern.matcher(commandText);
        if (matcher.find()) {
            review.setRawRating(Double.parseDouble(matcher.group(1)));
        }

        // Extract body
        matcher = bodyPattern.matcher(commandText);
        if (matcher.find()) {
            review.setBody(matcher.group(1));
        }

        // Extract sentiment
        matcher = sentimentPattern.matcher(commandText);
        if (matcher.find()) {
            review.setSentiment(matcher.group(1));
        }

        // Extract email
        matcher = emailPattern.matcher(commandText);
        if (matcher.find()) {
            review.setEmail(matcher.group(1));
        }

        // Extract displayName
        matcher = displayNamePattern.matcher(commandText);
        if (matcher.find()) {
            review.setDisplayName(matcher.group(1));
        }

        // Extract hasBody
        matcher = hasBodyPattern.matcher(commandText);
        if (matcher.find()) {
            review.setHasBody(Integer.parseInt(matcher.group(1)) == 1);
        }

        // Extract status
        matcher = statusPattern.matcher(commandText);
        if (matcher.find()) {
            review.setStatus(matcher.group(1));
        }

        // Extract reviewSource
        matcher = reviewSourcePattern.matcher(commandText);
        if (matcher.find()) {
            review.setReviewSource(matcher.group(1));
        }

        // Extract reviewSourceId
        matcher = reviewSourceIdPattern.matcher(commandText);
        if (matcher.find()) {
            review.setReviewSourceId(matcher.group(1));
        }

        // Extract reviewDate
        matcher = reviewDatePattern.matcher(commandText);
        if (matcher.find()) {
            review.setReviewDate(matcher.group(1));
        }

        // Extract destination
        matcher = destinationPattern.matcher(commandText);
        if (matcher.find()) {
            review.setDestination(matcher.group(1));
        }

        // Extract maturity
        matcher = maturityPattern.matcher(commandText);
        if (matcher.find()) {
            review.setMaturity(matcher.group(1));
        }

        // Extract publishDate
        matcher = publishDatePattern.matcher(commandText);
        if (matcher.find()) {
            review.setPublishDate(matcher.group(1));
        }

        // Extract distributionDate
        matcher = distributionDatePattern.matcher(commandText);
        if (matcher.find()) {
            review.setDistributionDate(matcher.group(1));
        }

        // Extract lastModified
        matcher = lastModifiedPattern.matcher(commandText);
        if (matcher.find()) {
            review.setLastModified(matcher.group(1));
        }

        // Extract created
        matcher = createdPattern.matcher(commandText);
        if (matcher.find()) {
            review.setCreated(matcher.group(1));
        }


        return review; // Return the populated Review object
    }
}
