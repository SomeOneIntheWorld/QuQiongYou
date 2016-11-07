package dream.quqiongyou.postdetail.model;

import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.bean.PostBean;
import dream.quqiongyou.service.OkService;
import dream.quqiongyou.utils.RxUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/10.
 */
public class PostDetailModelImpl implements PostDetailModel {
    private final static String TAG = "HOMEMODELIMPL_TEST";
    private List<PostBean> data = new ArrayList<>();
    private int maxPage = 1;

    @Override
    public void loadComments(String id,int page,CallBackByPostDetailModel callBackByPostDetailModel) {
        if(page < 1 || page > maxPage){
            callBackByPostDetailModel.loadFail("page should be legal");
            return;
        }

        OkService.PostDetailService service = RxUtils.createService(OkService.PostDetailService.class);
        service.getPostDetailData(id,page)
                .compose(RxUtils.normalSchedulers())
                .subscribe(listResponse->{
                    maxPage = Integer.parseInt(listResponse.message);
                    if(page == 1){
                        data.clear();
                    }
                    data.addAll(listResponse.data);
                    callBackByPostDetailModel.loadSuccess(data);
                }, error->{
                    callBackByPostDetailModel.loadFail(error.getMessage());
                });
    }
}
