package joey.com.leetroid.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import joey.com.leetroid.R;

public class BaseContainerFragment extends Fragment {

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        System.out.println("replaceFragment with " + fragment.toString());
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.container_framelayout, fragment); // Replace操作就是把其他相同container_framelayout id的那些fragment全部remove掉，然后再加上自己的。
        transaction.commit(); // Commit操作其实就是构造一个Op，然后把这个Op放到链表里面去
        getChildFragmentManager().executePendingTransactions(); // executePendingTransaction其实就是将一些列的Runnable执行
    }

    public boolean popFragment() {
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            getChildFragmentManager().popBackStack();
            return true;
        }
        return false;
    }

}
