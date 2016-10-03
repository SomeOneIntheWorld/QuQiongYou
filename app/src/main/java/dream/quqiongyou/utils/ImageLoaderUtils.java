package dream.quqiongyou.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import dream.quqiongyou.R;

/**
 * Created by SomeOneInTheWorld on 2016/5/26.
 */
public class ImageLoaderUtils {

    public static void display(Context context, final ImageView imageView, final String url,
                               int placeholder,int error){
        if(imageView == null){
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder).error(error)
                .crossFade().into(imageView);
    }

    public static void display(Context context,final ImageView imageView,final String url){
        if(imageView == null){
            throw new IllegalArgumentException("argument error");
        }

        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .into(imageView);
    }
}
