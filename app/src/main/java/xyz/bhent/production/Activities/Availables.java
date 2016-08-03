package xyz.bhent.production.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Map;

import xyz.bhent.production.Connectivity.DatabaseHelper;
import xyz.bhent.production.Connectivity.InfosRetrieving;
import static xyz.bhent.production.Methods.Data.*;
import xyz.bhent.production.Model.ItemModel;
import xyz.bhent.production.R;
import xyz.bhent.production.adapters.AvailableAdapter;


public class Availables extends AppCompatActivity {
    private ListView availsListView;
    private AvailableAdapter availableAdapter;
    private ArrayList<ItemModel> itemModels;
    private boolean state;
    private ItemModel itemSelected;
    private DatabaseHelper databaseHelper;
    private InfosRetrieving retrieving;
    private static int  REQUEST_CODE = 0001;
    private ItemModel model;
    private LinearLayout notification;
    private Animation animation;
    private TextView comTextView;
    private String confirmation = "";
    private int counter = 0;
    private Bundle bundle;


    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availables);
        availsListView = (ListView)findViewById(R.id.available);
        databaseHelper = new DatabaseHelper(this); //getting an instant of the database helper
        itemModels = new ArrayList<>();
        model = new ItemModel();
        notification = (LinearLayout)findViewById(R.id.notify);
        comTextView = (TextView)findViewById(R.id.confirmed);
        notification.setVisibility(View.GONE);


        bundle  = getIntent().getExtras();

        retrieving = new InfosRetrieving(this); //instant of where we post, retrieve information
        // using volley library



        setListData(bundle.getString("category"));


        Resources resources = getResources();

        availableAdapter = new AvailableAdapter(Availables.this, itemModels, resources);

        availsListView.setAdapter(availableAdapter);

        overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_right_to_left);


        if(getSupportActionBar() != null){

                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setDisplayUseLogoEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(bundle.getString("category"));

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.slide_right_to_left, android.R.anim.fade_out);
                finish();
                break;
            case R.id.active:
               Map<String, String> map = databaseHelper.getAllItems();
                //check if there is any item from the database
                if(map.isEmpty()){
                    //Toast.makeText(Availables.this, "Oops! Cart Is Empty", Toast.LENGTH_LONG).show();
                    notification.setVisibility(View.VISIBLE);
                    comTextView.setText("Empty Cart!");
                    notification.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notification.setVisibility(View.GONE);

                        }
                    }, 2000);
                }else {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        Toast.makeText(Availables.this, "The Items is " + entry.getKey() + " / the price is " + entry.getValue(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.delete:
                //here i will handle the reservation process and resetting the database
                //need a means to let the user know what to do, pop dialog, alert-dialog
                Map<String, String> check = databaseHelper.getAllItems();
                if(check.isEmpty()){
                    notification.setVisibility(View.VISIBLE);
                    comTextView.setText("Empty Cart");
                    notification.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notification.setVisibility(View.GONE);
                        }
                    }, 2000);
                }else {
                    final AlertDialog.Builder confirmReservations = new AlertDialog.Builder(Availables.this, R.style.BWAlertDialogTheme);

                    //setting the title
                    confirmReservations.setTitle("Confirm Reservation...");

                    //setting the message
                    confirmReservations.setMessage("Do you want to make reservation?");

                    //setting the icon
                    confirmReservations.setIcon(R.mipmap.ic_launcher);

                    //setting the positive response "Yes"
                    confirmReservations.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //make reservation call here *having some probs here need to come back to it
                            Map<String, String> map = databaseHelper.getAllItems();
                            for (Map.Entry<String, String> entry : map.entrySet()) {
                                if (counter != databaseHelper.itemsCount()) {
                                    model = new ItemModel();
                                    model.setTitle(entry.getKey());
                                    model.setPrice(entry.getValue());

                                } else if(counter == databaseHelper.itemsCount()){
                                    break;
                                }

                            }
                            retrieving.POST_DATA(model);

                        }
                    });

                    //setting the negative response "No"
                    confirmReservations.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    //show the alert to the user
                    AlertDialog dialog = confirmReservations.create();
                    dialog.show();

                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    //method that populate the list adapter
    public void setListData(String item){
            switch (item){
                case "Boissons Chaudes":
                    Map<String, String> title_price = BChaudes();
                    for(Map.Entry<String, String> entyValue : title_price.entrySet()){
                        model = new ItemModel();
                        model.setTitle(entyValue.getKey());
                        model.setPrice(entyValue.getValue()+" Fcfa");
                        itemModels.add(model);
                    }
                    break;
                case "Boissons Froides":
                    Map<String, String> bFroides = BFroides();
                    for(Map.Entry<String, String> entyValue : bFroides.entrySet()){
                        model = new ItemModel();
                        model.setTitle(entyValue.getKey());
                        model.setPrice(entyValue.getValue()+" Fcfa");
                        itemModels.add(model);
                    }
                    break;
                case "Boissons Gazeuzes (sans alcool)":
                    Map<String, String> BGasanalc  = BGsansAlcol();
                    for(Map.Entry<String, String> entyValue : BGasanalc.entrySet()){
                        model = new ItemModel();
                        model.setTitle(entyValue.getKey());
                        model.setPrice(entyValue.getValue()+" Fcfa");
                        itemModels.add(model);
                    }
                    break;
                case "Boissons Energétiques":
                    Map<String, String> BEnger  = BEnerge();
                    for(Map.Entry<String, String> entyValue : BEnger.entrySet()){
                        model = new ItemModel();
                        model.setTitle(entyValue.getKey());
                        model.setPrice(entyValue.getValue()+" Fcfa");
                        itemModels.add(model);
                    }
                    break;
                case "Boissons Gazeuzes (avec alcool)":
                    Map<String, String> BEngerAlc  = BEngeAlc();
                    for(Map.Entry<String, String> entyValue : BEngerAlc.entrySet()){
                        model = new ItemModel();
                        model.setTitle(entyValue.getKey());
                        model.setPrice(entyValue.getValue()+" Fcfa");
                        itemModels.add(model);
                    }
                    break;
                case "Bieres":
                    Map<String, String> Bies  = Bieres();
                    for(Map.Entry<String, String> entyValue : Bies.entrySet()){
                        model = new ItemModel();
                        model.setTitle(entyValue.getKey());
                        model.setPrice(entyValue.getValue()+" Fcfa");
                        itemModels.add(model);
                    }
                    break;
                case "Americano":
                case "Caipirinha":
                case "Margarita":
                case "Pina Colada":
                case "Tequila Sunrise":
                case "Mojito":
                case "B52":
                case "Cosmopolitan":
                case "Daiquiri":
                case "Manhattan":
                case "Martini Cocktail":
                    model = new ItemModel();
                    model.setTitle(bundle.getString("inclusive"));
                    model.setPrice("5,000 Fcfa");
                    itemModels.add(model);
                    break;
                case "Negroni":
                case "Long Island Ice-Tea":
                case "Mexican Ice-Tea":
                    model = new ItemModel();
                    model.setTitle(bundle.getString("inclusive"));
                    model.setPrice("7,500 Fcfa");
                    itemModels.add(model);
                    break;
                case "Black&White":
                case "Florida":
                case "Virgin Mojito":
                    model = new ItemModel();
                    model.setTitle(bundle.getString("inclusive"));
                    model.setPrice("4,000 Fcfa");
                    itemModels.add(model);
                    break;
                case "Virgin Etekjito":
                    model = new ItemModel();
                    model.setTitle(bundle.getString("inclusive"));
                    model.setPrice("4,000 Fcfa");
                    itemModels.add(model);
                    break;
                case "Blueteki":
                case "Etekjito":
                case "Eteki Samba":
                    model = new ItemModel();
                    model.setTitle(bundle.getString("inclusive"));
                    model.setPrice("5,000 Fcfa");
                    itemModels.add(model);
                    break;
                case "Eteki Boom Boom (4 shots)":
                    model = new ItemModel();
                    model.setTitle(bundle.getString("inclusive"));
                    model.setPrice("8,000 Fcfa");
                    itemModels.add(model);
                    break;
                case  "Whisky":
                    break;
                case  "Cocktails Eteki":
                    break;
                case "Vin Rouge":

                    break;
                case  "Vin Blanc":
                    break;
                case "Rose":
                    Map<String, String> rose  = RoseSub();
                    for(Map.Entry<String, String> entyValue : rose.entrySet()){
                        model = new ItemModel();
                        model.setTitle(entyValue.getKey());
                        model.setPrice(entyValue.getValue()+" Fcfa");
                        itemModels.add(model);
                    }
                    break;
                case "Vin Mousseux":
                    Map<String, String> VinMo  = VinMous();
                    for(Map.Entry<String, String> entyValue : VinMo.entrySet()){
                        model = new ItemModel();
                        model.setTitle(entyValue.getKey());
                        model.setPrice(entyValue.getValue()+" Fcfa");
                        itemModels.add(model);
                    }
                    break;
                case "Champagnes":
                    Map<String, String> champs  = Champs();
                    for(Map.Entry<String, String> entyValue : champs.entrySet()){
                        model = new ItemModel();
                        model.setTitle(entyValue.getKey());
                        model.setPrice(entyValue.getValue()+" Fcfa");
                        itemModels.add(model);
                    }
                    break;
                default:
                    model = new ItemModel();
                    model.setTitle("Empty");
                    itemModels.add(model);
        }
    }

    //method call upon clicking a specific item on the list
    public void onItemClick(int mPosition){
        itemSelected = itemModels.get(mPosition);//get the reference of the selected item on the list

        if(bundle.getString("category").equalsIgnoreCase("Boissons Chaudes")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BWAlertDialogTheme);
            builder.setTitle("Add Item");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    state = databaseHelper.insertItems(itemSelected.getTitle(), "", itemSelected.getPrice());
                    if (state == true) {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText(itemSelected.getTitle() + " Added!");
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    } else {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText("Error!!!");
                        comTextView.setTextColor(Color.RED);
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }else if(bundle.getString("category").equalsIgnoreCase("Boissons Froides")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BWAlertDialogTheme);
            builder.setTitle("Add Item");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    state = databaseHelper.insertItems(itemSelected.getTitle(), "", itemSelected.getPrice());
                    if (state == true) {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText(itemSelected.getTitle() + " Added!");
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    } else {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText("Error!!!");
                        comTextView.setTextColor(Color.RED);
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }else if(bundle.getString("category").equalsIgnoreCase("Boissons Gazeuzes (sans alcool)")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BWAlertDialogTheme);
            builder.setTitle("Add Item");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    state = databaseHelper.insertItems(itemSelected.getTitle(), "", itemSelected.getPrice());
                    if (state == true) {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText(itemSelected.getTitle() + " Added!");
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    } else {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText("Error!!!");
                        comTextView.setTextColor(Color.RED);
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }else if(bundle.getString("category").equalsIgnoreCase("Boissons Energétiques")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BWAlertDialogTheme);
            builder.setTitle("Add Item");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    state = databaseHelper.insertItems(itemSelected.getTitle(), "", itemSelected.getPrice());
                    if (state == true) {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText(itemSelected.getTitle() + " Added!");
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    } else {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText("Error!!!");
                        comTextView.setTextColor(Color.RED);
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }else if(bundle.getString("category").equalsIgnoreCase("Boissons Gazeuzes (avec alcool)")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BWAlertDialogTheme);
            builder.setTitle("Add Item");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    state = databaseHelper.insertItems(itemSelected.getTitle(), "", itemSelected.getPrice());
                    if (state == true) {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText(itemSelected.getTitle() + " Added!");
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    } else {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText("Error!!!");
                        comTextView.setTextColor(Color.RED);
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }else if(bundle.getString("category").equalsIgnoreCase("Bieres")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BWAlertDialogTheme);
            builder.setTitle("Add Item");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    state = databaseHelper.insertItems(itemSelected.getTitle(), "", itemSelected.getPrice());
                    if (state == true) {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText(itemSelected.getTitle() + " Added!");
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    } else {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText("Error!!!");
                        comTextView.setTextColor(Color.RED);
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }else if(bundle.getString("category").equalsIgnoreCase("Rose")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BWAlertDialogTheme);
            builder.setTitle("Add Item");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    state = databaseHelper.insertItems(itemSelected.getTitle(), "", itemSelected.getPrice());
                    if (state == true) {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText(itemSelected.getTitle() + " Added!");
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    } else {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText("Error!!!");
                        comTextView.setTextColor(Color.RED);
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }else if(bundle.getString("category").equalsIgnoreCase("Vin Mousseux")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BWAlertDialogTheme);
            builder.setTitle("Add Item");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    state = databaseHelper.insertItems(itemSelected.getTitle(), "", itemSelected.getPrice());
                    if (state == true) {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText(itemSelected.getTitle() + " Added!");
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    } else {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText("Error!!!");
                        comTextView.setTextColor(Color.RED);
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }else if(bundle.getString("category").equalsIgnoreCase("Champagnes")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BWAlertDialogTheme);
            builder.setTitle("Add Item");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    state = databaseHelper.insertItems(itemSelected.getTitle(), "", itemSelected.getPrice());
                    if (state == true) {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText(itemSelected.getTitle() + " Added!");
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    } else {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText("Error!!!");
                        comTextView.setTextColor(Color.RED);
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }else if(bundle.getString("category").equalsIgnoreCase("Americano") ||
                bundle.getString("category").equalsIgnoreCase("Caipirinha") ||
                bundle.getString("category").equalsIgnoreCase("Margarita") ||
                bundle.getString("category").equalsIgnoreCase("Pina Colada") ||
                bundle.getString("category").equalsIgnoreCase("Tequila Sunrise") ||
                bundle.getString("category").equalsIgnoreCase("Mojito") ||
                bundle.getString("category").equalsIgnoreCase("B52") ||
                bundle.getString("category").equalsIgnoreCase("Cosmopolitan") ||
                bundle.getString("category").equalsIgnoreCase("Daiquiri") ||
                bundle.getString("category").equalsIgnoreCase("Manhattan") ||
                bundle.getString("category").equalsIgnoreCase("Martini Cocktail") ||
                bundle.getString("category").equalsIgnoreCase("Negroni") ||
                bundle.getString("category").equalsIgnoreCase("Long Island Ice-Tea") ||
                bundle.getString("category").equalsIgnoreCase("Mexican Ice-Tea")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BWAlertDialogTheme);
            builder.setTitle("Add Item");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    state = databaseHelper.insertItems(bundle.getString("category"), "", itemSelected.getPrice());
                    if (state == true) {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText(itemSelected.getTitle() + " Added!");
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    } else {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText("Error!!!");
                        comTextView.setTextColor(Color.RED);
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }else if(bundle.getString("category").equalsIgnoreCase("Black&White") ||
                bundle.getString("category").equalsIgnoreCase("Florida") ||
                bundle.getString("category").equalsIgnoreCase("Virgin Mojito")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BWAlertDialogTheme);
            builder.setTitle("Add Item");
            builder.setMessage("Are you sure?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    state = databaseHelper.insertItems(bundle.getString("category"), "", itemSelected.getPrice());
                    if (state == true) {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText(itemSelected.getTitle() + " Added!");
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    } else {
                        notification.setVisibility(View.VISIBLE);
                        comTextView.setText("Error!!!");
                        comTextView.setTextColor(Color.RED);
                        notification.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                notification.setVisibility(View.GONE);

                            }
                        }, 2000);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Availables.this, itemSelected.getTitle(), Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }
        else {
            Intent intent = new Intent(Availables.this, Selections.class);
            Bundle bundle = new Bundle();
            bundle.putString("selectedItem", itemSelected.getTitle());
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_CODE);
            overridePendingTransition(R.anim.activity_open, R.anim.slide_left_to_right);
        }

   }

    //getting the return result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            //first checking if the request code  is same as what was sent
            if (requestCode == REQUEST_CODE) {
                 confirmation = data.getStringExtra("add");
                if(confirmation.isEmpty()){

                }else{
                    notification.setVisibility(View.VISIBLE);
                    comTextView.setText(confirmation);
                    notification.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //notification.setVisibility(View.GONE);
                            animation = AnimationUtils.loadAnimation(Availables.this, android.R.anim.fade_in);
                            animation.setDuration(500);
                            notification.setAnimation(animation);
                            notification.setVisibility(View.GONE);
                        }
                    }, 5000);

                Toast.makeText(Availables.this, confirmation, Toast.LENGTH_LONG).show();
                    //notification.setVisibility(View.GONE);
                }
            } else {

            }
        }catch (Exception e){}
    }

    //this method handles the way the reservation is made, some items have quantity while others don't



}
