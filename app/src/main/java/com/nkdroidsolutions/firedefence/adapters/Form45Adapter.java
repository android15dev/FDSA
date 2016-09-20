package com.nkdroidsolutions.firedefence.adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.nkdroidsolutions.firedefence.R;
import com.nkdroidsolutions.firedefence.fragment.Form_4.Form44;
import com.nkdroidsolutions.firedefence.model.AppConstant;
import com.nkdroidsolutions.firedefence.model.Form4Model.AddDefect;
import com.nkdroidsolutions.firedefence.util.observer.AllObserver;
import com.nkdroidsolutions.firedefence.web_api.Base64ToBitmap;
import com.nkdroidsolutions.firedefence.web_api.GetBitmap;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Form45Adapter extends RecyclerView.Adapter<Form45Adapter.Form45ViewHolder> implements AllObserver {

    Form44 context;

    public Form45Adapter(Form44 context) {

        this.context = context;
    }

    @Override
    public Form45ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_lay_defect, null);
        Form45ViewHolder rcv = new Form45ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final Form45ViewHolder holder, final int position) {

        AddDefect defect = observerFormFour.getFormfour().getResponse().getReport4().getAddDefectProp().getAddDefect().get(position);

        holder.edit_defect.setText(defect.getDefects());
        holder.edit_preffereddate.setText(defect.getPrefferDate());
        holder.edit_deadline.setText(defect.getRepairDeadline());
        holder.edit_importance.setText(defect.getImportance());
       /* Bitmap bm = ExifUtils.decodeFile(itemList.get(position).defect_image_path);
        bm = ExifUtils.rotateBitmap(itemList.get(position).defect_image_path, bm);*/
        if (!TextUtils.isEmpty(defect.getDefectImage())) {
            if (defect.getDefectImage().startsWith("data")) {

                Base64ToBitmap base64ToBitmap = new Base64ToBitmap();

                base64ToBitmap.execute(defect.getDefectImage());
                base64ToBitmap.enqueue(new GetBitmap() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        holder.img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });

            } else {
                ImageLoader.getInstance().displayImage(AppConstant.IMAGE_URL + defect.getDefectImage(), holder.img);
            }
        } else {
            holder.img.setImageBitmap(null);
        }
    }

    @Override
    public int getItemCount() {
        return observerFormFour.getFormfour().getResponse().getReport4().getAddDefectProp().getAddDefect().size();
    }

    public class Form45ViewHolder extends RecyclerView.ViewHolder {

        private EditText edit_defect, edit_preffereddate, edit_deadline, edit_importance;
        private ImageView img;

        public Form45ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            edit_defect = (EditText) itemView.findViewById(R.id.edit_defect);
            edit_preffereddate = (EditText) itemView.findViewById(R.id.edit_preffered_date);
            edit_deadline = (EditText) itemView.findViewById(R.id.edit_deadline);
            edit_importance = (EditText) itemView.findViewById(R.id.edit_importance);
        }
    }
}