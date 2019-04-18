package net.app.lblpack.puch;

import android.text.TextUtils;

public class Presenter implements IPresenter{
    private IView mView;
    public Presenter(IView iView){
        mView = iView;
    }
    @Override
    public void search() {
        String inputString = mView.getInputString();
        if(TextUtils.isEmpty(inputString)){
            return;
        }
        int hashCode = inputString.hashCode();
        IUserService service = new UserService();
        String serviceResult = service.search(hashCode);
        String result = "Result"+inputString+"---" +serviceResult;
        //关闭界面loading
        mView.setResultString(result);
    }
}
