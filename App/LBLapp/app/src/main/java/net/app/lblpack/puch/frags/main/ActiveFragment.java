package net.app.lblpack.puch.frags.main;


import net.app.lblpack.common.app.Fragment;
import net.app.lblpack.common.wiget.GalleryView;
import net.app.lblpack.puch.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveFragment extends Fragment {
    @BindView(R.id.galleryview)
    GalleryView mGalley;

    public ActiveFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initData() {
        super.initData();
        mGalley.setup(getLoaderManager(), new GalleryView.SelectedChangeListener() {
            @Override
            public void onSelectedCountChanged(int count) {

            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_active;
    }

}
