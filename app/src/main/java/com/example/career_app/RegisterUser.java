package com.example.career_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterUser";
    private TextView registerUser;

    private EditText editTextName,
            editTextEmail,
            editTextPassword,
            editTextVerifyPassword,
            //editTextCareer,
            editTextExtras,
            editTextPhone;

    //long string
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterCareers;

    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterCareers = new ArrayAdapter<String>(this, R.layout.list_career,careers);
        autoCompleteTxt.setAdapter(adapterCareers);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String career = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Career "+ career, Toast.LENGTH_LONG).show();
                return;
            }
        });


        mAuth = FirebaseAuth.getInstance();


        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextVerifyPassword = (EditText) findViewById(R.id.editTextVerifyPassword);
        //editTextCareer = (EditText) findViewById(R.id.editTextCareer);
        editTextExtras = (EditText) findViewById(R.id.editTextExtras);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);

        progressBar = (ProgressBar) findViewById(R.id.progressBarReg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerUser:
               if( registerUser()){
                   //test to go to the main activity successful
                   setContentView(R.layout.activity_main);
                   break;
               }


        }

    }

    private Boolean registerUser() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String verify_password = editTextVerifyPassword.getText().toString().trim();
        //dropdown
        String career = autoCompleteTxt.getText().toString();
        String extras = editTextExtras.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        //validating if it is empty
        if(name.isEmpty()){
            editTextName.setError("Full Name is Required");
            editTextName.requestFocus();
            return false;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Provide a Valid Email");
            editTextEmail.requestFocus();
            return false;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return false;
        }
        //firebase only accepts password with at least 6 characters
        if(password.length()<6){
            editTextPassword.setError("Min password length should be 6 characters");
            editTextPassword.requestFocus();
            return false;
        }
        if(verify_password.isEmpty()){
            editTextVerifyPassword.setError("Please verify your password");
            editTextVerifyPassword.requestFocus();
            return false;
        }
        if(!password.equals(verify_password)){
            editTextVerifyPassword.setError("Password does not match");
            editTextVerifyPassword.requestFocus();
            return false;
        }
        if(career.isEmpty()){
            //editTextCareer.setError("Please provide a Career");
            //editTextCareer.requestFocus();
            Log.i(TAG, "Please select a Career!");
            return false;
        }
        if(phone.isEmpty()){
            editTextPhone.setError("Phone Number is required");
            editTextPhone.requestFocus();
            return false;
        }
        if(!Patterns.PHONE.matcher(phone).matches()){
            editTextPhone.setError("Please provide a valid Phone Number");
            editTextPhone.requestFocus();
            return false;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(name, email, phone, extras, career, password, password);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {



                            @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterUser.this,"User Registered Successfully",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);

                                Log.i(TAG, "User created with email: " + email + ", and name: " + name  + ".");
                            }else{
                                Toast.makeText(RegisterUser.this,"Failed to register, try again",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        });

        return true;
    }

    private String [] careers = {"Academic librarian",
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
