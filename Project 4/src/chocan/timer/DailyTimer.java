/**
 * This class represents a daily timer that executes a service at a specified time every day.
 * It uses a TimerTask to schedule the execution of the service at the specified time and repeats daily.
 * The execution time is calculated based on the current time and the specified hours, minutes, and seconds.
 * The service execution logic should be added in the executeService() method.
 */
package chocan.timer;

import chocan.controller.AbstractController;


import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DailyTimer extends Thread{

    private final int hours;
    private final int minutes;
    private final int seconds;

    AbstractController timedController;


    /*
     * Constructor for DailyTimer.
     * 
     * @param hours The hour of the day to execute the service.
     * @param minutes The minute of the hour to execute the service.
     * @param seconds The second of the minute to execute the service.
     */
    public DailyTimer(int hours, int minutes, int seconds, AbstractController controller) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.timedController = controller;
    }


    /*
     * Utility method to get the current date and time.
     * 
     * @return LocalDateTime The current date and time.
     */
    public static LocalDateTime getCurrentDateAndTime() {
        return LocalDateTime.now();
    }


    /*
     * Execute the service at the specified time every day.
     * The service execution logic should be added in the executeService() method.
     */
    public void execute() {
        Timer timer = new Timer();

        // Get the current time.
        Calendar now = Calendar.getInstance();

        // Calculate the time until the next execution.
        long delay = calculateDelay(now, hours, minutes, seconds);

        // Schedule a TimerTask to execute the service at the specified time.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                executeService();
            }
        }, delay, 24 * 60 * 60 * 1000); // Repeat daily.

        // You can cancel the timer explicitly if needed.
        // timer.cancel();
    }

    /*
     * Calculate the delay until the next execution.
     * The delay is calculated based on the current time and the specified hours, minutes, and seconds.
     */
    private long calculateDelay(Calendar now, int hours, int minutes, int seconds) {
        // Create a calendar for the next execution time.
        Calendar nextExecution = Calendar.getInstance();
        nextExecution.set(Calendar.HOUR_OF_DAY, hours);
        nextExecution.set(Calendar.MINUTE, minutes);
        nextExecution.set(Calendar.SECOND, seconds);

        // If the next execution time has already passed for today, schedule it for the next day.
        if (nextExecution.before(now)) {
            nextExecution.add(Calendar.DAY_OF_YEAR, 1);
        }

        // Calculate the delay until the next execution.
        return nextExecution.getTimeInMillis() - now.getTimeInMillis();
    }


    /*
     * Service execution logic.
     * This method is called by the TimerTask when the service is executed.
     * Add your service execution logic here.
     */
    private void executeService() {
        System.out.println("Executing service at " + hours + " hours, " + minutes + " minutes, and " + seconds + " seconds.");
    }

    public void run() {
        this.execute();
    }
}
