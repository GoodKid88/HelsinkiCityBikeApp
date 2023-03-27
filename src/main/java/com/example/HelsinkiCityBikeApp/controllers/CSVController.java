package com.example.HelsinkiCityBikeApp.controllers;


import com.example.HelsinkiCityBikeApp.services.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/index")
public class CSVController {

    private final CSVService csvService;


    @Autowired
    public CSVController(CSVService csvService) {
        this.csvService = csvService;
    }


    @GetMapping()
    public String homePage() {
        return "/index";
    }

    @GetMapping("/upload")
    public String uploadJourneys() {
        csvService.upload();
       //  csvService.uploadJourneys();
        // csvService.uploadStations();
        return "redirect:/index";
    }


//    @PostMapping("/upload")
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
//
//    @PostMapping("/uploadStations")
//    public String uploadStations() {
//        CVS
//        return "";
//        InputStream inputStream = null;
//        try {
//            URL url = new URL("https://opendata.arcgis.com/datasets/726277c507ef4914b0aec3cbcfcbfafc_0.csv");
//            URLConnection urlConnection = url.openConnection();
//            inputStream = urlConnection.getInputStream();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//             CSVParser csvParser = new CSVParser(fileReader,
//                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
//
//            List<Station> stationList = new ArrayList<>();
//            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//            csvRecords.forEach(record -> {
//                try {
//                    Station station = new Station();
//                    station.setFid(Integer.parseInt(record.get("\uFEFFFID")));
//                    station.setStationId(record.get("ID"));
//                    station.setStationNameFI(record.get("Nimi"));
//                    station.setStationNameSW(record.get("Nimi"));
//                    station.setStationNameEn(record.get("Name"));
//                    station.setAddressFI(record.get("Osoite"));
//                    station.setAddressSW(record.get("Adress"));
//                    station.setCityFI(record.get("Kaupunki"));
//                    station.setCitySW(record.get("Stad"));
//                    station.setOperator(record.get("Operaattor"));
//                    station.setCapacity(Integer.parseInt(record.get("Kapasiteet")));
//                    station.setX(Double.parseDouble(record.get("x")));
//                    station.setY(Double.parseDouble(record.get("y")));
//                    stationList.add(station);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//            stationRepository.saveAll(stationList);
//            return "Stations upload Successful!";
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//    @PostMapping("/upload")
//    public String uploadJourneys(@RequestParam("file") MultipartFile file) throws Exception {
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
//

