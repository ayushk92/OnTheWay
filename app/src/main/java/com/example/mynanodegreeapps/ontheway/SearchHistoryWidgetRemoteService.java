package com.example.mynanodegreeapps.ontheway;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.mynanodegreeapps.ontheway.provider.searchhistory.SearchhistoryColumns;

/**
 * Created by akhatri on 16/03/16.
 */
public class SearchHistoryWidgetRemoteService extends RemoteViewsService {
    public static final String LOG_TAG = SearchHistoryWidgetRemoteService.class.getSimpleName();
    private static final int _ID = 0;
    private static final int SOURCE_LOCATION = 1;
    private static final int DESTINATION_LOCATION = 2;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {


            private Cursor data = null;

            @Override
            public void onCreate() {
                Log.d(LOG_TAG, "Collection service created");
            }

            @Override
            public void onDataSetChanged() {
                if (data != null) {
                    data.close();
                }
                // This method is called by the app hosting the widget (e.g., the launcher)
                // However, our ContentProvider is not exported so it doesn't have access to the
                // data. Therefore we need to clear (and finally restore) the calling identity so
                // that calls use our process and permission
                final long identityToken = Binder.clearCallingIdentity();
                data = getContentResolver().query(SearchhistoryColumns.CONTENT_URI, null, null,
                        null,null );
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }
                RemoteViews remoteViews = new RemoteViews(getPackageName(),
                        R.layout.widget_collection_searchhistory_item);
                remoteViews.setTextViewText(R.id.sourceLocation,data.getString(SOURCE_LOCATION));
                remoteViews.setTextViewText(R.id.destinationLocation,data.getString(DESTINATION_LOCATION));

                return remoteViews;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_collection_searchhistory);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (data.moveToPosition(position))
                    return data.getInt(_ID);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
