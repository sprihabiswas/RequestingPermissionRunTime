package skillbooting.requestingpermissionruntime;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissions();
    }

    /**To help find situations where the user might need an explanation, Android provides a utiltity method,
     * shouldShowRequestPermissionRationale(). This method returns true if the app has requested this permission previously
     * and the user denied the request.
     * If the user turned down the permission request in the past and chose the Don't ask again option in the permission request
     * system dialog, this method returns false.
     * The method also returns false if a device policy prohibits the app from having that permission.
     */
    public void permissions()
    {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != (PackageManager.PERMISSION_GRANTED)) {

            System.out.println("Here = "+1);
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {

                System.out.println("Here = "+2);
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                System.out.println("Here = "+3);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_CAMERA is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        else
            Toast.makeText(this, "Permission granted by user",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted by user",Toast.LENGTH_LONG).show();

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission denied by user",Toast.LENGTH_LONG).show();
                }
                return;
            }
            default: System.out.println("default");

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
