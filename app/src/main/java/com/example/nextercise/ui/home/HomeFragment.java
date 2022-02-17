package com.example.nextercise.ui.home;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

//    public static final String TAG = "HomeFragment";
//    private EditText etExercise;
//    private ImageView ivExerciseImage;
//    private EditText etExcDescription;
//    private Button btnSkip;
//    private Button btnView;
//
//
//    public HomeFragment() {
//        //Required empty public constructor
//    }
//
//    private HomeViewModel homeViewModel;
//    private FragmentHomeBinding binding;
//    // The onCreateView method is called when Fragment should create its view object hierarchy.
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//    // inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_recommend, container, false);
//    }
//
//    // This event is triggered soon after onCreateView().
//    //Any view setup should occur here
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        etExercise = view.findViewById(R.id.etExercise);
//        ivExerciseImage = view.findViewById(R.id.ivExerciseImage);
//        etExcDescription = view.findViewById(R.id.etExcDescription);
//        btnSkip = view.findViewById(R.id.btnSkip);
//        btnView = view.findViewById(R.id.btnView);
//
//        btnSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            goExerciseActivity();
//            }
//        });
//
//    }
//
//    private void goExerciseActivity() {
//        Intent i = new Intent(getContext(), ExerciseActivity.class);
//        startActivity(i);
//    }
//
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}