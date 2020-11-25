package com.example.objectdetector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // The request code used in ActivityCompat.requestPermissions()
    // and returned in the Activity's onRequestPermissionsResult()
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {



            // android.Manifest.permission.READ_CONTACTS,
            // android.Manifest.permission.WRITE_CONTACTS,







            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA,
            // android.Manifest.permission.INTERNET

    };



  //  private FirebaseAuth mAuth;
   // private GoogleSignInClient mGoogleSignInClient;
    private Button signIn;
    private int RC_SIGN_IN = 1;
    private String TAG = "nothing...";
    public static final int REQUEST_PERMISSION = 300;

   // GoogleSignInAccount acct;
   // FirebaseUser user;

   // https://www.flaticon.com/free-icon/face-detection_1177931
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


        // acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());



        signIn = (Button) findViewById(R.id.sign_in_button);

        // Initialize Firebase Auth
        // mAuth = FirebaseAuth.getInstance();


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure Google Sign In
        // GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();


        // Build a GoogleSignInClient with the options specified by gso.
        //  mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // if(mAuth.getCurrentUser()!=null){
        //FirebaseUser user = mAuth.getCurrentUser();
        //    updateUI(mAuth.getCurrentUser());
        //}



        //lambdas for button clicks.
        signIn.setOnClickListener(v->{


            if(hasPermissions(this, PERMISSIONS)){
                signIn();
            }
            else{
                Toast.makeText(this, "Allow permission", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
            }




        });


    }



    private void signIn() {
        //Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //startActivityForResult(signInIntent, RC_SIGN_IN);
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivity(intent);
        finish();
    }

/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }



    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "you are not able to log in to Google", Toast.LENGTH_LONG).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {


        acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


            Toast.makeText(this, "logged in as "+personName, Toast.LENGTH_LONG).show();

            String pe = acct.getFamilyName()+acct.getGivenName();

            Intent intent = new Intent(this, TakeImage.class);

            intent.putExtra("KEY",pe);

            startActivity(intent);
            finish();
        }
    }




 */


    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


}