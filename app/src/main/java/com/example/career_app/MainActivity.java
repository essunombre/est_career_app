package com.example.career_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static User userdata;
    public static String idUsuario;
    private TextView register;
    private TextView forgotPassword;


    private static final String TAG = "MainActivity";
    private EditText editTextEmail, editTextPassword;

    private FirebaseAuth myAuth;

    private Button signIn;

    //initializing edittext


    //Whatever Sign in button does, goes inside this method.
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.signIn) void signIn(View view){
        userLogin();
//        Toast.makeText(this, "log in!", Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this, Home.class);
//        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        //signIn = (Button) findViewById(R.id.signIn);
        //signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        forgotPassword = (TextView) findViewById((R.id.forgotPassword));
        forgotPassword.setOnClickListener(this);

        myAuth = FirebaseAuth.getInstance();

        //



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this,RegisterUser.class));
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this,ForgotPassword.class));
                break;

            //case R.id.signIn:
                //userLogin();
                //break;

        }

    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Provide a Valid Email");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = task.getResult().getUser();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/" + user.getUid());
                    MainActivity.idUsuario = user.getUid();

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Get user value
                            User user = dataSnapshot.getValue(User.class);
                            MainActivity.userdata = user;
                        }

                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });




                    Toast.makeText(MainActivity.this,"Welcome!",Toast.LENGTH_LONG).show();
                    Log.i(TAG, "User with email: " + email + " logged successfully.");

                    startActivity(new Intent(MainActivity.this, Home.class));


                }else{
                    Toast.makeText(MainActivity.this,"Failed to login! Please verify your credentials!",Toast.LENGTH_LONG).show();
                    Log.i(TAG, "User with email: " + email + " failed to login!.");
                }
            }
        });
        }


    public static String [] careers = {"Academic librarian",
            "Accountant",
            "Accounting technician",
            "Actuary",
            "Adult nurse",
            "Advertising account executive",
            "Advertising account planner",
            "Advertising copywriter",
            "Advice worker",
            "Advocate (Scotland)",
            "Aeronautical engineer",
            "Agricultural consultant",
            "Agricultural manager",
            "Aid worker/humanitarian worker",
            "Air traffic controller",
            "Airline cabin crew",
            "Amenity horticulturist",
            "Analytical chemist",
            "Animal nutritionist",
            "Animator",
            "Archaeologist",
            "Architect",
            "Architectural technologist",
            "Archivist",
            "Armed forces officer",
            "Aromatherapist",
            "Art therapist",
            "Arts administrator",
            "Auditor",
            "Automotive engineer",
            "Barrister",
            "Barrister’s clerk",
            "Bilingual secretary",
            "Biomedical engineer",
            "Biomedical scientist",
            "Biotechnologist",
            "Brand manager",
            "Broadcasting presenter",
            "Building control officer/surveyor",
            "Building services engineer",
            "Building surveyor",
            "Camera operator",
            "Careers adviser (higher education)",
            "Careers adviser",
            "Careers consultant",
            "Cartographer",
            "Catering manager",
            "Charities administrator",
            "Charities fundraiser",
            "Chemical (process) engineer",
            "Child psychotherapist",
            "Children's nurse",
            "Chiropractor",
            "Civil engineer",
            "Civil Service administrator",
            "Clinical biochemist",
            "Clinical cytogeneticist",
            "Clinical microbiologist",
            "Clinical molecular geneticist",
            "Clinical research associate",
            "Clinical scientist - tissue typing",
            "Clothing and textile technologist",
            "Colour technologist",
            "Commercial horticulturist",
            "Commercial/residential/rural surveyor",
            "Commissioning editor",
            "Commissioning engineer",
            "Commodity broker",
            "Communications engineer",
            "Community arts worker",
            "Community education officer",
            "Community worker",
            "Company secretary",
            "Computer sales support",
            "Computer scientist",
            "Conference organiser",
            "Consultant",
            "Consumer rights adviser",
            "Control and instrumentation engineer",
            "Corporate banker",
            "Corporate treasurer",
            "Counsellor",
            "Courier/tour guide",
            "Court reporter/verbatim reporter",
            "Credit analyst",
            "Crown Prosecution Service lawyer",
            "Crystallographer",
            "Curator",
            "Customs officer",
            "Cyber security specialist",
            "Dance movement therapist",
            "Data analyst",
            "Data scientist",
            "Data visualisation analyst",
            "Database administrator",
            "Debt/finance adviser",
            "Dental hygienist",
            "Dentist",
            "Design engineer",
            "Developer",
            "Dietitian",
            "Diplomatic service",
            "Doctor (general practitioner, GP)",
            "Doctor (hospital)",
            "Dramatherapist",
            "Economist",
            "Editorial assistant",
            "Education administrator",
            "Electrical engineer",
            "Electronics engineer",
            "Employment advice worker",
            "Energy conservation officer",
            "Engineering geologist",
            "Environmental education officer",
            "Environmental health officer",
            "Environmental manager",
            "Environmental scientist",
            "Equal opportunities officer",
            "Equality and diversity officer",
            "Ergonomist",
            "Estate agent",
            "European Commission administrators",
            "Exhibition display designer",
            "Exhibition organiser",
            "Exploration geologist",
            "Facilities manager",
            "Field trials officer",
            "Financial manager",
            "Firefighter",
            "Fisheries officer",
            "Fitness centre manager",
            "Food scientist",
            "Food technologist",
            "Forensic scientist",
            "Geneticist",
            "Geographical information systems manager",
            "Geomatics/land surveyor",
            "Government lawyer",
            "Government research officer",
            "Graphic designer",
            "Health and safety adviser",
            "Health and safety inspector",
            "Health promotion specialist",
            "Health service manager",
            "Health visitor",
            "Herbalist",
            "Heritage manager",
            "Higher education administrator",
            "Higher education advice worker",
            "Homeless worker",
            "Horticultural consultant",
            "Hotel manager",
            "Housing adviser",
            "Human resources officer",
            "Hydrologist",
            "Illustrator",
            "Immigration officer",
            "Immunologist",
            "Industrial/product designer",
            "Information scientist",
            "Information systems manager",
            "Information technology/software trainers",
            "Insurance broker",
            "Insurance claims inspector",
            "Insurance risk surveyor",
            "Insurance underwriter",
            "Interpreter",
            "Investment analyst",
            "Investment banker - corporate finance",
            "Investment banker – operations",
            "Investment fund manager",
            "IT consultant",
            "IT support analyst",
            "Journalist",
            "Laboratory technician",
            "Land-based engineer",
            "Landscape architect",
            "Learning disability nurse",
            "Learning mentor",
            "Lecturer (adult education)",
            "Lecturer (further education)",
            "Lecturer (higher education)",
            "Legal executive",
            "Leisure centre manager",
            "Licensed conveyancer",
            "Local government administrator",
            "Local government lawyer",
            "Logistics/distribution manager",
            "Magazine features editor",
            "Magazine journalist",
            "Maintenance engineer",
            "Management accountant",
            "Manufacturing engineer",
            "Manufacturing machine operator",
            "Manufacturing toolmaker",
            "Marine scientist",
            "Market research analyst",
            "Market research executive",
            "Marketing account manager",
            "Marketing assistant",
            "Marketing executive",
            "Marketing manager (social media)",
            "Materials engineer",
            "Materials specialist",
            "Mechanical engineer",
            "Media analyst",
            "Media buyer",
            "Media planner",
            "Medical physicist",
            "Medical representative",
            "Mental health nurse",
            "Metallurgist",
            "Meteorologist",
            "Microbiologist",
            "Midwife",
            "Mining engineer",
            "Mobile developer",
            "Multimedia programmer",
            "Multimedia specialists",
            "Museum education officer",
            "Museum/gallery exhibition officer",
            "Music therapist",
            "Nanoscientist",
            "Nature conservation officer",
            "Naval architect",
            "Network administrator",
            "Nurse",
            "Nutritional therapist",
            "Nutritionist",
            "Occupational therapist",
            "Oceanographer",
            "Office manager",
            "Operational researcher",
            "Orthoptist",
            "Outdoor pursuits manager",
            "Packaging technologist",
            "Paediatric nurse",
            "Paramedic",
            "Patent attorney",
            "Patent examiner",
            "Pension scheme manager",
            "Personal assistant",
            "Petroleum engineer",
            "Pharmacist",
            "Pharmacologist",
            "Pharmacovigilance officer",
            "Photographer",
            "Physiotherapist",
            "Picture researcher",
            "Planning and development surveyor",
            "Planning technician",
            "Plant breeder",
            "Police officer",
            "Political party agent",
            "Political party research officer",
            "Practice nurse",
            "Press photographer",
            "Press sub-editor",
            "Prison officer",
            "Private music teacher",
            "Probation officer",
            "Product development scientist",
            "Production manager",
            "Programme researcher",
            "Project manager",
            "Psychologist (clinical)",
            "Psychologist (educational)",
            "Psychotherapist",
            "Public affairs consultant (lobbyist)",
            "Public affairs consultant (research)",
            "Public house manager",
            "Public librarian",
            "Public relations (PR) officer",
            "QA analyst",
            "Quality assurance manager",
            "Quantity surveyor",
            "Records manager",
            "Recruitment consultant",
            "Recycling officer",
            "Regulatory affairs officer",
            "Research chemist",
            "Research scientist",
            "Restaurant manager",
            "Retail banker",
            "Retail buyer",
            "Retail manager",
            "Retail merchandiser",
            "Retail pharmacist",
            "Sales executive",
            "Sales manager",
            "Scene of crime officer",
            "Secretary",
            "Seismic interpreter",
            "Site engineer",
            "Site manager",
            "Social researcher",
            "Social worker",
            "Software developer",
            "Soil scientist",
            "Solicitor",
            "Speech and language therapist",
            "Sports coach",
            "Sports development officer",
            "Sports therapist",
            "Statistician",
            "Stockbroker",
            "Structural engineer",
            "Systems analyst",
            "Systems developer",
            "Tax inspector",
            "Teacher (nursery/early years)",
            "Teacher (primary)",
            "Teacher (secondary)",
            "Teacher (special educational needs)",
            "Teaching/classroom assistant",
            "Technical author",
            "Technical sales engineer",
            "TEFL/TESL teacher",
            "Television production assistant",
            "Test automation developer",
            "Tour operator",
            "Tourism officer",
            "Tourist information manager",
            "Town and country planner",
            "Toxicologist",
            "Trade union research officer",
            "Trader",
            "Trading standards officer",
            "Training and development officer",
            "Translator",
            "Transportation planner",
            "Travel agent",
            "TV/film/theatre set designer",
            "UX designer",
            "Validation engineer",
            "Veterinary nurse",
            "Veterinary surgeon",
            "Video game designer",
            "Video game developer",
            "Volunteer work organiser",
            "Warehouse manager",
            "Waste disposal officer",
            "Water conservation officer",
            "Water engineer",
            "Web designer",
            "Web developer",
            "Welfare rights adviser",
            "Writer",
            "Youth worker"};
    }