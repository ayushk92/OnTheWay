package com.example.mynanodegreeapps.ontheway;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;


import java.util.ArrayList;
import java.util.HashMap;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by akhatri on 05/03/16.
 */
public class CuisineAdapter extends CursorAdapter {

    Context mContext;
    Cursor mCursor;

    private final int ITEM_ID = 0;
    private final int ITEM_NAME = 1;

    private final int IS_CHECKED = 0;
    private HashMap<Long,Boolean> checkedStatus;

    private ArrayList<String> selectedCuisines;

    public CuisineAdapter(Context context, Cursor c) {
        super(context, c);
        this.mContext = context;
        this.mCursor = c;
        selectedCuisines = new ArrayList<String>();
        checkedStatus = new HashMap<Long,Boolean>();

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View mItem = LayoutInflater.from(context).inflate(R.layout.fragment_checkbox_resultitem, parent, false);
        ViewHolder mHolder = new ViewHolder(mItem);
        mItem.setTag(mHolder);
        return mItem;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder mHolder = (ViewHolder)view.getTag();

        mHolder.itemName.setText(cursor.getString(ITEM_NAME));

        if(!checkedStatus.containsKey(cursor.getLong(ITEM_ID))){
            checkedStatus.put(cursor.getLong(ITEM_ID),false);
        }

        mHolder.itemName.setTag(new Integer(cursor.getPosition()));

        mHolder.itemName.setChecked(checkedStatus.get(cursor.getLong(ITEM_ID)));





        //mHolder.checkBox.setChecked(false);
    }

    public ArrayList<Integer> getCheckedCuisine(){
        ArrayList<Integer> checkedCuisines = new ArrayList<Integer>();
        for (Long key : checkedStatus.keySet()){
            if(checkedStatus.get(key))
                checkedCuisines.add(Integer.parseInt(key.toString()));
        }
        return checkedCuisines;
    }

    public ArrayList<String> getSelectedCuisines(){
        return selectedCuisines;
    }

    class ViewHolder{

     @Bind(R.id.filter_item_name_textView)
        CheckedTextView itemName;

     @OnClick(R.id.filter_item_name_textView)
        void checkBoxClick(){
         if (itemName.isChecked()) {
             itemName.setChecked(false);
             if(selectedCuisines.contains(itemName.getText().toString())){
                 selectedCuisines.remove(itemName.getText().toString());
             }
         }
         else {
             itemName.setChecked(true);
             if(!selectedCuisines.contains(itemName.getText().toString())){
                 selectedCuisines.add(itemName.getText().toString());
             }
         }
         getCursor().moveToPosition((int)itemName.getTag());
         checkedStatus.put(getCursor().getLong(ITEM_ID),itemName.isChecked());

         //update list in global filters.
         //RestaurantFilters.getInstance().setCuisineIDs(getCheckedCuisine());
     }
//     @Bind(R.id.filter_item_checked_checkBox)
//        CheckBox checkBox;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
