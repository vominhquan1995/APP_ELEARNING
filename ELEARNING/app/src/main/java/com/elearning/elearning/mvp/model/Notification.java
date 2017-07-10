package com.elearning.elearning.mvp.model;

import com.elearning.elearning.network.APIConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.elearning.elearning.prefs.DatetimeFomat.DATE_FORMAT_YYYYMMDD;

/**
 * Created by MinhQuan on 11/07/2017.
 */

public class Notification {
    private int id;
    private String title;
    private String body;
    private String urlImage;
    private Date dateStart;
    private Date dateEnd;

    public Notification() {

    }

    public Notification(int id, String title, String body, String urlImage, Date editTime, Date addTime) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.urlImage = urlImage;
        this.dateStart = editTime;
        this.dateEnd = addTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public static List<Notification> getListNotification(JSONArray jsonArray) {
        List<Notification> notificationList = new ArrayList();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = (JSONObject) jsonArray.get(i);
                Notification notification = new Notification();
                //TODO remove it
                notification.setId(data.getInt(APIConstant.NOTIFICATION_ID));
                notification.setTitle(data.getString(APIConstant.NOTIFICATION_TITLE));
                notification.setBody(data.getString(APIConstant.NOTIFICATION_BODY));
                notification.setUrlImage(data.getString(APIConstant.NOTIFICATION_URLIMAGE));
                notification.setDateEnd(DATE_FORMAT_YYYYMMDD.parse(data.getString(APIConstant.NOTIFICATION_ADDDATE).substring(0, 10)));
                notification.setDateStart(DATE_FORMAT_YYYYMMDD.parse(data.getString(APIConstant.NOTIFICATION_EDITDATE).substring(0, 10)));
                notificationList.add(notification);
            }
            return notificationList;
        } catch (JSONException e) {
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
