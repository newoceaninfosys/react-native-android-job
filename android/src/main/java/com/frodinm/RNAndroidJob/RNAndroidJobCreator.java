package com.frodinm.rnandroidjob;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class RNAndroidJobCreator implements JobCreator {
    public static HashMap<String, Task> jobMap = new HashMap<>();

    public static void addTask(String taskId) {
        SQLHelper sqlh = new SQLHelper();
        Date date = new Date();
        if(taskId != null){
            switch (taskId){
                case "up":
                    sqlh.insertTask("up", (int)date.getTime());
                    jobMap.put(taskId, new Task(){
                        @Override
                        public Job taskToReturn() {
                            return new UpJob();
                        }
                    } );
                    String tasks = sqlh.getAllTasks();
                    Log.d(RNAndroidJob.TAG,"SQLite db : " + tasks);
                    Log.d(RNAndroidJob.TAG, "Job with taskID " + taskId + " has been registered");
                    break;
                case "down":
                    jobMap.put(taskId, new Task(){
                        @Override
                        public Job taskToReturn() {
                            return new DownJob();
                        }
                    } );
                    Log.d(RNAndroidJob.TAG, "Job with taskID " + taskId + " has been registered");
                    break;
                case "left":
                    break;
                case "right":
                    break;
            }
        }else{
            Log.d(RNAndroidJob.TAG, "TaskId did not match any of the defined jobs");
        }
    }

    @Override
    public Job create(@NonNull String taskId) {

        return jobMap.get(taskId).taskToReturn();
    }
}