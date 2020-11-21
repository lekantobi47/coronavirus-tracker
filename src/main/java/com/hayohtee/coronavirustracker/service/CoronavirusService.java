package com.hayohtee.coronavirustracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.hayohtee.coronavirustracker.model.CoronavirusData;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CoronavirusService {
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<CoronavirusData> data = new ArrayList<>();     // hold the fetched data to make sure user doesn't have to wait when it's updating

    @PostConstruct                          // run this method when spring starts
    @Scheduled(cron = "* * 1 * * *")        // run every 1 hour
    public void fetchData() throws IOException, InterruptedException {
        /*
            make an http request and fetching the data as string
        */

        List<CoronavirusData> workingData = new ArrayList<>();   


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =  HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(response.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            CoronavirusData data = new CoronavirusData();
            int currentCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));

            data.setCountry(record.get("Country/Region"));
            data.setState(record.get("Province/State"));
            data.setCurrentCases(currentCases);
            data.setPrevDayCases(prevDayCases);
            workingData.add(data);
        }
        this.data = workingData; 
    }

    public List<CoronavirusData> getData() {
        return data;
    }

}
