package com.basnal.tagit;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.basnal.tagit.models.HotspotQueryModel;
import com.basnal.tagit.tagitapi.TagItLocationManager;
import com.basnal.tagit.tagitapi.TagItLocationResult;
import com.basnal.tagit.tagitapi.TagItRequests;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateTagActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher
{
    private TextView hotspotComments;
    private EditText hotspotEditComments;
    private EditText hotspotName;
    private Button btnSendHotspot;

    private AlertDialog processSpinner = null;

    private Dictionary<String, Object> viewHolder = new Hashtable<String, Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tag);

        initializeComponents();
    }

    private void initializeComponents()
    {
        hotspotComments = (TextView) findViewById(R.id.hotspot_comments);
        hotspotEditComments = (EditText) findViewById(R.id.hotspot_edit_comments);
        hotspotName = (EditText) findViewById(R.id.hotspot_edit_name);
        btnSendHotspot = (Button) findViewById(R.id.btn_send_hotspot);

        hotspotEditComments.addTextChangedListener(this);
        btnSendHotspot.setOnClickListener(this);
    }

    private void createProcessSpinner()
    {
        AlertDialog.Builder processSpinnerBuilder = new AlertDialog.Builder(CreateTagActivity.this);
        View spinnerView = getLayoutInflater().inflate(R.layout.dialog_box, null);
        processSpinnerBuilder.setView(spinnerView);

        viewHolder.put("processSpinner", spinnerView);

        processSpinner = processSpinnerBuilder.create();
    }

    private void showDialog(String message, Boolean showSpinner)
    {
        if(processSpinner == null)
            createProcessSpinner();

        View spinnerView = (View) viewHolder.get("processSpinner");

        TextView processes = (TextView) spinnerView.findViewById(R.id.processtext);

        ProgressBar spinner = (ProgressBar) spinnerView.findViewById(R.id.progressspinner);

        if(showSpinner)
            spinner.setVisibility(View.VISIBLE);
        else
            spinner.setVisibility(View.GONE);

        processes.setText(message);

        processSpinner.show();
    }

    private void dismissProcessDialog()
    {
        if(processSpinner == null) return;

        processSpinner.dismiss();
        processSpinner = null;

        Intent intent = new Intent(this, TagRequestsActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onClick(View v)
    {

        if(hotspotName.getText().toString().equals(""))
        {
            showDialog("Add a name for the place", false);
            return;
        }

        showDialog("Sharing...", true);

        TagItLocationManager.getInstance((LocationManager) getSystemService(Context.LOCATION_SERVICE)).getLocation(tagItLocationResult);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {


        //hotspotComments.setText(title + hotspotEditComments.getText());
    }

    @Override
    public void afterTextChanged(Editable s)
    {

    }


    private TagItLocationResult tagItLocationResult = new TagItLocationResult()
    {
        @Override
        public void gotLocation(Location location)
        {
            HotspotQueryModel hotspotQueryModel = new HotspotQueryModel();
            hotspotQueryModel.setComments(hotspotEditComments.getText().toString());
            hotspotQueryModel.setName(hotspotName.getText().toString());
            hotspotQueryModel.setLocation(location);
            hotspotQueryModel.setImages("");
            hotspotQueryModel.setPortal("");

            Pattern pattern = Pattern.compile("(#[\\S]*)");
            Matcher matcher = pattern.matcher(hotspotEditComments.getText().toString());

            String tags = "";
            while(matcher.find())
            {
                tags += matcher.group() + " ";
            }
            hotspotQueryModel.setTags(tags);

            TagItRequests request = new TagItRequests(hotspotQueryModel);
            request.makeRequest();
            dismissProcessDialog();
        }
    };


}
