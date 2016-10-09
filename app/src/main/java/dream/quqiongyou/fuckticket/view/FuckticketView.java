package dream.quqiongyou.fuckticket.view;

import java.util.List;

import dream.quqiongyou.bean.PostBean;

/**
 * Created by SomeOneInTheWorld on 2016/10/7.
 */
public interface FuckticketView{
    void showProgressBar();
    void hideProgressBar();
    void showPostBeanList(List<PostBean>topList,List<PostBean>normalList);
    void showErrorMessage(String message);
}
