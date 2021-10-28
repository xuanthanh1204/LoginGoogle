package com.example.androidlogin;

public class LoginActivity extends AppCompatActivity{
    private GoogleSignInClient mGoogleSignInClient;
    private TextView tvTitle;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        findViewById(R.id.btnLogout).setOnClickListener(v -> signOut());
        tvTitle = findViewById(R.id.tvTitle);
        imageView = findViewById(R.id.imageView);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            imageView.setImageURI(null);
            imageView.setImageURI(personPhoto);
            tvTitle.setText(personName+"\n"+personGivenName+"\n"+personFamilyName+"\n"
                    +personEmail+"\n"+personId);
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        logIn();
                    }
                });
    }

    private void logIn() {
        startActivity(new Intent(this,MainActivity.class));
    }
}
