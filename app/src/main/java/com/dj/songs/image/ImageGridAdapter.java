package com.dj.songs.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dj.songs.R;
import com.dj.songs.image.imageloader.Utils.MyUtils;
import com.dj.songs.image.imageloader.loader.LoaderImage;

import java.util.List;

/**
 * author : dengjiejie
 * date : 2020/7/25 2:16 PM
 * description :
 */
public class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.ImageViewHolder> {
    private LayoutInflater mInflater;
    private Drawable mDefaultBitmapDrawable;

    private List<ImageData> mImageDataList;
    LoaderImage mImageLoader;
    private int mImageWidth = 0;
    private boolean mIsGridViewIdle = true;
    private boolean mIsWifi = false;
    private boolean mCanGetBitmapFromNetWork = false;

    private boolean mUseFresco = true;


    public ImageGridAdapter(Context context, List<ImageData> list) {
        mInflater = LayoutInflater.from(context);
        mDefaultBitmapDrawable = context.getResources().getDrawable(R.drawable.image_default);
        mImageDataList = list;
        mImageLoader = LoaderImage.Companion.build(context);

        int screenWidth = MyUtils.getScreenMetrics(context).widthPixels;
        int space = (int) MyUtils.dp2px(context, 20f);
        mImageWidth = (screenWidth - space) / 3;
        mIsWifi = MyUtils.isWifi(context);
        if (mIsWifi) {
            mCanGetBitmapFromNetWork = true;
        }
    }

    public ImageData getItem(int position) {
        return mImageDataList.get(position);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewHolder(mInflater.inflate(R.layout.image_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageView imageView = holder.imageView;
        ImageData imageData = getItem(position);
        holder.text.setText(imageData.mText);

        final String tag = (String) imageView.getTag();
        final String uri = imageData.mUrl;

        if (uri.equals((tag))) {
            return;
        }
        imageView.setImageDrawable(mDefaultBitmapDrawable);
        if (mIsGridViewIdle && mCanGetBitmapFromNetWork) {
            imageView.setTag(uri);

            if (mUseFresco) {

            } else {
                mImageLoader.bindBitmap(uri, imageView, mImageWidth, mImageWidth);
            }
        }
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mImageDataList.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView text;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);

            text = (TextView) itemView.findViewById(R.id.text);
        }

    }

    public void setmIsGridViewIdle(boolean mIsGridViewIdle) {
        this.mIsGridViewIdle = mIsGridViewIdle;
    }

    public void setmCanGetBitmapFromNetWork(boolean mCanGetBitmapFromNetWork) {
        this.mCanGetBitmapFromNetWork = mCanGetBitmapFromNetWork;
    }

    public void setmUseFresco(boolean mUseFresco) {
        this.mUseFresco = mUseFresco;
    }
}
