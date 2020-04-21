package com.neo.notification_app.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class PendingTasksResponse {

    @SerializedName("PendingTask")
    @Expose
    private String PendingTask;

    public String getPendingTask() {
        return PendingTask;
    }

    public void setPendingTask(String pendingTask) {
        PendingTask = pendingTask;
    }
}
