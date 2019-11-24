package com.example.assignment2;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {
    View vReport;
    PieChart pieChart;
    DatePicker datePicker;
    SimpleDateFormat sdf;
    MainActivity mainActivity;
    int userId;
    int totalCalorieConsumed;
    int totalCalorieBurnedRest;
    int totlaCalorieBurnedStep;
    int calorieConsumedPeriod;
    int calorieBurnedPeriod;
    int calorieGoal;
    int remainingCalorie;

    EditText startDate;
    EditText endDate;
    Button buttonPieChart;
    Button buttonBarChart;
    String pickedDate;
    BarChart barChart;

    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vReport =  inflater.inflate(R.layout.fragment_report, container, false);
        mainActivity = (MainActivity) getActivity();
        userId = mainActivity.getUserId();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        startDate = vReport.findViewById(R.id.startDate_editText);
        endDate = vReport.findViewById(R.id.endDate_editText);
        buttonPieChart = vReport.findViewById(R.id.btnPieChart);
        buttonBarChart = vReport.findViewById(R.id.btnBarChart);

        buttonPieChart.setOnClickListener(new View.OnClickListener() {
            //including onClick() method
            public void onClick(View v) {
                datePicker = (DatePicker) vReport.findViewById(R.id.datePicker);
                int  day  = datePicker.getDayOfMonth();
                int  month = datePicker.getMonth();
                int  year = datePicker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                pickedDate = sdf.format(calendar.getTime());
                GetAsyncTask getAsyncTask = new GetAsyncTask();
                getAsyncTask.execute(String.valueOf(userId),pickedDate);


            }
        });
        buttonBarChart.setOnClickListener(new View.OnClickListener() {
            //including onClick() method
            public void onClick(View v) {
                String startDay = startDate.getText().toString();
                String endDay = endDate.getText().toString();
                BarChartAsyncTask barChartAsyncTask = new BarChartAsyncTask();
                barChartAsyncTask.execute(startDay,endDay);
                }
        });


        return vReport;

    }
    public void setPieChart() {
        pieChart = vReport.findViewById(R.id.piechart_1);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        pieChart.setTransparentCircleRadius(2f);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(0.0f);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(totalCalorieConsumed,"total calories consumed"));
        yValues.add(new PieEntry(totalCalorieBurnedRest,"total calories burned rest"));
        yValues.add(new PieEntry(totlaCalorieBurnedStep,"total calories burned step token"));
        yValues.add(new PieEntry(remainingCalorie,"remaining calorie"));
        PieDataSet dataSet = new PieDataSet(yValues, "Desease PerRegions");
                dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData((dataSet));
        pieData.setValueTextSize(8f);
        pieData.setValueTextColor(Color.YELLOW);
        pieChart.setData(pieData);
        //PieChart Ends Here
    }

    public void setBarChart(){
        barChart = (BarChart) vReport.findViewById(R.id.barChart);
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();

        BarEntry e1 = new BarEntry(0, calorieConsumedPeriod);
        valueSet1.add(e1);
        BarEntry e2 = new BarEntry(1, calorieBurnedPeriod);
        valueSet2.add(e2);
//        BarEntry v1e4 = new BarEntry(3, 0f);
//        valueSet1.add(v1e4);
//        BarEntry v1e5 = new BarEntry(4, 0f);
//        valueSet1.add(v1e5);
//        BarEntry v1e6 = new BarEntry(5, 0f);
//        valueSet1.add(v1e6);

        BarDataSet set1;
        BarDataSet set2;
        set1 = new BarDataSet(valueSet1, "The year 2017");

        set1.setColors(Color.parseColor("#F78B5D"));

        set2 = new BarDataSet(valueSet2, "The year 2018");

        set2.setColors( Color.parseColor("#FCB232"));


        dataSets.add(set1);
        dataSets.add(set2);

        BarData data = new BarData(dataSets);

        // hide Y-axis
        YAxis left = barChart.getAxisLeft();
        left.setDrawLabels(false);

        //set legend
        Legend legend = barChart.getLegend();
        List<LegendEntry> entries = new ArrayList<>();

            LegendEntry entry = new LegendEntry();
            entry.formColor = Color.parseColor("#F78B5D");
            entry.label = "Calorie consumed";
            entries.add(entry);

        LegendEntry entry2 = new LegendEntry();
        entry2.formColor = Color.parseColor("#FCB232");
        entry2.label = "Calorie burned";
        entries.add(entry2);
        legend.setCustom(entries);




//        String[] labels = {"Name1","name2","","","","",""};
//        IAxisValueFormatter xAxisFormatter = new LabelFormatter(barChart, labels);
//
//        XAxis xAxis = barChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setValueFormatter(xAxisFormatter);

        barChart.setData(data);
        //  description
        barChart.getDescription().setText("Calorie Tracker");


        barChart.animateY(1000);
        barChart.invalidate();

    }

    private class GetAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String report = RestClient.findByUserIdANDReportDate(params[0],params[1]);
            if(!report.equals("[]")){
            totalCalorieConsumed = Integer.valueOf(RestClient.getTotalCalorieConsumed(report));
            totalCalorieBurnedRest = Double.valueOf(RestClient.getCalorieBurnedRest(params[0])).intValue();
            calorieGoal = Integer.valueOf(RestClient.getCalorieGoal(report));
            int step = Integer.valueOf(RestClient.getTotalStepsTaken(report));
            Double calorieBurnedPerStep = Double.valueOf(RestClient.getCaloriesBurnedPerStep(params[0]));
            Double result = step * calorieBurnedPerStep;
            totlaCalorieBurnedStep = result.intValue();
            remainingCalorie = calorieGoal+ totalCalorieBurnedRest + totlaCalorieBurnedStep - totalCalorieConsumed;
            setPieChart();
            return "0";
            }
            else{
                return "";
            }

        }
        @Override
        protected void onPostExecute(String response) {
            if(response.isEmpty())
            Toast.makeText(getActivity().getApplicationContext(), "No record,select another date",Toast.LENGTH_LONG).show();
        }
    }

    private class BarChartAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = RestClient.addUpCalorieAndStep(String.valueOf(userId),params[0],params[1]);

            try{
                JSONArray jsonArray = new JSONArray(result);
                calorieConsumedPeriod = Integer.valueOf(jsonArray.getJSONObject(0).getString("totalCaloriesConsumed"));
                calorieBurnedPeriod = Integer.valueOf(jsonArray.getJSONObject(0).getString("totalCaloriesBurned"));
            }catch (Exception e){
                e.printStackTrace();
                calorieConsumedPeriod = 0;
                calorieBurnedPeriod = 0;
            }

            return "";
        }
        @Override
        protected void onPostExecute(String response) {
            setBarChart();
        }
    }

}
