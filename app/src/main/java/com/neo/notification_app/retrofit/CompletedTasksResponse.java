package com.neo.notification_app.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class CompletedTasksResponse {

    @SerializedName("completedTasks")
    @Expose
    private String completedTasks;

    public String getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(String completedTasks) {
        this.completedTasks = completedTasks;
    }
}
