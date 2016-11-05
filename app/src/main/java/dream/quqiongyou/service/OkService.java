package dream.quqiongyou.service;

import java.util.List;

import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.bean.Response;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by SomeOneInTheWorld on 2016/10/11.
 */

public interface OkService{
    interface LoginService {
        @POST("quqiongyou/dengluapi.php")
        Observable<Response<QuUser>> getUserMessage(@Body RequestBody body);
    }
    interface IdentifyingCodeGetService {
        @POST("quqiongyou/zhuce1/sendMsg.php")
        Observable<Response<String>> getIdentifyingCode(@Body RequestBody body);
    }
    interface RegisterService{
        @POST("quqiongyou/zhuce1/phonezhuce.php")
        Observable<Response<String>> getRegisterResult(@Body RequestBody body);
    }
    interface MainDataService{
        @GET("quqiongyou/activity/activitybrow.php")
        Observable<Response<List<HomeItemBean>>> getMainData(@Query("page")int page);
    }
}

