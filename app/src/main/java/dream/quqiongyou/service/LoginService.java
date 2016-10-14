package dream.quqiongyou.service;

import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.bean.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by SomeOneInTheWorld on 2016/10/11.
 */
public interface LoginService {
    @POST("quqiongyou/dengluapi.php")
    Observable<Response<QuUser>> getUserMessage(@Body QuUser user);
}
