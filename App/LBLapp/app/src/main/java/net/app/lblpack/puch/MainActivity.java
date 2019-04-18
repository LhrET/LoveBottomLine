package net.app.lblpack.puch;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.app.lblpack.common.app.Activity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends Activity implements IView {
    @BindView(R.id.txt_result)
    TextView mTestText;
    @BindView(R.id.btn_submit)
    Button button;
    @BindView(R.id.edit_text)
    EditText editText;

    private IPresenter iPresenter;

    @Override
    protected void initData() {
        super.initData();
        iPresenter = new Presenter(this);
    }
    @OnClick(R.id.btn_submit)
    void submit(){
        iPresenter.search();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public String getInputString() {
        return editText.getText().toString();
    }

    @Override
    public void setResultString(String string) {
        mTestText.setText(string);
    }
}
