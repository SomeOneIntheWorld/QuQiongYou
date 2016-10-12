package dream.quqiongyou.bean;

import com.google.gson.Gson;

/**
 * Created by SomeOneInTheWorld on 2016/10/12.
 */
public abstract class BaseBean {
    public String toJson() {
        return toJson(this);
    }

    public static String toJson(BaseBean bean) {
        if (bean != null) {
            return new Gson().toJson(bean);
        }

        return null;
    }

    public static <T extends BaseBean> T fromJson(String jsonStr,
                                                  Class<? extends BaseBean> subClass) {
        BaseBean newObj = new Gson().fromJson(jsonStr,
                subClass);

        return (T) newObj;

    }

    public String toString() {
        return toJson();
    }
}