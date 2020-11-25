package com.example.objectdetector;

public class ViewDialog {




    public void showDialog(Activity activity, String TAG, String message, Uri uri){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);




        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(),uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView label = (TextView) dialog.findViewById(R.id.label);
        label.setText(TAG);

        TextView accuracy = (TextView) dialog.findViewById(R.id.accuracy);
        accuracy.setText(message);


        ImageView imagepredict = (ImageView) dialog.findViewById(R.id.imagepredict);
        imagepredict.setImageBitmap(bitmap);

        TextView dialogButton = (TextView) dialog.findViewById(R.id.gotIt);
        dialogButton.setOnClickListener(v-> {
            dialog.dismiss();
        });


        dialog.show();

    }
}
