package com.example.mynanodegreeapps.ontheway.widget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.example.mynanodegreeapps.ontheway.Constants;
import com.example.mynanodegreeapps.ontheway.MainActivity;
import com.example.mynanodegreeapps.ontheway.MapsActivity;
import com.example.mynanodegreeapps.ontheway.R;
import com.example.mynanodegreeapps.ontheway.SearchHistoryWidgetRemoteService;

import java.util.Map;

/**
 * Created by akhatri on 16/03/16.
 */
public class SearchHistoryCollectionWidget extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_collection_searchhistory);


            // Create an Intent to launch MainActivity
            Intent intent = new Intent(context, MapsActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.widgetSeachHistory, pendingIntent);
            setRemoteAdapter(context, views);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        super.onReceive(context, intent);
        if (Constants.SEARCH_HISTORY_DATA_UPDATED.equals(intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                    new ComponentName(context, getClass()));
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widgetSeachHistory);
        }
    }


    /**
     * Sets the remote adapter used to fill in the list items
     *
     * @param views RemoteViews to set the RemoteAdapter
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.widgetSearchHistoryList,
                new Intent(context, SearchHistoryWidgetRemoteService.class));
    }
}
