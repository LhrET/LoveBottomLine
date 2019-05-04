package net.app.lblpack.puch.frags.panel;

import android.view.View;

import net.app.lblpack.common.widget.recycler.RecyclerAdapter;
import net.app.lblpack.face.Face;
import net.app.lblpack.puch.R;

import java.util.List;

/**
 * @version 1.0.0
 */
public class FaceAdapter extends RecyclerAdapter<Face.Bean> {

    public FaceAdapter(List<Face.Bean> beans, AdapterListener<Face.Bean> listener) {
        super(beans, listener);
    }

    @Override
    protected int getItemViewType(int position, Face.Bean bean) {
        return R.layout.cell_face;
    }

    @Override
    protected ViewHolder<Face.Bean> onCreateViewHolder(View root, int viewType) {
        return new FaceHolder(root);
    }
}
