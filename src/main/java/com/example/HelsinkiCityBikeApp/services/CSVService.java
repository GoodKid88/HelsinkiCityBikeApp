package com.example.HelsinkiCityBikeApp.services;

import com.example.HelsinkiCityBikeApp.model.Journey;
import com.example.HelsinkiCityBikeApp.model.Station;
import com.example.HelsinkiCityBikeApp.repositories.JourneyRepository;
import com.example.HelsinkiCityBikeApp.repositories.StationRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService {
    private InputStream inputStream = null;
    private final JourneyRepository journeyRepository;

    private final StationRepository stationRepository;

    @Autowired
    public CSVService(JourneyRepository journeyRepository, StationRepository stationRepository) {
        this.journeyRepository = journeyRepository;
        this.stationRepository = stationRepository;
    }

    public String uploadJourneys() {
        inputStream = openInputStream("https://dev.hsl.fi/citybikes/od-trips-2021/2021-05.csv");

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Journey> journeyList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            csvRecords.forEach(record -> {
                if (record.get("Covered distance (m)") == null) {
                    System.out.println("Distance is empty " + record);
                } else if (Double.parseDouble(record.get("Covered distance (m)")) % 1.0 > 0) {
                    System.out.println("Distance is less than 10 m " + record);
                } else if (Integer.parseInt(record.get("Covered distance (m)")) < 10 ||
                        Integer.parseInt(record.get("Duration (sec.)")) < 10) {
                    System.out.println("Covered distance is less than 10 m or Duration is shorter than 10 sec");
                } else {
                    try {
                        Journey journey = new Journey();
                        journey.setDeparture(LocalDateTime.parse(record.get("\uFEFFDeparture")));
                        journey.setReturnDate(LocalDateTime.parse(record.get("Return")));
                        journey.setDepartureStationId(Integer.parseInt(record.get("Departure station id")));
                        journey.setDepartureStation(record.get("Departure station name"));
                        journey.setReturnStationId(Integer.parseInt(record.get("Return station id")));
                        journey.setReturnStation(record.get("Return station name"));
                        journey.setDistance(Double.parseDouble(record.get("Covered distance (m)")));
                        journey.setDuration(Double.parseDouble(record.get("Duration (sec.)")));
                        journeyList.add(journey);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            journeyRepository.saveAll(journeyList);
            inputStream.close();
            return "Journeys upload Successful!";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream openInputStream(String stringUrl) {
        try {
            URL url = new URL(stringUrl);
            URLConnection urlConnection = url.openConnection();
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputStream;
    }

    public String uploadStations() {
        inputStream = openInputStream("https://opendata.arcgis.com/datasets/726277c507ef4914b0aec3cbcfcbfafc_0.csv");


        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Station> stationList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            csvRecords.forEach(record -> {
                try {
                    Station station = new Station();
                    station.setFid(Integer.parseInt(record.get("\uFEFFFID")));
                    station.setStationId(Integer.parseInt(record.get("ID")));
                    station.setStationNameFI(record.get("Nimi"));
                    station.setStationNameSW(record.get("Nimi"));
                    station.setStationNameEn(record.get("Name"));
                    station.setAddressFI(record.get("Osoite"));
                    station.setAddressSW(record.get("Adress"));
                    station.setCityFI(record.get("Kaupunki"));
                    station.setCitySW(record.get("Stad"));
                    station.setOperator(record.get("Operaattor"));
                    station.setCapacity(Integer.parseInt(record.get("Kapasiteet")));
                    station.setX(Double.parseDouble(record.get("x")));
                    station.setY(Double.parseDouble(record.get("y")));
//                    station.setStartingFromStation(journeyRepository.countAllByDepartureStation(station.getStationNameFI()));
//                    station.setEndingFromStation(journeyRepository.countAllByReturnStation(station.getStationNameFI()));
//                    station.setAvgDistanceFromStation(journeyRepository.countAllDistanceFromStation(station.getStationNameFI()));
//                    station.setAvgDistanceToStation(journeyRepository.countAllDistanceToStation(station.getStationNameFI()));
                    stationList.add(station);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            stationRepository.saveAll(stationList);
            inputStream.close();
            return "Stations upload Successful!";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public String uploadJourneysFromCVS(@RequestParam("file") MultipartFile file) throws Exception {
//        List<Journey> journeyList = new ArrayList<>();
//        InputStream inputStream = file.getInputStream();
//        CsvParserSettings settings = new CsvParserSettings();
//        settings.setHeaderExtractionEnabled(true);
//        CsvParser parser = new CsvParser(settings);
//        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
//        parseAllRecords.forEach(record -> {
//            if (record.getString("Covered distance (m)") == null) {
//                System.out.println("Distance is empty " + record);
//            } else if (Double.parseDouble(record.getString("Covered distance (m)")) % 1.0 > 0) {
//                System.out.println("Distance is less than 10 m " + record);
//            } else if (Integer.parseInt(record.getString("Covered distance (m)")) < 10 ||
//                    Integer.parseInt(record.getString("Duration (sec.)")) < 10) {
//                System.out.println("Covered distance is less than 10 m or Duration is shorter than 10 sec");
//            } else {
//                try {
//                    Journey journey = new Journey();
//                    journey.setDeparture(LocalDateTime.parse(record.getString("Departure")));
//                    journey.setReturnDate(LocalDateTime.parse(record.getString("Return")));
//                    journey.setDepartureStationId(Integer.parseInt(record.getString("Departure station id")));
//                    journey.setDepartureStation(record.getString("Departure station name"));
//                    journey.setReturnStationId(Integer.parseInt(record.getString("Return station id")));
//                    journey.setReturnStation(record.getString("Return station name"));
//                    journey.setDistance(Double.parseDouble(record.getString("Covered distance (m)")));
//                    journey.setDuration(Double.parseDouble(record.getString("Duration (sec.)")));
//                    journeyList.add(journey);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        journeyRepository.saveAll(journeyList);
//        return "Journeys upload Successful!";
//    }
}
