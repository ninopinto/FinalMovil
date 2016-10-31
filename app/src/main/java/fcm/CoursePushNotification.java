package fcm;

import java.util.UUID;

/**
 * Created by estemanp on 24/10/16.
 */

public class CoursePushNotification {
    private String id;
    private String mTitle;
    private String mDescription;
    private float mDiscount;

    public CoursePushNotification() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDiscount(float discountValue) {
        mDiscount = discountValue;
    }

    public float getDiscount() {
        return mDiscount;
    }
}
