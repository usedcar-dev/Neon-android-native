package com.gaadi.neon.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaadi.neon.activity.ImageReviewActivity;
import com.gaadi.neon.model.ImageTagModel;
import com.gaadi.neon.util.FileInfo;
import com.gaadi.neon.util.SingletonClass;
import com.scanlibrary.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alokmishra on 27/11/15.
 */

public class ImageTagsAdapter extends ArrayAdapter<ImageTagModel> {

    private final LayoutInflater mInflater;
    private Context context;
    private ImageTagsModelsHolder mHolder;
    private FileInfo fileOnViewPager;

    public ImageTagsAdapter(Context context, FileInfo _fileOnViewPager) {
        super(context, android.R.layout.simple_spinner_dropdown_item);
        this.context = context;
        fileOnViewPager = _fileOnViewPager;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setDropDownViewResource(R.layout.color_layout_row);
    }

    @Override
    public int getCount() {

        return SingletonClass.getSingleonInstance().getGenericParam().getImageTagsModel().size();
    }

    @Override
    public ImageTagModel getItem(int position) {

        return SingletonClass.getSingleonInstance().getGenericParam().getImageTagsModel().get(position);
    }

    @Override
    public long getItemId(int position) {

        return SingletonClass.getSingleonInstance().getGenericParam().getImageTagsModel().indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageTagModel tagsModel = SingletonClass.getSingleonInstance().getGenericParam().getImageTagsModel().get(position);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.color_layout_row, parent, false);

            mHolder = new ImageTagsModelsHolder();
            mHolder.text = (TextView) convertView.findViewById(R.id.colorValue);
            mHolder.colorLayoutRoot = (View) convertView.findViewById(R.id.colorLayoutRoot);
            mHolder.iv_color = (ImageView) convertView.findViewById(R.id.color);
            convertView.setTag(mHolder);

        } else {
            mHolder = (ImageTagsModelsHolder) convertView.getTag();

        }
        if(fileOnViewPager != null && fileOnViewPager.getFileTag() != null &&
                fileOnViewPager.getFileTag().getTagId().equals(
                        tagsModel.getTagId())){
            ContextCompat.getColor(context, android.R.color.darker_gray);
        }else{
            ContextCompat.getColor(context, android.R.color.transparent);
        }

        if(tagsModel.isMandatory()){//Constants.MANDATORY_TAGS is 1
            mHolder.text.setText("");
            mHolder.text.append("*"+tagsModel.getTagName());
            Spannable sText = (Spannable) mHolder.text.getText();
            sText.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, 0);
            mHolder.text.setText(sText,TextView.BufferType.SPANNABLE);

        }else{
            mHolder.text.setText("  "+tagsModel.getTagName());
        }

        mHolder.iv_color.setVisibility(View.GONE);

        return convertView;

    }

    private class ImageTagsModelsHolder {
        TextView text;
        ImageView iv_color;
        View colorLayoutRoot;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.color_layout_row, parent, false);

            mHolder = new ImageTagsModelsHolder();
            mHolder.text = (TextView) convertView.findViewById(R.id.colorValue);
            mHolder.colorLayoutRoot = (View) convertView.findViewById(R.id.colorLayoutRoot);
            mHolder.iv_color = (ImageView) convertView.findViewById(R.id.color);

            convertView.setTag(mHolder);

        } else {
            mHolder = (ImageTagsModelsHolder) convertView.getTag();

        }
        mHolder.text.setText(SingletonClass.getSingleonInstance().getGenericParam().getImageTagsModel().get(position).getTagName());


        return convertView;

    }


}
