package com.iti.mishwary.ui.History;

import androidx.annotation.NonNull;

import com.iti.mishwary.Models.Trip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryPresenter implements HistoryContract.HistoryPresenter {
    private HistoryContract.HistoryView historyFragment;
    private DatabaseReference firebaseReference;
    private String id;

    public HistoryPresenter(HistoryContract.HistoryView historyFragment, String id) {
        this.historyFragment = historyFragment;
        this.id = id;
    }

    @Override
    public void getHistoryTrips() {
        final List<Trip> upcomingTrips = new ArrayList<>();
        firebaseReference = FirebaseDatabase.getInstance().getReference("history_trip").child(id);
        firebaseReference.keepSynced(true);
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                upcomingTrips.clear();
                for (DataSnapshot trip : dataSnapshot.getChildren()) {
                    Trip upcoming = trip.getValue(Trip.class);
                    upcomingTrips.add(upcoming);
                }
                if (historyFragment!=null){
                    if (upcomingTrips.size() > 0) {
                        historyFragment.displayTrips(upcomingTrips);
                    } else {
                        historyFragment.displayNoTrips();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void stop() {
        historyFragment = null;
    }


}
