package baby.moneybaby.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import baby.moneybaby.R;
import baby.moneybaby.card.CardFragment;
import baby.moneybaby.loan.LoanFragment;
import baby.moneybaby.mine.MineFragment;

/**
 * 首页
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private TextView[] mTabs;
    private int index;
    private int currentIndex = 0;
    private Fragment[] fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        mTabs = new TextView[5];
        mTabs[0] = (TextView) findViewById(R.id.activity_main_loan);
        mTabs[1] = (TextView) findViewById(R.id.activity_main_card);
        mTabs[2] = (TextView) findViewById(R.id.activity_main_mine);
        for (int i = 0; i < 3; i++) {
            mTabs[i].setOnClickListener(this);
        }
        // 把第一个tab设为选中状态
        mTabs[0].setSelected(true);
        LoanFragment loanFragment = new LoanFragment();
        CardFragment cardFragment = new CardFragment();
        MineFragment mineFragment = new MineFragment();
        fragments = new Fragment[]{loanFragment, cardFragment, mineFragment};
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_home, loanFragment)
                .show(loanFragment).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_loan:
                index = 0;
                break;
            case R.id.activity_main_card:
                index = 1;
                break;
            case R.id.activity_main_mine:
                index = 2;
                break;
        }
        cutTab();
    }

    private void cutTab() {
        mTabs[currentIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.hide(fragments[currentIndex]);
        if (!fragments[index].isAdded()) {
            trx.add(R.id.activity_main_home, fragments[index]);
        }
        trx.show(fragments[index]).commit();
        currentIndex = index;
    }
}
