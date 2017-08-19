package hello;

import java.util.Date;

public class TimeAppoint {
    private String timeExpression;
    private Date time;
    private String timeNorm;

    private boolean isAllDay;

    public String getTimeExpression() {
        return timeExpression;
    }

    public void setTimeExpression(String timeExpression) {
        this.timeExpression = timeExpression;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    public String getTimeNorm() {
        return timeNorm;
    }

    public void setTimeNorm(String timeNorm) {
        this.timeNorm = timeNorm;
    }
}
